/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.helper;

import duan1.nhom8.model.NguoiDung;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Nguyen Van Dien
 */
public class UHelper {

    public static boolean checkNull(JTextField jt, String mess) {
        if (jt.getText().length() == 0) {
            DialogHelper.alert(jt, mess + " không được để trống");
            jt.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkSizePassWord(JPasswordField jp, String mess) {
        if (jp.getText().length() <= 4) {
            DialogHelper.alert(jp, mess + " phải lớn hơn 4 kí tự");
            jp.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkSizeMaNV(JTextField jt, String mess) {
        if (jt.getText().length() < 5) {
            DialogHelper.alert(jt, mess + "Mã nhân viên phải lớn hơn 5");
            jt.requestFocus();
            return false;
        } else {
            return true;
        }
    }
   public static boolean checkMaCD(JTextField jt, String mess){
       if(jt.getText().length()>5 || jt.getText().length()<5){
           DialogHelper.alert(jt, mess + "Mã chuyên đề phải đúng 5 kí tự");
            jt.requestFocus();
            return false;
       }
       return true;
   }
   
//  
}
