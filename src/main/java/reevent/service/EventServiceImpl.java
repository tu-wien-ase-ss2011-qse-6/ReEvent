package reevent.service;

import org.springframework.beans.factory.annotation.Autowired;

import reevent.dao.EventDao;
import reevent.domain.Event;

public class EventServiceImpl implements EventService {
	@Autowired
    EventDao dao;
	
	@Override
	public boolean isAvailable(String eventname) {
		return !dao.eventNameExists(eventname);
	}

	@Override
	public Event create(Event event) {
		return dao.save(event);
	}

}
