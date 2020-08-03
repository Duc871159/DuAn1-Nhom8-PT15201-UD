/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.dao;

import duan1.nhom8.helper.JdbcHelper;
import duan1.nhom8.model.NguoiDoc;

/**
 *
 * @author User
 */
public class NguoiDocDao {
    public void save(NguoiDoc model) {
        String sql = "INSERT INTO NguoiDoc (maNguoiDung) VALUES (?)";
        try {
            JdbcHelper.executeUpdate(sql,
                    model.getMaNguoiDung());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
