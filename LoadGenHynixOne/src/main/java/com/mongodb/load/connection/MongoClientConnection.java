package com.mongodb.load.connection;


import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.load.MongoDBLoadRunner;


public class MongoClientConnection {
	
	private MongoClient mongoClient = null;
	private MongoDatabase prudentialDB = null;
	
	private static MongoClientConnection instance = new MongoClientConnection();
	
	private MongoClientConnection() {}
	
	public static MongoClientConnection getInstance() {
		return instance;
	}
	
	public MongoClient getMongoClient() throws RuntimeException {
		if (mongoClient == null) {
			
			try {
				mongoClient = MongoClients.create(MongoDBLoadRunner.getMongodbConnection());
			} catch (MongoException ex) {
				System.out.println("An error occoured when connecting to MongoDB" + ex.toString());
			} catch (Exception ex) {
				System.out.println("An error occoured when connecting to MongoDB" + ex.toString());
			}
		}

		return mongoClient;
	}
	
	public MongoClient getMongoClient(String connection) throws RuntimeException {
		if (mongoClient == null) {
			
			try {
				mongoClient = MongoClients.create(connection);
			} catch (MongoException ex) {
				System.out.println("An error occoured when connecting to MongoDB" + ex.toString());
			} catch (Exception ex) {
				System.out.println("An error occoured when connecting to MongoDB" + ex.toString());
			}
		}

		return mongoClient;
	}
	
	public MongoDatabase getMongoDatabase(String connection) throws RuntimeException {
		getMongoClient(connection);
		if (prudentialDB == null) {
			
			try {
				prudentialDB = mongoClient.getDatabase(MongoDBLoadRunner.getDatabase());
			} catch (MongoException ex) {
				System.out.println("An error occoured when connecting to MongoDB" + ex.toString());
			} catch (Exception ex) {
				System.out.println("An error occoured when connecting to MongoDB" + ex.toString());
			}
		}

		return prudentialDB;
	}
	
	public MongoDatabase getMongoDatabase() throws RuntimeException {
		getMongoClient();
		if (prudentialDB == null) {
			
			try {
				prudentialDB = mongoClient.getDatabase(MongoDBLoadRunner.getDatabase());
			} catch (MongoException ex) {
				System.out.println("An error occoured when connecting to MongoDB" + ex.toString());
			} catch (Exception ex) {
				System.out.println("An error occoured when connecting to MongoDB" + ex.toString());
			}
		}

		return prudentialDB;
	}
	
	public void init() {
		mongoClient = null;
	}
	
	public void closeMongoClient()
	{
		//String ConnectionStr = "mongodb://"+mongodbUser+":"+mongodbPass+"@"+mongodbEndpoint;
		//String ConnectionStr = "mongodb+srv://"+mongodbUser+":"+mongodbPass+"@"+mongodbEndpoint;
		//System.out.println ("InsertCustomer.connectMongoClient:"); 
		try{
			
			if (mongoClient != null) {
				//System.out.println ("InsertCustomer.mongoClient:"+LoadGenData.getMongodbConnection()); 
				mongoClient.close();
			}	
		}catch(Exception e)
	    {
	    	System.out.println("Making MongoDB Close Exception:"+e.toString());
	    }
	}

}