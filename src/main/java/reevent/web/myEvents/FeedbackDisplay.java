package reevent.web.myEvents;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import reevent.domain.Feedback;
import reevent.web.StyledPanel;

/**
 * A block that shows the main picture and basic data about an event.
 */
public class FeedbackDisplay extends StyledPanel {
    Label title;
    Label text;
    Label rating;
    Label createdBy;

    public FeedbackDisplay(String id, final IModel<Feedback> feedback) {
        super(id, new CompoundPropertyModel<Feedback>(feedback));
        this.add(title = new Label("title"));
        this.add(text = new Label("text"));
        this.add(rating = new Label("rating"));
        this.add(createdBy = new Label("createdBy", new ComponentPropertyModel("createdBy.username")));
        
    }
}
