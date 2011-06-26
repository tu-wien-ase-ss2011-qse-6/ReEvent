package reevent.web.myEvents;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import reevent.dao.FeedbackDao;
import reevent.domain.Event;
import reevent.domain.Feedback;
import reevent.domain.Rating;
import reevent.domain.User;
import reevent.web.ReEventSession;
import reevent.web.StyledPanel;
import reevent.web.Template;

import java.util.Arrays;
import java.util.List;

/**
 * A block that shows the main picture and basic data about an event.
 */
public class NewFeedback extends StyledPanel {
	TextField title;
	TextArea text;
	//TextField createdBy;
	
	DropDownChoice<Rating> rating;
    
	private static final List RATINGS = Arrays.asList(new String[] {"NEGATIVE", "NEUTRAL", "POSITIVE"});
	
    Form<Feedback> form;

    @SpringBean
    FeedbackDao feedbackDao;
    
    public NewFeedback(String id, IModel<Event> event) {
    	super(id, event);

        CompoundPropertyModel<Feedback> formModel = new CompoundPropertyModel<Feedback>(new Feedback());
        form = new Form<Feedback>("form", formModel) {
			@Override
			protected void onSubmit() {
                User user = ReEventSession.get().getModUserSignedIn().getObject();
                Feedback fb = getModelObject();
                fb.setCreatedBy(user);
                fb.setEvent((Event) NewFeedback.this.getDefaultModelObject());
                feedbackDao.save(fb);
			}
    	};
    	
    	add(form);
    	
    	form.add(rating = new DropDownChoice<Rating>("rating", Arrays.asList(Rating.values())));
    	
    	form.add(title = new TextField<String>("feedbackTitle", formModel.<String>bind("title")));
    	
    	form.add(text = new TextArea<String>("text"));
    	
    	Template.addFormLabels(title, rating, text);
    	
    }

    
    
}
