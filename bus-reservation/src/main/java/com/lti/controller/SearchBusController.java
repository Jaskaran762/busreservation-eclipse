package com.lti.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.SearchBusDto;
import com.lti.dto.ShowBusDto;
import com.lti.entity.Bus;
import com.lti.exception.BusServiceException;
import com.lti.service.SearchBusService;

@RestController
@CrossOrigin
public class SearchBusController {

	@Autowired
	private SearchBusService busService;

	@PostMapping(path="/searchBuses")
	public List<ShowBusDto> search(@RequestBody SearchBusDto searchBusDto) {

			List<Bus> list = busService.searchBus(searchBusDto.getSource(), searchBusDto.getDestination());
			List<ShowBusDto> buses = new ArrayList<ShowBusDto>();

			for(Bus bus : list) {
				ShowBusDto showBus = new ShowBusDto();
				showBus.setId(bus.getId());
				showBus.setBusName(bus.getBusName());
				showBus.setBusNumber(bus.getBusNumber());
				showBus.setAmount(bus.getAmount());
				showBus.setSeats(bus.getSeats());
				showBus.setStatus(bus.getStatus());
				showBus.setType(bus.getType());
				showBus.setDepartureTime(busService.getDepartureTime(searchBusDto.getSource(), bus.getId()));
				showBus.setArrivalTime(busService.getArrivalTime(searchBusDto.getSource(), bus.getId()));
				buses.add(showBus);
			}
			return buses;

	}
	
	@GetMapping(path="/stopsList")
	public List<String> getStops(){
		List<String> stops = busService.getList();
		return stops;

	}

}
