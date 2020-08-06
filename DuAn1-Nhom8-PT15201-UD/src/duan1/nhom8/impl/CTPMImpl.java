/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.impl;

import duan1.nhom8.dao.SachDao;
import duan1.nhom8.helper.JdbcHelper;
import duan1.nhom8.i.CTPMInterface;
import duan1.nhom8.model.Sach;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class CTPMImpl implements CTPMInterface{

    SachDao sachDao = new SachDao();
    
    @Override
    public void fillCombobox(JComboBox cbbSach, Integer maPM) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbSach.getModel();
        model.removeAllElements();
        try {
            List<Sach> list = sachDao.selectByTrangThaiSach(maPM);
            for (Sach s : list) {
                model.addElement(s);
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn cbb sách: " + e);
        }
    }

    @Override
    public void load(JTable tbSachMuon, Integer maPM) {
        DefaultTableModel model = (DefaultTableModel) tbSachMuon.getModel();
        model.setRowCount(0);
        try {
            String sql = "Select * from trangThaiSach tts inner join sach s on tts.maSach = s.maSach where maPhieuMuon = ?";
            ResultSet rs = JdbcHelper.executeQuery(sql, maPM);
            while (rs.next()) {                
                Object[] row = {
                    rs.getString("tenSach"),
                    rs.getString("LoaiSach"),
                    rs.getString("ViTri"),
                    rs.getBoolean("TrangThai"),
                    false
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println("Lỗi truy vấn load: " + e);
        }
    }
    
}
