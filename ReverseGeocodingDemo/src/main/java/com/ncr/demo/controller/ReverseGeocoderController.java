package com.ncr.demo.controller;

import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ncr.demo.output.AddressInfo;
import com.ncr.demo.service.ReverseGeocoderService;

@RestController
@RequestMapping("/reversegeocoder")
public class ReverseGeocoderController {
	
	@Autowired
	private ReverseGeocoderService reverseGeocoderService;

	@RequestMapping(value = "/getAddress/lat/{lat}/lon/{lon}/", method = RequestMethod.GET) 
	public AddressInfo getAddressInfo(@PathVariable("lat") @NotNull(message = "lat required") String lat, @PathVariable("lon") @NotNull(message = "lon required") String lon) throws Exception {	
        // delegate to service layer
		return reverseGeocoderService.getAddressInfo(lat, lon);
	}
	
	@RequestMapping(value = "/getLatestQueries", method = RequestMethod.GET) 
	public Collection<AddressInfo> getLatestQueries() {	
        // delegate to service layer
		return reverseGeocoderService.getAllRecentQueries();
	}
}
