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
    
    boolean save(JTextField txtTaiKhoan, JPasswordField matKhau, JRadioButton rbNhanVien, JRadioButton rbNguoiDoc, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDIenThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh);
    
    boolean update(JTable tbDSND, int index, JTextField txtTaiKhoan, JPasswordField matKhau, JRadioButton rbNhanVien, JRadioButton rbNguoiDoc, JTextField txtHoTen, JTextField txtNgaySinh, JTextField txtSoDIenThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh);
    
    boolean delete(JTextField txtTaiKhoan);
    
    void selectImage(JLabel lblHinhAnh);
    
    boolean edit(JTable tbDSND, int index, JTextField txtTaiKhoan, JPasswordField txxMatKhau, JRadioButton rbNhanVien, JRadioButton rbNguoiDoc, JTextField txtHoVaTen, JTextField txtNgaySinh, JTextField txtSoDienThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh);
}
