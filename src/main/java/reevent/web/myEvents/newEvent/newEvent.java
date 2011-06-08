
package reevent.web.myEvents.newEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;
import org.apache.wicket.validation.validator.UrlValidator;
import reevent.domain.Event;
import reevent.domain.User;
import reevent.domain.media.MediaBase;
import reevent.service.EventService;
import reevent.service.MediaService;
import reevent.service.MediaUploadException;
import reevent.web.HomePage;
import reevent.web.ReEventSession;
import reevent.web.myEvents.myEvents;

import java.util.List;

import static java.util.Arrays.asList;
import static reevent.util.WicketErrorUtil.getError;

@AuthorizeInstantiation("USER")
public class newEvent extends myEvents{

    Form<Event> newEventForm;

    TextField<String> genre;
    TextField<String> performer;
    TextField<String> name;
    TextField<String> start;
    TextField<String> locationName;
    TextField<String> locationAddress;

    TextField<String> pictureUrl;
    FileUploadField pictureFile;

    List<Event> myEvents;

    @SpringBean
    EventService events;
    Event event;

    @SpringBean
    MediaService mediaService;

    Link newLocationLink;

    public newEvent(){

        CompoundPropertyModel<Event> formModel = new CompoundPropertyModel<Event>(new Event());

        add(newEventForm = new Form<Event>("newEventForm", formModel) {
            @Override
            protected void onSubmit() {

                event = newEventForm.getModelObject();
                User user = ReEventSession.userSignedInModel.getObject();

                MediaBase mainPicture = null;
                try {
                    String url = pictureUrl.getModelObject();
                    FileUpload file = pictureFile.getFileUpload();
                    if (!StringUtils.isBlank(url)) {
                        mainPicture = mediaService.create(url, user);
                    } else if (file != null) {
                        mainPicture = mediaService.create(file, user);
                    }
                } catch (MediaUploadException e) {
                    error(getError(this, "error", e));
                }
                event.setMainPicture(mainPicture);
                event.setCreatedBy(user);
                events.create(event);

                HomePage page = new HomePage();
                page.info(getString("success"));
                setResponsePage(page);

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

        newEventForm.add(pictureUrl = new TextField<String>("pictureUrl"));
        pictureUrl.add(new UrlValidator());

        newEventForm.add(pictureFile = new FileUploadField("pictureFile"));

        // required fields
        List<TextField<String>> fields = asList(name, performer,start, genre, locationName, locationAddress);
        addFormLabels(fields);

        for (FormComponent fc : fields) {
            fc.setRequired(true);
        }

    }
}
