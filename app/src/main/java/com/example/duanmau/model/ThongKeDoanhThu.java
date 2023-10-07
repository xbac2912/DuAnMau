package com.example.duanmau.model;

public class ThongKeDoanhThu {
    private String thang;
//    private String nam;
    private int doanhThu;

    public ThongKeDoanhThu(String thang, int doanhThu) {
        this.thang = thang;
        this.doanhThu = doanhThu;
    }

    public ThongKeDoanhThu() {
    }

    public String getThang() {
        return thang;
    }

    public void setThang(String thang) {
        this.thang = thang;
    }


    public int getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(int doanhThu) {
        this.doanhThu = doanhThu;
    }
}
