package reevent.dao;

import org.springframework.stereotype.Repository;
import reevent.domain.QUser;
import reevent.domain.User;

@Repository
public class UserDaoJpa extends EntityDaoBase<User> implements UserDao {
    QUser $ = QUser.user;
    @Override
    public User findByUsername(String username) {
        return query().from($).where($.username.eq(username)).uniqueResult($);
    }
}
