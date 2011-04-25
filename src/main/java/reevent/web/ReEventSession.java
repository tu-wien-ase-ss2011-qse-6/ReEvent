package reevent.web;

import org.apache.commons.lang.Validate;
import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;
import reevent.domain.User;
import reevent.domain.UserRole;
import reevent.service.UserService;

public class ReEventSession extends AuthenticatedWebSession {
    /**
     * Profile of the currently signed in user.
     */
    User userSignedIn;

    @SpringBean
    UserService userService;

    {
        Injector.get().inject(this);
    }

    public static ReEventSession get() {
        return (ReEventSession) Session.get();
    }

    public ReEventSession(Request request) {
        super(request);
    }

    @Override
    public Roles getRoles() {
        if (!isSignedIn()) {
            // guest access
            return new Roles();
        } else {
            Validate.notNull(userSignedIn);

            Roles roles = new Roles();
            for (UserRole role : userSignedIn.getRoles()) {
                roles.add(role.name());
            }
            return roles;
        }
    }

    @Override
    public boolean authenticate(String username, String password) {
        userSignedIn = userService.authenticate(username, password);
        return userSignedIn != null;
    }

    @Override
    public void signOut() {
        userSignedIn = null;
        super.signOut();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
