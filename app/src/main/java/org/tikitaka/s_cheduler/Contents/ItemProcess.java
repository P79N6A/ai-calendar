package org.tikitaka.s_cheduler.Contents;

public class ItemProcess {
    private String Result;
    private String Date;
    private int Photo;

    public ItemProcess() {

    }

    public ItemProcess(String result, String date, int photo) {
        Result = result;
        Date = date;
        Photo = photo;
    }

    public String getResult() {
        return Result;
    }

    public String getDate() {
        return Date;
    }

    public int getPhoto() {
        return Photo;
    }

    public void setResult(String result) {
        Result = result;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setPhoto(int photo) {
        Photo = photo;
    }
}
