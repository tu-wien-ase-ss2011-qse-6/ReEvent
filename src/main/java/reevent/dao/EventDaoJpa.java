package reevent.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.jpa.JPQLQuery;

import reevent.domain.Event;
import reevent.domain.QEvent;
import reevent.domain.QUser;

@Transactional
@Repository
public class EventDaoJpa extends EntityDaoBase<Event> implements EventDao {
	
	

	QEvent $ = QEvent.event;
	
	@Override
	public boolean eventNameExists(String eventname) {
		return queryByEventname(eventname).exists();
	}
	
	

    private JPQLQuery queryByEventname(String eventname) {
        return query().from($).where($.event.name.eq(eventname));
    }
}
