package com.mycompany.LogUI;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
 
@ManagedBean
@ViewScoped
public class MetadataObjects implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private List<ManifestRecord> records;
	public List<ManifestRecord> getRecords() {
		return records;
	}

	public void setRecords(List<ManifestRecord> records) {
		this.records = records;
	}



	private boolean showTable;

	private String DBGUID;
	
	public void showDT(){
		showTable = true;
	}
     
    public boolean isShowTable() {
		return showTable;
	}

	public void setShowTable(boolean showTable) {
		this.showTable = showTable;
	}


        
	@ManagedProperty("#{LNATableService}")
    private LNATableService service;
 
	@PostConstruct
    public void init() {
    	records = service.createRecords(10);
    
    }
	
	public String getDBGUID() {
		return DBGUID;
	}

	public void setDBGUID(String dBGUID) {
		DBGUID = dBGUID;
	}

 
    public void setService(LNATableService service) {
        this.service = service;
    }

	
    
    
 
    
    
    
   
     
    
}
