package com.mycompany.LogUI;

public class HBaseTableObj {
	String IPAddress;
	String URL;
	Integer minTime;
	Integer maxTime;
	Integer avgTime;

	/*public HBaseTableObj(String IPAddress, String URL, 
			Integer minTime, Integer maxTime, Integer avgTime) {

		this.IPAddress = IPAddress;
		this.URL = URL;
		this.minTime = minTime;
		this.maxTime = maxTime;
		this.avgTime = avgTime;
	}*/

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public Integer getMinTime() {
		return minTime;
	}

	public void setMinTime(Integer minTime) {
		this.minTime = minTime;
	}

	public Integer getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(Integer maxTime) {
		this.maxTime = maxTime;
	}

	public Integer getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(Integer avgTime) {
		this.avgTime = avgTime;
	}
}

