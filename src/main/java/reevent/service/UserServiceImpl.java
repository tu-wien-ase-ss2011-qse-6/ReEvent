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
    @Resource
    UserDao userDao;

    @Override
    public String hashPassword(String salt, String password) {
        String salted = salt+password;
        salted = normalize(salted, NFD);
        try {
            return md5DigestAsHex(salted.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError("UTF-8 should always be available");
        }
    }

    private String hashPassword(User user, String password) {
        return hashPassword(user.getId().toString(), password);
    }

    @Override
    public User register(User newUser, String password) {
        newUser.setPasswordHash(hashPassword(newUser, password));
        return userDao.save(newUser);
    }

    @Override
    public User authenticate(String username, String password) {
        User user = userDao.findByUsername(username);

        if (user == null) {
            // Unknown user name.
            return null;
        }
        String pwHash = hashPassword(user, password);
        if (user.getPasswordHash().equals(pwHash)) {
            return user;
        } else {
            // invalid password
            return null;
        }
    }
}
