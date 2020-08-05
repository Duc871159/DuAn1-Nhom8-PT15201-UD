/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.dao;

import duan1.nhom8.helper.JdbcHelper;
import duan1.nhom8.model.NguoiDoc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class NguoiDocDao {

    private NguoiDoc readFromResultSet(ResultSet rs) throws SQLException {
        NguoiDoc model = new NguoiDoc();
        model.setMaNguoiDoc(rs.getInt("MaNguoiDoc"));
        model.setMaNguoiDung(rs.getString("maNguoiDung"));
        return model;
    }

    private List<NguoiDoc> select(String sql, Object... args) {
        List<NguoiDoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                try {
                    rs = JdbcHelper.executeQuery(sql, args);
                } catch (Exception e) {
                    System.out.println(e);
                }
                while (rs.next()) {
                    NguoiDoc model = readFromResultSet(rs);
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

    public List<NguoiDoc> select() {
        String sql = "SELECT * FROM NguoiDoc";
        return select(sql);
    }

    public void save(NguoiDoc model) {
        String sql = "INSERT INTO NguoiDoc (maNguoiDung) VALUES (?)";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getMaNguoiDung());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void deleteNguoiDung(String maNguoiDung) {
        String sql = "Delete From NguoiDoc WHERE MaNguoiDung = ?";
        try {
            JdbcHelper.executeUpdate(sql, maNguoiDung);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public NguoiDoc findById(int maNguoiDoc) {
        String sql = "SELECT * FROM NguoiDoc WHERE maNguoiDoc = ?";
        List<NguoiDoc> list = select(sql, maNguoiDoc);
        return list.size() > 0 ? list.get(0) : null;
    }
    
    public NguoiDoc findByMaND(String maNguoiDung) {
        String sql = "SELECT * FROM NguoiDoc WHERE maNguoiDung = ?";
        List<NguoiDoc> list = select(sql, maNguoiDung);
        return list.size() > 0 ? list.get(0) : null;
    }
}
