/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.impl;

import duan1.nhom8.dao.NguoiDungDao;
import duan1.nhom8.dao.NhanVienDao;
import duan1.nhom8.helper.DialogHelper;
import duan1.nhom8.helper.UHelper;
import duan1.nhom8.i.NhanVienInterface;
import duan1.nhom8.model.NguoiDung;
import duan1.nhom8.model.NguoiDungNhanVien;
import duan1.nhom8.model.NhanVien;
import java.awt.HeadlessException;
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
public class NhanVienImpl implements NhanVienInterface {

    NguoiDungDao nguoiDungDao = new NguoiDungDao();
    NhanVienDao dao = new NhanVienDao();

    @Override
    public void fillCombobox(JComboBox cbbNguoiDung) {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbNguoiDung.getModel();
        model.removeAllElements();
        try {
            List<NguoiDung> list = nguoiDungDao.selectNv();
            for (NguoiDung nd : list) {
                model.addElement(nd);
            }
        } catch (Exception e) {
            DialogHelper.alert(null, "Lỗi truy vấn combobox");
        }
    }

    @Override
    public void selectCombobox(JComboBox cbbNguoiDung, JTextField txtHoTen, JTextField txtSDT, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu) {
//        fillCombobox(cbbNguoiDung);
        NguoiDung nguoiDung = (NguoiDung) cbbNguoiDung.getSelectedItem();
        txtHoTen.setText(nguoiDung.getHoTen());
        txtEmail.setText(nguoiDung.getEmail());
        txtSDT.setText(nguoiDung.getSoDienThoai());
        if (nguoiDung.isGioiTinh()) {
            rbNam.setSelected(true);
        }
        if (!nguoiDung.isGioiTinh()) {
            rbNu.setSelected(true);
        }
    }

    @Override
    public void load(JTable tbDSNV, JTextField txtTimKiem) {
        DefaultTableModel model = (DefaultTableModel) tbDSNV.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<NguoiDungNhanVien> list = dao.selectByKeyword(keyword, keyword, keyword, keyword, keyword, keyword, keyword);
//            List<NguoiDungNhanVien> list = dao.select();            
            for (NguoiDungNhanVien ndnv : list) {
                Object[] row = {
                    ndnv.getMaNhanVien(),
                    ndnv.getCaLamViec(),
                    ndnv.getLuong(),
                    ndnv.getMaNguoiDung(),
                    ndnv.getHoTen(),
                    ndnv.isGioiTinh() ? "Nam" : "Nữ",
                    ndnv.getSoDienThoai(),
                    ndnv.getEmail()                      
                };
                model.addRow(row);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public boolean save(JTextField txtCaLamViec, JTextField txtLuong, JComboBox cbbNguoiDung) {
        NguoiDung nguoiDung = (NguoiDung) cbbNguoiDung.getSelectedItem();
        NhanVien model = new NhanVien();
        model.setCaLamViec(Integer.parseInt(txtCaLamViec.getText()));
        model.setLuong(Float.parseFloat(txtLuong.getText()));
        model.setMaNguoiDung(nguoiDung.getMaNguoiDung());
        try {
            if (!UHelper.checkNull(txtLuong, "Lương")) {
                return false;
            }
            if (!UHelper.checkNull(txtCaLamViec, "Ca làm việc")) {
                return false;
            }
            dao.save(model);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean edit(JTable tbDSNV, int index, JComboBox cbbNguoiDung, JTextField txtMaNV, JTextField txtLuong, JTextField txtCaLamViec, JTextField txtHoTen, JTextField txtSDT, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu) {
        Integer maNV = (Integer) tbDSNV.getValueAt(index, 0);
        NguoiDungNhanVien model = dao.findById(maNV);
        if (model != null) {
            cbbNguoiDung.setToolTipText(String.valueOf(model.getMaNhanVien()));
            cbbNguoiDung.getModel().setSelectedItem(nguoiDungDao.findById(model.getMaNguoiDung()));
            txtMaNV.setText(String.valueOf(model.getMaNhanVien()));
            txtCaLamViec.setText(String.valueOf(model.getCaLamViec()));
            txtLuong.setText(String.valueOf(model.getLuong()));
            txtHoTen.setText(model.getHoTen());
            txtEmail.setText(model.getEmail());
            txtSDT.setText(model.getSoDienThoai());
            if (model.isGioiTinh()) {
                rbNam.setSelected(true);
            }
            if (!model.isGioiTinh()) {
                rbNu.setSelected(true);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean update(JTextField txtCaLamViec, JTextField txtLuong, JComboBox cbbNguoiDung, JTextField txtMaNV) {
        NguoiDung nguoiDung = (NguoiDung) cbbNguoiDung.getSelectedItem();
        NhanVien model = new NhanVien();
        model.setCaLamViec(Integer.parseInt(txtCaLamViec.getText()));
        model.setLuong(Float.parseFloat(txtLuong.getText()));
        model.setMaNguoiDung(nguoiDung.getMaNguoiDung());
        model.setMaNhanVien(Integer.parseInt(txtMaNV.getText()));
        try {
            if (!UHelper.checkNull(txtLuong, "Lương")) {
                return false;
            }
            if (!UHelper.checkNull(txtCaLamViec, "Ca làm việc")) {
                return false;
            }
            dao.update(model);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(JTextField txtMaNV) {
        if (DialogHelper.confirm(null, "Bạn thực sự muốn xóa người học này?")) {
            int maNv = Integer.parseInt(txtMaNV.getText());
            try {
                dao.delete(maNv);
                return true;
            } catch (HeadlessException e) {
                return false;
            }
        }
        return false;
    }

}
