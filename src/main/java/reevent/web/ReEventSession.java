package reevent.web;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

public class ReEventSession extends AuthenticatedWebSession {
    public static ReEventSession get() {
        return (ReEventSession) Session.get();
    }

    public ReEventSession(Request request) {
        super(request);
    }

    @Override
    public Roles getRoles() {
        throw new UnsupportedOperationException(); // todo implement -dV
    }
}
