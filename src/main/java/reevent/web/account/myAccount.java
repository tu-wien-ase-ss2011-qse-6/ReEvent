package reevent.web.account;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.StringResourceModel;
import reevent.web.ReEventSession;
import reevent.web.Template;

@AuthorizeInstantiation("USER")
public class myAccount extends Template {
    private BookmarkablePageLink changeAccountLink;
    private BookmarkablePageLink deleteAccountLink;
    ;

	public myAccount(){

        setDefaultModel(ReEventSession.get().getModUserSignedIn());
        StringResourceModel firstNameMessage =
			new StringResourceModel("you.are.logged.in.as.user.firstName", getDefaultModel());
		
		add(new Label("userSignedInLabelFirst", firstNameMessage));
		
		StringResourceModel lastNameMessage =
			new StringResourceModel("you.are.logged.in.as.user.lastName", getDefaultModel());
		
		add(new Label("userSignedInLabelLast", lastNameMessage));
		
		StringResourceModel nickNameMessage =
			new StringResourceModel("you.are.logged.in.as.user.username", getDefaultModel());
		
		add(new Label("userSignedInLabelNick", nickNameMessage));
		
		
		add(changeAccountLink = new BookmarkablePageLink("changeAccountLink", changeAccount.class));
		
	
		add(deleteAccountLink = new BookmarkablePageLink("deleteAccountLink", DeleteAccount.class));
	}
}
