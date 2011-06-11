package reevent.web;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.SimpleFormComponentLabel;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.ResourceModel;
import reevent.web.signinout.SignInPanel;
import reevent.web.signinout.SignOutPanel;

import java.util.Arrays;

public class Template extends WebPage {
    FeedbackPanel feedback;
    Panel signInOutPanel;

    public static void addFormLabels(Iterable<? extends FormComponent<?>> fields) {
        for (FormComponent field : fields) {
            field.setLabel(new ResourceModel(field.getId()));
            String labelId = field.getId() + "Label";
            field.getParent().add(new SimpleFormComponentLabel(labelId, field));
        }
    }

    public static void addFormLabels(FormComponent<?>... fields) {
        addFormLabels(Arrays.asList(fields));
    }

    {
        add(feedback = new FeedbackPanel("feedback"));

        // Accept all messages not in sidebars.
        feedback.setFilter(new IFeedbackMessageFilter() {
            @Override
            public boolean accept(FeedbackMessage message) {
                Component reporter = message.getReporter();
                if (reporter == null) return true;
                
                // Reject feedback messages from sign in / sign out panel
                if (reporter == signInOutPanel) return false;
                return !signInOutPanel.contains(reporter, true);
            }
        });
    }
    @Override
    protected void onBeforeRender() {
        super.onBeforeRender();
        ReEventSession session = ReEventSession.get();
        if (session.isSignedIn() && !(signInOutPanel instanceof SignOutPanel)) {
            // logged in and no logout panel
            this.addOrReplace(signInOutPanel = new SignOutPanel("signInOutPanel"));
        } else if (!session.isSignedIn() && !(signInOutPanel instanceof SignInPanel)) {
            // logged out and no login panel
            this.addOrReplace(signInOutPanel = new SignInPanel("signInOutPanel"));
        }
    }
}
