package reevent.dao;

import org.springframework.stereotype.Repository;
import reevent.domain.Venue;

@Repository
public class VenueDaoJpa extends EntityDaoBase<Venue> implements VenueDao {
}
