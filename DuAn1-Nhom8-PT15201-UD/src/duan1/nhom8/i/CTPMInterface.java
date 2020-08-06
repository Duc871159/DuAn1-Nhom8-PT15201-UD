/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.i;

import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author User
 */
public interface CTPMInterface {
    void fillCombobox(JComboBox cbbSach, Integer maPM);
    void load(JTable tbSachMuon, Integer maPM);
}
