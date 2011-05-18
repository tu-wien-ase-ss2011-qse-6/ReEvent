package reevent.service;

import java.util.ArrayList;
import java.util.List;

import reevent.domain.Event;
import reevent.domain.User;

public interface EventService {

	/**
     * Check if the given event name is available.
     * @param eventname The eventname to check
     * @return
     */
    boolean isAvailable(String eventname);
    
    
    /**
     * Creates a new event.
     * @param event The event to create.
     */
    Event create(Event event);
    
    
    
    /**
     * Find all events by User
     * @param user 
     */
    public List<Event> getByUser(User user);
}
