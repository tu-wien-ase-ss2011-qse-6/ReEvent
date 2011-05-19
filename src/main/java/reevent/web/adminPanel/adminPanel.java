package reevent.web.adminPanel;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;

import reevent.web.Template;
import reevent.web.account.myAccount;
import reevent.web.myEvents.newEvent.deleteEvent;
import reevent.web.myEvents.newEvent.editEvent;
import reevent.web.myEvents.newEvent.newEvent;

public class adminPanel extends Template{

	Link editUserLink;
	Link deleteUserLink;
	
	public adminPanel() {
		
		add(editUserLink = new BookmarkablePageLink("editUserLink", editUser.class));
		add(deleteUserLink = new BookmarkablePageLink("deleteUserLink", deleteUser.class));
		
	}
}
