package reevent.dao;

import com.mysema.query.jpa.JPQLQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reevent.domain.QUser;
import reevent.domain.User;

@Transactional
@Repository
public class UserDaoJpa extends EntityDaoBase<User> implements UserDao {
    QUser $ = QUser.user;
    @Override
    public User findByUsername(String username) {
        return queryByUsername(username).uniqueResult($);
    }

    @Override
    public boolean usernameExists(String username) {
        return queryByUsername(username).exists();
    }

    private JPQLQuery queryByUsername(String username) {
        return query().from($).where($.username.eq(username));
    }
}
