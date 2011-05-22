package reevent.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reevent.domain.media.MediaBase;

@Transactional
@Repository
public class MediaDaoJpa extends EntityDaoBase<MediaBase> implements MediaDao {
}
