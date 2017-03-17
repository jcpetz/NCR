package com.ncr.demo.clientRESTservices;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncr.demo.output.AddressInfo;

public class MapQuestReverseGecoding  extends BaseClientREST implements ReverseGeocoding {
	private final static String providerUrl = "http://www.mapquestapi.com/geocoding/v1/reverse?";

	private static final String providerDefaults = "key=%s&includeRoadMetadata=true&includeNearestIntersection=true";
	private static final String latlongFormat = "&location=%f,%f";
	
	@Override
	public String getRawAddressFromLatLon(double lat, double lon) {
		// build URL using specified lat/lon and invoke service
		String url = providerUrl + String.format(providerDefaults, getApiKey()) + String.format(latlongFormat, lat, lon);
        return invokeGET(url);
	}

	@Override
	public AddressInfo formatResult(String jsonResponse) {
		// format known json raw response into readable output
		AddressInfo addressInfo = new AddressInfo();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(jsonResponse);
			rootNode = rootNode.get("results");
			if (rootNode == null || rootNode.size() == 0) {
				return addressInfo;
			}
			JsonNode addressComponents = rootNode.get(0).get("locations");
			if (addressComponents == null || addressComponents.size() == 0) {
				return addressInfo;
			}
			String[] streetInfo = splitTokens(addressComponents.get(0).get("street").asText(), " ");
			addressInfo.setStreetNumber(streetInfo[0]);
			if (streetInfo.length > 1) {
			   addressInfo.setStreet(streetInfo[1]);
			}
			addressInfo.setCity(addressComponents.get(0).get("adminArea5").asText());
			addressInfo.setState(addressComponents.get(0).get("adminArea3").asText());
			addressInfo.setCountry(addressComponents.get(0).get("adminArea1").asText());
			String[] postalCode = splitTokens(addressComponents.get(0).get("postalCode").asText(), "-");
			addressInfo.setZipCode(postalCode[0]);
			if (postalCode.length > 1) {
			   addressInfo.setExtZipCode(postalCode[1]);
			}
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		return addressInfo;
	}
}
