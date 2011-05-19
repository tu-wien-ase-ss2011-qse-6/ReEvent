package reevent.web.account;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;

import reevent.web.ReEventApplication;
import reevent.web.ReEventSession;
import reevent.web.Template;

public class DeleteAccount extends Template {

	public DeleteAccount(){

		Form deleteForm = new Form("deleteForm");

		Button yesButton = new Button("yes") {
			public void onSubmit() {
				ReEventSession.get().signOut();
				setResponsePage(ReEventApplication.get().getHomePage());	
			}
		};
		deleteForm.add(yesButton);

		Button noButton = new Button("no") {
			public void onSubmit() {
				setResponsePage(ReEventApplication.get().getAccount());
			}
		};
		noButton.setDefaultFormProcessing(false);
		deleteForm.add(noButton);

		add(deleteForm);
	}
}
