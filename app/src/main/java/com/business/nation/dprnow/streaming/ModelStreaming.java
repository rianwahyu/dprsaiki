package com.business.nation.dprnow.streaming;

public class ModelStreaming {
    String ID, NAMA, URL;

    public ModelStreaming(String ID, String NAMA, String URL) {
        this.ID = ID;
        this.NAMA = NAMA;
        this.URL = URL;
    }

    public ModelStreaming() {
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

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
