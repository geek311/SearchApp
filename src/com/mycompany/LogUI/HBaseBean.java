package com.mycompany.LogUI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

@ManagedBean(name = "HBaseBean", eager = true)
@SessionScoped
public class HBaseBean {

	public List<HBaseTableObj> getRows() {
		process();
		return rows;
	}
 
	public void setRows(List<HBaseTableObj> rows) {
		this.rows = rows;
	}

	List<HBaseTableObj> rows = new ArrayList<HBaseTableObj>();
	
	public HBaseBean() {
		System.out.println("HelloWorld started!");
	}

	public void process() {
		Configuration hBaseConfig = HBaseConfiguration.create();
		hBaseConfig.set("hbase.zookeeper.quorum", "data2.perfluent.com,data1.perfluent.com,name1.perfluent.com");
		hBaseConfig.set("hbase.zookeeper.property.clientPort", "2181");

		// Create Table
		try (Connection connection = ConnectionFactory.createConnection(hBaseConfig);
				Admin admin = connection.getAdmin()) {

			HTableDescriptor table = new HTableDescriptor(TableName.valueOf("LogAnalysis"));
			table.addFamily(new HColumnDescriptor("stats"));

			try {
				if (!admin.tableExists(table.getTableName())) {
					System.out.print("Creating table. ");
					admin.createTable(table);
					System.out.println(" Done.");
				} else {
					Scan scan = new Scan();

					// Getting the scan result
					ResultScanner scanner = ((Table) table).getScanner(scan);
					scan.addFamily(Bytes.toBytes("stats"));

					HBaseTableObj tableObj = new HBaseTableObj();

					// Reading values from scan result
					for (Result result = scanner.next(); result != null; result = scanner.next()) {
						System.out.println("Found row : " + result);
						for (Cell cell : result.listCells()) {
							String qualifier = Bytes.toString(CellUtil.cloneQualifier(cell));
							String value = Bytes.toString(CellUtil.cloneValue(cell));
							if (qualifier.equalsIgnoreCase("IPAddress")) {
								tableObj.setIPAddress(value);
							} else if (qualifier.equalsIgnoreCase("URL")) {
								tableObj.setURL(value);
							} else if (qualifier.equalsIgnoreCase("Min")) {
								tableObj.setMinTime(Integer.parseInt(value));
							} else if (qualifier.equalsIgnoreCase("Max")) {
								tableObj.setMaxTime(Integer.parseInt(value));
							} else if (qualifier.equalsIgnoreCase("Avg")) {
								tableObj.setAvgTime(Integer.parseInt(value));
							}
						}
						
						rows.add(tableObj);
					}

					// closing the scanner
					scanner.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public String getMessage() {
		return "Log Analysis";
	}
}
