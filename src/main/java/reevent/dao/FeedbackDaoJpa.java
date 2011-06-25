package reevent.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reevent.domain.Event;
import reevent.domain.Feedback;
import reevent.domain.QFeedback;
import reevent.domain.media.MediaBase;

@Transactional
@Repository
public class FeedbackDaoJpa extends EntityDaoBase<Feedback> implements FeedbackDao {

	QFeedback $ = QFeedback.feedback;
	@Override
	public List<Feedback> findForEvent(Event o) {
        return query().from($).where($.event.id.eq(o.getId())).orderBy($.createdAt.asc()).list($);
    }

}
