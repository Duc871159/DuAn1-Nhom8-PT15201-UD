/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.i;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public interface NhanVienInterface {
    void fillCombobox(JComboBox cbbNguoiDung);
    void selectCombobox(JComboBox cbbNguoiDung, JTextField txtHoTen, JTextField txtSDT, JTextField txtEmail, JRadioButton rdNam, JRadioButton rdNu);
}
