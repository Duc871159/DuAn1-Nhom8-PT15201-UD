/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.impl;

import duan1.nhom8.dao.NguoiDocDao;
import duan1.nhom8.dao.NguoiDungDao;
import duan1.nhom8.helper.DateHelper;
import duan1.nhom8.helper.DialogHelper;
import duan1.nhom8.helper.ShareHelper;
import duan1.nhom8.helper.UHelper;
import duan1.nhom8.i.NguoiDungInterface;
import duan1.nhom8.model.NguoiDoc;
import duan1.nhom8.model.NguoiDung;
import duan1.nhom8.ui.DangNhapJDialog;
import java.awt.HeadlessException;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
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
public class NguoiDungImpl implements NguoiDungInterface{

    NguoiDungDao dao = new NguoiDungDao();
    NguoiDocDao nguoiDocDao = new NguoiDocDao();
    
    @Override
    public boolean login(JTextField txtTenDN, JPasswordField txxMk) {
        if (!UHelper.checkNull(txtTenDN, "Tên đăng nhập")) {
            return false;
        }
        if (!UHelper.checkNull(txxMk, "Mật khẩu")) {
            return false;
        }
        String maNguoiDung = txtTenDN.getText();
        String matKhauInput = new String(txxMk.getPassword());
        try {
            NguoiDung nguoidung = dao.findById(maNguoiDung);
            if (nguoidung != null) {
                String matKhau = nguoidung.getMatKhau();
                if (matKhauInput.equals(matKhau)) {
                    ShareHelper.USER = nguoidung;
                    DialogHelper.alert(null, "Đăng nhập thành công");
                    System.out.println(nguoidung);
                    return true;
                } else{
                    DialogHelper.alert(null, "Sai mật khẩu !");
                    return false;
                }
            } else{
                DialogHelper.alert(null, "Sai tên đăng nhâp !");
                return false;
            }
        } catch (Exception e) {
            System.err.println(e);
            System.out.println(ShareHelper.USER);
        }
        return false;
    }


    
    @Override
    public void load(JTable tbDSND, JTextField txtTimKiem) {
        DefaultTableModel model = (DefaultTableModel) tbDSND.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<NguoiDung> list = dao.selectByKeyword(keyword, keyword, keyword, keyword, keyword, keyword);
            for (NguoiDung nd : list) {
                Object[] row = {
                    nd.getMaNguoiDung(),
                    nd.getMatKhau(),
                    nd.isVaiTro() ? "Nhân viên" : "Người đọc",
                    nd.getHoTen(),
                    nd.getDiaChi(),
                    nd.isGioiTinh() ? "Nam" : "Nữ",
                    DateHelper.toString(nd.getNgaySinh()),
                    nd.getEmail(),
                    nd.getSoDienThoai(),
                    nd.getHinhAnh()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            DialogHelper.alert(null, "Lỗi truy vấn dữ liệu");
        }
    }

    @Override
    public boolean save(JTextField txtTaiKhoan, JPasswordField txxmatKhau, JRadioButton rbNhanVien, JRadioButton rbNguoiDoc, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDienThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh) {
        NguoiDung model = new NguoiDung();
        model.setMaNguoiDung(txtTaiKhoan.getText());
        model.setMatKhau(new String(txxmatKhau.getPassword()));
        if (rbNhanVien.isSelected()) {
            model.setVaiTro(true);
        } if (rbNguoiDoc.isSelected()) {
            model.setVaiTro(false);
        }
        model.setHoTen(txtHoTen.getText());
        Date ngaysinh = DateHelper.toDate(txtNgaySinh.getText());
        java.sql.Date sqlStartDate = new java.sql.Date(ngaysinh.getTime());
        model.setNgaySinh(sqlStartDate);
        model.setSoDienThoai(txtSoDienThoai.getText());
        model.setEmail(txtEmail.getText());
        if (rbNam.isSelected()) {
            model.setGioiTinh(true);
        } if (rbNu.isSelected()) {
            model.setGioiTinh(false);
        }
        model.setDiaChi(txtDiaChi.getText());
        model.setHinhAnh(lblHinhAnh.getToolTipText());
        try {
            NguoiDung maNgươiDung = dao.findById(txtTaiKhoan.getText());
            NguoiDung email = dao.findByEmail(txtEmail.getText());
            NguoiDung sdt = dao.findBySDT(txtSoDienThoai.getText());
            if (!UHelper.checkNull(txtTaiKhoan, "Tên tài khoản")) {
                return false;
            } if (!UHelper.checkNull(txxmatKhau, "Mật khẩu")) {
                return false;
            } if (!UHelper.checkNull(txtHoTen, "Họ tên")) {
                return false;
            } if (!UHelper.checkNull(txtEmail, "Email")) {
                return false;
            } if (!UHelper.checkNull(txtSoDienThoai, "Số điện thoại")) {
                return false;
            } if (maNgươiDung != null) {
                DialogHelper.alert(null, "Tài khoản đã tồn tại!");
                return false;
            } if (email != null) {
                DialogHelper.alert(null, "Email đã được sử dụng!");
                return false;
            } if (sdt != null) {
                DialogHelper.alert(null, "Số điện thoại đã được sử dụng!");
                return false;
            } if (!txtSoDienThoai.getText().matches("^0\\d{9}$")) {
                DialogHelper.alert(null, "Số điện thoại không đúng định dạng!");
                return false;
            } if (!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")) {
                DialogHelper.alert(null, "Email không đúng định dạng!");
                return false;
            }
            dao.save(model);
            if (!model.isVaiTro()) {
                NguoiDoc nguoiDoc = new NguoiDoc();
                nguoiDoc.setMaNguoiDung(txtTaiKhoan.getText());
                nguoiDocDao.save(nguoiDoc);
                return true;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(JTextField txtTaiKhoan, JPasswordField txxmatKhau, JRadioButton rbNhanVien, JRadioButton rbNguoiDoc, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDienThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(JTextField txtTaiKhoan) {
        if (DialogHelper.confirm(null, "Bạn thực sự muốn xóa người học này?")) {
            String maNd = txtTaiKhoan.getText();
            try {
                if (dao.findById(txtTaiKhoan.getText()) != null) {
                    DialogHelper.alert(null, "Bạn không thể xóa tài khoản nhân viên");
                    return false;
                } else{
                    dao.delete(maNd);
                    return true;
                }
            } catch (HeadlessException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public NguoiDung getModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void selectImage(JLabel lblHinhAnh) {
        JFileChooser filechooser = new JFileChooser();
        filechooser.showOpenDialog(null);
        if (filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            if (ShareHelper.saveLogo(file)) {
                lblHinhAnh.setIcon(ShareHelper.readLogo(file.getName()));
                lblHinhAnh.setToolTipText(file.getName());
            }
        }
    }
}
