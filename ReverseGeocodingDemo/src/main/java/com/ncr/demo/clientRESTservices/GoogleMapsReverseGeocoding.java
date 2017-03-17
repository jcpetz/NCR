package com.ncr.demo.clientRESTservices;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncr.demo.output.AddressInfo;

public class GoogleMapsReverseGeocoding extends BaseClientREST implements ReverseGeocoding {
	private static final String providerUrl = "https://maps.googleapis.com/maps/api/geocode/json?";
	private static final String providerDefaults = "key=%s";
	private static final String latlongFormat = "&latlng=%f,%f";

	public GoogleMapsReverseGeocoding() {
	}

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
			JsonNode addressComponents = rootNode.get(0).get("address_components");
			if (addressComponents == null || addressComponents.size() == 0) {
				return addressInfo;
			}
			int size = addressComponents.size();
			// pull long name for each address node [short ok for country]
			addressInfo.setStreetNumber(size > 0 ? addressComponents.get(0).get("long_name").asText() : null);
			addressInfo.setStreet(size > 1 ? addressComponents.get(1).get("long_name").asText() : null);
			addressInfo.setCity(size > 2 ? addressComponents.get(2).get("long_name").asText() : null);
			// (3) is county - ignore
			addressInfo.setState(size > 4 ? addressComponents.get(4).get("long_name").asText() : null);
			addressInfo.setCountry(size > 5 ? addressComponents.get(5).get("long_name").asText() : null);
			addressInfo.setZipCode(size > 6 ? addressComponents.get(6).get("long_name").asText() : null);
			addressInfo.setExtZipCode(size > 7 ? addressComponents.get(7).get("long_name").asText() : null);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
		return addressInfo;
	}
}
