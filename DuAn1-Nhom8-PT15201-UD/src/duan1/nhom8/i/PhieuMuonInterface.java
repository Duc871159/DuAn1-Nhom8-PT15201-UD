/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.i;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public interface PhieuMuonInterface {
    void load(JTable tbDSPM, JTextField txtTimKiem);
    boolean save(JTextField txtNguoiMuon, JTextField txtNguoiTao, JTextField txtNgayMuon, JTextField txtNgayTra);
    boolean update(JTextField txtMaPM, JTextField txtNgayTra, JRadioButton rbChuaTra, JRadioButton rbDaTra);
    boolean delete(JTextField txtMaPM);
    boolean edit(JTable tbDSPM, int index, JTextField txtMaPM, JTextField txtNguoiMuon, JTextField txtNguoiTra, JTextField txtNgayMuon, JTextField txtNgayTra, JRadioButton rbChuaTra, JRadioButton rbDaTra);
    void setStatus();
    void sendMail();
}
