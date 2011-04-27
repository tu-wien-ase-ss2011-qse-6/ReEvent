package reevent.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reevent.domain.Event;

@Transactional
@Repository
public class EventDaoJpa extends EntityDaoBase<Event> implements EventDao {
}
