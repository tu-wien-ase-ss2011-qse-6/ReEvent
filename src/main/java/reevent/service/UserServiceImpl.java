package reevent.service;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import reevent.dao.UserDao;
import reevent.domain.User;

import java.io.UnsupportedEncodingException;
import java.text.Normalizer;

@Service
public class UserServiceImpl implements UserService {
    static final String PASSWORD_SALT = "reevent$";

    @Required
    UserDao userDao;

    @Override
    public String hashPassword(String password) {
        String salted = PASSWORD_SALT+password;
        salted = Normalizer.normalize(salted, Normalizer.Form.NFD);
        try {
            return DigestUtils.md5DigestAsHex(salted.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("UTF-8 should always be available");
        }
    }

    @Override
    public User register(User newUser, String password) {
        newUser.setPasswordHash(hashPassword(password));
        return userDao.save(newUser);
    }
}
