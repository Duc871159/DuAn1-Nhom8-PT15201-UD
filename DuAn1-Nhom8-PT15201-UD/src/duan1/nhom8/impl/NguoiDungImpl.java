/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.impl;

import duan1.nhom8.dao.NguoiDungDao;
import duan1.nhom8.helper.DialogHelper;
import duan1.nhom8.helper.ShareHelper;
import duan1.nhom8.helper.UHelper;
import duan1.nhom8.i.NguoiDungInterface;
import duan1.nhom8.model.NguoiDung;
import duan1.nhom8.ui.DangNhapJDialog;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public class NguoiDungImpl implements NguoiDungInterface{

    NguoiDungDao dao = new NguoiDungDao();
    

    
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
}
