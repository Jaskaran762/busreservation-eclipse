package com.lti.service;

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
		try {
			int srcId = searchBus.getSourceId(source);
			int dstId = searchBus.getDestinationId(destination);
			List<Bus> list = searchBus.getBusList(srcId, dstId);
			if(list!=null)
				return list;
			else
				throw new BusServiceException("No bus available for this route");
		}
		catch(Exception e) {
			throw new BusServiceException("Some error occurred while loading the bus list!");
		}
	}

}
