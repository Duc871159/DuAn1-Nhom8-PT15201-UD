/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.impl;

import duan1.nhom8.dao.SachDao;
import duan1.nhom8.helper.DialogHelper;
import duan1.nhom8.helper.ShareHelper;
import duan1.nhom8.helper.UHelper;
import duan1.nhom8.i.SachInterface;
import duan1.nhom8.model.Sach;
import java.awt.HeadlessException;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class SachImpl implements SachInterface {

    SachDao dao = new SachDao();

    @Override
    public void loadTrangChu(JTable tbSach, JTextField txtTimKiem) {
        DefaultTableModel model = (DefaultTableModel) tbSach.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<Sach> list = dao.selectByKeyword(keyword, keyword, keyword, keyword, keyword);
            for (Sach s : list) {
                Object[] row = {
                    s.getMaSach(),
                    s.getTenSach(),
                    s.getLoaiSach(),
                    s.getTacGia(),
                    s.getNhaXuatBan(),
                    s.getViTri(),
                    s.getSoLuong() > 0 ? true : false
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void load(JTable tbSach, JTextField txtTimKiem) {
        DefaultTableModel model = (DefaultTableModel) tbSach.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<Sach> list = dao.selectByKeyword(keyword, keyword, keyword, keyword, keyword);
            for (Sach s : list) {
                Object[] row = {
                    s.getMaSach(),
                    s.getTenSach(),
                    s.getLoaiSach(),
                    s.getViTri(),
                    s.getTacGia(),
                    s.getNhaXuatBan(),
                    s.getHinhAnh(),
                    s.getSoLuong(),
                    s.getSoLuong() > 0 ? true : false
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean save(JTextField txtTenSach, JTextField txtLoaiSach, JTextField txtViTri, JTextField txtTacGia, JTextField txtNhaXuatBan, JTextField txtSoLuong, JLabel lblHinhAnh) {
        Sach model = new Sach();
        try {
            if (!UHelper.checkNull(txtTenSach, "Tên sách")) {
                return false;
            }
            if (!UHelper.checkNull(txtLoaiSach, "Thể loại")) {
                return false;
            }
            if (!UHelper.checkNull(txtViTri, "Vị trí")) {
                return false;
            }
            if (!UHelper.checkNull(txtTacGia, "Tác giả")) {
                return false;
            }
            if (!UHelper.checkNull(txtNhaXuatBan, "Nhà xuất bản")) {
                return false;
            }
            if (!UHelper.checkNull(txtSoLuong, "Số lượng")) {
                return false;
            } if (Integer.valueOf(txtSoLuong.getText()) <= 0) {
                DialogHelper.alert(null, "Số lượng phải lớn hơn 1");
                return false;
            }
            model.setTenSach(txtTenSach.getText());
            model.setLoaiSach(txtLoaiSach.getText());
            model.setViTri(txtViTri.getText());
            model.setNhaXuatBan(txtNhaXuatBan.getText());
            model.setTacGia(txtTacGia.getText());
            model.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
            model.setHinhAnh(lblHinhAnh.getToolTipText());
            dao.save(model);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void selectImage(JLabel lblHinhAnh) {
        JFileChooser filechooser = new JFileChooser();
        filechooser.showOpenDialog(null);
        if (filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File file = filechooser.getSelectedFile();
            if (ShareHelper.saveLogo(file)) {
                lblHinhAnh.setIcon(ShareHelper.readLogo(file.getName()));
                lblHinhAnh.setToolTipText(file.getName());
            }
        }
    }

    @Override
    public boolean edit(JTable tbSach, int index, JTextField txtMaSach, JTextField txtTenSach, JTextField txtLoaiSach, JTextField txtViTri, JTextField txtTacGia, JTextField txtNhaXuatBan, JTextField txtSoLuong, JLabel lblHinhAnh) {
        try {
            Integer maSach = (Integer) tbSach.getValueAt(index, 0);
            Sach model = dao.findById(maSach);
            if (model != null) {
                txtMaSach.setText(String.valueOf(model.getMaSach()));
                txtTenSach.setText(model.getTenSach());
                txtLoaiSach.setText(model.getLoaiSach());
                txtTacGia.setText(model.getTacGia());
                txtNhaXuatBan.setText(model.getNhaXuatBan());
                txtSoLuong.setText(String.valueOf(model.getSoLuong()));
                txtViTri.setText(model.getViTri());
                if (model.getHinhAnh() != null) {
                    lblHinhAnh.setIcon(ShareHelper.readLogo(model.getHinhAnh()));
                } else {
                    lblHinhAnh.setIcon(null);
                }
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean delete(JTextField txtMaSach) {
        if (DialogHelper.confirm(null, "Bạn thực sự muốn xóa người dùng này?")) {
            Integer maS = Integer.valueOf( txtMaSach.getText());
            try {
                dao.delete(maS);
                return true;
            } catch (HeadlessException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean update(JTextField txtMaSach, JTextField txtTenSach, JTextField txtLoaiSach, JTextField txtViTri, JTextField txtTacGia, JTextField txtNhaXuatBan, JTextField txtSoLuong, JLabel lblHinhAnh) {
        Sach model = new Sach();
        try {
            if (!UHelper.checkNull(txtTenSach, "Tên sách")) {
                return false;
            }
            if (!UHelper.checkNull(txtLoaiSach, "Thể loại")) {
                return false;
            }
            if (!UHelper.checkNull(txtViTri, "Vị trí")) {
                return false;
            }
            if (!UHelper.checkNull(txtTacGia, "Tác giả")) {
                return false;
            }
            if (!UHelper.checkNull(txtNhaXuatBan, "Nhà xuất bản")) {
                return false;
            }
            if (!UHelper.checkNull(txtSoLuong, "Số lượng")) {
                return false;
            }
            model.setMaSach(Integer.valueOf(txtMaSach.getText()));
            model.setTenSach(txtTenSach.getText());
            model.setLoaiSach(txtLoaiSach.getText());
            model.setViTri(txtViTri.getText());
            model.setNhaXuatBan(txtNhaXuatBan.getText());
            model.setTacGia(txtTacGia.getText());
            model.setSoLuong(Integer.valueOf(txtSoLuong.getText()));
            model.setHinhAnh(lblHinhAnh.getToolTipText());
            dao.update(model);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
