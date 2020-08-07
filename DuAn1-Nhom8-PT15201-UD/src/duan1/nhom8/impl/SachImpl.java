/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.impl;

import duan1.nhom8.dao.SachDao;
import duan1.nhom8.i.SachInterface;
import duan1.nhom8.model.Sach;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class SachImpl implements SachInterface {

    SachDao sachDao = new SachDao();

    @Override
    public void loadTrangChu(JTable tbSach, JTextField txtTimKiem) {
        DefaultTableModel model = (DefaultTableModel) tbSach.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<Sach> list = sachDao.selectByKeyword(keyword, keyword, keyword, keyword, keyword);
            for (Sach s : list) {
                Object[] row = {
                    s.getMaSach(),
                    s.getTenSach(),
                    s.getLoaiSach(),
                    s.getTacGia(),
                    s.getNhaXuatBan(),
                    s.getViTri(),
                    s.getSoLuong() > 0 ? "Chưa cho mượn" : "Đã cho mượn"
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
