package com.example.ekathapro;

public class Memb {
    public String mname,mplace,mward,mmobile,muser,mpassw,munitnum;
    Boolean status;


    public Memb()
    {
        status=false;
    }

    public Memb(String mname, String mplace, String mward, String mmobile, String muser, String mpass, String munitnum, boolean status) {
        this.mname = mname;
        this.mplace = mplace;
        this.mward = mward;
        this.mmobile = mmobile;
        this.muser = muser;
        this.mpassw = mpass;
        this.munitnum = munitnum;
        this.status = status;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMplace() {
        return mplace;
    }

    public void setMplace(String mplace) {
        this.mplace = mplace;
    }

    public String getMward() {
        return mward;
    }

    public void setMward(String mward) {
        this.mward = mward;
    }

    public String getMmobile() {
        return mmobile;
    }

    public void setMmobile(String mmobile) {
        this.mmobile = mmobile;
    }

    public String getMuser() {
        return muser;
    }

    public void setMuser(String muser) {
        this.muser = muser;
    }

    public String getMpassw() {
        return mpassw;
    }

    public void setMpassw(String mpassw) {
        this.mpassw = mpassw;
    }

    public String getMunitnum() {
        return munitnum;
    }

    public void setMunitnum(String munitnum) {
        this.munitnum = munitnum;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
