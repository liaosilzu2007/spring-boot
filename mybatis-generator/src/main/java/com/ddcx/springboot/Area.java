package com.ddcx.springboot;

public class Area {
    private String fcode;

    private String fpcode;

    private String fname;

    private String ffullname;

    private Byte flevel;

    public String getFcode() {
        return fcode;
    }

    public void setFcode(String fcode) {
        this.fcode = fcode == null ? null : fcode.trim();
    }

    public String getFpcode() {
        return fpcode;
    }

    public void setFpcode(String fpcode) {
        this.fpcode = fpcode == null ? null : fpcode.trim();
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname == null ? null : fname.trim();
    }

    public String getFfullname() {
        return ffullname;
    }

    public void setFfullname(String ffullname) {
        this.ffullname = ffullname == null ? null : ffullname.trim();
    }

    public Byte getFlevel() {
        return flevel;
    }

    public void setFlevel(Byte flevel) {
        this.flevel = flevel;
    }
}