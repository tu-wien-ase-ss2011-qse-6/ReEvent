package reevent.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reevent.domain.Location;

@Transactional
@Repository
public class VenueDaoJpa extends EntityDaoBase<Location> implements VenueDao {
}
