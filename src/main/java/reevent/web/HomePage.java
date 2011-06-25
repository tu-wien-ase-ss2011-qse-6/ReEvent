package reevent.web;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import reevent.dao.EventDao;
import reevent.domain.Event;
import reevent.domain.Location;
import reevent.web.myEvents.EventSummary;

public class HomePage extends Template {
    ListView<Event> upcomingEventList;

    @SpringBean
    EventDao eventDao;

    WebMarkupContainer nearMe;

    Form<Location> nearMeForm;
    HiddenField<Double> latitude;
    HiddenField<Double> longitude;

    WebMarkupContainer everywhere;
    Link everywhereLink;

    public HomePage() {
        ReEventSession session = ReEventSession.get();

        add(upcomingEventList = new ListView<Event>("upcomingEventList", eventDao.findUpcoming(session.getLocation(), 0, 50)) {
            @Override
            protected void populateItem(ListItem<Event> item) {
                item.add(new EventSummary("summary", item.getModel()));
            }
        });

        add(nearMe = new WebMarkupContainer("nearMe"));
        add(everywhere = new WebMarkupContainer("everywhere"));

        nearMe.setVisible(session.getLocation() == null);
        everywhere.setVisible(session.getLocation() != null);

        everywhere.add(everywhereLink = new Link("everywhereLink") {
            @Override
            public void onClick() {
                ReEventSession.get().setLocation(null);
                HomePage.this.setResponsePage(HomePage.class);
            }
        });


        nearMe.add(nearMeForm = new Form<Location>("nearMeForm", new CompoundPropertyModel<Location>(new Location())) {
            @Override
            protected void onSubmit() {
                ReEventSession.get().setLocation(getModelObject());
                HomePage.this.setResponsePage(HomePage.class);
            }
        });

        nearMeForm.add(latitude = new HiddenField<Double>("latitude", Double.class));
        nearMeForm.add(longitude = new HiddenField<Double>("longitude", Double.class));


    }


}
