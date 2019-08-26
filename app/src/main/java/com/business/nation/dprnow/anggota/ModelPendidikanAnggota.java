package com.business.nation.dprnow.anggota;

public class ModelPendidikanAnggota {
    String ID, ID_ANGGOTA, NAMA, TAHUN;

    public ModelPendidikanAnggota(String ID, String ID_ANGGOTA, String NAMA, String TAHUN) {
        this.ID = ID;
        this.ID_ANGGOTA = ID_ANGGOTA;
        this.NAMA = NAMA;
        this.TAHUN = TAHUN;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID_ANGGOTA() {
        return ID_ANGGOTA;
    }

    public void setID_ANGGOTA(String ID_ANGGOTA) {
        this.ID_ANGGOTA = ID_ANGGOTA;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public String getTAHUN() {
        return TAHUN;
    }

    public void setTAHUN(String TAHUN) {
        this.TAHUN = TAHUN;
    }
}
