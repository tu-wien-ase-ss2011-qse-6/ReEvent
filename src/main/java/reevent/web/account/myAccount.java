package reevent.web.account;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.StringResourceModel;
import reevent.web.ReEventSession;
import reevent.web.Template;


public class myAccount extends Template {;

	public myAccount(){

		StringResourceModel firstNameMessage =
			new StringResourceModel("you.are.logged.in.as.user.firstName", ReEventSession.userSignedInModel);
		
		add(new Label("userSignedInLabelFirst", firstNameMessage));
		
		StringResourceModel lastNameMessage =
			new StringResourceModel("you.are.logged.in.as.user.lastName", ReEventSession.userSignedInModel);
		
		add(new Label("userSignedInLabelLast", lastNameMessage));
	}
}
