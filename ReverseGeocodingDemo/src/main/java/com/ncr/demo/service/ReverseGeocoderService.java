package com.ncr.demo.service;

import java.util.Collection;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncr.demo.ExternalConfigParams;
import com.ncr.demo.clientRESTservices.ReverseGeocoding;
import com.ncr.demo.input.LatLong;
import com.ncr.demo.output.AddressInfo;
import com.ncr.demo.output.SortedResultCache;

@Service
public class ReverseGeocoderService {
	
	@Autowired
	private ExternalConfigParams ecp;
	
	private SortedResultCache resultCache = SortedResultCache.getInstance();
	
	private final Logger LOGGER = LogManager.getLogManager().getLogger("ReverseGeocoderService");

	public AddressInfo getAddressInfo(String lat, String lon) throws Exception {
		try {
			String provider = ecp.getProvider();
			Class<?> providerClass = Class.forName("com.ncr.demo.clientRESTservices." + provider);
			ReverseGeocoding reverseGeocoding = (ReverseGeocoding) providerClass.newInstance();
			reverseGeocoding.setApiKey(ecp.getApiKey());
			LatLong latLong = new LatLong(Double.parseDouble(lat), Double.parseDouble(lon));
			String rawResponse = reverseGeocoding.getRawAddressFromLatLon(latLong.getLatitude(), latLong.getLongitude());
			if (rawResponse != null) {
				AddressInfo addressInfo = reverseGeocoding.formatResult(rawResponse);
				addressInfo.setLatLong(latLong);
				resultCache.put(addressInfo);
				return addressInfo;
			} else {
				throw new Exception("Reverse geocoder failed for provider/apiKey = " + provider + "/" + ecp.getApiKey());
			}
		} catch (Exception ex) {
			LOGGER.info(ex.getMessage());
			throw ex;
		}
	}
	
	public Collection<AddressInfo> getAllRecentQueries() {
		return resultCache.getAllValues();
	}
}
