package com.lti.repository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.entity.Bus;

@Repository
public class SearchBusImpl extends GenericRepositoryImpl{
	
	public int getSourceId(String source) {
		return (int)entityManager.createQuery("Select s.id from Stop s where s.name = :src")
				.setParameter("src", source).getSingleResult();
	}
	
	public int getDestinationId(String destination) {
		return (int)entityManager.createQuery("Select s.id from Stop s where s.name = :dst")
				.setParameter("dst", destination).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Bus> getBusList(int srcId, int dstId){
		List<Bus> list = new ArrayList<Bus>();
		List<Bus> b1 = entityManager.createQuery("select r.bus from Route r where r.stop.id= :srcId")
				.setParameter("srcId", srcId).getResultList();
		List<Bus> b2  = entityManager.createQuery("select r.bus from Route r where r.stop.id= :dstId")
				.setParameter("dstId", dstId).getResultList();
		
		for(Bus b:b1) {
			if(b2.contains(b)) {
				int sourceSequence= (Integer)entityManager.createQuery("select r.sequence from Route r where r.stop.id =:srcId and r.bus.id = :id")
						.setParameter("srcId", srcId).setParameter("id", b.getId()).getSingleResult();
				int destinationSequence= (Integer)entityManager.createQuery("select r.sequence from Route r where r.stop.id =:destId and r.bus.id = :id")
						.setParameter("destId", dstId).setParameter("id", b.getId()).getSingleResult();
				if(sourceSequence < destinationSequence)
				{
					int diff = destinationSequence-sourceSequence;
					double amount = b.getAmount();
					b.setAmount(amount*diff);
					list.add(b);
				} 
			}
		}
		
		
		return list;
			
	}
	
	public String getArrivalTime(int stopId, int busId) {
		return (String)entityManager.createQuery("select route.arrivalTime from Route route where route.bus.id=:busId and"
				+" route.stop.id=:stopId").setParameter("busId", busId).setParameter("stopId", stopId).getSingleResult();
	}

	public String getDepartureTime(int stopId, int busId) {
		return (String)entityManager.createQuery("select route.departureTime from Route route where route.bus.id=:busId and"
				+" route.stop.id=:stopId").setParameter("busId", busId).setParameter("stopId", stopId).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getStopsList(){
		List<String> l = new ArrayList<String>();
		l = entityManager.createQuery(" select s.name from Stop s").getResultList();
		return l;
	}
}
