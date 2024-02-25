package com.mongodb.load.runner;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoSocketReadTimeoutException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.load.MongoDBLoadRunner;
import com.mongodb.load.connection.MongoClientConnection;
import com.mongodb.load.data.Measurement;
import com.mongodb.load.data.TraceData;


public class InsertStaticFullTraceData {
	
	//private MongoClientConnection = null;
	private MongoDatabase atlas = null;
	
	private static int sizeOfMeasure = 400;
	//private static int sizeOfMessage = 10;
	//100 : 10Seconds, 10: 1second
	
	private static TraceData trace = null;
	
	public static TraceData getStaticTraceData()
	{
		if (InsertStaticFullTraceData.trace == null)
			trace = new TraceData(InsertStaticFullTraceData.sizeOfMeasure);
		return InsertStaticFullTraceData.trace;
	}
	
	
	
	
	
	public InsertStaticFullTraceData()
	{
		//System.out.println ("InsertCustomer.InsertCustomer:"); 
		connectMongoClient();
	}
	
	private void connectMongoClient()
	{
		MongoClientConnection mongoConnector = MongoClientConnection.getInstance();
		try{

			if (atlas == null)
				atlas = mongoConnector.getMongoDatabase();
				
		}catch(Exception e)
	    {
	    	System.out.println("Making MongoDB Connection Exception:"+e.toString());
	    }
	}
	
	public void InsertLoadData() {
	    try{
	    	connectMongoClient();
	    	//System.out.println ("InsertCustomer.insertCustomerData:"); 
	        MongoCollection<Document> insertCollection = atlas.getCollection(MongoDBLoadRunner.getCollection());
	        
	        long start = System.currentTimeMillis();
	        //TraceData data = new TraceData(sizeOfMeasure, sizeOfMessage);
	        long start2 = System.currentTimeMillis();
	        insertOneDocument(insertCollection, InsertStaticFullTraceData.getStaticTraceData());
	        long end = System.currentTimeMillis();
	        long timeElapsed = end - start2;
    		//System.out.println ("Insertion Ends *****************"+timeElapsed); 
	        if (MongoDBLoadRunner.isPrintLog()) System.out.println("Success Insert Document :"+timeElapsed);
	    }catch(MongoSocketReadTimeoutException e)
	    {
	    	if (MongoDBLoadRunner.isPrintLog()) System.out.println("Timeout Insert Document ");
	    }
	    catch(Exception e)
	    {
	    	if (MongoDBLoadRunner.isPrintLog()) System.out.println("Fail Insert Document : ");
	    	System.out.println("During Insert - Exception: "+e.toString());
	    }
	    //closeMongoClient();
	}
	
	public void InsertManyData() {
	    try{
	    	connectMongoClient();
	    	System.out.println ("InsertCustomer.InsertManyData:"); 
	        MongoCollection<Document> insertCollection = atlas.getCollection(MongoDBLoadRunner.getCollection());
	        
	        List<Document> data = new ArrayList();
	        
	        
	        for(int i=0;i<100;i++)
	        	data.add(generateNewTransaction(InsertStaticFullTraceData.getStaticTraceData())); 
	       
	        long start2 = System.currentTimeMillis();
	        insertManyDocument(insertCollection, data);
	        long end = System.currentTimeMillis();
	        long timeElapsed = end - start2;
    		//System.out.println ("Insertion Ends *****************"+timeElapsed); 
	        if (MongoDBLoadRunner.isPrintLog()) System.out.println("Success Insert Document :"+timeElapsed);
	    }catch(MongoSocketReadTimeoutException e)
	    {
	    	if (MongoDBLoadRunner.isPrintLog()) System.out.println("Timeout Insert Document ");
	    }
	    catch(Exception e)
	    {
	    	if (MongoDBLoadRunner.isPrintLog()) System.out.println("Fail Insert Document : ");
	    	System.out.println("During Insert - Exception: "+e.toString());
	    }
	    //closeMongoClient();
	}
	
