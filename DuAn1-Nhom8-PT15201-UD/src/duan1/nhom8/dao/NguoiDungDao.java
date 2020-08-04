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
        model.setHinhAnh(rs.getString("hinhAnh"));
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
    
    public void save(NguoiDung model) {
        String sql = "INSERT INTO NguoiDung (maNguoiDung, MatKhau, vaiTro, HoTen, diaChi, gioiTinh, ngaySinh, email, soDienThoai, hinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                    model.getSoDienThoai(),
                    model.getHinhAnh());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void update(NguoiDung model) {
        String sql
                = "Update NguoiDung Set MatKhau=?, VaiTro=?, HoTen=?, DiaChi=?, GioiTinh=?, NgaySinh=?, Email=?, SoDienThoai=?, HinhAnh=? Where MaNguoiDung=?";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getMatKhau(),
                    model.isVaiTro(),
                    model.getHoTen(),
                    model.getDiaChi(),
                    model.isGioiTinh(),
                    model.getNgaySinh(),
                    model.getEmail(),
                    model.getSoDienThoai(),
                    model.getHinhAnh(),
                    model.getMaNguoiDung());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void delete(String maNguoiDung) {
        String sql = "Delete From NguoiDung WHERE MaNguoiDung = ?";
        try {
            JdbcHelper.executeUpdate(sql, maNguoiDung);
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
    
    public NguoiDung findByEmail(String email) {
        String sql = "SELECT * FROM NguoiDung WHERE email = ?";
        List<NguoiDung> list = select(sql, email);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public NguoiDung findByEmailUpdate(String email, String tb) {
        String sql = "Select * from NguoiDung where Email = ? and not Email = ?";
        List<NguoiDung> list = select(sql, email, tb);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public NguoiDung findBySDT(String sdt) {
        String sql = "SELECT * FROM NguoiDung WHERE soDienThoai = ?";
        List<NguoiDung> list = select(sql, sdt);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public NguoiDung findBySdtUpdate(String sdt, String tb) {
        String sql = "Select * from NguoiDung where SoDienThoai = ? and not SoDienThoai = ?";
        List<NguoiDung> list = select(sql, sdt, tb);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public List<NguoiDung> selectByKeyword(String key1, String key2, String key3, String key4, String key5, String key6) {
        String sql = "Select * FROM NguoiDung \n" +
"where HoTen like ? or maNguoiDung like ? or DiaChi like ? or Email like ? or SoDienThoai like ? or ngaySinh like ?";
        return select(sql, "%" + key1 + "%", "%" + key2 + "%", "%" + key3 + "%", "%" + key4 + "%", "%" + key5 + "%", "%" + key6 + "%");
    }
    
}
