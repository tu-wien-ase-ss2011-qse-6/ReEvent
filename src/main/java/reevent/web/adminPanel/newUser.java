//package reevent.web.adminPanel;
//
//import static java.util.Arrays.asList;
//
//import java.util.Date;
//import java.util.List;
//
//import org.apache.wicket.markup.html.form.Form;
//import org.apache.wicket.markup.html.form.FormComponent;
//import org.apache.wicket.markup.html.form.TextField;
//import org.apache.wicket.model.CompoundPropertyModel;
//
//import reevent.domain.Event;
//import reevent.domain.User;
//import reevent.web.ReEventSession;
//
//public class newUser extends adminPanel {
//
//	
//	Form<User> newUserForm;
//	
//	TextField<String> firstName;
//	
//	public newUser(){
//		
//		CompoundPropertyModel<User> formModel = new CompoundPropertyModel<User>(new User());
//		
//		
//		add(newUserForm = new Form<User>("newUserForm", formModel) {
//	        
//        	@Override
//            protected void onSubmit() {
//
//             //TODO 
//               
//            }
//        });
//		
//		
//		
//		newUserForm.add(firstName = new TextField<String>("firstName", formModel.<String>bind("firstName")));
//		
//		 List<TextField<String>> fields = asList(firstName);
//	        addFormLabels(fields);
//	       
//	        for (FormComponent fc : fields) {
//	            fc.setRequired(true);
//	        }
//	}
//}
