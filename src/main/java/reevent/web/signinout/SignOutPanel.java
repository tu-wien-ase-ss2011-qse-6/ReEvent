package reevent.web.signinout;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.StringResourceModel;
import reevent.web.ReEventSession;
import reevent.web.myAccount;
import reevent.web.myEvents;
// todo uncomment after commiting file
//import reevent.web.myLocations;

public class SignOutPanel extends Panel {
    Label userSignedInLabel;
    Form<?> signOutForm;
    Link myAccount;
    Link myEvents;
    Link myLocations;

    public SignOutPanel(String id) {
        super(id);
        AbstractReadOnlyModel<String> realNameModel = new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                return ReEventSession.get().getUserSignedIn().getRealName();
            }
        };
        StringResourceModel realNameMessage =
                new StringResourceModel("you.are.logged.in.as.user", realNameModel);
        add(userSignedInLabel = new Label("userSignedInLabel", realNameMessage));
        
        add(signOutForm = new Form("signOutForm"){
            @Override
            protected void onSubmit() {
                ReEventSession.get().signOut();
            }
        });
        
        add(myAccount = new BookmarkablePageLink("myAccount", myAccount.class));
        
        add(myEvents = new BookmarkablePageLink("myEvents", myEvents.class));
         
    }

}
