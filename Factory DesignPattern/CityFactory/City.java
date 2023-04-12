package CityFactory;

import java.awt.*;

public class City {

    private String name;
    private float lat;
    private float lng;
    private String country;
    private String iso2;
    private String adminName;

    public City(String name, float lat, float lng, String country, String iso2, String adminName) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.country = country;
        this.iso2 = iso2;
        this.adminName = adminName;
    }

    public City(String[] attributes) {
        this.name = attributes[0];
        this.lat = Float.parseFloat(attributes[1]);
        this.lng = Float.parseFloat(attributes[2]);
        this.country = attributes[3];
        this.iso2 = attributes[4];
        this.adminName = attributes[5];
    }

    public String getCityString() {
        return this.name + "," + this.lat + "," + this.lng + "," + this.country + "," + this.iso2 + "," + this.adminName;
    }

    public String getName() {
        return this.name;
    }

    public String getAdminName() {
        return this.adminName;
    }

//    public float getLat() {
//        return this.lat;
//    }

//    public float getLng() {
//        return this.lng;
//    }

    public Point getLocation() {
        return new Point((int) ((lng * 70) - 4400), (int) (lat * 50) - 1200);
    }
}
