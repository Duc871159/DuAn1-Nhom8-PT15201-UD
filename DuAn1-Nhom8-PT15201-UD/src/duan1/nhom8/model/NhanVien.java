/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.model;

/**
 *
 * @author User
 */
public class NhanVien {
    private int maNhanVien;
    private int caLamViec;
    private float luong;
    private String maNguoiDung;

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public int getCaLamViec() {
        return caLamViec;
    }

    public void setCaLamViec(int caLamViec) {
        this.caLamViec = caLamViec;
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }
}
