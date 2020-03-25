package com.example.ekathapro;

public class Unitpres {
    public  String uno,una,upl,uwa,umo,uus,upa;
    Boolean status;

    public Unitpres() {
        status=false;
    }

    public Unitpres(String uno, String una, String upl, String uwa, String umo, String uus, String upa, Boolean status) {
        this.uno = uno;
        this.una = una;
        this.upl = upl;
        this.uwa = uwa;
        this.umo = umo;
        this.uus = uus;
        this.upa = upa;
        this.status = status;
    }

    public String getUno() {
        return uno;
    }

    public void setUno(String uno) {
        this.uno = uno;
    }

    public String getUna() {
        return una;
    }

    public void setUna(String una) {
        this.una = una;
    }

    public String getUpl() {
        return upl;
    }

    public void setUpl(String upl) {
        this.upl = upl;
    }

    public String getUwa() {
        return uwa;
    }

    public void setUwa(String uwa) {
        this.uwa = uwa;
    }

    public String getUmo() {
        return umo;
    }

    public void setUmo(String umo) {
        this.umo = umo;
    }

    public String getUus() {
        return uus;
    }

    public void setUus(String uus) {
        this.uus = uus;
    }

    public String getUpa() {
        return upa;
    }

    public void setUpa(String upa) {
        this.upa = upa;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
