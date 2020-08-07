/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.i;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public interface SachInterface {
    void loadTrangChu(JTable tbSach, JTextField txtTimKiem);
    void load(JTable tbSach, JTextField txtTimKiem);
    boolean save(JTextField txtTenSach, JTextField txtLoaiSach, JTextField txtViTri, JTextField txtTacGia, JTextField txtNhaXuatBan, JTextField txtSoLuong, JLabel lblHinhAnh);
    boolean delete(JTextField txtMaSach);   
    void selectImage(JLabel lblHinhAnh);
    boolean edit(JTable tbSach, int index, JTextField txtMaSach, JTextField txtTenSach, JTextField txtLoaiSach, JTextField txtViTri, JTextField txtTacGia, JTextField txtNhaXuatBan, JTextField txtSoLuong, JLabel lblHinhAnh);
    boolean update(JTextField txtMaSach, JTextField txtTenSach, JTextField txtLoaiSach, JTextField txtViTri, JTextField txtTacGia, JTextField txtNhaXuatBan, JTextField txtSoLuong, JLabel lblHinhAnh);
}
