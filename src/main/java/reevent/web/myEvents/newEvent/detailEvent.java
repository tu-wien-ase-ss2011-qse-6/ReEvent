package reevent.web.myEvents.newEvent;

import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import reevent.dao.EventDao;
import reevent.domain.Event;
import reevent.web.myEvents.EventDetails;
import reevent.web.myEvents.myEvents;

public class detailEvent extends myEvents {
	ListView<Event> detailEvent;

    @SpringBean
    EventDao eventDao;
	
	public detailEvent(Event o){
		// TO DO: instead of findAll(0, 1) which returns just the first Event there should be displayed the Event given in the method header?
		add(detailEvent = new ListView<Event>("detailEvent", eventDao.findAll(0, 1)) {
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
                item.add(new EventDetails("details", item.getModel()));
            }
        });
		
	}
	
}
