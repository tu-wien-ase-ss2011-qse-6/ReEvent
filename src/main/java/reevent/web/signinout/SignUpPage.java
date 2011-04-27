package reevent.web.signinout;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;
import reevent.domain.User;
import reevent.service.UserService;
import reevent.web.ReEventApplication;
import reevent.web.ReEventSession;
import reevent.web.Template;

import static java.util.Arrays.asList;

public class SignUpPage extends Template {
    Form<User> newUserForm;
    TextField<String> username;
    TextField<String> password;
    TextField<String> passwordVerify;
    TextField<String> realName;

    @SpringBean
    UserService users;

    public SignUpPage() {
        CompoundPropertyModel<User> formModel = new CompoundPropertyModel<User>(new User());
        add(newUserForm = new Form<User>("newUserForm", formModel) {
            @Override
            protected void onSubmit() {
                users.register(newUserForm.getModelObject(), password.getModelObject());
                ReEventSession.get().signIn(username.getModelObject(), password.getModelObject());
                setResponsePage(ReEventApplication.get().getHomePage());
            }
        });

        newUserForm.add(username = new TextField<String>("username", formModel.<String>bind("username"), String.class));
        username.add(new AbstractValidator<String>() {
            @Override
            protected void onValidate(IValidatable<String> field) {
                if (!users.isAvailable(field.getValue())) {
                    this.error(field, "username.not.available");
                }
            }
        });

        newUserForm.add(password = new PasswordTextField("password", Model.<String>of()));

        newUserForm.add(passwordVerify = new PasswordTextField("passwordVerify", Model.<String>of()));
        passwordVerify.add(new AbstractValidator<String>() {
            @Override
            protected void onValidate(IValidatable<String> field) {
                password.processInput();
                if (!password.isValid()) return;
                if (!field.getValue().equals(password.getInput())) {
                    error(field, "passwords.do.not.match");
                }
            }
        });

        newUserForm.add(realName = new TextField<String>("realName", formModel.<String>bind("realName")));

        // required fields
        for (FormComponent fc : asList(username, password, passwordVerify, realName)) {
            fc.setRequired(true);
        }
    }
}
