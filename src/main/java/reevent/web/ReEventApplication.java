package reevent.web;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.util.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReEventApplication extends AuthenticatedWebApplication {
    private Logger _log = LoggerFactory.getLogger(this.getClass());
    private Logger log() {
        return _log;
    }
    
    @Override
    protected void init() {
        log().debug(String.format("init()"));
        super.init();
        if (this.getConfigurationType() == RuntimeConfigurationType.DEVELOPMENT) {
            this.getResourceSettings().setResourcePollFrequency(Duration.ONE_SECOND);
        }
        this.getMarkupSettings().setStripComments(true);
        this.getMarkupSettings().setStripWicketTags(true);

        this.getComponentInstantiationListeners().add(new SpringComponentInjector(this));
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
        //todo maybe do a dedicated sign in page --dV
        return HomePage.class;
    }

    @Override
    public Class<? extends Page> getHomePage() {
        return HomePage.class;
    }
}
