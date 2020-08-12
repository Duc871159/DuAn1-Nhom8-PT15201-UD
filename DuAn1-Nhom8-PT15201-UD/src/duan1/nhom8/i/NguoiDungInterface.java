/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.i;

import duan1.nhom8.model.NguoiDung;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public interface NguoiDungInterface {
    boolean login(JTextField txtUser, JPasswordField txtPass);   
    void load(JTable tbDSND, JTextField txtTimKiem);    
    boolean saveKhachHang(JTextField txtTaiKhoan, JPasswordField matKhau, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDIenThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh);    
    boolean saveNhanVien(JTextField txtTaiKhoan, JPasswordField matKhau, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDIenThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh);
    boolean updateKhachHang(JTable tbDSND, int index, JTextField txtTaiKhoan, JPasswordField matKhau, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDIenThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh);
    boolean delete(JTextField txtTaiKhoan);   
    void selectImage(JLabel lblHinhAnh);
    boolean edit(JTable tbDSND, int index, JTextField txtTaiKhoan, JPasswordField txxMatKhau, JTextField txtHoVaTen, JTextField txtNgaySinh, JTextField txtSoDienThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh);
    void editPassword(JPasswordField txxMatKhauMoi, JPasswordField txxMatKhau, JPasswordField txxNhapLaiMatKhau);
    void loadCaNhan(String maNguoiDung, JTextField txtHoVaTen, JTextField txtNgaySinh, JTextField txtSoDienThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh);
    boolean updateCaNhan(String maNguoiDung, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDIenThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh);
    boolean updateNhanVien(JTable tbDSND, int index, JTextField txtTaiKhoan, JPasswordField matKhau, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDIenThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh);
}
