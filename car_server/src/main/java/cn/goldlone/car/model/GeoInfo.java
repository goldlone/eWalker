package cn.goldlone.car.model;

/**
 * @author : Created by CN on 2018/6/25 17:46
 */
public class GeoInfo {
    // UserId
    private String userId;
    // 纬度
    private double latitude;
    // 经度
    private double longitude;
    // 地址
    private String address;
    // 时间
    private String time;

    public GeoInfo() {
    }

    public GeoInfo(String userId, double latitude, double longitude, String address, String time) {
        this.userId = userId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
