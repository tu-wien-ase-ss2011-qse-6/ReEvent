
package reevent.web.myEvents.newEvent;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.convert.IConverter;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;
import org.apache.wicket.validation.validator.UrlValidator;
import reevent.dao.EventDao;
import reevent.domain.Event;
import reevent.domain.User;
import reevent.domain.media.MediaBase;
import reevent.service.EventService;
import reevent.service.MediaService;
import reevent.service.MediaUploadException;
import reevent.web.HomePage;
import reevent.web.ReEventSession;
import reevent.web.convert.DateTimeConverter;
import reevent.web.myEvents.myEvents;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.util.Arrays.asList;
import static reevent.util.WicketErrorUtil.getError;

@AuthorizeInstantiation("USER")
public class newEvent extends myEvents {
	
	private static final List GENRES = Arrays.asList(new String[] {"Concerts and Tour Dates", "Conferences and Tradeshows", "Education", "Kids and Family", "Festivals", "Film", "Food and Wine", "Fundraising and Charity", "Art Galleries and Exhibits", "Health and Wellness", "Literary and Books", "Museums and Attractions", "Neighborhood", "Business and Networking", "Nightlife and Singles", "University and Alumni", "Organizations and Meetups", "Outdoors and Recreation", "Performing Arts", "Pets", "Politics and Activism", "Sales and Retail", "Science", "Religion and Spirituality", "Sports", "Technology", "Other and Miscellaneous" });

    Form<Event> newEventForm;

    DropDownChoice<String> genre;
    TextField<String> performer;
    TextField<String> name;
    TextField<Date> start;
    TextField<String> locationName;
    TextField<String> locationAddress;
    
    // hidden fields to store the location of the marker on the map
    HiddenField<String> latitude;
    HiddenField<String> longitude;

    TextField<String> pictureUrl;
    FileUploadField pictureFile;

    List<Event> myEvents;

    @SpringBean
    EventService eventService;

    @SpringBean
    EventDao eventDao;

    Event event;

    @SpringBean
    MediaService mediaService;

    Link newLocationLink;

    boolean update = true   ;

    public newEvent(){
        this(Model.<Event>of(new Event()));
        update = false;
    }
    public newEvent(IModel<Event> model) {
        setDefaultModel(model);
        CompoundPropertyModel<Event> formModel = new CompoundPropertyModel<Event>(model);

        add(newEventForm = new Form<Event>("newEventForm", formModel) {
                    @Override
                    protected void onSubmit() {

                        Event event = newEventForm.getModelObject();
                        User user = ReEventSession.get().getModUserSignedIn().getObject();

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

                        if (update) {
                            eventDao.update(event);
                        } else {
                            event.setCreatedBy(user);
                            eventService.create(event);
                        }

                        ReEventSession.get().info(getString("success"));

                        onEventSaved();
                    }
                });

                newEventForm.add(name = new TextField<String>("name"));

                newEventForm.add(start = new TextField<Date>("start", Date.class) {
                    @Override
                    public IConverter getConverter(Class type) {
                        return DateTimeConverter.both(DateFormat.MEDIUM, DateFormat.SHORT);
                    }
                });

                name.add(new AbstractValidator<String>(){

                    @Override
                    protected void onValidate(IValidatable<String> field) {
                        if (!eventService.isAvailable(field.getValue())) {
                            this.error(field, "event.not.available");
                        }
                    }

                });

                newEventForm.add(performer = new TextField<String>("performer"));

                newEventForm.add(genre = new DropDownChoice<String>("genre", GENRES));

                newEventForm.add(locationName = new TextField<String>("locationName"));

                newEventForm.add(locationAddress = new TextField<String>("locationAddress"));

                // add the location hidden fields to the form
                newEventForm.add(latitude = new HiddenField<String>("latitude"));
                newEventForm.add(longitude = new HiddenField<String>("longitude"));

                newEventForm.add(pictureUrl = new TextField<String>("pictureUrl", new Model()));
                pictureUrl.add(new UrlValidator());

                newEventForm.add(pictureFile = new FileUploadField("pictureFile", new Model()));


                // required fields
                List<? extends FormComponent<?>> fields = asList(
                        name, performer, start, genre,
                        locationName, locationAddress);
                addFormLabels(pictureUrl,  pictureFile); // add nonrequired labels

                addFormLabels(fields);

                for (FormComponent fc : fields) {
                    fc.setRequired(true);
                }

    }

    protected void onEventSaved() {
        setResponsePage(new HomePage());
    }
}
