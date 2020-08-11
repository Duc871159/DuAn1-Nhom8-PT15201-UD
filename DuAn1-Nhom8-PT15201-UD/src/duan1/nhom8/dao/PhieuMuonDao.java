/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.dao;

import duan1.nhom8.helper.JdbcHelper;
import duan1.nhom8.model.NhanVien;
import duan1.nhom8.model.PhieuMuon;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class PhieuMuonDao {
    
    private PhieuMuon readFromResultSet(ResultSet rs) throws SQLException{
        PhieuMuon model = new PhieuMuon();
        model.setMaPhieuMuon(rs.getInt("MaPhieuMuon"));
        model.setMaNguoiDoc(rs.getInt("MaNguoiDoc"));
        model.setMaNhanVien(rs.getInt("MaNhanVien"));
        model.setNgayMuon(rs.getDate("NgayMuon"));
        model.setNgayTra(rs.getDate("NgayTra"));
        model.setGuiMail(rs.getInt("GuiMail"));
        model.setTrangThai(rs.getBoolean("TrangThai"));
        return model;
    }
    
    private List<PhieuMuon> select(String sql, Object... args) {
        List<PhieuMuon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                try {
                    rs = JdbcHelper.executeQuery(sql, args);
                } catch (Exception e) {
                    System.out.println(e);
                }
                while (rs.next()) {
                    PhieuMuon model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    public List<PhieuMuon> select() {
            String sql = "SELECT * FROM PhieuMuon";
            return select(sql);
    }
    
    public void save(PhieuMuon model) {
        String sql = "Insert Into PhieuMuon (maNguoiDoc, maNhanVien, ngayMuon, ngayTra, trangthai) Values (?, ?, ?, ?, 0)";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getMaNguoiDoc(),
                    model.getMaNhanVien(),
                    model.getNgayMuon(),
                    model.getNgayTra());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void update(PhieuMuon model) {
        String sql = "Update PhieuMuon set ngayTra = ? Where maPhieuMuon = ?";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getNgayTra(),
                    model.getMaPhieuMuon());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateGuiMail(PhieuMuon model) {
        String sql = "Update PhieuMuon set guiMail = guiMail + 1 Where maPhieuMuon = ?";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getMaPhieuMuon());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateTrue(PhieuMuon model) {
        String sql = "Update PhieuMuon set trangThai = 1 Where maPhieuMuon = ?";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getMaPhieuMuon());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateFalse(PhieuMuon model) {
        String sql = "Update PhieuMuon set trangThai = 0 Where maPhieuMuon = ?";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getMaPhieuMuon());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void delete(PhieuMuon model) {
        String sql = "Delete From PhieuMuon Where maPhieuMuon = ?";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getMaPhieuMuon());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public PhieuMuon findById(Integer maPM) {
        String sql = "Select * From PhieuMuon Where maPhieuMuon = ?";
        List<PhieuMuon> list = select(sql, maPM);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public List<PhieuMuon> selectByKeyword(String key1, String key2, String key3) {
        String sql = "Select * from PhieuMuon where maPhieuMuon like ? or NgayMuon like ? or NgayTra like ?";
        return select(sql, "%" + key1 + "%", "%" + key2 + "%", "%" + key3 + "%");
    }
}
