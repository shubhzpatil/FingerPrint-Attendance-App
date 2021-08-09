package com.example.mega2.FingerPrint;

import android.graphics.Bitmap;

import java.io.Serializable;

public class MyPojo implements Serializable {
    int cid;
    String sync;
    String name;
    String userid;
    String hand;
    String date;
    String time;
    String DeviceId;
    int fcnt;
    String pass;
    String finger;
    String file;
    Bitmap pic;

    public int getfcnt() {
        return fcnt;
    }

    public void setFcnt(int fcnt) {
        this.fcnt = fcnt;
    }

    public Bitmap getPic() { return pic; }

    public void setPic(Bitmap pic) {
        this.pic = pic;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getpass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

    public String getDeviceId() {
        return DeviceId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public String getDate() {return date;}

    public void setDate(String date) { this.date = date ;}

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {return userid;}

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public String getFinger() {
        return finger;
    }

    public void setFinger(String finger) {
        this.finger = finger;
    }

}
