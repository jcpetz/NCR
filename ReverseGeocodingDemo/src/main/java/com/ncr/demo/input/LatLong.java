package com.ncr.demo.input;

import java.time.LocalDateTime;

public class LatLong {
	private final double latitude;
	private final double longitude;
	private final LocalDateTime timeStamp;

	public LatLong(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.timeStamp = LocalDateTime.now();
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
}
