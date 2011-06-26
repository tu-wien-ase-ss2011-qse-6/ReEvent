package reevent.web.myEvents;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.convert.IConverter;
import reevent.dao.FeedbackDao;
import reevent.domain.Event;
import reevent.domain.Feedback;
import reevent.domain.media.MediaBase;
import reevent.web.ReEventSession;
import reevent.web.StyledPanel;
import reevent.web.convert.DateTimeConverter;
import reevent.web.media.MediaDisplay;

import java.text.DateFormat;

/**
 * A block that shows the main picture and basic data about an event.
 */
public class EventDetails extends StyledPanel {
    MediaDisplay mainPicture;
    Label name;
    Label locationName;
    Label start;
    Label genre;
    
    RepeatingView feedback;
    
    @SpringBean
    FeedbackDao feedbackDao;
    private NewFeedback newFeedback;

    public EventDetails(String id, IModel<Event> event) {
        super(id, new CompoundPropertyModel<Event>(event));
        MediaBase media = event.getObject().getMainPicture();
        this.add(mainPicture = new MediaDisplay("mainPicture", media == null ? null : media.getId()));
        this.add(new Label("name"));
        this.add(new Label("locationName"));
        this.add(new Label("locationAddress"));
        this.add(new Label("performer"));
        this.add(new Label("start") {
            @SuppressWarnings("unchecked")
            @Override
            public IConverter getConverter(Class type) {
                return DateTimeConverter.both(DateFormat.SHORT, DateFormat.SHORT);
            }
        });
        this.add(new Label("genre"));


        add(feedback = new RepeatingView("feedback"));

        for (Feedback fb : feedbackDao.findForEvent(event.getObject())) {
            feedback.add(new FeedbackDisplay(feedback.newChildId(), Model.of(fb)));
        }

        newFeedback = new NewFeedback("newFeedback", new Model(event));
        this.add(newFeedback);
        newFeedback.setVisible(ReEventSession.get().getUserSignedIn() != null);
    }

	@Override
	protected void onComponentTag(ComponentTag tag) {
		// TODO Auto-generated method stub
		super.onComponentTag(tag);
		Event e = (Event) getDefaultModelObject();
		tag.append("class", "event-location", " ");
        
		tag.put("data-lat", Double.toString(e.getLatitude()));
		tag.put("data-lng", Double.toString(e.getLongitude()));
	}
    
    
    
}
