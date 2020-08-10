/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.impl;

import duan1.nhom8.dao.PhieuMuonDao;
import duan1.nhom8.dao.SachDao;
import duan1.nhom8.dao.TrangThaiSachDao;
import duan1.nhom8.helper.DateHelper;
import duan1.nhom8.helper.DialogHelper;
import duan1.nhom8.helper.JdbcHelper;
import duan1.nhom8.i.CTPMInterface;
import duan1.nhom8.model.PhieuMuon;
import duan1.nhom8.model.Sach;
import duan1.nhom8.model.TrangThaiSach;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class CTPMImpl implements CTPMInterface {

    PhieuMuonDao phieuMuonDao = new PhieuMuonDao();
    SachDao sachDao = new SachDao();
    TrangThaiSachDao dao = new TrangThaiSachDao();

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
                    rs.getInt("MaTrangThai"),
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

    @Override
    public boolean save(JComboBox cbbSach, Integer maPM) {
        Sach sach = (Sach) cbbSach.getSelectedItem();
        TrangThaiSach model = new TrangThaiSach();
        model.setMaPhieuMuon(maPM);
        model.setMaSach(sach.getMaSach());
        model.setTrangThai(false);
        try {
            dao.save(model);
            sachDao.updateMuon(sach);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(JTable tbSachMuon, JTextField txtMaTT) {
        if (DialogHelper.confirm(null, "Bạn thực sự muốn xóa phiếu mượn này?")) {
            Integer maTT = Integer.valueOf(txtMaTT.getText());
            try {
                TrangThaiSach model = dao.findById(maTT);
                Sach sach = sachDao.findById(model.getMaSach());
                dao.delete(maTT);
                sachDao.updateTra(sach);
                return true;
            } catch (HeadlessException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public void edit(JTable tbDSND, int index, int maPM, JTextField txtMaTrangThai, JTextField txtNgayMuon, JTextField txtNgayTra, JTextField txtTenSach, JTextField txtTheLoai, JTextField txtViTri, JRadioButton rbChuaTra, JRadioButton rbDaTra) {
        try {
            Integer maTT = (Integer) tbDSND.getValueAt(index, 0);
            TrangThaiSach model = dao.findById(maTT);
            Sach sach = sachDao.findById(model.getMaSach());
            PhieuMuon phieuMuon = phieuMuonDao.findById(maPM);
            txtMaTrangThai.setText(String.valueOf(model.getMaTrangThai()));
            txtTenSach.setText(sach.getTenSach());
            txtTheLoai.setText(sach.getLoaiSach());
            txtViTri.setText(sach.getViTri());
            txtNgayMuon.setText(DateHelper.toString(phieuMuon.getNgayMuon()));
            txtNgayTra.setText(DateHelper.toString(phieuMuon.getNgayTra()));
            if (model.isTrangThai()) {
                rbDaTra.setSelected(true);
            }
            if (!model.isTrangThai()) {
                rbChuaTra.setSelected(true);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean update(JTextField txtMaTT, JRadioButton rbChuaTra, JRadioButton rbDaTra) {
        TrangThaiSach model = dao.findById(Integer.valueOf(txtMaTT.getText()));
        try {      
            Sach sach = sachDao.findById(model.getMaSach());
            if (rbChuaTra.isSelected()) {
                model.setTrangThai(false);
                dao.update(model);
                return true;
            }
            if (rbDaTra.isSelected()) {
                model.setTrangThai(true);
                dao.update(model);
                sachDao.updateTra(sach);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
