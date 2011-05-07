package reevent.web.account;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.StringResourceModel;
import reevent.web.ReEventSession;
import reevent.web.Template;


public class myAccount extends Template {;

	public myAccount(){
		
		AbstractReadOnlyModel<String> firstNameModel = new AbstractReadOnlyModel<String>() {
			@Override
			public String getObject() {
				return ReEventSession.get().getUserSignedIn().getFirstName();
			}
		};
		StringResourceModel firstNameMessage =
			new StringResourceModel("you.are.logged.in.as.user.firstName", firstNameModel);
		
		add(new Label("userSignedInLabelFirst", firstNameMessage));
		
		AbstractReadOnlyModel<String> lastNameModel = new AbstractReadOnlyModel<String>() {
			@Override
			public String getObject() {
				return ReEventSession.get().getUserSignedIn().getLastName();
			}
		};
		StringResourceModel lastNameMessage =
			new StringResourceModel("you.are.logged.in.as.user.lastName", lastNameModel);
		
		add(new Label("userSignedInLabelLast", lastNameMessage));
	}
}
