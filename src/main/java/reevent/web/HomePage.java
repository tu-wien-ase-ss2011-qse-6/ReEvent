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
        add(upcomingEventList = new ListView<Event>("upcomingEventList", eventDao.findAll(0, 50)) {
            @Override
            protected void populateItem(ListItem<Event> item) {
                item.add(new EventSummary("summary", item.getModel()));
            }
        });
        

    }


}
