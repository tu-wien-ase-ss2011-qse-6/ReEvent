package reevent.web.myEvents.newEvent;

import static java.util.Arrays.asList;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;

import reevent.dao.EventDao;
import reevent.domain.Event;
import reevent.domain.User;
import reevent.service.EventService;
import reevent.service.UserService;
import reevent.web.ReEventApplication;
import reevent.web.ReEventSession;
import reevent.web.myEvents.myEvents;

public class newEvent extends myEvents{

	Form<Event> newEventForm;
    
	TextField<String> genre;
    TextField<String> band;
    TextField<String> location;
    TextField<String> name;
    //TODO date
    
    @SpringBean
    EventService events;
    
	public newEvent(){
		
		CompoundPropertyModel<Event> formModel = new CompoundPropertyModel<Event>(new Event());
		
        add(newEventForm = new Form<Event>("newEventForm", formModel) {
        
        	@Override
            protected void onSubmit() {
            	
              // events.create(newEventForm.getModelObject());
               
            }
        });
        
        
        newEventForm.add(name = new TextField<String>("name", formModel.<String>bind("name")));
        
        /*name.add(new AbstractValidator<String>(){

			@Override
			protected void onValidate(IValidatable<String> field) {
				if (!events.isAvailable(field.getValue())) {
                    this.error(field, "username.not.available");
                }
			}
        	
        });*/
        
        newEventForm.add(band = new TextField<String>("band", formModel.<String>bind("band")));
        newEventForm.add(location = new TextField<String>("location", formModel.<String>bind("location")));
        newEventForm.add(genre = new TextField<String>("genre", formModel.<String>bind("genre")));
        
        // required fields
        for (FormComponent fc : asList(name, band, location, genre)) {
            fc.setRequired(true);
            fc.setLabel(new ResourceModel(fc.getId()));
            String labelId = fc.getId() + "Label";
            newEventForm.add(new SimpleFormComponentLabel(labelId, fc));
        }
        
	}
}
