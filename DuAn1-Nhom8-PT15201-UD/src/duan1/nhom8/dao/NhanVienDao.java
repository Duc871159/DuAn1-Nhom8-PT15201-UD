/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.dao;

import duan1.nhom8.helper.JdbcHelper;
import duan1.nhom8.model.NguoiDungNhanVien;
import duan1.nhom8.model.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class NhanVienDao {

    private NguoiDungNhanVien readFromResultSet(ResultSet rs) throws SQLException {
        NguoiDungNhanVien model = new NguoiDungNhanVien();
        model.setMaNhanVien(rs.getInt("MaNhanVien"));
        model.setCaLamViec(rs.getInt("CaLamViec"));
        model.setLuong(rs.getFloat("Luong"));
        model.setMaNguoiDung(rs.getString("MaNguoiDung"));
        model.setHoTen(rs.getString("HoTen"));
        model.setSoDienThoai(rs.getString("SoDienThoai"));
        model.setEmail(rs.getString("Email"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        return model;
    }

    private List<NguoiDungNhanVien> select(String sql, Object... args) {
        List<NguoiDungNhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                try {
                    rs = JdbcHelper.executeQuery(sql, args);
                } catch (Exception e) {
                    System.out.println(e);
                }
                while (rs.next()) {
                    NguoiDungNhanVien model = readFromResultSet(rs);
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

    public List<NguoiDungNhanVien> select() {
        String sql = "Select nv.maNhanVien, nv.maNguoiDung, nv.caLamviec, nv.luong, nd.HoTen, nd.GioiTinh, nd.SoDienThoai, nd.Email \n"
                + "from nhanVien as nv inner join nguoiDung as nd on nv.maNguoiDung = nd.maNguoiDung";
        return select(sql);
    }

    public List<NguoiDungNhanVien> selectByKeyword(String key1, String key2, String key3, String key4, String key5, String key6, String key7) {
        String sql = "Select nv.maNhanVien, nv.maNguoiDung, nv.caLamviec, nv.luong, nd.HoTen, nd.GioiTinh, nd.SoDienThoai, nd.Email\n"
                + "from nhanVien as nv inner join nguoiDung as nd on nv.maNguoiDung = nd.maNguoiDung\n"
                + "where nv.maNhanVien like ? or nv.caLamviec like ? or nv.luong like ? or nv.maNguoiDung like ? or nd.HoTen like ? or nd.SoDienThoai like ? or nd.email like ?";
        return select(sql, "%" + key1 + "%", "%" + key2 + "%", "%" + key3 + "%", "%" + key4 + "%", "%" + key5 + "%", "%" + key6 + "%", "%" + key7 + "%");
    }
    
    public void save(NhanVien model) {
        String sql = "Insert Into NhanVien (maNguoiDung, CaLamViec, Luong) Values (?, ?, ?)";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getMaNguoiDung(),
                    model.getCaLamViec(),
                    model.getLuong());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
