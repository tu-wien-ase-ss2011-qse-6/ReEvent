package reevent.web.myEvents;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.convert.IConverter;
import reevent.domain.Event;
import reevent.domain.Feedback;
import reevent.domain.media.MediaBase;
import reevent.web.StyledPanel;
import reevent.web.convert.DateTimeConverter;
import reevent.web.media.MediaDisplay;
import reevent.web.myEvents.newEvent.detailEvent;
import reevent.web.myEvents.newEvent.genreEvent;

import java.text.DateFormat;

/**
 * A block that shows the main picture and basic data about an event.
 */
public class FeedbackDisplay extends StyledPanel {
    Label feedbackCreatedBy;
    Label feedbackRating;
    Label feedbackTitle;
    Label feedbackText;    

    public FeedbackDisplay(String id, final IModel<Feedback> feedback) {
        super(id, new CompoundPropertyModel<Feedback>(feedback));
        this.add(new Label("title"));
        this.add(new Label("text"));
        this.add(new Label("rating"));
        this.add(new Label("createdBy"));
        
    }
}
