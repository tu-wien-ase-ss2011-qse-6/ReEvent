package reevent.dao;

import reevent.domain.User;

public interface UserDao extends EntityDao<User> {
    boolean authenticate(String username, String passwordHash);
}
