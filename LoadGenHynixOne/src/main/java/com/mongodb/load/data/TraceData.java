package com.mongodb.load.data;

import java.util.Date;
import java.util.Random;


public class TraceData {
	public String genLowerString(int length) {
		 
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'

	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(length);
	    for (int i = 0; i < length; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	    return generatedString;
	}
	
	private Random r;
	/*
	private String command="";
	private String eqpid="";
	private String[] headerList=null;
	private String lotId="";
	private String recpId="";
	private String collectionInterval="";
	private String collectionType="";
	private Date eventTime=null;
	private String trid="";
	private int devideOrder=0;
	
	private int[] chamberRawIdList=null;
	private String[] chamberAliasList=null;
	*/
	private String chamberRawId="";
	private String chamberTypeRawId="";
	private String chamberTypeName="";
	private String chamberAlias="";
	private String moduleName="";
	private String mesChamberName="";
	private String lot_id="";
	private String slot_no="";
	private String lot_type="";
	private Date startTime=null;
	private Date endTime=null;
	
	private Measurement[] measurements = null;
	
	private Date[] history=null;
	
	//private int sizeOfMessage = 10;
	//100 : 10Seconds, 10: 1second
	
	//private int sizeOfMeasure = 400;
	//private int sizeOfMessage = 100;
	public TraceData(int measureSize, int sizeOfMessage) {
		// measureSize : 400
		//sizeOfMessage : 100
		super();
		String chamber="131212";
		setChamberRawId(chamber);
		setChamberTypeRawId(chamber);
		setChamberTypeName("Chamber_type_name");
		setChamberAlias("M16eqp123_ch1");
		setModuleName("M16eqp123_ch1");
		setMesChamberName("M16eqp123_ch1");
		setLot_id("lot233_slot3584");
		setSlot_no("1");
		setLot_type("production");
		setStartTime(new Date());
		setEndTime(new Date());
		
		measurements = new Measurement[measureSize];
		history = new Date[sizeOfMessage];
		for (int i=0; i<measureSize;i++)
		{
			//System.out.println("Error?");
			measurements[i] = new Measurement();
			measurements[i].setMeasurement(i,sizeOfMessage);
			//measurements[i].printMeasure();
		}
		for (int i=0; i<sizeOfMessage;i++)
		{
			history[i] = new Date();
		}
	}
	
	private String[] paramAliasList=null;
	private String[] paramNameList=null;
	private String[] paramValueList=null;
	
	public String[] getParamAliasList() {
		return paramAliasList;
	}


	public void setParamAliasList(String[] paramAliasList) {
		this.paramAliasList = paramAliasList;
	}


	public String[] getParamNameList() {
		return paramNameList;
	}


	public void setParamNameList(String[] paramNameList) {
		this.paramNameList = paramNameList;
	}


	public String[] getParamValueList() {
		return paramValueList;
	}


	public void setParamValueList(String[] paramValueList) {
		this.paramValueList = paramValueList;
	}


	public TraceData(int measureSize) {
		// measureSize : 400
		//sizeOfMessage : 100
		super();
		String chamber="131212";
		setChamberRawId(chamber);
		setChamberTypeRawId(chamber);
		setChamberTypeName("Chamber_type_name");
		setChamberAlias("M16eqp123_ch1");
		setModuleName("M16eqp123_ch1");
		setMesChamberName("M16eqp123_ch1");
		setLot_id("lot233_slot3584");
		setSlot_no("1");
		setLot_type("production");
		
		paramAliasList = new String[measureSize];
		paramNameList = new String[measureSize];
		paramValueList = new String[measureSize];
		
		for (int i=0; i<measureSize;i++)
		{
			//System.out.println("Error?");
			paramAliasList[i] = genLowerString(5)+i;
			paramNameList[i]= genLowerString(5)+i;
			paramValueList[i]= genLowerString(5)+i;
			
		}
	}
	

	public Date[] getHistory() {
		return history;
	}


	public void setHistory(Date[] history) {
		this.history = history;
	}


	public String getChamberRawId() {
		return chamberRawId;
	}

	public void setChamberRawId(String chamberRawId) {
		this.chamberRawId = chamberRawId;
	}

	public String getChamberTypeRawId() {
		return chamberTypeRawId;
	}

	public void setChamberTypeRawId(String chamberTypeRawId) {
		this.chamberTypeRawId = chamberTypeRawId;
	}

	public String getChamberTypeName() {
		return chamberTypeName;
	}

	public void setChamberTypeName(String chamberTypeName) {
		this.chamberTypeName = chamberTypeName;
	}

	public String getChamberAlias() {
		return chamberAlias;
	}

	public void setChamberAlias(String chamberAlias) {
		this.chamberAlias = chamberAlias;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getMesChamberName() {
		return mesChamberName;
	}

	public void setMesChamberName(String mesChamberName) {
		this.mesChamberName = mesChamberName;
	}

	public String getLot_id() {
		return lot_id;
	}

	public void setLot_id(String lot_id) {
		this.lot_id = lot_id;
	}

	public String getSlot_no() {
		return slot_no;
	}

	public void setSlot_no(String slot_no) {
		this.slot_no = slot_no;
	}

	public String getLot_type() {
		return lot_type;
	}

	public void setLot_type(String lot_type) {
		this.lot_type = lot_type;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Measurement[] getMeasurements() {
		return measurements;
	}

	public void setMeasurements(Measurement[] measurements) {
		this.measurements = measurements;
	}
	

}
