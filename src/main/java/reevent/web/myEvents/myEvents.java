package reevent.web.myEvents;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListView;

import reevent.domain.Event;
import reevent.web.Template;
import reevent.web.myEvents.newEvent.deleteEvent;
import reevent.web.myEvents.newEvent.editEvent;
import reevent.web.myEvents.newEvent.newEvent;
import reevent.web.signinout.SignUpPage;

public class myEvents extends Template{
	
	
	ListView<Event> myEventList;
	Link newEventLink;
	
	public myEvents(){
		
		add(newEventLink = new BookmarkablePageLink("newEventLink", newEvent.class));
		add(newEventLink = new BookmarkablePageLink("editEventLink", editEvent.class));
		add(newEventLink = new BookmarkablePageLink("deleteEventLink", deleteEvent.class));
		
	}

}