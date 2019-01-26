package org.tikitaka.s_cheduler;

public class ItemSuccess {
    private String Title;
    private String Date;
    private int Photo;

    public ItemSuccess() {

    }

    public ItemSuccess(String title, String date, int photo) {
        Title = title;
        Date = date;
        Photo = photo;
    }

    public String getTitle() {
        return Title;
    }

    public String getDate() {
        return Date;
    }

    public int getPhoto() {
        return Photo;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }
}
