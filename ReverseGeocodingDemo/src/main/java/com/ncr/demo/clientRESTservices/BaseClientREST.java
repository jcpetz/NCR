package com.ncr.demo.clientRESTservices;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class BaseClientREST {
	
	private RestTemplate restTemplate;
	private String apiKey;

	public String invokeGET(String url){
		final String errMsgFormat = "ERROR: failed REST response for url = %s; error was: %s";
		try {
			// invoke REST service
			restTemplate = new RestTemplate();
			// - on failure log error
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			String address = response.getBody();
			return address;
		} catch (HttpStatusCodeException ex) {
			System.err.println(String.format(errMsgFormat, url, ex.getResponseBodyAsString()));
		} catch (Throwable ex) {
			System.err.println(String.format(errMsgFormat, url, ex.getMessage()));
		}
		return null;	
	}
	
	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;	
	}
	
	// helper methods
	public String[] splitTokens(String rawText, String sep) {
		String[] split = rawText.split(sep);
		if (split.length > 1) {
		   split[1] = rawText.substring(split[0].length() + sep.length()).trim();
		}
		return split;
	}
}
