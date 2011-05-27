package reevent.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

import javax.persistence.Embeddable;
import javax.persistence.Transient;
import java.io.Serializable;

@Embeddable
/**
 * A location for users and events.
 */
public class Location implements Serializable {
    /**
     * A human-readable locationName for this location.
     *
     * Optional / unused for a user's home location.
     */
    String locationName;

    /**
     * A street address for this location.
     *
     * Optional / unused for a user's home location.
     */
    String locationAddress;

    /**
     * GPS coordinates of this location.
     *
     * If either is null, the other must also be null, and the location is to
     * be considered unset.
     *
     * @see #isEmpty
     */
    Double latitude;

    /**
     * @see #latitude
     */
    Double longitude;

    public Location() {
    }

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(double latitude, double longitude, String name, String address) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationName = name;
        this.locationAddress = address;
    }


    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String address) {
        this.locationAddress = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Transient
    public boolean isEmpty() {
        if (latitude == null && longitude == null) {
            // both are null
            return true;
        } else if (latitude == null || longitude == null) {
            // only one is null
            throw new IllegalStateException("Both lat and lng have to be set or unset at the same time.");
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("latitude", latitude).
                append("longitude", longitude).
                append("locationName", locationName).
                append("locationAddress", locationAddress).
                toString();
    }
}
