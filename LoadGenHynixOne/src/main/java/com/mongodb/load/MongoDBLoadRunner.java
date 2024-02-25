package com.mongodb.load;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


import com.mongodb.load.runner.InsertThreadRunner;
import com.mongodb.load.runner.InsertTraceData;
import com.mongodb.load.runner.InsertStaticFullTraceRunner;
import com.mongodb.load.runner.InsertStaticThreadRunner;


public class MongoDBLoadRunner {
	
	private static String mongodbConnection = "";
	private static boolean printLog = false;
	private static String collection = "";
	private static String database = "";
	private static int countPerPage=0;
	private static String OneMany="O";
	private static String fullPartial="F";
	
	public static String getOneMany() {
		return OneMany;
	}

	public static void setOneMany(String oneMany) {
		OneMany = oneMany;
	}

	public static String getFullPartial() {
		return fullPartial;
	}

	public static void setFullPartial(String fullPartial) {
		MongoDBLoadRunner.fullPartial = fullPartial;
	}

	private static String cidkey="";
	
	public static String getDatabase() {
		return database;
	}

	public static void setDatabase(String database) {
		MongoDBLoadRunner.database = database;
	}

	private static String updatedata = "";
			
	
	public static String getUpdatedata() {
		return updatedata;
	}

	public static void setUpdatedata(String updatedata) {
		MongoDBLoadRunner.updatedata = updatedata;
	}

	public static String getCollection() {
		return collection;
	}

	public static void setCollection(String collection) {
		MongoDBLoadRunner.collection = collection;
	}

	public static String getMongodbConnection() {
		return mongodbConnection;
	}

	public static boolean isPrintLog() {
		return printLog;
	}

	public static void setPrintLog(boolean printLog) {
		MongoDBLoadRunner.printLog = printLog;
	}

	public static void setMongodbConnection(String mongodbConnection) {
		MongoDBLoadRunner.mongodbConnection = mongodbConnection;
	}

	public MongoDBLoadRunner(String mongodbEndpoint, String mongodbUser, String mongodbPass, String mongodriver)
	{
		super();
		MongoDBLoadRunner.setMongodbConnection (mongodriver + mongodbUser+ ":"+mongodbPass + "@"+mongodbEndpoint);	
	}
	
	public void commandRunning(String dbcommand, String prefix, int startX, int endX, int sleepmi, int findloop, int threadcount)
	{

		InsertTraceData insertData = null;

		if(dbcommand.equals("I"))
		{
			System.out.println ("Insert starts *****************"); 
			//inser = new InsertOrderBookData();
			try {
				insertData = new InsertTraceData();
				for (int startIdx = startX; startIdx <= endX; startIdx++)
				{
	    			insertData.InsertLoadData();
	    			//insertNaverZ.Insert0001Transaction(startIdx);
	    			//insertbucket.InsertBucketTransaction(tmapuserkey);
					Thread.sleep(sleepmi);

					System.out.println("execute 1:"+startIdx);
				}
	    		System.out.println ("End  *****************");
	    		/*
				for (int i=0;i<threadcount;i++)
				{
					InsertThreadRunner insertrunner = new InsertThreadRunner(i, startX, endX, sleepmi);
					insertrunner.start();
					
					System.out.println("execute 1:"+i);
				}
	    		System.out.println ("End  *****************");
	    		*/
    		}catch(Exception e)
			{
				System.out.println("During Insert Java error : Thread.sleep"+e.toString());
			}
		}
		else if(dbcommand.equals("TI"))
		{
			System.out.println ("Insert starts *****************"); 
			//inser = new InsertOrderBookData();
			
			if (fullPartial.equals("F")) {
				try {
					System.out.println ("Insert Full Document *****************"); 
					for (int i=0;i<threadcount;i++)
					{
						InsertStaticFullTraceRunner insertrunner = new InsertStaticFullTraceRunner(i, startX, endX, sleepmi);
						insertrunner.setOneMany(MongoDBLoadRunner.getOneMany());
						insertrunner.start();
						
						System.out.println("execute 1:"+i);
					}
		    		System.out.println ("End  *****************");
		    		
	    		}catch(Exception e)
				{
					System.out.println("During Insert Java Thread error : Thread.sleep"+e.toString());
				}
				
			}
			else
			{
				try {
					System.out.println ("Insert Partial Document *****************"); 
					for (int i=0;i<threadcount;i++)
					{
						InsertStaticThreadRunner insertrunner = new InsertStaticThreadRunner(i, startX, endX, sleepmi);
						insertrunner.setOneMany(MongoDBLoadRunner.getOneMany());
						insertrunner.start();
						
						System.out.println("execute 1:"+i);
					}
		    		System.out.println ("End  *****************");
		    		
	    		}catch(Exception e)
				{
					System.out.println("During Insert Java Thread error : Thread.sleep"+e.toString());
				}
				
			}
			
			
		}
		// Thread One Insert
		else if(dbcommand.equals("TOI"))
		{
			System.out.println ("Insert starts *****************"); 
			//inser = new InsertOrderBookData();
			try {
				
	    		
				for (int i=0;i<threadcount;i++)
				{
					InsertStaticThreadRunner insertrunner = new InsertStaticThreadRunner(i, startX, endX, sleepmi);
					insertrunner.start();
					
					System.out.println("execute 1:"+i);
				}
	    		System.out.println ("End  *****************");
	    		
    		}catch(Exception e)
			{
				System.out.println("During Insert Java Thread error : Thread.sleep"+e.toString());
			}
		}
		
		else if(dbcommand.equals("SI"))
		{
			System.out.println ("Insert starts *****************"); 
			//inser = new InsertOrderBookData();
			try {
				
	    		
				for (int i=0;i<threadcount;i++)
				{
					InsertStaticThreadRunner insertrunner = new InsertStaticThreadRunner(i, startX, endX, sleepmi);
					insertrunner.start();
					
					System.out.println("execute 1:"+i);
				}
	    		System.out.println ("End  *****************");
	    		
    		}catch(Exception e)
			{
				System.out.println("During Insert Java Thread error : Thread.sleep"+e.toString());
			}
		}
		
		else
		{
			System.out.println ("The operation command have to be one in the options [I, U, D, F]");
    	}
		
	}

