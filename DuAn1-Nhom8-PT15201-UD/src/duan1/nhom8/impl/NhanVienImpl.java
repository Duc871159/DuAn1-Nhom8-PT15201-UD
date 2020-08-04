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
            } if (!UHelper.checkNull(txtCaLamViec, "Ca làm việc")) {
                return false;
            }
            dao.save(model);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
