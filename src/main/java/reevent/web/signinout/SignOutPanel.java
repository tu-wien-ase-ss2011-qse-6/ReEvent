package reevent.web.signinout;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.StringResourceModel;
import reevent.web.ReEventSession;

public class SignOutPanel extends Panel {
    Label userSignedInLabel;
    Form<?> signOutForm;

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
    }

}
