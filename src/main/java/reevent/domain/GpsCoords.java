package reevent.domain;

import javax.persistence.Embeddable;

@Embeddable
public class GpsCoords {
    Integer latitude;
    Integer longitude;

    public GpsCoords() {
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
