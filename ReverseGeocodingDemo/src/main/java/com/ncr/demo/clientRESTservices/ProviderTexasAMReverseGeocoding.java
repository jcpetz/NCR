package com.ncr.demo.clientRESTservices;

import com.ncr.demo.output.AddressInfo;

public class ProviderTexasAMReverseGeocoding extends BaseClientREST implements ReverseGeocoding {
	private static final String providerUrl = "https://geoservices.tamu.edu/Services/ReverseGeocoding/WebService/v04_01/HTTP/default.aspx?";
	private static final String providerDefaults = "apikey=%s&format=csv&notStore=false&version=4.10";
	private static final String latlongFormat = "&lat=%f&lon=%f";

	public ProviderTexasAMReverseGeocoding() {
	}

	@Override
	public String getRawAddressFromLatLon(double lat, double lon) {
		// build URL using specified lat/lon and invoke service
		String url = providerUrl + String.format(providerDefaults, getApiKey()) + String.format(latlongFormat, lat, lon);
        return invokeGET(url);
	}

	@Override
	public AddressInfo formatResult(String rawResponse) {
		// format known CSV raw response into readable output
		// - working backwards => extended zipcode, zipcode, state, city, streetName&Address, <ignore the rest>
		// - separate street <space> Address into number, street name <use regex or further string splitting>
		AddressInfo addressInfo = new AddressInfo();
		String[] tokens = rawResponse.trim().split(",");
		int len = tokens.length;
		String[] streetInfo = splitTokens(tokens[len-5], " ");
		addressInfo.setStreetNumber(streetInfo[0]);
		if (streetInfo.length > 1) {
		   addressInfo.setStreet(streetInfo[1]);
		}
		addressInfo.setCity(tokens[len-4]);
		addressInfo.setState(tokens[len-3]);
		addressInfo.setZipCode(tokens[len-2]);
		addressInfo.setExtZipCode(tokens[len-1]);

		return addressInfo;	
	}
}