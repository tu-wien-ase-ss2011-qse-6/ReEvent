package reevent.web;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.util.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReEventApplication extends AuthenticatedWebApplication {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Override
    protected void init() {
        super.init();
        if (this.getConfigurationType() == RuntimeConfigurationType.DEVELOPMENT) {
            this.getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);
        }
        this.getMarkupSettings().setStripComments(true);
        this.getMarkupSettings().setStripWicketTags(true);
    }

    public static ReEventApplication get() {
        return (ReEventApplication) Application.get();
    }

    @Override
    protected Class<? extends AuthenticatedWebSession> getWebSessionClass() {
        return ReEventSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return FacebookConnectPage.class;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }
}
