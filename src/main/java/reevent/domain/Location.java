package reevent.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Location {
    Integer latitude;
    Integer longitude;

    public Location() {
    }
    
    public void setLocation(Integer latitude, Integer longitude) {
    	
    	this.latitude = latitude;
    	this.longitude = longitude;
    	
    }
    
    public Integer getLatitude() {
    	return this.latitude;
    }
    
    public Integer getLongitude() {
    	return this.longitude;
    }
}
