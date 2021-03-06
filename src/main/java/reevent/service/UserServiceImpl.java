package reevent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reevent.dao.UserDao;
import reevent.domain.User;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import static java.text.Normalizer.Form.NFD;
import static java.text.Normalizer.normalize;
import static org.springframework.util.DigestUtils.md5DigestAsHex;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao dao;

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
        return dao.save(newUser);
    }

    @Override
    public User authenticate(String username, String password) {
        User user = dao.findByUsername(username);

        if (user == null) {
            // Unknown user name.
            return null;
        }
        String pwHash = hashPassword(user, password);
        if (user.getPasswordHash().equals(pwHash) && user.isEnabled()) {
            return user;
        } else {
            // invalid password
            return null;
        }
    }

    @Override
    public boolean isAvailable(String username) {
        return !dao.usernameExists(username);
    }

	@Override
	public User update(User user, String password) {
        user.setPasswordHash(hashPassword(user, password));
        return dao.update(user);
	}

	@Override
	public void disable(UUID id) {
        dao.load(id).setEnabled(false);
	}
}
