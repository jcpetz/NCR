package com.ncr.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.ncr.demo.clientRESTservices.ReverseGeocoding;
import com.ncr.demo.input.LatLong;
import com.ncr.demo.output.AddressInfo;
import com.ncr.demo.output.SortedResultCache;

@SpringBootApplication
public class ReverseGeocodingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReverseGeocodingDemoApplication.class, args);
	}

	// support for command line testing when implementing CommandLineRunner
	private LatLong getLatLong() {
		LatLong latLong = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			boolean validParams = false;
			while (!validParams) {
				System.out.print("Enter Lat,Long [? to quit] => ");
				String response = in.readLine();
				if (response.equals("?")) {
					return latLong;
				}
				String[] stringLatLong = response.split(",");
				if (stringLatLong.length != 2) {
					System.out.print("invalid selection!!\n");
					continue;
				}
				try {
					double lat = Double.parseDouble(stringLatLong[0]);
					double lon = Double.parseDouble(stringLatLong[1]);
					latLong = new LatLong(lat, lon);
					validParams = true;
				} catch (NumberFormatException ex) {
					System.out.print("numeric error!! " + ex.getMessage() + "\n");
				}
			}
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		return latLong;
	}

}
