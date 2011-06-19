package reevent.util;

import reevent.domain.Location;

import java.util.Arrays;
import java.util.List;

import static java.lang.Math.*;

/**
 * Utilities for GPS calculations
 */
public class LocationUtil {
    public static final double R = 6371d; // earth radius in km

    /**
     * Given a start point, initial bearing, and distance, this will
     * calculate the destination point and final bearing travelling along a
     * (shortest distance) great circle arc.
     *
     * Stolen from http://www.movable-type.co.uk/scripts/latlong.html
     *
     * @param start starting point
     * @param d distance in kilometers
     * @param brngDeg bearing in degrees
     * @return destination point coordinates
     */
    public static Location destination(Location start, double d, double brngDeg) {
        double lat1 = toRadians(start.getLatitude());
        double lng1 = toRadians(start.getLongitude());
        double brng = toRadians(brngDeg);

        double lat2 = asin(sin(lat1)*cos(d/R)
                + cos(lat1)*sin(d/R)*cos(brng));

        double lng2 = lng1 + atan2(sin(brng)*sin(d/R)*cos(lat1),
                cos(d/R)-sin(lat1)*sin(lat2));

        return new Location(toDegrees(lat2), toDegrees(lng2));
    }

    public static Location north(Location start, double d) {
        return destination(start, d, 0);
    }

    public static Location east(Location start, double d) {
        return destination(start, d, 90);
    }

    public static Location south(Location start, double d) {
        return destination(start, d, 180);
    }

    public static Location west(Location start, double d) {
        return destination(start, d, 270);
    }

    public static Location[] bounds(Location start, double d) {
        Location n = north(start, d);
        Location s = south(start, d);
        Location e = east(start, d);
        Location w = west(start, d);

        Location[] result = new Location[2];
        result[0] = new Location(s.getLatitude(), w.getLongitude());
        result[1] = new Location(n.getLatitude(), e.getLongitude());
        return result;
    }

    public static void main(String[] args) {
        Location loc = new Location(60, 30);
        List<Location> bounds = Arrays.asList(bounds(loc, 100));
        System.out.println("bounds = " + bounds);
    }
}
