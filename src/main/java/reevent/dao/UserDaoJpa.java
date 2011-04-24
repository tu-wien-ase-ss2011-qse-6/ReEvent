package reevent.dao;

import org.springframework.stereotype.Repository;
import reevent.domain.QUser;
import reevent.domain.User;

@Repository
public class UserDaoJpa extends EntityDaoBase<User> implements UserDao {
    @Override
    public boolean authenticate(String username, String passwordHash) {
        QUser _ = QUser.user;
        return query().from(_).where(            _.username.eq(username)
                .and(_.passwordHash.eq(passwordHash)))
                .exists();
    }
}
