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
    public void insert(Sach model) {
        String sql
                = "INSERT INTO Sach (maSach,TenSach,LoaiSach,tacGia,NhaSuatBan,SoLuong,ViTri,HinhAnh) VALUES( ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?)";
        JdbcHelper.executeUpdate(sql,
                model.getMaSach(),
                model.getTenSach(),
                model.getLoaiSach(),
                model.getTacGia(),
                model.getNhaXuatBan(),
                model.getSoLuong(),
                model.getViTri(),
                model.getHinhAnh());
    }
    public void update(Sach model) {
        String sql = "UPDATE Sach SET TenSach=?,LoaiSach=?,tacGia=?,NhaSuatBan=?,SoLuong=?,ViTri=?,HinhAnh=? HoTen=? WHERE maSach=?";
        JdbcHelper.executeUpdate(sql,
                model.getTenSach(),
                model.getLoaiSach(),
                model.getTacGia(),
                model.getNhaXuatBan(),
                model.getSoLuong(),
                model.getViTri(),
                model.getHinhAnh());
    }
    public void delete(String maSach) {
        String sql = "DELETE FROM Sach WHERE maSach=?";
        JdbcHelper.executeUpdate(sql, maSach);
    }
    public Sach findById(String maSach){
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = select(sql,maSach);
        return list.size()>0 ? list.get(0): null;
    }
    
    public List<Sach> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Sach WHERE TenSach LIKE ?";
        return select(sql, "%" + keyword + "%");
    }
}
