package reevent.dao;

import reevent.domain.Event;
import reevent.domain.Location;
import reevent.domain.User;

import java.util.List;

public interface EventDao extends EntityDao<Event> {

	boolean eventNameExists(String eventname);

    List<Event> findByCreator(User user, Integer first, Integer count);
    List<Event> findByCreator(String username, Integer first, Integer count);

    /**
     * Finds all events in a given distance from a point.
     * @param origin
     * @param distance
     * @return
     */
    List<Event> findNear(Location origin, double distance, int first, int count);

    List<? extends Event> findUpcoming(Location location, int first, int count);
}
