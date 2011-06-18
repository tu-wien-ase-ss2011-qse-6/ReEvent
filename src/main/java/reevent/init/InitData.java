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
import reevent.domain.media.MediaBase;
import reevent.service.MediaService;
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

    @Autowired
    MediaDao mediaDao;

    @Autowired
    MediaService mediaService;
    private MediaBase eventImage;
    private User admin;

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
            admin = userDao.findByUsername("admin");
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
        admin = userService.register(user, "admin");
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
        e1.setPerformer("AC/DC");
        e1.setMainPicture(mediaService.create("http://www.zmemusic.com/wp-content/uploads/2009/06/2.jpg", admin));
        e1.setLatitude(48.19898);
        e1.setLongitude(16.369904);
        eventDao.save(e1);

        Event e2 = new Event("Technoparty", new Date(), "Riverdog", "Operngasse 1, Vienna");
        e2.setCreatedBy(userDao.findByUsername("user1"));
        e2.setGenre("Techno");
        e2.setPerformer("Scooter");
        e2.setMainPicture(mediaService.create("http://images.wikia.com/lyricwiki/images/9/9b/Scooter_-_The_Stadium_Techno_Experience.jpg", admin));
        e2.setLatitude(48.203385);
        e2.setLongitude(16.368557);
        eventDao.save(e2);

        Event e3 = new Event("Bruce is Back!", new Date(), "Donauinsel", "Donauinsel 1, 1220 Wien, Austria");
        e3.setCreatedBy(userDao.findByUsername("user1"));
        e3.setGenre("Rock");
        e3.setPerformer("Bruce Springsteen & the E-Street Band");
        e3.setMainPicture(mediaService.create("http://www.popstarsplus.com/images/BruceSpringsteenPicture.jpg", admin));
        e3.setLatitude(48.228215);
        e3.setLongitude(16.409154);
        eventDao.save(e3);
        
        Event e4 = new Event("Neil Young Concert", new Date(), "Los Angeles Concert Hall 123", "Concert Park Drive, Los Angeles, CA");
        e4.setCreatedBy(userDao.findByUsername("user2"));
        e4.setGenre("Rock");
        e4.setPerformer("Neil Young");
        e4.setMainPicture(mediaService.create("http://www.aquariumdrunkard.com/wp-content/uploads/2007/10/neil-young.jpg", admin));
        e4.setLatitude(33.974552);
        e4.setLongitude(-118.424176);
        eventDao.save(e4);
    }

    private void initMedia() {
        if (!isEmpty(mediaDao)) {
            eventImage = mediaDao.findAll(0, 1).get(0);
            return;
        }

        eventImage = mediaService.create("http://kittensforever.files.wordpress.com/2008/11/kittens4blog1.jpg", admin);
        
    }

    private Logger _log = LoggerFactory.getLogger(this.getClass());
    private Logger log() {
        return _log;
    }
}