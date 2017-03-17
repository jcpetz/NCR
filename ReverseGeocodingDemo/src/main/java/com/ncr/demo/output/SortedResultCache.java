package com.ncr.demo.output;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.ncr.demo.input.LatLong;

// singleton class - synchronize all methods since underlying TreeMap is not thread-safe
public class SortedResultCache {
	
	private final Map<LocalDateTime, AddressInfo> sortedCache;
	private final int cacheRolloff = 10;
	private static SortedResultCache singleton = new SortedResultCache();
	
	public static SortedResultCache getInstance() {
		return singleton;
	}

	private SortedResultCache() {
		sortedCache = new TreeMap<LocalDateTime, AddressInfo>(
				new Comparator<LocalDateTime>() {
					@Override
					public int compare(LocalDateTime o1, LocalDateTime o2) {
						return o1.compareTo(o2);
					}
				});
	}
	
	public synchronized void put(AddressInfo value) {
		sortedCache.put(value.getLatLong().getTimeStamp(), value);
		if (sortedCache.size() > cacheRolloff) {
		   LocalDateTime oldestKey = getOldestEntryKey();
		   sortedCache.remove(oldestKey);	
		}
	}
	
	public synchronized AddressInfo get(LatLong key) {
		return sortedCache.get(key);
	}
	
	public synchronized void purge() {
		sortedCache.clear();
	}
	
	public synchronized Collection<AddressInfo> getAllValues() {
		Collection<AddressInfo> result =  sortedCache.values();
		return result;
	}
	
	private synchronized LocalDateTime getOldestEntryKey() {
		List<AddressInfo> list = new ArrayList<AddressInfo>(sortedCache.values());
		return list.get(0).getLatLong().getTimeStamp();
	}
	
	private synchronized void printCache() {
		for (Map.Entry<LocalDateTime, AddressInfo> entry : sortedCache.entrySet()) {
            System.out.println("Key : " + entry.getKey()
				+ " Value : " + entry.getValue());
        }
	}
}
