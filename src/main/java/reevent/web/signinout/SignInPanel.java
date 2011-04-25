package reevent.web.signinout;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import reevent.web.ReEventSession;

public class SignInPanel extends Panel {
    FeedbackPanel feedback;

    Form<?> signInForm;
    TextField<String> username;
    TextField<String> password;

    public SignInPanel(String id) {
        super(id);
        final SignInPanel self = this;

        add(feedback = new ComponentFeedbackPanel("feedback", this));

        add(signInForm = new Form("signInForm") {
            @Override
            protected void onSubmit() {
                super.onSubmit();
                ReEventSession session = ReEventSession.get();
                if (!session.signIn(
                        username.getModelObject(),
                        password.getModelObject())) {
                    self.error(self.getString("invalid.username.or.password"));
                }
            }
        });
        signInForm.add(username = new TextField<String>("username", new Model<String>(), String.class));
        username.setRequired(true);

        signInForm.add(password = new PasswordTextField("password", new Model<String>()));
        password.setRequired(true);
    }
}
