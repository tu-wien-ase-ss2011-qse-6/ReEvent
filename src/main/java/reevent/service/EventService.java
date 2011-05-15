package reevent.service;

import reevent.domain.Event;

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
}
