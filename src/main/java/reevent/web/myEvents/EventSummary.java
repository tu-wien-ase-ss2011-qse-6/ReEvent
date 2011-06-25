package reevent.web.myEvents;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.convert.IConverter;
import reevent.domain.Event;
import reevent.domain.media.MediaBase;
import reevent.web.StyledPanel;
import reevent.web.convert.DateTimeConverter;
import reevent.web.media.MediaDisplay;
import reevent.web.myEvents.newEvent.detailEvent;
import reevent.web.myEvents.newEvent.genreEvent;

import java.text.DateFormat;

/**
 * A block that shows the main picture and basic data about an event.
 */
public class EventSummary extends StyledPanel {
    MediaDisplay mainPicture;
    Label name;
    Label locationName;
    Label start;
    Label genre;
    
    Link detailEvent;
    Link genreEvent;

    public EventSummary(String id, final IModel<Event> event) {
        super(id, new CompoundPropertyModel<Event>(event));
        MediaBase media = event.getObject().getMainPicture();
        this.add(mainPicture = new MediaDisplay("mainPicture", media == null ? null : media.getId()));
        this.add(new Label("name"));
        this.add(new Label("locationName"));
        this.add(new Label("performer"));
        this.add(new Label("start") {
            @SuppressWarnings("unchecked")
            @Override
            public IConverter getConverter(Class type) {
                return DateTimeConverter.both(DateFormat.SHORT, DateFormat.SHORT);
            }
        });
        this.add(genreEvent = new Link("genreEvent", new Model()){
        	public void onClick() {
        		
                setResponsePage(new genreEvent(event.getObject().getGenre()));
        		
        	}
        });
        
        genreEvent.setBody(new PropertyModel<Event>(event, "genre"));
        
        this.add(detailEvent = new Link("detailEvent", new Model()){
        	public void onClick() {
        		
                setResponsePage(new detailEvent(event.getObject()));
        		
        	}
        });
    }
}
