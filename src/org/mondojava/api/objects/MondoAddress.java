package org.mondojava.api.objects;

/**
 *
 * @author dgraf
 * Represents the address format used in the merchant
 */
public class MondoAddress {

    public MondoAddress() {
        address = "";
        city = "";
        country = "";
        latitude = 0.0f;
        longitude = 0.0f;
        postcode = "";
        region = "";
        short_formatted = "";
        formatted = "";
        zoom_level = 0;
        approximate = false;
    }

    private String address;
    private String city;
    private String country;
    private float latitude;
    private float longitude;
    private String postcode;
    private String region;
    private String short_formatted;
    private String formatted;
    private long zoom_level;
    private boolean approximate;

    public String getShort_formatted() {
        return short_formatted;
    }

    public void setShort_formatted(String short_formatted) {
        this.short_formatted = short_formatted;
    }

    public String getFormatted() {
        return formatted;
    }

    public void setFormatted(String formatted) {
        this.formatted = formatted;
    }

    public long getZoom_level() {
        return zoom_level;
    }

    public void setZoom_level(long zoom_level) {
        this.zoom_level = zoom_level;
    }

    public boolean isApproximate() {
        return approximate;
    }

    public void setApproximate(boolean approximate) {
        this.approximate = approximate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

}
