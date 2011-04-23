package reevent.service;

import reevent.domain.User;

public interface UserService {
    User register(User newUser, String password);
    String hashPassword(String password);
}
