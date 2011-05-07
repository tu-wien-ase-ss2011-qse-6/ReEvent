package reevent.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import reevent.dao.EntityDao;
import reevent.dao.EventDao;
import reevent.dao.UserDao;
import reevent.domain.Event;
import reevent.domain.User;
import reevent.domain.UserRole;
import reevent.service.UserService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


public class InitData {
    @PersistenceContext
    EntityManager em;

    @Autowired
    PlatformTransactionManager txManager;

    @Autowired
    EventDao eventDao;

    @Autowired
    UserDao userDao;
    @Autowired
    UserService userService;

    @PostConstruct
    public void initData() {
        TransactionTemplate txTemplate = new TransactionTemplate(txManager);

        txTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                initEvents();
                initUsers();
            }
        });
    }

    private static boolean isEmpty(EntityDao dao) {
        return dao.findAll(0, 1).isEmpty();
    }

    private void initUsers() {
        if (!isEmpty(userDao)) {
            return;
        }

        log().debug(String.format("initUsers()"));

        User user;

        user = new User();
        user.setUsername("user1");
        user.setFirstName("User 1");
        user.setLastName("Us");
        userService.register(user, "user1");

        user = new User();
        user.setUsername("user2");
        user.setFirstName("User 2");
        user.setLastName("Us");
        userService.register(user, "user2");

        user = new User();
        user.setUsername("user3");
        user.setFirstName("User 3");
        user.setLastName("Us");
        userService.register(user, "user3");

        user = new User();
        user.setUsername("admin");
        user.setFirstName("Administrator");
        user.setLastName("Us");
        Set<UserRole> adminRoles = new LinkedHashSet<UserRole>();
        adminRoles.add(UserRole.USER);
        adminRoles.add(UserRole.ADMIN);
        user.setRoles(adminRoles);
        userService.register(user, "admin");
    }

    private void initEvents() {
        if (!isEmpty(eventDao)) {
            // Already initialised
            return;
        }

        log().debug(String.format("initEvents()"));
        eventDao.save(new Event("event 1", new Date()));
        eventDao.save(new Event("event 2", new Date()));
        eventDao.save(new Event("event 3", new Date()));
    }

    private Logger _log = LoggerFactory.getLogger(this.getClass());
    private Logger log() {
        return _log;
    }
}