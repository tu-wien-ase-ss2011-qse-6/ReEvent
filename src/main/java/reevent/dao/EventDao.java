package reevent.dao;

import reevent.domain.Event;
import reevent.domain.User;

import java.util.List;

public interface EventDao extends EntityDao<Event> {

	boolean eventNameExists(String eventname);

    List<Event> findByCreator(User user, Integer first, Integer count);
    List<Event> findByCreator(String username, Integer first, Integer count);

}
