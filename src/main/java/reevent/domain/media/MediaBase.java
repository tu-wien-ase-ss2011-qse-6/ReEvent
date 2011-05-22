package reevent.domain.media;

import reevent.domain.EntityBase;
import reevent.domain.User;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.ManyToOne;

@Entity
@Inheritance
public abstract class MediaBase extends EntityBase {
    @ManyToOne
    User addedBy;
}
