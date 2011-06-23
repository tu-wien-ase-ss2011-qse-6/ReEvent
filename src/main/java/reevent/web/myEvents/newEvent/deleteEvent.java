package reevent.web.myEvents.newEvent;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.hibernate.event.RefreshEvent;
import org.springframework.beans.factory.annotation.Autowired;

import reevent.dao.EventDao;
import reevent.domain.Event;
import reevent.web.ReEventApplication;
import reevent.web.ReEventSession;
import reevent.web.myEvents.myEvents;

@AuthorizeInstantiation("USER")
public class deleteEvent extends myEvents {

	//@Autowired
    //EventDao eventDao;
	
	public deleteEvent(Event o){
		

		Form deleteForm = new Form("deleteForm");

		Button yesButton = new Button("yes") {
			public void onSubmit() {
				// TO DO: DELETE EVENT HERE!
				//eventDao.delete(o.getId());
				
				setResponsePage(ReEventApplication.get().getEditEvent());	
			}
		};
		deleteForm.add(yesButton);

		Button noButton = new Button("no") {
			public void onSubmit() {
				setResponsePage(ReEventApplication.get().getEditEvent());
			}
		};
		noButton.setDefaultFormProcessing(false);
		deleteForm.add(noButton);

		add(deleteForm);
	}
		
		
}
	

