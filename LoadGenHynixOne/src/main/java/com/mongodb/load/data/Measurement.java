package com.mongodb.load.data;

import java.util.Date;
import java.util.Random;

public class Measurement {
	
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
	
	public Measurement() {
		super();
	}
		
	public void setMeasurement(int num, int count) {
		r = new Random();
		
		this.paramAlias = genLowerString(5)+num;
		this.paramName = genLowerString(5)+num;
		
		paramValues = new String[count];
		for (int i=0;i<count;i++)
		{
			paramValues[i]=	genLowerString(5)+i;
		}
	}
	
	public String getParamAlias() {
		return paramAlias;
	}
	public void setParamAlias(String paramAlias) {
		this.paramAlias = paramAlias;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String[] getParamValues() {
		return paramValues;
	}
	public void setParamValues(String[] paramValues) {
		this.paramValues = paramValues;
	}

	private String paramAlias="";
	private String paramName="";
	private String[] paramValues=null;
	
	/*
	public void printMeasure()
	{
		System.out.println("Parameter:"+paramAlias+":"+paramName);
		System.out.println("ParamValues:"+paramValues.toString());
	}
	*/

}
