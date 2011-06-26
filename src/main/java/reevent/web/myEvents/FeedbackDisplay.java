package reevent.web.myEvents;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import reevent.dao.EventDao;
import reevent.dao.FeedbackDao;
import reevent.domain.Feedback;
import reevent.web.ReEventApplication;
import reevent.web.ReEventSession;
import reevent.web.StyledPanel;
import reevent.web.myEvents.newEvent.detailEvent;

/**
 * A block that shows the main picture and basic data about an event.
 */
public class FeedbackDisplay extends StyledPanel {
    Label title;
    Label text;
    Label rating;
    Label createdBy;
    
    Link deleteFeedback;
    
    @SpringBean
    FeedbackDao feedbackDao;

    public FeedbackDisplay(String id, final IModel<Feedback> feedback) {
        super(id, new CompoundPropertyModel<Feedback>(feedback));
        this.add(title = new Label("title"));
        this.add(text = new Label("text"));
        this.add(rating = new Label("rating"));
        this.add(createdBy = new Label("createdBy", new ComponentPropertyModel("createdBy.username")));
        
        this.add(deleteFeedback = new Link("deleteFeedback", new Model()){
        	public void onClick() {
        		
        		feedbackDao.delete(feedback.getObject().getId());
        		
                setResponsePage(new detailEvent(feedback.getObject().getEvent()));
                
                
        		
        	}
        });
        
        // display Delete Feedback link only on user that is logged in and the creator of the shown feedback
        if (ReEventSession.get().getUserSignedIn() == null) {
        	deleteFeedback.setVisible(false);
        } else if (ReEventSession.get().getUserSignedIn() != null){
        	deleteFeedback.setVisible(ReEventSession.get().getUserSignedIn().equals(feedback.getObject().getCreatedBy().getId()));
        }
        
        
        
    }
}
