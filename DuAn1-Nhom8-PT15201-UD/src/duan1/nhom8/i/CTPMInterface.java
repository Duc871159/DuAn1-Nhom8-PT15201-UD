/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.i;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public interface CTPMInterface {
    void fillCombobox(JComboBox cbbSach, Integer maPM);
    void load(JTable tbSachMuon, Integer maPM);
    boolean save(JComboBox cbbSach, Integer maPM);
    boolean update(JTextField txtMaTT, JRadioButton rbChuaTra, JRadioButton rbDaTra);
    boolean delete(JTable tbSachMuon, JTextField txtMaTT);
    void edit(JTable tbDSSM, int index, int maPM, JTextField txtMaTrangThai, JTextField txtNgayMuon, JTextField txtNgayTra, JTextField txtTenSach, JTextField txtTheLoai, JTextField txtViTri, JRadioButton rbChuaTra, JRadioButton rbDaTra);
}
