package reevent.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
    Integer latitude;
    Integer longitude;

    public Location() {
    }

    public Location(int latitude, int longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
