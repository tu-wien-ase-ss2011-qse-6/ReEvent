package reevent.dao;

import java.util.List;

import reevent.domain.Event;
import reevent.domain.Feedback;
import reevent.domain.media.MediaBase;

public interface FeedbackDao extends EntityDao<Feedback> {

	List<Feedback> findForEvent(Event o);
}
