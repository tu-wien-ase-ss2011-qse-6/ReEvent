
package reevent.web.myEvents.newEvent;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;
import reevent.domain.Event;
import reevent.service.EventService;
import reevent.web.ReEventSession;
import reevent.web.myEvents.myEvents;
import reevent.web.myEvents.newLocation.newLocation;

import java.util.List;

import static java.util.Arrays.asList;

@AuthorizeInstantiation("USER")
public class newEvent extends myEvents{

	Form<Event> newEventForm;
    
	TextField<String> genre;
    TextField<String> performer;
    TextField<String> name;
    TextField<String> start;
    TextField<String> locationName;
    TextField<String> locationAddress;

    List<Event> myEvents;
    @SpringBean
    EventService events;
    Event event;
    
    Link newLocationLink;
    
	public newEvent(){
		
		CompoundPropertyModel<Event> formModel = new CompoundPropertyModel<Event>(new Event());
		
        add(newEventForm = new Form<Event>("newEventForm", formModel) {
        
        	@Override
            protected void onSubmit() {

        		event = newEventForm.getModelObject();
                event.setCreatedBy(ReEventSession.userSignedInModel.getObject());
                
                events.create(event);
               
            }
        });
         
        newEventForm.add(name = new TextField<String>("name", formModel.<String>bind("name")));
        
        newEventForm.add(start = new TextField("start", formModel.<String>bind("start")));
        
        name.add(new AbstractValidator<String>(){

			@Override
			protected void onValidate(IValidatable<String> field) {
				if (!events.isAvailable(field.getValue())) {
                    this.error(field, "event.not.available");
                }
			}
        	
        });
        
        newEventForm.add(performer = new TextField<String>("performer", formModel.<String>bind("performer")));
        
        newEventForm.add(genre = new TextField<String>("genre", formModel.<String>bind("genre")));
        
        newEventForm.add(locationName = new TextField<String>("locationName", formModel.<String>bind("locationName")));
        
        newEventForm.add(locationAddress = new TextField<String>("locationAddress", formModel.<String>bind("locationAddress")));
        
        // required fields
        List<TextField<String>> fields = asList(name, performer,start, genre, locationName, locationAddress);
        addLabels(fields);
       
        for (FormComponent fc : fields) {
            fc.setRequired(true);
        }
        
	}
}
