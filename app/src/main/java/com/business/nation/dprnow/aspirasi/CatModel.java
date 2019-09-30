package com.business.nation.dprnow.aspirasi;

public class CatModel {
    String ID, NAMA;

    public CatModel(String ID, String NAMA) {
        this.ID = ID;
        this.NAMA = NAMA;
    }

    public CatModel() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }
}
