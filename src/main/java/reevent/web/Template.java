package reevent.web;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.panel.Panel;
import reevent.web.signinout.SignInPanel;
import reevent.web.signinout.SignOutPanel;

public class Template extends WebPage {
    Panel signInOutPanel;

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
