package reevent.web.account;

import static java.util.Arrays.asList;

import java.util.List;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.validator.AbstractValidator;

import reevent.domain.User;
import reevent.service.UserService;
import reevent.web.ReEventApplication;
import reevent.web.ReEventSession;
import reevent.web.Template;

public class changeAccount extends Template {
	
	Form<User> changeUserForm;
    TextField<String> username;
    TextField<String> password;
    TextField<String> passwordVerify;
    TextField<String> firstName;
    TextField<String> lastName;
    
    UserService users;

	public changeAccount(){
		CompoundPropertyModel<User> formModel = new CompoundPropertyModel<User>(new User());
		
        add(changeUserForm = new Form<User>("changeUserForm", formModel) {
            @Override
            protected void onSubmit() {
                users.update(changeUserForm.getModelObject(), password.getModelObject());
                setResponsePage(ReEventApplication.get().getAccount());
            }
        });

        changeUserForm.add(username = new TextField<String>("username", formModel.<String>bind("username"), String.class));
        username.add(new AbstractValidator<String>() {
            @Override
            protected void onValidate(IValidatable<String> field) {
                if (!users.isAvailable(field.getValue())) {
                    this.error(field, "username.not.available");
                }
            }
        });

        changeUserForm.add(password = new PasswordTextField("password", Model.<String>of()));

        changeUserForm.add(passwordVerify = new PasswordTextField("passwordVerify", Model.<String>of()));
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

        changeUserForm.add(firstName = new TextField<String>("firstName", formModel.<String>bind("firstName")));
        
        changeUserForm.add(lastName = new TextField<String>("lastName", formModel.<String>bind("lastName")));

        // required fields
        List<TextField<String>> fields = asList(username, password, passwordVerify, firstName, lastName);
        addLabels(fields);
        for (FormComponent fc : fields) {
            fc.setRequired(true);
        }
    }
}
