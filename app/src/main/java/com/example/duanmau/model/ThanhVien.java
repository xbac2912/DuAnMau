package com.example.duanmau.model;

public class ThanhVien {
    private int maTV;
    private String tenTV;
    private String namSinh;
    private String CCCD;

    public ThanhVien() {
    }

    public ThanhVien(int maTV, String tenTV, String namSinh, String CCCD) {
        this.maTV = maTV;
        this.tenTV = tenTV;
        this.namSinh = namSinh;
        this.CCCD = CCCD;
    }

    public ThanhVien(String tenTV, String namSinh, String CCCD) {
        this.tenTV = tenTV;
        this.namSinh = namSinh;
        this.CCCD = CCCD;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }
}
