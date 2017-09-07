package com.example.jsonparsingexam;

/**
 * Created by junsuk on 2017. 9. 7..
 */

public class Location {
    private String name;
    private double lat;
    private double lng;
    private String address;

    public Location(String name, double lat, double lng, String address) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Location{");
        sb.append("name='").append(name).append('\'');
        sb.append(", lat=").append(lat);
        sb.append(", lng=").append(lng);
        sb.append(", address='").append(address).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
