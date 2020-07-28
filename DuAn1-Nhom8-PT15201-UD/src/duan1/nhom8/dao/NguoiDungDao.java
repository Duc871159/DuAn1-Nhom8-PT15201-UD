/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.dao;

import duan1.nhom8.helper.JdbcHelper;
import duan1.nhom8.model.NguoiDung;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class NguoiDungDao {
    
    private NguoiDung readFromResultSet(ResultSet rs) throws SQLException{
        NguoiDung model = new NguoiDung();
        model.setMaNguoiDung(rs.getString("maNguoiDung"));
        model.setMatKhau(rs.getString("matKhau"));
        model.setVaiTro(rs.getBoolean("vaiTro"));
        model.setHoTen(rs.getString("hoTen"));
        model.setDiaChi(rs.getString("diaChi"));
        model.setGioiTinh(rs.getBoolean("gioiTinh"));
        model.setNgaySinh(rs.getDate("ngaySinh"));
        model.setEmail(rs.getString("email"));
        model.setSoDienThoai(rs.getString("soDienThoai"));
        return model;
    }
    
    private List<NguoiDung> select(String sql, Object... args) {
        List<NguoiDung> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                try {
                    rs = JdbcHelper.executeQuery(sql, args);
                } catch (Exception e) {
                    System.out.println(e);
                }
                while (rs.next()) {
                    NguoiDung model = readFromResultSet(rs);
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
    
    public void insert(NguoiDung model) {
        String sql = "INSERT INTO NguoiDung (maNguoiDung, MatKhau, vaiTro, HoTen, diaChi, gioiTinh, ngaySinh, email, soDienThoai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getMaNguoiDung(),
                    model.getMatKhau(),
                    model.isVaiTro(),
                    model.getHoTen(),
                    model.getDiaChi(), 
                    model.isGioiTinh(),
                    model.getNgaySinh(),
                    model.getEmail(),
                    model.getSoDienThoai());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public List<NguoiDung> select() {
            String sql = "SELECT * FROM NguoiDung";
            return select(sql);
    }
    
    public NguoiDung findById(String maNguoiDung) {
        String sql = "SELECT * FROM NguoiDung WHERE maNguoiDung = ?";
        List<NguoiDung> list = select(sql, maNguoiDung);
        return list.size() > 0 ? list.get(0) : null;
    }
    
}
