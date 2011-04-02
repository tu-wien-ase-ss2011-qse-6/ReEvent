package reevent.dao;

import org.springframework.stereotype.Repository;
import reevent.domain.Event;

@Repository
public class EventDaoJpa extends EntityDaoBase<Event> implements EventDao {
}
