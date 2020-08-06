/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.dao;

import duan1.nhom8.helper.JdbcHelper;
import duan1.nhom8.model.TrangThaiSach;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class TrangThaiSachDao {
    private TrangThaiSach readFromResultSet(ResultSet rs) throws SQLException{
        TrangThaiSach model = new TrangThaiSach();
        model.setMaTrangThai(rs.getInt("MaTrangThai"));
        model.setMaPhieuMuon(rs.getInt("MaPhieuMuon"));
        model.setMaSach(rs.getInt("MaSach"));
        model.setTrangThai(rs.getBoolean("TrangThai"));
        return model;
    }
    
    private List<TrangThaiSach> select(String sql, Object... args) {
        List<TrangThaiSach> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                try {
                    rs = JdbcHelper.executeQuery(sql, args);
                } catch (Exception e) {
                    System.out.println(e);
                }
                while (rs.next()) {
                    TrangThaiSach model = readFromResultSet(rs);
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
    
    public void save(TrangThaiSach model){
        String sql = "Insert Into TrangThaiSach (maPhieuMuon, maSach, TrangThai) Values (?, ?, ?)";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getMaPhieuMuon(),
                    model.getMaSach(),
                    model.isTrangThai());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void update(TrangThaiSach model) {
        String sql = "Update TrangThaiSach set trangThai = ? where maTrangThai = ?";
        try {
            JdbcHelper.executeUpdate(sql, 
                    model.isTrangThai(),
                    model.getMaTrangThai());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void delete(int maTrangThaiSach) {
        String sql = "Delete From TrangThaiSach Where maTrangThai = ?";
        try {
            JdbcHelper.executeUpdate(sql, maTrangThaiSach);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public TrangThaiSach findById(Integer maTT) {
        String sql = "Select * From TrangThaiSach Where maTrangThai = ?";
        List<TrangThaiSach> list = select(sql, maTT);
        return list.size() > 0 ? list.get(0) : null;
    }
}