	private void insertOneDocument(MongoCollection<Document> insertCollection, TraceData data) throws Exception{
		
		Document transcationdata = generateNewTransaction(data);
		insertCollection.insertOne(transcationdata);
		
        //System.out.println("One grade inserted for studentId 10000.");
    }
	
	private void insertManyDocument(MongoCollection<Document> insertCollection, List<Document> data) throws Exception{
		
		InsertManyResult result = insertCollection.insertMany(data, new InsertManyOptions().ordered(false));
        //System.out.println("One grade inserted for studentId 10000.");
    }
	
	private Document generateNewTransaction(TraceData data)
	{
		
		Document tracedata = new Document();

		Document transcationdata = null;
		
		Document measurements = null;
		
		List<String> paramValue = null;
		List<String> paramName = null;
		List<String> paramAlias = null;
		
		
		transcationdata = new Document()
				.append("chamberRawId",data.getChamberRawId())
				.append("chamberTypeRawId",data.getChamberTypeRawId())
				.append("chamberTypeName",data.getChamberTypeName())
				.append("chamberAlias",data.getChamberAlias())
				.append("moduleName",data.getModuleName())
				.append("mesChamberName",data.getMesChamberName())
				.append("lot_id",data.getLot_id())
				
				.append("slot_no",data.getSlot_no())
				.append("lot_type",data.getLot_type());
		
		//transcationdata.append("timeStamp", new Date());
		
		measurements = new Document();
		paramValue = new ArrayList<String> ();
		paramName = new ArrayList<String> ();
		paramAlias = new ArrayList<String> ();
		
		for (int j=0;j<sizeOfMeasure;j++)
		{
			paramValue.add(data.getParamValueList()[j]);
			paramName.add(data.getParamNameList()[j]);
			paramAlias.add(data.getParamAliasList()[j]);
		}
		
		
		measurements.append("paramAlias", paramAlias);
		measurements.append("paramName", paramName);
		measurements.append("paramValue", paramValue);
		
		transcationdata.append("measurements", measurements);
		
		List<String> headerList = new ArrayList<String> ();
		headerList.add("TDS");
		headerList.add("M16ThinFilm01");
		headerList.add("M16ThinFilm01");
		
		tracedata.append("command", "TOOLDATA")
		.append("eqpId", data.genLowerString(8))
		.append("headerList", headerList)
		.append("recipId", "RECIPE1,RECIPE2,RECIPE3")
		.append("collectionInterval", 0.1)
		.append("collectionType", "SECs")
		.append("eventTime", new Date())
		.append("trid","101")
		.append("devideOrder", 1);
		
		List<String> chamberRawIdList = new ArrayList<String> ();
		List<String> chamberAliasList = new ArrayList<String> ();
		
		chamberRawIdList.add("131211");
		chamberRawIdList.add("131212");
		chamberRawIdList.add("131213");
		chamberRawIdList.add("131214");
		chamberRawIdList.add("131215");
		chamberRawIdList.add("131216");
		
		chamberAliasList.add("M16EQP123_CH1");
		chamberAliasList.add("M16EQP123_CH2");
		chamberAliasList.add("M16EQP123_CH3");
		chamberAliasList.add("M16EQP123_CH4");
		chamberAliasList.add("M16EQP123_CH5");
		chamberAliasList.add("M16EQP123_CH6");
		
		tracedata.append("chamberRawIdList", chamberRawIdList);
		tracedata.append("chamberAliasList", chamberAliasList);
		
		tracedata.append("131211", transcationdata)
		.append("131212", transcationdata)
		.append("131213", transcationdata)
		.append("131214", transcationdata)
		.append("131215", transcationdata)
		.append("131216", transcationdata);
		
		
		return tracedata;
		
	}

	
	private static String isNull(String input)
	{
		return (input.equals("") || input.isEmpty())? "" : input ;
	}


}