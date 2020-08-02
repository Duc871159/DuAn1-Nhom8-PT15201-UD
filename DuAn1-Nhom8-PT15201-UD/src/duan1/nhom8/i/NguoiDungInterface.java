/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.i;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author User
 */
public interface NguoiDungInterface {
    boolean login(JTextField txtUser, JPasswordField txtPass);
}
