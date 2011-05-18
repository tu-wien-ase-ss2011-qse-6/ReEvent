package reevent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reevent.dao.EventDao;
import reevent.domain.Event;
import reevent.domain.User;


@Transactional
@Service
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

	@Override
	public List<Event> getByUser(User user) {
		
		List<Event> userEvents = dao.findByCreator(user.getUsername(), 0, 10);
		
		
		return userEvents;
	}

}
