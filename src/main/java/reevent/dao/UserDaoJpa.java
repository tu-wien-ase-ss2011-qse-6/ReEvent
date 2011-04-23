package reevent.dao;

import org.springframework.stereotype.Repository;
import reevent.domain.User;

@Repository
public class UserDaoJpa extends EntityDaoBase<User> implements UserDao {
}
