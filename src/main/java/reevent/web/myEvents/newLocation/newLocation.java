package reevent.web.myEvents.newLocation;

import static java.util.Arrays.asList;

import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;

import reevent.domain.Location;
import reevent.web.Template;

public class newLocation extends Template {

	Form<Location> newLocationForm;
    
    TextField<String> name;
	TextField<String> address;
    //TextField<String> type;
    TextField<String> stage;
    //TextField<String> coords;
    
    
	public newLocation(){
		
		CompoundPropertyModel<Location> formModel = new CompoundPropertyModel<Location>(new Location());
		
        add(newLocationForm = new Form<Location>("newLocationForm", formModel) {
        
        	@Override
            protected void onSubmit() {
            	
              // events.create(newLocationForm.getModelObject());
               
            }
        });
        
        
        newLocationForm.add(name = new TextField<String>("name", formModel.<String>bind("name")));
        newLocationForm.add(address = new TextField<String>("address", formModel.<String>bind("address")));
        //newLocationForm.add(type = new TextField<String>("type", formModel.<String>bind("type")));
        newLocationForm.add(stage = new TextField<String>("stage", formModel.<String>bind("stage")));
        //newLocationForm.add(coords = new TextField<String>("coords", formModel.<String>bind("coords")));
        
        // required fields
        for (FormComponent fc : asList(name, address, stage)) {
            fc.setRequired(true);
            fc.setLabel(new ResourceModel(fc.getId()));
            String labelId = fc.getId() + "Label";
            newLocationForm.add(new SimpleFormComponentLabel(labelId, fc));
        }
        
	}
}
