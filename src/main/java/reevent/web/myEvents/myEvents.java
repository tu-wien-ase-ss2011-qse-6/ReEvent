package reevent.web.myEvents;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListView;
import reevent.domain.Event;
import reevent.web.Template;
import reevent.web.myEvents.newEvent.viewEvent;
import reevent.web.myEvents.newEvent.editEvent;
import reevent.web.myEvents.newEvent.newEvent;

@AuthorizeInstantiation("USER")
public class myEvents extends Template{
	
	
	ListView<Event> myEventList;
	Link newEventLink;
	Link viewEventLink;
	Link editEventLink;
	Link deleteEventLink;
	
	public myEvents(){
		
		add(newEventLink = new BookmarkablePageLink("newEventLink", newEvent.class));
		add(viewEventLink = new BookmarkablePageLink("viewEventLink", viewEvent.class));
		add(editEventLink = new BookmarkablePageLink("editEventLink", editEvent.class));
		
	}

}