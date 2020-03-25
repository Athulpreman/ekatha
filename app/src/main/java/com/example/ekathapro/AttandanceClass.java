package com.example.ekathapro;

public class AttandanceClass
{
    String Date;
    Boolean present;

    public AttandanceClass()
    {
    }

    public AttandanceClass(String date, Boolean present)
    {
        Date = date;
        this.present = present;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Boolean getPresent() {
        return present;
    }

    public void setPresent(Boolean present) {
        this.present = present;
    }
}
