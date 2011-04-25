package reevent.web.signinout;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class SignOutPanel extends Panel {
    public SignOutPanel(String id) {
        super(id);
    }

    public SignOutPanel(String id, IModel<?> model) {
        super(id, model);
    }
}
