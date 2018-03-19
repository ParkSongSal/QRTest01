package com.example.user.qr;

import android.graphics.Bitmap;

/**
 * Created by user on 2018-02-23.
 */

public class Store {

    //Bitmap image;
    int id;
    String title;
    String address;
    //String work;
    //String leave;

    public Store(int id, String title, String address) {
        //this.image = image;
        this.id = id;
        this.title = title;
        this.address = address;
        //this.work = work;
        //this.leave = leave;
    }

   /*public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
    */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getLeave() {
        return leave;
    }

    public void setLeave(String leave) {
        this.leave = leave;
    }*/
}