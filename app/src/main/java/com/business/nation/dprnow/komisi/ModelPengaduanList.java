package com.business.nation.dprnow.komisi;

public class ModelPengaduanList {
    String ID, NAMA;

    public ModelPengaduanList(String ID, String NAMA) {
        this.ID = ID;
        this.NAMA = NAMA;
    }

    public ModelPengaduanList() {
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
