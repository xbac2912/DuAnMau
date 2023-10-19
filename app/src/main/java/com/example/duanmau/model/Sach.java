package com.example.duanmau.model;

public class Sach {
    private int maSach;
    private String tenSach;
    private int giaThue;
    private int namXB;
    private int maLoai;
    private String tenLoai;

    public Sach() {
    }

    public Sach(String tenSach, int giaThue, int namXB, int maLoai) {
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.namXB = namXB;
        this.maLoai = maLoai;
    }

    public Sach(String tenSach, int giaThue, int namXB, int maLoai, String tenLoai) {
        this.tenSach = tenSach;
        this.giaThue = giaThue;
        this.namXB = namXB;
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public int getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(int giaThue) {
        this.giaThue = giaThue;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public int getNamXB() {
        return namXB;
    }

    public void setNamXB(int namXB) {
        this.namXB = namXB;
    }
}
