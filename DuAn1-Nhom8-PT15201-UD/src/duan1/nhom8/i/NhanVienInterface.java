/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.i;

import javax.swing.JComboBox;
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
public interface NhanVienInterface {
    void load(JTable tbDSNV, JTextField txtTimKiem);
    boolean save(JTextField txtCaLamViec, JTextField txtLuong, JTextField txtTaiKhoan);
    boolean update (JTextField txtCaLamViec, JTextField txtLuong, JTextField txtTaiKhoan, JTextField txtMaNV);
    boolean delete (JTextField txtMaNV);
    boolean edit(JTable tbDSNV, int index, JTextField txtMaNV, JTextField txtCaLamViec, JTextField txtLuong, JTextField txtTaiKhoan, JPasswordField txxMatKhau, JTextField txtHoVaTen, JTextField txtNgaySinh, JTextField txtSoDienThoai, JTextField txtEmail, JRadioButton rbNam, JRadioButton rbNu, JTextArea txtDiaChi, JLabel lblHinhAnh);
}
