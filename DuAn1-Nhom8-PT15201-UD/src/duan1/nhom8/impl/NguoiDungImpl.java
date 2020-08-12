/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.impl;

import duan1.nhom8.dao.NguoiDocDao;
import duan1.nhom8.dao.NguoiDungDao;
import duan1.nhom8.dao.NhanVienDao;
import duan1.nhom8.helper.DateHelper;
import duan1.nhom8.helper.DialogHelper;
import duan1.nhom8.helper.ShareHelper;
import duan1.nhom8.helper.UHelper;
import duan1.nhom8.i.NguoiDungInterface;
import duan1.nhom8.model.NguoiDoc;
import duan1.nhom8.model.NguoiDung;
import duan1.nhom8.model.NhanVien;
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
public class NguoiDungImpl implements NguoiDungInterface {

    NguoiDungDao dao = new NguoiDungDao();
    NguoiDocDao nguoiDocDao = new NguoiDocDao();
    NhanVienDao nhanVienDao = new NhanVienDao();

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
                    return true;
                } else {
                    DialogHelper.alert(null, "Sai mật khẩu !");
                    return false;
                }
            } else {
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
            List<NguoiDung> list = dao.selectByKeywordKhachHang(keyword, keyword, keyword, keyword, keyword, keyword);
            for (NguoiDung nd : list) {
                Object[] row = {
                    nd.getMaNguoiDung(),
                    nd.getMatKhau(),
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
    public boolean saveKhachHang(JTextField txtTaiKhoan, JPasswordField txxmatKhau, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDienThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh) {
        NguoiDung model = new NguoiDung();
        model.setMaNguoiDung(txtTaiKhoan.getText());
        model.setMatKhau(new String(txxmatKhau.getPassword()));
        model.setVaiTro(false);
        model.setHoTen(txtHoTen.getText());
        Date ngaysinh = DateHelper.toDate(txtNgaySinh.getText());
        java.sql.Date sqlStartDate = new java.sql.Date(ngaysinh.getTime());
        model.setNgaySinh(sqlStartDate);
        model.setSoDienThoai(txtSoDienThoai.getText());
        model.setEmail(txtEmail.getText());
        if (rbNam.isSelected()) {
            model.setGioiTinh(true);
        }
        if (rbNu.isSelected()) {
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
            }
            if (!UHelper.checkNull(txxmatKhau, "Mật khẩu")) {
                return false;
            }
            if (!UHelper.checkNull(txtHoTen, "Họ tên")) {
                return false;
            }
            if (!UHelper.checkNull(txtEmail, "Email")) {
                return false;
            }
            if (!UHelper.checkNull(txtSoDienThoai, "Số điện thoại")) {
                return false;
            }
            if (maNgươiDung != null) {
                DialogHelper.alert(null, "Tài khoản đã tồn tại!");
                return false;
            }
            if (email != null) {
                DialogHelper.alert(null, "Email đã được sử dụng!");
                return false;
            }
            if (sdt != null) {
                DialogHelper.alert(null, "Số điện thoại đã được sử dụng!");
                return false;
            }
            if (!txtSoDienThoai.getText().matches("^0\\d{9}$")) {
                DialogHelper.alert(null, "Số điện thoại không đúng định dạng!");
                return false;
            }
            if (!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")) {
                DialogHelper.alert(null, "Email không đúng định dạng!");
                return false;
            }
            dao.save(model);
            NguoiDoc nguoiDoc = new NguoiDoc();
            nguoiDoc.setMaNguoiDung(txtTaiKhoan.getText());
            nguoiDocDao.save(nguoiDoc);
            return true;
        } catch (Exception e) {
            return false;
        }
    }  

    @Override
    public boolean delete(JTextField txtTaiKhoan) {
        if (DialogHelper.confirm(null, "Bạn thực sự muốn xóa người dùng này?")) {
            String maNd = txtTaiKhoan.getText();
            NguoiDung nd = dao.findByIdNV(txtTaiKhoan.getText());
            NhanVien nv = nhanVienDao.findByMaND(nd.getMaNguoiDung());
            try {
                if (nd != null) {
                    nhanVienDao.delete(nv.getMaNhanVien());
                    dao.delete(maNd);
                    return true;
                } else {
                    nguoiDocDao.deleteNguoiDung(maNd);
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

    @Override
    public boolean edit(JTable tbDSND, int index, JTextField txtTaiKhoan, JPasswordField txxMatKhau, JTextField txtHoVaTen, JTextField txtNgaySinh, JTextField txtSoDienThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh) {
        try {
            String mand = (String) tbDSND.getValueAt(index, 0);
            NguoiDung model = dao.findById(mand);
            if (model != null) {
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
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public void editPassword(JPasswordField txxMatKhauMoi, JPasswordField txxMatKhau, JPasswordField txxNhapLaiMatKhau) {
        NguoiDung model = new NguoiDung();
        model.setMaNguoiDung(ShareHelper.USER.getMaNguoiDung());
        model.setMatKhau(new String(txxMatKhauMoi.getPassword()));
        NguoiDung nd = dao.findById(ShareHelper.USER.getMaNguoiDung());
        if (nd != null) {
            if (!new String(txxMatKhau.getPassword()).equals(nd.getMatKhau())) {
                DialogHelper.alert(null, "Mật khẩu của bạn không đúng");
                return;
            } else if (new String(txxMatKhauMoi.getPassword()).equals("") && new String(txxNhapLaiMatKhau.getPassword()).equals("")) {
                DialogHelper.alert(null, "Mật khẩu không được để trống");
                return;
            } else if (!new String(txxNhapLaiMatKhau.getPassword()).equals(new String(txxMatKhauMoi.getPassword()))) {
                DialogHelper.alert(null, "Xác nhận mật khẩu không hợp lệ");
                return;
            } else {
                dao.updatePassword(model);
                DialogHelper.alert(null, "Đổi mật khẩu thành công");
            }
        }
    }

    @Override
    public void loadCaNhan(String maNguoiDung, JTextField txtHoVaTen, JTextField txtNgaySinh, JTextField txtSoDienThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh) {
        try {
            NguoiDung model = dao.findById(maNguoiDung);
            if (model != null) {
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
            }
        } catch (Exception e) {
        }
    }

    @Override
    public boolean updateCaNhan(String maNguoiDung, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDIenThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh) {
        NguoiDung model = new NguoiDung();
        model.setMaNguoiDung(maNguoiDung);
        model.setMatKhau(ShareHelper.USER.getMatKhau());
        model.setVaiTro(ShareHelper.USER.isVaiTro());
        model.setHoTen(txtHoTen.getText());
        Date ngaysinh = DateHelper.toDate(txtNgaySinh.getText());
        java.sql.Date sqlStartDate = new java.sql.Date(ngaysinh.getTime());
        model.setNgaySinh(sqlStartDate);
        model.setSoDienThoai(txtSoDIenThoai.getText());
        model.setEmail(txtEmail.getText());
        if (rbNam.isSelected()) {
            model.setGioiTinh(true);
        }
        if (rbNu.isSelected()) {
            model.setGioiTinh(false);
        }
        model.setDiaChi(txtDiaChi.getText());
        model.setHinhAnh(lblHinhAnh.getToolTipText());
        try {
            NguoiDung email = dao.findByEmailUpdate(txtEmail.getText(), ShareHelper.USER.getEmail());
            NguoiDung sdt = dao.findBySdtUpdate(txtSoDIenThoai.getText(), ShareHelper.USER.getSoDienThoai());
            if (!UHelper.checkNull(txtHoTen, "Họ tên")) {
                return false;
            }
            if (!UHelper.checkNull(txtEmail, "Email")) {
                return false;
            }
            if (!UHelper.checkNull(txtSoDIenThoai, "Số điện thoại")) {
                return false;
            }
            if (email != null) {
                DialogHelper.alert(null, "Email đã được sử dụng!");
                return false;
            }
            if (sdt != null) {
                DialogHelper.alert(null, "Số điện thoại đã được sử dụng!");
                return false;
            }
            if (!txtSoDIenThoai.getText().matches("^0\\d{9}$")) {
                DialogHelper.alert(null, "Số điện thoại không đúng định dạng!");
                return false;
            }
            if (!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")) {
                DialogHelper.alert(null, "Email không đúng định dạng!");
                return false;
            }
            dao.update(model);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean saveNhanVien(JTextField txtTaiKhoan, JPasswordField txxmatKhau, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDienThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh) {
        NguoiDung model = new NguoiDung();
        model.setMaNguoiDung(txtTaiKhoan.getText());
        model.setMatKhau(new String(txxmatKhau.getPassword()));
        model.setVaiTro(true);
        model.setHoTen(txtHoTen.getText());
        Date ngaysinh = DateHelper.toDate(txtNgaySinh.getText());
        java.sql.Date sqlStartDate = new java.sql.Date(ngaysinh.getTime());
        model.setNgaySinh(sqlStartDate);
        model.setSoDienThoai(txtSoDienThoai.getText());
        model.setEmail(txtEmail.getText());
        if (rbNam.isSelected()) {
            model.setGioiTinh(true);
        }
        if (rbNu.isSelected()) {
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
            }
            if (!UHelper.checkNull(txxmatKhau, "Mật khẩu")) {
                return false;
            }
            if (!UHelper.checkNull(txtHoTen, "Họ tên")) {
                return false;
            }
            if (!UHelper.checkNull(txtEmail, "Email")) {
                return false;
            }
            if (!UHelper.checkNull(txtSoDienThoai, "Số điện thoại")) {
                return false;
            }
            if (maNgươiDung != null) {
                DialogHelper.alert(null, "Tài khoản đã tồn tại!");
                return false;
            }
            if (email != null) {
                DialogHelper.alert(null, "Email đã được sử dụng!");
                return false;
            }
            if (sdt != null) {
                DialogHelper.alert(null, "Số điện thoại đã được sử dụng!");
                return false;
            }
            if (!txtSoDienThoai.getText().matches("^0\\d{9}$")) {
                DialogHelper.alert(null, "Số điện thoại không đúng định dạng!");
                return false;
            }
            if (!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")) {
                DialogHelper.alert(null, "Email không đúng định dạng!");
                return false;
            }
            dao.save(model);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateKhachHang(JTable tbDSND, int index, JTextField txtTaiKhoan, JPasswordField txxmatKhau, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDienThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh) {
        NguoiDung model = new NguoiDung();
        model.setMaNguoiDung(txtTaiKhoan.getText());
        model.setMatKhau(new String(txxmatKhau.getPassword()));
        model.setVaiTro(false);
        model.setHoTen(txtHoTen.getText());
        Date ngaysinh = DateHelper.toDate(txtNgaySinh.getText());
        java.sql.Date sqlStartDate = new java.sql.Date(ngaysinh.getTime());
        model.setNgaySinh(sqlStartDate);
        model.setSoDienThoai(txtSoDienThoai.getText());
        model.setEmail(txtEmail.getText());
        if (rbNam.isSelected()) {
            model.setGioiTinh(true);
        }
        if (rbNu.isSelected()) {
            model.setGioiTinh(false);
        }
        model.setDiaChi(txtDiaChi.getText());
        model.setHinhAnh(lblHinhAnh.getToolTipText());
        try {
            NguoiDung email = dao.findByEmailUpdate(txtEmail.getText(), tbDSND.getValueAt(index, 6).toString());
            NguoiDung sdt = dao.findBySdtUpdate(txtSoDienThoai.getText(), tbDSND.getValueAt(index, 7).toString());
            if (!UHelper.checkNull(txxmatKhau, "Mật khẩu")) {
                return false;
            }
            if (!UHelper.checkNull(txtHoTen, "Họ tên")) {
                return false;
            }
            if (!UHelper.checkNull(txtEmail, "Email")) {
                return false;
            }
            if (!UHelper.checkNull(txtSoDienThoai, "Số điện thoại")) {
                return false;
            }
            if (email != null) {
                DialogHelper.alert(null, "Email đã được sử dụng!");
                return false;
            }
            if (sdt != null) {
                DialogHelper.alert(null, "Số điện thoại đã được sử dụng!");
                return false;
            }
            if (!txtSoDienThoai.getText().matches("^0\\d{9}$")) {
                DialogHelper.alert(null, "Số điện thoại không đúng định dạng!");
                return false;
            }
            if (!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")) {
                DialogHelper.alert(null, "Email không đúng định dạng!");
                return false;
            }
            dao.update(model);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateNhanVien(JTable tbDSND, int index, JTextField txtTaiKhoan, JPasswordField txxmatKhau, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDienThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh) {
        NguoiDung model = new NguoiDung();
        model.setMaNguoiDung(txtTaiKhoan.getText());
        model.setMatKhau(new String(txxmatKhau.getPassword()));
        model.setVaiTro(true);
        model.setHoTen(txtHoTen.getText());
        Date ngaysinh = DateHelper.toDate(txtNgaySinh.getText());
        java.sql.Date sqlStartDate = new java.sql.Date(ngaysinh.getTime());
        model.setNgaySinh(sqlStartDate);
        model.setSoDienThoai(txtSoDienThoai.getText());
        model.setEmail(txtEmail.getText());
        if (rbNam.isSelected()) {
            model.setGioiTinh(true);
        }
        if (rbNu.isSelected()) {
            model.setGioiTinh(false);
        }
        model.setDiaChi(txtDiaChi.getText());
        model.setHinhAnh(lblHinhAnh.getToolTipText());
        try {
            NguoiDung email = dao.findByEmailUpdate(txtEmail.getText(), tbDSND.getValueAt(index, 9).toString());
            NguoiDung sdt = dao.findBySdtUpdate(txtSoDienThoai.getText(), tbDSND.getValueAt(index, 8).toString());
            if (!UHelper.checkNull(txxmatKhau, "Mật khẩu")) {
                return false;
            }
            if (!UHelper.checkNull(txtHoTen, "Họ tên")) {
                return false;
            }
            if (!UHelper.checkNull(txtEmail, "Email")) {
                return false;
            }
            if (!UHelper.checkNull(txtSoDienThoai, "Số điện thoại")) {
                return false;
            }
            if (email != null) {
                DialogHelper.alert(null, "Email đã được sử dụng!");
                return false;
            }
            if (sdt != null) {
                DialogHelper.alert(null, "Số điện thoại đã được sử dụng!");
                return false;
            }
            if (!txtSoDienThoai.getText().matches("^0\\d{9}$")) {
                DialogHelper.alert(null, "Số điện thoại không đúng định dạng!");
                return false;
            }
            if (!txtEmail.getText().matches("\\w+@\\w+(\\.\\w+){1,2}")) {
                DialogHelper.alert(null, "Email không đúng định dạng!");
                return false;
            }
            dao.update(model);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
