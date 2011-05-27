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
import reevent.dao.MediaDao;
import reevent.dao.UserDao;
import reevent.domain.Event;
import reevent.domain.Location;
import reevent.domain.User;
import reevent.domain.UserRole;
import reevent.domain.media.ExternalImage;
import reevent.service.UserService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;


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

    @Autowired
    MediaDao mediaDao;
    
    @PostConstruct
    public void initData() {
//        if (ReEventApplication.get().getConfigurationType() != RuntimeConfigurationType.DEVELOPMENT) {
//            init data only in dev mode
//            return;
//        }

        TransactionTemplate txTemplate = new TransactionTemplate(txManager);

        txTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                initUsers();
                initMedia();
                initEvents();
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

        Location loc = new Location();
        log().debug(String.format("initEvents()"));

        Event e1 = new Event("Rock Festival", new Date(), "Karlsplatz Park", "Karlsplatz 12, Vienna");
        e1.setCreatedBy(userDao.findByUsername("user1"));
        e1.setGenre("Hard Rock");
        eventDao.save(e1);

        Event e2 = new Event("Technoparty", new Date(), "Riverdog", "Operngasse 1, Vienna");
        e2.setCreatedBy(userDao.findByUsername("user2"));
        e2.setGenre("Techno");
        eventDao.save(e2);

        Event e3 = new Event("Bruce is Back!", new Date(), "Donauinsel", "Donauinsel 1, 1220 Wien, Austria");
        e3.setCreatedBy(userDao.findByUsername("user3"));
        e3.setGenre("Rock");
        eventDao.save(e3);
        
        Event e4 = new Event("Neil Young Concert", new Date(), "Los Angeles Concert Hall 123", "Concert Park Drive, Los Angeles, CA");
        e4.setCreatedBy(userDao.findByUsername("user3"));
        e4.setGenre("Rock");
        eventDao.save(e4);
    }

    private void initMedia() {
        if (!isEmpty(mediaDao)) {
            return;
        }

        ExternalImage kittywig = new ExternalImage("http://www.kittywigs.com/img/pink2.jpg");
        kittywig.setId(new UUID(0, 0));
        mediaDao.save(kittywig);
    }

    private Logger _log = LoggerFactory.getLogger(this.getClass());
    private Logger log() {
        return _log;
    }
}