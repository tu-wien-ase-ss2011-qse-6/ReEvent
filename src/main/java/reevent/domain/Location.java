package reevent.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Location extends EntityBase {
    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    String type;

    boolean stage = false;
    
    GpsCoords coords;

    @OneToMany(mappedBy= "location")
    Set<Event> events;

    public Location() {
    }

    public Location(String address, String name, boolean stage) {
        this.address = address;
        this.name = name;
        this.stage = stage;
    }

    public Location(String address, String name, boolean stage, String type) {
        this.address = address;
        this.name = name;
        this.stage = stage;
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStage() {
        return stage;
    }

    public void setStage(boolean stage) {
        this.stage = stage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GpsCoords getCoords() {
        return coords;
    }

    public void setCoords(GpsCoords coords) {
        this.coords = coords;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }
}
