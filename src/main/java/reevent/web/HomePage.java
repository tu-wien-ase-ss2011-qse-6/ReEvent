package reevent.web;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.convert.IConverter;
import reevent.dao.EventDao;
import reevent.domain.Event;
import reevent.web.convert.DateTimeConverter;

import java.text.DateFormat;
import java.util.Date;

public class HomePage extends Template {
    BookmarkablePageLink<?> signInLink;
    ListView<Event> upcomingEventList;

    @SpringBean
    EventDao eventDao;

    public HomePage() {
        signInLink = new BookmarkablePageLink<Object>("signInLink", FacebookConnectPage.class);
        add(signInLink);

        add(upcomingEventList = new ListView<Event>("upcomingEventList", eventDao.findAll(0, 10)) {
            @Override
            protected void populateItem(ListItem<Event> item) {
                item.add(new Label("eventName", new PropertyModel(item.getModel(), "name")));
                item.add(new Label("eventStart", new PropertyModel(item.getModel(), "start")) {
                    @Override
                    public <C> IConverter<C> getConverter(Class<C> type) {
                        if (!Date.class.isAssignableFrom(type)) {
                            throw new IllegalArgumentException("Can't convert from types other than Date");
                        }
                        return (IConverter<C>) DateTimeConverter.both(DateFormat.SHORT, DateFormat.SHORT);
                    }
                });
            }
        });
    }


}
