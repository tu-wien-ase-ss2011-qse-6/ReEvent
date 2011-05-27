package reevent.web.adminPanel;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import reevent.web.Template;

@AuthorizeInstantiation("ADMIN")
public class adminPanel extends Template {

	Link editUserLink;
	Link deleteUserLink;
	
	public adminPanel() {
		
		add(editUserLink = new BookmarkablePageLink("editUserLink", editUser.class));
		add(deleteUserLink = new BookmarkablePageLink("deleteUserLink", deleteUser.class));
		
	}
}
