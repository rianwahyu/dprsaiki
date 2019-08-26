package com.business.nation.dprnow.anggota;

public class ModelAnggota {
    String ID, NAMA, TGL_LAHIR, ALAMAT, EMAIL, NO_HP, AGAMA, PARTAI, FOTO, POSISI, NIK, TEMPAT_LAHIR;

    public ModelAnggota() {
    }

    public ModelAnggota(String ID, String NAMA, String TGL_LAHIR, String ALAMAT, String EMAIL, String NO_HP, String AGAMA, String PARTAI, String FOTO, String POSISI, String NIK) {
        this.ID = ID;
        this.NAMA = NAMA;
        this.TGL_LAHIR = TGL_LAHIR;
        this.ALAMAT = ALAMAT;
        this.EMAIL = EMAIL;
        this.NO_HP = NO_HP;
        this.AGAMA = AGAMA;
        this.PARTAI = PARTAI;
        this.FOTO = FOTO;
        this.POSISI = POSISI;
        this.NIK = NIK;
    }

    public String getTEMPAT_LAHIR() {
        return TEMPAT_LAHIR;
    }

    public void setTEMPAT_LAHIR(String TEMPAT_LAHIR) {
        this.TEMPAT_LAHIR = TEMPAT_LAHIR;
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

    public String getTGL_LAHIR() {
        return TGL_LAHIR;
    }

    public void setTGL_LAHIR(String TGL_LAHIR) {
        this.TGL_LAHIR = TGL_LAHIR;
    }

    public String getALAMAT() {
        return ALAMAT;
    }

    public void setALAMAT(String ALAMAT) {
        this.ALAMAT = ALAMAT;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getNO_HP() {
        return NO_HP;
    }

    public void setNO_HP(String NO_HP) {
        this.NO_HP = NO_HP;
    }

    public String getAGAMA() {
        return AGAMA;
    }

    public void setAGAMA(String AGAMA) {
        this.AGAMA = AGAMA;
    }

    public String getPARTAI() {
        return PARTAI;
    }

    public void setPARTAI(String PARTAI) {
        this.PARTAI = PARTAI;
    }

    public String getFOTO() {
        return FOTO;
    }

    public void setFOTO(String FOTO) {
        this.FOTO = FOTO;
    }

    public String getPOSISI() {
        return POSISI;
    }

    public void setPOSISI(String POSISI) {
        this.POSISI = POSISI;
    }

    public String getNIK() {
        return NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }
}
