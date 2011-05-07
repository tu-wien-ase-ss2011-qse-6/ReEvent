package reevent.web.account;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.StringResourceModel;
import reevent.web.ReEventSession;
import reevent.web.Template;


public class myAccount extends Template {;

	public myAccount(){
		
		AbstractReadOnlyModel<String> realNameModel = new AbstractReadOnlyModel<String>() {
			@Override
			public String getObject() {
				return ReEventSession.get().getUserSignedIn().getRealName();
			}
		};
		StringResourceModel realNameMessage =
			new StringResourceModel("you.are.logged.in.as.user", realNameModel);
		
		add(new Label("userSignedInLabel", realNameMessage));
	}
}
