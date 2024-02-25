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


public class InsertStaticTraceData {
	
	//private MongoClientConnection = null;
	private MongoDatabase atlas = null;
	
	private static int sizeOfMeasure = 400;
	private static int sizeOfMessage = 10;
	//100 : 10Seconds, 10: 1second
	
	public static TraceData trace = new TraceData(InsertStaticTraceData.sizeOfMeasure, InsertStaticTraceData.sizeOfMessage);
	
	
	public InsertStaticTraceData()
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
	        
	        //long start = System.currentTimeMillis();
	        //TraceData data = new TraceData(sizeOfMeasure, sizeOfMessage);
	        long start2 = System.currentTimeMillis();
	        insertOneDocument(insertCollection, InsertStaticTraceData.trace);
	        long end = System.currentTimeMillis();
	        long timeElapsed = end - start2;
    		//System.out.println ("Insertion Ends *****************"+timeElapsed); 
	        if (MongoDBLoadRunner.isPrintLog()) System.out.println("Success Insert One Document :"+timeElapsed);
	    }catch(MongoSocketReadTimeoutException e)
	    {
	    	if (MongoDBLoadRunner.isPrintLog()) System.out.println("Timeout Insert One Document ");
	    }
	    catch(Exception e)
	    {
	    	if (MongoDBLoadRunner.isPrintLog()) System.out.println("Fail Insert One Document : ");
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
	        	data.add(generateNewTransaction(InsertStaticTraceData.trace)); 
	       
	        long start2 = System.currentTimeMillis();
	        insertManyDocument(insertCollection, data);
	        long end = System.currentTimeMillis();
	        long timeElapsed = end - start2;
    		//System.out.println ("Insertion Ends *****************"+timeElapsed); 
	        if (MongoDBLoadRunner.isPrintLog()) System.out.println("Success Insert Many Document :"+timeElapsed);
	    }catch(MongoSocketReadTimeoutException e)
	    {
	    	if (MongoDBLoadRunner.isPrintLog()) System.out.println("Timeout Insert Many Document ");
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

		Document transcationdata = null;
		Measurement measurementData = null;
		
		Document measurements = null;
		
		List<String> paramValues = null;
		
		List<Document> measure = new ArrayList<Document> ();
		
		List<Date> history = new ArrayList<Date> ();
		
		transcationdata = new Document()
				.append("eqpId", data.genLowerString(8))
				.append("chamberRawId",data.getChamberRawId())
				.append("chamberTypeRawId",data.getChamberTypeRawId())
				.append("chamberTypeName",data.getChamberTypeName())
				.append("chamberAlias",data.getChamberAlias())
				.append("moduleName",data.getModuleName())
				.append("mesChamberName",data.getMesChamberName())
				.append("lot_id",data.getLot_id())
				
				.append("slot_no",data.getSlot_no())
				.append("lot_type",data.getLot_type())
				.append("startTime",data.getStartTime())
		.append("endTime",data.getEndTime());
		
		//private int sizeOfMeasure = 400;
		//private int sizeOfMessage = 100;

		for (int i=0;i<sizeOfMeasure; i++)
		{
			measurementData = data.getMeasurements()[i];
			//System.out.println("Start measurement:::");
			//measurementData.printMeasure();
			measurements = new Document()
					.append("paramAlias", measurementData.getParamAlias())
					.append("paramName", measurementData.getParamName());
			paramValues = new ArrayList<String> ();
			for (int j=0;j<sizeOfMessage;j++)
			{
				paramValues.add(measurementData.getParamValues()[j]);
			}
			measurements.append("paramValues", paramValues);
			measure.add(measurements);
		}
		for (int i=0; i<sizeOfMessage;i++)
		{
			history.add(data.getHistory()[i]);
		}

		transcationdata.append("measurements", measure);
		transcationdata.append("history", history);
		
		return transcationdata;
		
	}

	
	private static String isNull(String input)
	{
		return (input.equals("") || input.isEmpty())? "" : input ;
	}


}