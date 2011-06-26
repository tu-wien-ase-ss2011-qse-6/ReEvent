package reevent.web.myEvents;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.ComponentPropertyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.convert.IConverter;
import reevent.dao.FeedbackDao;
import reevent.domain.Event;
import reevent.domain.Feedback;
import reevent.domain.Rating;
import reevent.domain.media.MediaBase;
import reevent.web.StyledPanel;
import reevent.web.Template;
import reevent.web.convert.DateTimeConverter;
import reevent.web.media.MediaDisplay;

import java.text.DateFormat;
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
    
    public NewFeedback(String id, IModel<Event> event) {
    	super(id, event);
    	
    	form = new Form<Feedback>("form", new CompoundPropertyModel<Feedback>(new Feedback())) {
			@Override
			protected void onSubmit() {
				super.onSubmit();
			}
    		
    	};
    	
    	add(form);
    	
    	form.add(rating = new DropDownChoice<Rating>("rating", Arrays.asList(Rating.values())));
    	
    	form.add(title = new TextField<String>("feedbackTitle", new ComponentPropertyModel("title")));
    	
    	form.add(text = new TextArea<String>("text"));
    	
    	Template.addFormLabels(title, rating, text);
    	
    }

    
    
}
