package com.business.nation.dprnow.agenda;

public class ModelAgenda {
    String ID, JUDUL, TANGGAL, TEMPAT, DESKRIPSI, JAM;

    public ModelAgenda(String ID, String JUDUL, String TANGGAL, String TEMPAT, String DESKRIPSI, String JAM) {
        this.ID = ID;
        this.JUDUL = JUDUL;
        this.TANGGAL = TANGGAL;
        this.TEMPAT = TEMPAT;
        this.DESKRIPSI = DESKRIPSI;
        this.JAM = JAM;
    }

    public ModelAgenda() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getJUDUL() {
        return JUDUL;
    }

    public void setJUDUL(String JUDUL) {
        this.JUDUL = JUDUL;
    }

    public String getTANGGAL() {
        return TANGGAL;
    }

    public void setTANGGAL(String TANGGAL) {
        this.TANGGAL = TANGGAL;
    }

    public String getTEMPAT() {
        return TEMPAT;
    }

    public void setTEMPAT(String TEMPAT) {
        this.TEMPAT = TEMPAT;
    }

    public String getDESKRIPSI() {
        return DESKRIPSI;
    }

    public void setDESKRIPSI(String DESKRIPSI) {
        this.DESKRIPSI = DESKRIPSI;
    }

    public String getJAM() {
        return JAM;
    }

    public void setJAM(String JAM) {
        this.JAM = JAM;
    }
}
