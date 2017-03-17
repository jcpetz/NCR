package com.ncr.demo.clientRESTservices;

import com.ncr.demo.output.AddressInfo;

public interface ReverseGeocoding {
	
	public String getRawAddressFromLatLon(double lat, double lon);
	public AddressInfo formatResult(String rawResponse);
	public void setApiKey(String key);
}