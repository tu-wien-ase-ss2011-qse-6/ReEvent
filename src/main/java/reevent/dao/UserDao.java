package reevent.dao;

import reevent.domain.User;

public interface UserDao extends EntityDao<User> {
    User findByUsername(String username);

    boolean usernameExists(String username);
}
