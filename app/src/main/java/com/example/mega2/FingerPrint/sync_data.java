package com.example.mega2.FingerPrint;

import java.io.Serializable;

class sync_data implements Serializable {

    private String user;
    private String date_time;
    private String DeviceId_HR;
    private String DeviceId_Unique;
    private String punchmode;
    private String Latitude;
    private String Longitude;
    private String matchscore;
    private String photofilename;
    private String fingerquality;
    private String date;
    private String time;


    public String getUser() {return user;}

    void setuser(String user) { this.user = user ;}

    String getDate_time() {return date_time;}

    void setDate_time(String date_time) {
        this.date_time = date_time;
    }


    String getDeviceId_HR() {
        return DeviceId_HR;
    }

    void setDeviceId_HR(String deviceId_HR) {
        DeviceId_HR = deviceId_HR;
    }

    String getDeviceId_Unique() {
        return DeviceId_Unique;
    }

    void setDeviceId_Unique(String deviceId_Unique) {
        DeviceId_Unique = deviceId_Unique;
    }

    String getPunchmode() {return punchmode;}

    void setPunchmode(String punchmode) { this.punchmode = punchmode ;}

    String getLatitude() {
        return Latitude;
    }

    void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    String getLongitude() {
        return Longitude;
    }

    void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }


    String getMatchscore() {
        return matchscore;
    }

    void setMatchscore(String matchscore) {
        this.matchscore = matchscore;
    }

    String getPhotofilename() {
        return photofilename;
    }

    void setPhotofilename(String photofilename) {
        this.photofilename = photofilename;
    }

    String getFingerquality() {
        return fingerquality;
    }

    void setFingerquality(String fingerquality) {
        this.fingerquality = fingerquality;
    }

    String getDate() {return date;}

    void setDate(String date) {
        this.date = date;
    }

    String getTime() {return time;}

    void setTime(String time) {
        this.time = time;
    }

}
