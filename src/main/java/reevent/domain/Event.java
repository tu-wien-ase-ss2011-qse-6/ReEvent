package reevent.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Event extends EntityBase {
    @Column
    private String name;

    @Column
    private Date start;
}
