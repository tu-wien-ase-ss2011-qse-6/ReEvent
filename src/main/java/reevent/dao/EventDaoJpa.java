package reevent.dao;

import com.mysema.query.jpa.JPQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reevent.domain.Event;
import reevent.domain.Location;
import reevent.domain.QEvent;
import reevent.domain.User;
import reevent.util.LocationUtil;

import java.util.List;

@Transactional
@Repository
public class EventDaoJpa extends EntityDaoBase<Event> implements EventDao {
	QEvent $ = QEvent.event;

    @Autowired
    UserDao userDao;
	
	@Override
	public boolean eventNameExists(String eventname) {
		return queryByEventname(eventname).exists();
	}

    @Override
    public List<Event> findByCreator(User user, Integer first, Integer count) {
        return query($, first, count).where($.createdBy.eq(user)).list($);
    }

    @Override
    public List<Event> findByCreator(String username, Integer first, Integer count) {
        return query($, first, count).where($.createdBy.username.eq(username)).list($);
    }

    @Override
    public List<Event> findNear(Location origin, double distance, int first, int count) {
        Location[] b = LocationUtil.bounds(origin, distance);
        return query($, first, count).where(
                $.location.longitude.goe(b[0].getLongitude()),
                $.location.longitude.loe(b[1].getLongitude()),
                $.location.latitude.goe(b[0].getLatitude()),
                $.location.latitude.goe(b[1].getLatitude()))
                .list($);
    }

    private JPQLQuery queryByEventname(String eventname) {
        return query().from($).where($.name.eq(eventname));
    }
}
