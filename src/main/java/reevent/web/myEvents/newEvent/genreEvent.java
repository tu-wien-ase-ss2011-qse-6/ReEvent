package reevent.web.myEvents.newEvent;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.convert.IConverter;

import reevent.dao.EventDao;
import reevent.domain.Event;
import reevent.domain.User;
import reevent.service.EventService;
import reevent.web.ReEventSession;
import reevent.web.Template;
import reevent.web.convert.DateTimeConverter;
import reevent.web.myEvents.myEvents;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class genreEvent extends Template {

	ListView<Event> myEventList;
	
	private static final List GENRES = Arrays.asList(new String[] {"Concerts and Tour Dates", "Conferences and Tradeshows", "Education", "Kids and Family", "Festivals", "Film", "Food and Wine", "Fundraising and Charity", "Art Galleries and Exhibits", "Health and Wellness", "Literary and Books", "Museums and Attractions", "Neighborhood", "Business and Networking", "Nightlife and Singles", "University and Alumni", "Organizations and Meetups", "Outdoors and Recreation", "Performing Arts", "Pets", "Politics and Activism", "Sales and Retail", "Science", "Religion and Spirituality", "Sports", "Technology", "Other and Miscellaneous" });
	DropDownChoice<String> genre;
	
	Link detailEvent;
	
	@SpringBean
	EventService events;
	
	@SpringBean
	EventDao eventDao;
	
	
	public genreEvent(String eventGenre){
		
	add(new Label("genreName", eventGenre));
	add(genre = new DropDownChoice<String>("genre", new Model(eventGenre), GENRES) {
		@Override
		protected boolean wantOnSelectionChangedNotifications() {
			return true;
		};
		protected void onSelectionChanged(String newSelection) {
			setResponsePage(new genreEvent(newSelection));
		};
		
	});
   
    add(myEventList = new ListView<Event>("myEventList", eventDao.queryByEventgenre(eventGenre)) {
        @Override
        protected void populateItem(final ListItem<Event> item) {
        	
            item.add(new Label("eventName", new PropertyModel(item.getModel(), "name")));
            
            item.add(detailEvent = new Link("detailEvent"){
            	public void onClick() {
            		
            		Event obj = (Event) item.getModelObject();
                    setResponsePage(new detailEvent(obj));
            		
            	}
            });
            
            item.add(new Label("eventStart", new PropertyModel(item.getModel(), "start")) {
                @Override
                public <C> IConverter<C> getConverter(Class<C> type) {
                    if (!Date.class.isAssignableFrom(type)) {
                        throw new IllegalArgumentException("Can't convert from types other than Date");
                    }
                    return (IConverter<C>) DateTimeConverter.both(DateFormat.SHORT, DateFormat.SHORT);
                }
            });
            
            item.add(new Label("eventPerformer", new PropertyModel(item.getModel(), "performer")));
            item.add(new Label("eventLocationName", new PropertyModel(item.getModel(), "locationName")));
            
        }
    });
		
	}
	
	
}
