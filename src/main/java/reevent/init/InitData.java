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
import reevent.dao.FeedbackDao;
import reevent.dao.MediaDao;
import reevent.dao.UserDao;
import reevent.domain.Event;
import reevent.domain.Feedback;
import reevent.domain.Location;
import reevent.domain.Rating;
import reevent.domain.User;
import reevent.domain.UserRole;
import reevent.domain.media.MediaBase;
import reevent.service.MediaService;
import reevent.service.UserService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    FeedbackDao feedbackDao;

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

        Event e1 = new Event("Rock Festival", new Date(111, 8, 1, 8, 00), "Karlsplatz Park", "Karlsplatz 12, Vienna");
        e1.setCreatedBy(userDao.findByUsername("user1"));
        e1.setGenre("Festivals");
        e1.setPerformer("AC/DC");
        e1.setMainPicture(mediaService.create("http://www.zmemusic.com/wp-content/uploads/2009/06/2.jpg", admin));
        e1.setLatitude(48.19898);
        e1.setLongitude(16.369904);
        eventDao.save(e1);
        
        Feedback f1 = new Feedback(e1, userDao.findByUsername("user1"), Rating.POSITIVE, "Keep on rocking!", "Really enjoyed this rock event! Hopefully you all can see it sometime.");
        feedbackDao.save(f1);               

        Event e2 = new Event("Concerts in Mozart's House", new Date(111, 6, 23, 7, 30), "Mozarthaus", "Singerstrasse 7 Vienna, 9  Austria");
        e2.setCreatedBy(userDao.findByUsername("user1"));
        e2.setGenre("Concerts and Tour Dates");
        e2.setPerformer("Different bands");
        e2.setMainPicture(mediaService.create("http://static.eventful.com/images/edpborder500/I0-001/003/739/078-6.jpeg", admin));
        e2.setLatitude(48.207493);
        e2.setLongitude(16.373515);
        eventDao.save(e2);
        
        Feedback f2 = new Feedback(e2, userDao.findByUsername("user1"), Rating.NEUTRAL, "Nice concet but aweful guests!", "Liked the concert but not the people there, they were too loud.");
        feedbackDao.save(f2);

        Event e3 = new Event("Bruce is Back!", new Date(111, 8, 1, 8, 00), "Donauinsel", "Donauinsel 1, 1220 Wien, Austria");
        e3.setCreatedBy(userDao.findByUsername("user1"));
        e3.setGenre("Concerts and Tour Dates");
        e3.setPerformer("Bruce Springsteen & the E-Street Band");
        e3.setMainPicture(mediaService.create("http://www.popstarsplus.com/images/BruceSpringsteenPicture.jpg", admin));
        e3.setLatitude(48.228215);
        e3.setLongitude(16.409154);
        eventDao.save(e3);
        
        Feedback f3 = new Feedback(e3, userDao.findByUsername("user1"), Rating.POSITIVE, "Keep on rocking in the free world!", "Really enjoyed this rock event! Hopefully you all can see it sometime. Love Bruce Springsteen!!!");
        feedbackDao.save(f3);
        
        Feedback f11 = new Feedback(e3, userDao.findByUsername("user2"), Rating.POSITIVE, "Yeah!!!", "You are right user1, this just rocks!");
        feedbackDao.save(f11);
        
        Event e4 = new Event("Neil Young Concert", new Date(111, 8, 1, 8, 00), "Los Angeles Concert Hall 123", "Concert Park Drive, Los Angeles, CA");
        e4.setCreatedBy(userDao.findByUsername("user2"));
        e4.setGenre("Concerts and Tour Dates");
        e4.setPerformer("Neil Young");
        e4.setMainPicture(mediaService.create("http://rockhall.com/media/assets/inductees/default/neil-young.jpg", admin));
        e4.setLatitude(33.974552);
        e4.setLongitude(-118.424176);
        eventDao.save(e4);
        
        Feedback f4 = new Feedback(e4, userDao.findByUsername("user2"), Rating.POSITIVE, "Neil Young rocks!", "Had an awesome time there! Neil Young knows about life!!");
        feedbackDao.save(f4);
        Feedback f13 = new Feedback(e4, userDao.findByUsername("user1"), Rating.POSITIVE, "Awesome times!", "Enjoy N. Young everytime!!");
        feedbackDao.save(f13);
        
        Event e5 = new Event("Bon Jovi", new Date(111, 6, 22, 20, 0), "Ernst Happel Stadium", "Meiereistrasse Vienna, 9  Austria");
        e5.setCreatedBy(userDao.findByUsername("user1"));
        e5.setGenre("Concerts and Tour Dates");
        e5.setPerformer("Bon Jovi");
        e5.setMainPicture(mediaService.create("http://static.eventful.com/images/edpborder500/I0-001/003/860/890-1.jpeg", admin));
        e5.setLatitude(48.20787);
        e5.setLongitude(16.418331);
        eventDao.save(e5);
        
        Feedback f5 = new Feedback(e5, userDao.findByUsername("user1"), Rating.POSITIVE, "Nice!", "Really liked it.");
        feedbackDao.save(f5);
        
        Event e6 = new Event("30 Seconds To Mars", new Date(111, 7, 2), "Volt Festival", "Sopron, Lővér campsite Sopron, GS  Hungary");
        e6.setCreatedBy(userDao.findByUsername("user1"));
        e6.setGenre("Festivals");
        e6.setPerformer("30 Seconds To Mars");
        e6.setMainPicture(mediaService.create("http://static.eventful.com/images/edpborder500/I0-001/001/178/940-3.png", admin));
        e6.setLatitude(47.684898);
        e6.setLongitude(16.583053);
        eventDao.save(e6);
        
        Feedback f6 = new Feedback(e6, userDao.findByUsername("user1"), Rating.POSITIVE, "30 Seconds to Rock!", "Really enjoyed this festival! Those guys rock!");
        feedbackDao.save(f6);
        
        Event e7 = new Event("Maroon 5", new Date(111, 12, 13, 20, 0), "Gasometer", "Guglgasse 8 Vienna, 9  Austria");
        e7.setCreatedBy(userDao.findByUsername("user2"));
        e7.setGenre("Festivals");
        e7.setPerformer("Maroon 5");
        e7.setMainPicture(mediaService.create("http://static.eventful.com/images/edpborder500/I0-001/003/310/001-5.jpeg", admin));
        e7.setLatitude(48.185483);
        e7.setLongitude(16.419468);
        eventDao.save(e7);
        
        Feedback f7 = new Feedback(e7, userDao.findByUsername("user2"), Rating.POSITIVE, "Marooooon 5!", "Had a great time there with my friends. Will be there next year!");
        feedbackDao.save(f7);
        
        Event e8 = new Event("Modellbau Messe", new Date(111, 10, 26), "Messe Wien Exhibition & Congress Center", "Messe Wien Exhibition Congress Center, Wien");
        e8.setCreatedBy(userDao.findByUsername("user2"));
        e8.setGenre("Conferences and Tradeshows");
        e8.setPerformer("Modellbau Messe Wien");
        e8.setMainPicture(mediaService.create("http://www.kunstforum.com/forum/vbcms_areas/vbcms_custom_content/images/original_images/316messe_wien_foto_intercolor.jpg", admin));
        e8.setLatitude(48.208174);
        e8.setLongitude(16.373819);
        eventDao.save(e8);
        
        Feedback f8 = new Feedback(e8, userDao.findByUsername("user1"), Rating.NEGATIVE, "Aweful!", "Didn't like this Congress, too many people wanted to get my money.");
        feedbackDao.save(f8);
        Feedback f12 = new Feedback(e8, userDao.findByUsername("user2"), Rating.NEUTRAL, "Not veeeery interesting!", "I wouldn't say its aweful but it was boooring.");
        feedbackDao.save(f12);
        
        Event e9 = new Event("Wiener Hofburg Charity", new Date(111, 6, 23), "Wiener Hofburg", "Heldenplatz 1 Vienna, 9  Austria");
        e9.setCreatedBy(userDao.findByUsername("user2"));
        e9.setGenre("Fundraising and Charity");
        e9.setPerformer("Wiener Hofburg Orchester");
        e9.setMainPicture(mediaService.create("http://static.eventful.com/images/edpborder500/I0-001/003/739/110-1.jpeg", admin));
        e9.setLatitude(48.206135);
        e9.setLongitude(16.363296);
        eventDao.save(e9);
        
        Feedback f9 = new Feedback(e9, userDao.findByUsername("user2"), Rating.POSITIVE, "Awesome!", "Really enjoyed to see how many people like to give money away for charity! Join us!!!");
        feedbackDao.save(f9);
        
        Event e10 = new Event("19th Annual International Conference on Intelligent Systems", new Date(111, 7, 19), "Austria Center Vienna", "Bruno-Kreisky-Platz 1 A-1220 Vienna, Austria");
        e10.setCreatedBy(userDao.findByUsername("user2"));
        e10.setGenre("Technology");
        e10.setPerformer("Annual international conference on Intelligent Systems for Molecular Biology Participants");
        e10.setMainPicture(mediaService.create("http://static.eventful.com/store/skin/no_image/categories/250x250/technology.jpg", admin));
        e10.setLatitude(48.234902);
        e10.setLongitude(16.413756);
        eventDao.save(e10);
        
        Feedback f10 = new Feedback(e10, userDao.findByUsername("user2"), Rating.NEUTRAL, "Very interesting!", "Interesting conference but too much talk about the participants.");
        feedbackDao.save(f10);
        
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