	public static void main(String[] args) throws Exception {
    	String dbcommand = null;
    	String prefix = null;
    	int startX = 0;
    	int endX = 0;
    	
    	int findloop=0;
    	int threadcount=0;
    	
    	String mongodbEndpoint = null;
    	String mongodbUser = null;     	
    	String mongodbPass = null;
    	String mongodbdriver  = null;
    	String collectionName = null;
    	String databaseName=null;
    	
    	int sleepMiSec=0;
    	
    	MongoDBLoadRunner load = null;
    			
    	try {
    		
    		InputStream inputStream = new FileInputStream(args[0]);
            Properties prop = new Properties();
            
         // load a properties file
        	prop.load(inputStream);
        	
        	// get value by key
        	dbcommand = prop.getProperty("mongodb.command");
        	startX = (new Integer(prop.getProperty("mongodb.start"))).intValue();
        	endX = (new Integer(prop.getProperty("mongodb.end"))).intValue();

        	mongodbEndpoint = prop.getProperty("mongodb.endpoint");
        	mongodbUser = prop.getProperty("mongodb.user");
        	mongodbPass = prop.getProperty("mongodb.pass");
        	mongodbdriver = prop.getProperty("mongodb.driver");
        	collectionName = prop.getProperty("mongodb.collection");
        	databaseName = prop.getProperty("mongodb.database");
        	updatedata = prop.getProperty("mongodb.updatedata");
        	cidkey = prop.getProperty("mongodb.cid");
        	OneMany = prop.getProperty("hynix.OneMany");
        	fullPartial = prop.getProperty("hynix.fullPartial");
        	
        	
        	sleepMiSec = (new Integer(prop.getProperty("sleepmi"))).intValue();
        	findloop = (new Integer(prop.getProperty("findloop"))).intValue();
        	threadcount = (new Integer(prop.getProperty("threadcount"))).intValue();
        	countPerPage = (new Integer(prop.getProperty("pagepercount"))).intValue();
        	
        	setPrintLog(new Boolean(prop.getProperty("mongodb.print")).booleanValue() );
        	setCollection(collectionName);
        	setDatabase(databaseName);
        	
        	setUpdatedata(updatedata);
        	
        	
        	
        	load = new MongoDBLoadRunner(mongodbEndpoint,mongodbUser, mongodbPass, mongodbdriver);
        	load.commandRunning(dbcommand, prefix, startX, endX, sleepMiSec, findloop,threadcount);
        } catch (NumberFormatException ex) {
        	System.out.println("Start and End must be number format");
        	System.out.println(ex.toString());
        }
    	catch (IOException io) {
            io.printStackTrace();
        }
    	
    	

    }

	public static int getCountPerPage() {
		return countPerPage;
	}

	public static void setCountPerPage(int countPerPage) {
		MongoDBLoadRunner.countPerPage = countPerPage;
	}

}