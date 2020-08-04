/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.impl;

import duan1.nhom8.dao.NguoiDungDao;
import duan1.nhom8.helper.DialogHelper;
import duan1.nhom8.i.NhanVienInterface;
import duan1.nhom8.model.NguoiDung;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class NhanVienImpl implements NhanVienInterface{

    NguoiDungDao nguoiDungDao = new NguoiDungDao();
    
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
        fillCombobox(cbbNguoiDung);
        NguoiDung nguoiDung = (NguoiDung) cbbNguoiDung.getSelectedItem();
        txtHoTen.setText(nguoiDung.getHoTen());
        txtEmail.setText(nguoiDung.getEmail());
        txtSDT.setText(nguoiDung.getSoDienThoai());
        if (nguoiDung.isGioiTinh()) {
            rbNam.setSelected(true);
        } if (!nguoiDung.isGioiTinh()) {
            rbNu.setSelected(true);
        }
    }
    
}
