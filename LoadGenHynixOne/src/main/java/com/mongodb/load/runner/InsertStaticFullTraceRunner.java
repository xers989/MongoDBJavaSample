package com.mongodb.load.runner;


public class InsertStaticFullTraceRunner extends Thread{
	
	private int num =  0;
	private int startX = 0;
	private int endX=0;
	private int sleepmi=0;
	
	private String OneMany="O";
	
	public String getOneMany() {
		return OneMany;
	}

	public void setOneMany(String oneMany) {
		OneMany = oneMany;
	}

	public InsertStaticFullTraceRunner() {
		this.num = 0;
	}
	
	public InsertStaticFullTraceRunner(int num,int startX, int endX, int sleepmi) {
		this.num = num;
		this.startX = startX;
		this.endX=endX;
		this.sleepmi=sleepmi;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getSleepmi() {
		return sleepmi;
	}

	public void setSleepmi(int sleepmi) {
		this.sleepmi = sleepmi;
	}

	@Override
	public void run() {
		
		System.out.println("Insertion Thread #"+this.num+ " start");
		int prefix = (num+1)*100000;
		InsertStaticFullTraceData insert = null;
		insert = new InsertStaticFullTraceData();
		
		if (OneMany.equals("O"))
		{
			for (int startIdx = startX; startIdx <= endX; startIdx++)
			{
				try {
	    			//insert = new InsertCustomer();
					//Sinsert.InsertLoadData();
					//OneMany.equals("O");
					insert.InsertLoadData();
					//insert.InsertLoadData(prefix + startIdx);
					Thread.sleep(sleepmi);
				}catch(Exception e)
				{
					System.out.println("Java Thread error : Thread.sleep"+e.toString());
				}
			}
		}
		else
		{
			for (int startIdx = startX; startIdx <= endX; startIdx++)
			{
				try {
	    			//insert = new InsertCustomer();
					//Sinsert.InsertLoadData();
					//OneMany.equals("O");
					//insert.InsertLoadData(prefix + startIdx);
					insert.InsertManyData();
					Thread.sleep(sleepmi);
				}catch(Exception e)
				{
					System.out.println("Java Thread error : Thread.sleep"+e.toString());
				}
			}
			
		}
		
		System.out.println("Insertion Thread #" + num + " end");
	}

}