package reevent.web.myEvents.newLocation;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import reevent.domain.Location;
import reevent.web.Template;

import java.util.List;

import static java.util.Arrays.asList;

public class newLocation extends Template {

    Form<Location> newLocationForm;

    TextField<String> name;
    TextField<String> address;
    //TextField<String> type;
    TextField<String> stage;
    //TextField<String> coords;


    public newLocation() {

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
        List<TextField<String>> fields = asList(name, address, stage);
        addLabels(fields);

        for (FormComponent fc : fields) {
            fc.setRequired(true);
        }
    }

}
