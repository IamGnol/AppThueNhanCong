package com.example.loginandregister;

import java.io.Serializable;

public class DichVu  {
    String anh,ten,mota,gia;

    public DichVu() {
    }

    public DichVu(String anh, String ten, String mota, String gia) {
        this.anh = anh;
        this.ten = ten;
        this.mota = mota;
        this.gia = gia;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
    //    public DichVu() {
//    }
//
//    public DichVu(String anh, String ten, String mota) {
//        this.anh = anh;
//        this.ten = ten;
//        this.mota = mota;
//    }
//
//    public String getAnh() {
//        return anh;
//    }
//
//    public void setAnh(String anh) {
//        this.anh = anh;
//    }
//
//    public String getTen() {
//        return ten;
//    }
//
//    public void setTen(String ten) {
//        this.ten = ten;
//    }
//
//    public String getMota() {
//        return mota;
//    }
//
//    public void setMota(String mota) {
//        this.mota = mota;
//    }

}
