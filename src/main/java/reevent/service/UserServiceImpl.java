package reevent.service;

import org.springframework.stereotype.Service;
import reevent.dao.UserDao;
import reevent.domain.User;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

import static java.text.Normalizer.Form.NFD;
import static java.text.Normalizer.normalize;
import static org.springframework.util.DigestUtils.md5DigestAsHex;

@Service
public class UserServiceImpl implements UserService {
    static final String PASSWORD_SALT = "reevent$";

    @Resource
    UserDao userDao;

    @Override
    public String hashPassword(String password) {
        String salted = PASSWORD_SALT+password;
        salted = normalize(salted, NFD);
        try {
            return md5DigestAsHex(salted.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("UTF-8 should always be available");
        }
    }

    @Override
    public User register(User newUser, String password) {
        newUser.setPasswordHash(hashPassword(password));
        return userDao.save(newUser);
    }

    @Override
    public boolean authenticate(String username, String password) {
        return userDao.authenticate(username, hashPassword(password));
    }
}
