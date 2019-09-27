package com.business.nation.dprnow.berita;

public class ModelBerita {
    String ID, JUDUL, TANGGAL, TEMPAT, DESKRIPSI,JAM, ID_KEGIATAN, FILE_FOTO;

    public ModelBerita(String ID, String JUDUL, String TANGGAL, String TEMPAT,
                       String DESKRIPSI, String JAM, String ID_KEGIATAN, String FILE_FOTO) {
        this.ID = ID;
        this.JUDUL = JUDUL;
        this.TANGGAL = TANGGAL;
        this.TEMPAT = TEMPAT;
        this.DESKRIPSI = DESKRIPSI;
        this.JAM = JAM;
        this.ID_KEGIATAN = ID_KEGIATAN;
        this.FILE_FOTO = FILE_FOTO;
    }

    public ModelBerita() {
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

    public String getID_KEGIATAN() {
        return ID_KEGIATAN;
    }

    public void setID_KEGIATAN(String ID_KEGIATAN) {
        this.ID_KEGIATAN = ID_KEGIATAN;
    }

    public String getFILE_FOTO() {
        return FILE_FOTO;
    }

    public void setFILE_FOTO(String FILE_FOTO) {
        this.FILE_FOTO = FILE_FOTO;
    }
}
