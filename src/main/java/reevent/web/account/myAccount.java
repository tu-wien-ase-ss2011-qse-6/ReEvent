package reevent.web.account;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.StringResourceModel;

import reevent.web.ReEventApplication;
import reevent.web.ReEventSession;
import reevent.web.Template;
import reevent.web.signinout.SignUpPage;


public class myAccount extends Template {;

	public myAccount(){
		
		BookmarkablePageLink changeAccountLink;

		StringResourceModel firstNameMessage =
			new StringResourceModel("you.are.logged.in.as.user.firstName", ReEventSession.userSignedInModel);
		
		add(new Label("userSignedInLabelFirst", firstNameMessage));
		
		StringResourceModel lastNameMessage =
			new StringResourceModel("you.are.logged.in.as.user.lastName", ReEventSession.userSignedInModel);
		
		add(new Label("userSignedInLabelLast", lastNameMessage));
		
		StringResourceModel nickNameMessage =
			new StringResourceModel("you.are.logged.in.as.user.username", ReEventSession.userSignedInModel);
		
		add(new Label("userSignedInLabelNick", nickNameMessage));
		
		
		add(changeAccountLink = new BookmarkablePageLink("changeAccountLink", changeAccount.class));
	}
}
