package reevent.web;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;
import reevent.dao.EventDao;
import reevent.domain.Event;
import reevent.web.myEvents.EventSummary;

public class HomePage extends Template {
    ListView<Event> upcomingEventList;

    @SpringBean
    EventDao eventDao;

    public HomePage() {
        add(upcomingEventList = new ListView<Event>("upcomingEventList", eventDao.findAll(0, 10)) {
            @Override
            protected void populateItem(ListItem<Event> item) {
                /*
                item.add(new Label("eventName", new PropertyModel(item.getModel(), "name")));
                item.add(new Label("eventStart", new PropertyModel(item.getModel(), "start")) {
                    @Override
                    public <C> IConverter<C> getConverter(Class<C> type) {
                        if (!Date.class.isAssignableFrom(type)) {
                            throw new IllegalArgumentException("Can't convert from types other than Date");
                        }
                        return (IConverter) DateTimeConverter.both(DateFormat.SHORT, DateFormat.SHORT);
                    }
                });
                item.add(new Label("eventGenre", new PropertyModel(item.getModel(), "genre")));
                item.add(new Label("locationName", new PropertyModel(item.getModel(), "locationName")));
                */
                item.add(new EventSummary("summary", item.getModel()));
            }
        });
        

    }


}
