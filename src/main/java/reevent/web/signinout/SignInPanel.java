package reevent.web.signinout;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import reevent.web.HomePage;
import reevent.web.ReEventSession;

public class SignInPanel extends Panel {
    FeedbackPanel feedback;

    Form<?> signInForm;
    TextField<String> username;
    TextField<String> password;

    Link signUpLink;

    public SignInPanel(String id) {
        super(id);
        final SignInPanel self = this;

        add(feedback = new ComponentFeedbackPanel("feedback", this));

        add(signInForm = new Form("signInForm") {
            @Override
            protected void onSubmit() {
                // handle sign in
                super.onSubmit();
                ReEventSession session = ReEventSession.get();
                if (!session.signIn(
                        username.getModelObject(),
                        password.getModelObject())) {
                    self.error(self.getString("invalid.username.or.password"));
                } else {
//                    if (getPage() instanceof SignUpPage) {
                        RequestCycle.get().setResponsePage(HomePage.class);
//                    }
                }
            }
        });
        signInForm.add(username = new TextField<String>("username", new Model<String>(), String.class));
        username.setRequired(true);

        signInForm.add(password = new PasswordTextField("password", new Model<String>()));
        password.setRequired(true);

        add(signUpLink = new BookmarkablePageLink("signUpLink", SignUpPage.class));
    }
}
