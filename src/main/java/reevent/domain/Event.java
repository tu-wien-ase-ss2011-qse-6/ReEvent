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

    public Event() {
    }

    public Event(String name, Date start) {
        this.name = name;
        this.start = start;
    }
}
