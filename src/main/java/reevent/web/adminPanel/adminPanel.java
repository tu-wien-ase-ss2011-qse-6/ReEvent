package reevent.web.adminPanel;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import reevent.dao.UserDao;
import reevent.domain.User;
import reevent.service.UserService;
import reevent.web.ReEventSession;
import reevent.web.Template;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.ComponentPropertyModel;

@AuthorizeInstantiation("ADMIN")
public class adminPanel extends Template {

	ListView<User> myUserList;
	
	@SpringBean
	UserDao userDao;
	
	@SpringBean
	UserService userService;
	
	public adminPanel(){
	
		User adminuser = ReEventSession.get().getModUserSignedIn().getObject();
		
		add(myUserList = new ListView<User>("myUserList", userDao.findAll()) {
			
			@Override
			protected void populateItem(final ListItem<User> item) {
				
				
					item.add(new Label("userName", new ComponentPropertyModel("username")));
					
					item.add(new Link("deleteUser"){
		            	public void onClick() {
		            		
		            		User obj = (User) item.getModelObject();
		            		userService.disable(obj.getId());
		            		setResponsePage(new adminPanel());
		            		
		            	}
		            });
					
					// do not display disabled users
					item.setVisible(item.getModelObject().isEnabled());
				
				
				
				
			}
			
			
		});
		
	}
}
