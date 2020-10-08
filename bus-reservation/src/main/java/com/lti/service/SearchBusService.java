package com.lti.service;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entity.Bus;
import com.lti.exception.BusServiceException;
import com.lti.repository.SearchBusImpl;

@Service
public class SearchBusService {

	@Autowired
	private SearchBusImpl searchBus;

	public List<Bus> searchBus(String source, String destination){
		int srcId = searchBus.getSourceId(source);
		int dstId = searchBus.getDestinationId(destination);
		List<Bus> list = searchBus.getBusList(srcId, dstId);
		return list;
	}
	
	public String getArrivalTime(String stop, int busId) {
		int stopId = searchBus.getSourceId(stop);
		return searchBus.getArrivalTime(stopId, busId);
	}

	public String getDepartureTime(String stop, int busId) {
		int stopId = searchBus.getSourceId(stop);
		return searchBus.getDepartureTime(stopId, busId);
	}
	
	public List<String> getList(){
		List<String> list = searchBus.getStopsList();
		return list;
	}
}
