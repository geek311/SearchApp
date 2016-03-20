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
public class ViewObjects implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private boolean value1 = false;  
    private String selectStatus;
	private List<ManifestRecord> records;
	private boolean showTable;
	private PieChartModel pieModel1;
	private BarChartModel barModel;

    private Integer progress;
	
	public void showDT(){
		showTable = true;
	}
     
    public boolean isShowTable() {
		return showTable;
	}

	public void setShowTable(boolean showTable) {
		this.showTable = showTable;
	}

	public BarChartModel getBarModel() {
        return barModel;
    }
        
	@ManagedProperty("#{LNATableService}")
    private LNATableService service;
 
    @PostConstruct
    public void init() {
    	records = service.createRecords(10);
    	createPieModels();
    	createBarModel();
    }
     
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
     
    private void createPieModels() {
        createPieModel1();
    }
    
    public List<ManifestRecord> getRecords() {
        return records;
    }
 
    public void setService(LNATableService service) {
        this.service = service;
    }
    
    public String getSelectStatus() {
		return selectStatus;
	}

	public void setSelectStatus(String selectStatus) {
		this.selectStatus = selectStatus;
	}

	public boolean isValue1() {
        return value1;
    }
 
    public void setValue1(boolean value1) {
        this.value1 = value1;
    }
        
    public void addMessage() {
        String summary = value1 ? "Checked" : "Unchecked";
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }
    
    public Integer getProgress() {
        if(progress == null) {
            progress = 0;
        }
        else {
            progress = progress + (int)(Math.random() * 35);
             
            if(progress > 100)
                progress = 100;
        }
         
        return progress;
    }
 
    public void setProgress(Integer progress) {
        this.progress = progress;
    }
     
    public void onComplete() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Progress Completed"));
    }
     
    public void cancel() {
        progress = null;
    }
    
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
         
        pieModel1.set("DBs", 540);
        pieModel1.set("Docs", 1325);
        pieModel1.set("Docs in Solr", 702);
        pieModel1.set("Docs in HBase", 702);
         
        pieModel1.setTitle("Ingestion State");
        pieModel1.setLegendPosition("w");
    }
    
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries databases = new ChartSeries();
        databases.setLabel("Total DBs");
        databases.set("Ingestion Status", 170);
 
 
        ChartSeries HDFSInegsted = new ChartSeries();
        HDFSInegsted.setLabel("HDFS Ingested");
        HDFSInegsted.set("Ingestion Status", 82);
  
        ChartSeries PIDatabases = new ChartSeries();
        PIDatabases.setLabel("DBs with PI");
        PIDatabases.set("Ingestion Status", 30);
 
        model.addSeries(databases);
        model.addSeries(HDFSInegsted);
        model.addSeries(PIDatabases);
         
        return model;
    }
    
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Bar Chart");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
       // xAxis.setLabel("Gender");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
      //  yAxis.setLabel("Births");
        yAxis.setMin(0);
        yAxis.setMax(250);
    }
     
     
    
}
