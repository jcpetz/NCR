package com.ncr.demo.output;

import com.ncr.demo.input.LatLong;

public class AddressInfo {
	
	private String streetNumber;
	private String street;
	private String city;
	private String state;
	private String zipCode;
	private String extZipCode;
	private String country = "";  // not always available
	private LatLong latLong;
	
	public AddressInfo() {
	}
	
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getExtZipCode() {
		return extZipCode;
	}
	public void setExtZipCode(String extZipCode) {
		this.extZipCode = extZipCode;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public LatLong getLatLong() {
		return latLong;
	}

	public void setLatLong(LatLong latLong) {
		this.latLong = latLong;
	}

	// formatted output
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(streetNumber + " " + street + "\n");
		sb.append(city + "," + state + " " + zipCode + "-" + extZipCode + " " + country + "\n");
		sb.append("given input Lat,Long = " + latLong.getLatitude() + "," + latLong.getLongitude() + " at time " + latLong.getTimeStamp());
		return sb.substring(0);
	}
}
