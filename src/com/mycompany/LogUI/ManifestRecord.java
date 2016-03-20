package com.mycompany.LogUI;

import java.io.Serializable;

public class ManifestRecord implements Serializable{

	private static final long serialVersionUID = 1L;



public String getDBName() {
		return DBName;
	}
	public void setDBName(String dBName) {
		DBName = dBName;
	}
	public String getDBGUID() {
		return DBGUID;
	}
	public void setDBGUID(String dBGUID) {
		DBGUID = dBGUID;
	}
	public int getScheduled() {
		return Scheduled;
	}
	public void setScheduled(int scheduled) {
		Scheduled = scheduled;
	}
	public int getNumDocs() {
		return numDocs;
	}
	public void setNumDocs(int numDocs) {
		this.numDocs = numDocs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDocGuid() {
		return DocGuid;
	}
	public void setDocGuid(String docGuid) {
		DocGuid = docGuid;
	}
	public String getWorkEffortTitle() {
		return WorkEffortTitle;
	}
	public void setWorkEffortTitle(String workEffortTitle) {
		WorkEffortTitle = workEffortTitle;
	}
	
String DBName;
String DBGUID;
String DocGuid;
String WorkEffortTitle;
   int Scheduled;
   int numDocs;
   String status;
   


public ManifestRecord(String dBGUID, String dBName, int scheduled, int numDocs, String status, String DocGuid, String WorkEffortTitle) {
	super();
	DBName = dBName;
	DBGUID = dBGUID;
	Scheduled = scheduled;
	this.WorkEffortTitle = WorkEffortTitle;
	this.DocGuid = DocGuid;
	this.numDocs = numDocs;
	this.status = status;
}

}
