package reevent.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reevent.domain.Venue;

@Transactional
@Repository
public class VenueDaoJpa extends EntityDaoBase<Venue> implements VenueDao {
}
