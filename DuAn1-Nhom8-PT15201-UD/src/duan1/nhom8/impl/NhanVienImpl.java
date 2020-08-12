/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.impl;

import duan1.nhom8.dao.NguoiDungDao;
import duan1.nhom8.dao.NhanVienDao;
import duan1.nhom8.helper.DateHelper;
import duan1.nhom8.helper.DialogHelper;
import duan1.nhom8.helper.ShareHelper;
import duan1.nhom8.helper.UHelper;
import duan1.nhom8.i.NhanVienInterface;
import duan1.nhom8.model.NguoiDung;
import duan1.nhom8.model.NguoiDungNhanVien;
import duan1.nhom8.model.NhanVien;
import java.awt.HeadlessException;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
    public void load(JTable tbDSNV, JTextField txtTimKiem) {
        DefaultTableModel model = (DefaultTableModel) tbDSNV.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<NguoiDungNhanVien> list = dao.selectByKeyword(keyword, keyword, keyword, keyword, keyword, keyword, keyword, keyword, keyword);
//            List<NguoiDungNhanVien> list = dao.select();            
            for (NguoiDungNhanVien ndnv : list) {
                int lung = (int) ndnv.getLuong();
                Object[] row = {
                    ndnv.getMaNhanVien(),
                    ndnv.getCaLamViec(),
                    lung,
                    ndnv.getMaNguoiDung(),
                    ndnv.getMatKhau(),
                    ndnv.getHoTen(),
                    DateHelper.toString(ndnv.getNgaySinh()),
                    ndnv.isGioiTinh() ? "Nam" : "Nữ",
                    ndnv.getSoDienThoai(),
                    ndnv.getEmail(),
                    ndnv.getDiaChi(),
                    ndnv.getHinhAnh()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
        }
    }

    @Override
    public boolean save(JTextField txtCaLamViec, JTextField txtLuong, JTextField txtTaiKhoan) {
        NhanVien model = new NhanVien();
        model.setCaLamViec(Integer.parseInt(txtCaLamViec.getText()));
        model.setLuong(Float.parseFloat(txtLuong.getText()));
        model.setMaNguoiDung(txtTaiKhoan.getText());
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
    public boolean edit(JTable tbDSNV, int index, JTextField txtMaNV, JTextField txtCaLamViec, JTextField txtLuong, JTextField txtTaiKhoan, JPasswordField txxMatKhau, JTextField txtHoVaTen, JTextField txtNgaySinh, JTextField txtSoDienThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh) {
        Integer maNV = (Integer) tbDSNV.getValueAt(index, 0);
        NguoiDungNhanVien model = dao.findById(maNV);
        if (model != null) {
            txtMaNV.setText(String.valueOf(model.getMaNhanVien()));
            txtCaLamViec.setText(String.valueOf(model.getCaLamViec()));
            int lung = (int) model.getLuong();
            txtLuong.setText(String.valueOf(lung));
            txtTaiKhoan.setText(model.getMaNguoiDung());
            txxMatKhau.setText(model.getMatKhau());
            txtHoVaTen.setText(model.getHoTen());
            txtNgaySinh.setText(DateHelper.toString(model.getNgaySinh()));
            txtSoDienThoai.setText(model.getSoDienThoai());
            txtEmail.setText(model.getEmail());
            if (model.isGioiTinh()) {
                rbNam.setSelected(true);
            }
            if (!model.isGioiTinh()) {
                rbNu.setSelected(true);
            }
            txtDiaChi.setText(model.getDiaChi());
            if (model.getHinhAnh() != null) {
                lblHinhAnh.setIcon(ShareHelper.readLogo(model.getHinhAnh()));
            } else {
                lblHinhAnh.setIcon(null);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean update(JTextField txtCaLamViec, JTextField txtTaiKhoan, JTextField txtLuong, JTextField txtMaNV) {
        NhanVien model = new NhanVien();
        model.setCaLamViec(Integer.parseInt(txtCaLamViec.getText()));
        model.setLuong(Float.parseFloat(txtLuong.getText()));
        model.setMaNguoiDung(txtTaiKhoan.getText());
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
        if (DialogHelper.confirm(null, "Bạn thực sự muốn xóa nhân viên này?")) {
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
