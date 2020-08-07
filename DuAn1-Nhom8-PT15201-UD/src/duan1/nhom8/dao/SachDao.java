/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.dao;

import duan1.nhom8.helper.JdbcHelper;
import duan1.nhom8.model.Sach;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class SachDao {
    private Sach readFromResultSet(ResultSet rs) throws SQLException{
        Sach model = new Sach();
        model.setMaSach(rs.getInt("maSach"));
        model.setTenSach(rs.getString("TenSach"));
        model.setLoaiSach(rs.getString("LoaiSach"));
        model.setTacGia(rs.getString("tacGia"));
        model.setNhaXuatBan(rs.getString("NhaXuatBan"));
        model.setSoLuong(rs.getInt("SoLuong"));
        model.setViTri(rs.getString("ViTri"));
        model.setHinhAnh(rs.getString("HinhAnh"));
        return model;
    }
    
    private List<Sach> select(String sql, Object... args) {
        List<Sach> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                try {
                    rs = JdbcHelper.executeQuery(sql, args);
                } catch (Exception e) {
                    System.out.println(e);
                }
                while (rs.next()) {
                    Sach model = readFromResultSet(rs);
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
    
    public List<Sach> select() {
        String sql = "Select * From Sach";
        return select(sql);
    }
    
    //Lấy danh sách sách trừ những sách đã có trong phiếu mượn
    public List<Sach> selectByTrangThaiSach(Integer maPM) {
        String sql = "Select * from sach where soLuong > 0 and maSach not in (Select maSach from trangThaiSach where maPhieuMuon = ?)";
        return select(sql, maPM);
    }
    
    public void updateMuon(Sach model) {
        String sql = "Update Sach set SoLuong = SoLuong - 1 Where maSach = ?";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getMaSach());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void updateTra(Sach model) {
        String sql = "Update Sach set SoLuong = SoLuong + 1 Where maSach = ?";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getMaSach());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public Sach findById(Integer maSach) {
        String sql = "Select * From Sach Where maSach = ?";
        List<Sach> list = select(sql, maSach);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public List<Sach> selectByKeyword(String key1, String key2, String key3, String key4, String key5) {
        String sql = "Select * from Sach where TenSach like ? or LoaiSach like ? or TacGia like ? or NhaXuatBan like ? or ViTri like ?";
        return select(sql, "%" + key1 + "%", "%" + key2 + "%", "%" + key3 + "%", "%" + key4 + "%", "%" + key5 + "%");
    }
}
