/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.model;

import java.util.Date;

/**
 *
 * @author User
 */
public class PhieuMuon {
    private int maPhieuMuon;
    private Date NgayMuon;
    private Date NgayTra;
    private int guiMail;
    private int maNguoiDoc;
    private int maNhanVien;
    private boolean trangThai;

    public int getMaPhieuMuon() {
        return maPhieuMuon;
    }

    public void setMaPhieuMuon(int maPhieuMuon) {
        this.maPhieuMuon = maPhieuMuon;
    }

    public Date getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(Date NgayMuon) {
        this.NgayMuon = NgayMuon;
    }

    public Date getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(Date NgayTra) {
        this.NgayTra = NgayTra;
    }

    public int getGuiMail() {
        return guiMail;
    }

    public void setGuiMail(int guiMail) {
        this.guiMail = guiMail;
    }

    public int getMaNguoiDoc() {
        return maNguoiDoc;
    }

    public void setMaNguoiDoc(int maNguoiDoc) {
        this.maNguoiDoc = maNguoiDoc;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
}
