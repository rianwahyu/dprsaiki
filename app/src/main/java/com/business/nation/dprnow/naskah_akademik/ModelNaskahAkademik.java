package com.business.nation.dprnow.naskah_akademik;

public class ModelNaskahAkademik {
    String ID, NAMA, ISI, FILE_PDF;

    public ModelNaskahAkademik(String ID, String NAMA, String ISI, String FILE_PDF) {
        this.ID = ID;
        this.NAMA = NAMA;
        this.ISI = ISI;
        this.FILE_PDF = FILE_PDF;
    }

    public ModelNaskahAkademik() {
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

    public String getISI() {
        return ISI;
    }

    public void setISI(String ISI) {
        this.ISI = ISI;
    }

    public String getFILE_PDF() {
        return FILE_PDF;
    }

    public void setFILE_PDF(String FILE_PDF) {
        this.FILE_PDF = FILE_PDF;
    }
}
