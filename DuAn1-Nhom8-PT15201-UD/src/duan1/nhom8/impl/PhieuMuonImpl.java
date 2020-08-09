/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.impl;

import duan1.nhom8.dao.NguoiDocDao;
import duan1.nhom8.dao.NguoiDungDao;
import duan1.nhom8.dao.NhanVienDao;
import duan1.nhom8.dao.PhieuMuonDao;
import duan1.nhom8.helper.DateHelper;
import duan1.nhom8.helper.DialogHelper;
import duan1.nhom8.helper.UHelper;
import duan1.nhom8.i.PhieuMuonInterface;
import duan1.nhom8.model.NguoiDoc;
import duan1.nhom8.model.NguoiDung;
import duan1.nhom8.model.NhanVien;
import duan1.nhom8.model.PhieuMuon;
import java.awt.HeadlessException;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class PhieuMuonImpl implements PhieuMuonInterface {

    PhieuMuonDao dao = new PhieuMuonDao();
    NguoiDungDao nguoiDungDao = new NguoiDungDao();
    NguoiDocDao nguoiDocDao = new NguoiDocDao();
    NhanVienDao nhanVienDao = new NhanVienDao();

    @Override
    public void load(JTable tbDSPM, JTextField txtTimKiem) {
        DefaultTableModel model = (DefaultTableModel) tbDSPM.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtTimKiem.getText();
            List<PhieuMuon> list = dao.selectByKeyword(keyword, keyword, keyword);
            for (PhieuMuon pm : list) {
                NguoiDoc ndoc = nguoiDocDao.findById(pm.getMaNguoiDoc());
                NhanVien nv = nhanVienDao.findById(pm.getMaNhanVien());
                NguoiDung nd = nguoiDungDao.findById(ndoc.getMaNguoiDung());
                NguoiDung ndnv = nguoiDungDao.findById(nv.getMaNguoiDung());
                long ngay = ((new Date()).getTime() - pm.getNgayTra().getTime()) / (24 * 3600 * 1000);
                long ngayGuiMail = ((pm.getNgayTra().getTime()) - (new Date()).getTime()) / (24 * 3600 * 1000);
                Object[] row = {
                    pm.getMaPhieuMuon(),
                    nd.getMaNguoiDung(),
                    ndnv.getMaNguoiDung(),
                    DateHelper.toString(pm.getNgayMuon()),
                    DateHelper.toString(pm.getNgayTra()),
                    pm.isTrangThai() ? "Đã trả" : "Chưa trả",
                    ngay > 0 && !pm.isTrangThai() ? ngay * 10000 : 0
                };
                model.addRow(row);
//                try {
//                    if (ngay < 5 && !pm.isTrangThai()) {
//                        Properties mailServerProperties;
//                        Session getMailSession;
//                        MimeMessage mailMessage;
//
//                        //Setup Mail server
//                        mailServerProperties = System.getProperties();
//                        mailServerProperties.put("mail.smtp.port", "587");
//                        mailServerProperties.put("mail.smtp.auth", "true");
//                        mailServerProperties.put("mail.smtp.starttls.enable", "true");
//                        //Get Mail Session
//                        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
//                        mailMessage = new MimeMessage(getMailSession);
//
//                        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(nd.getEmail())); //Mail người nhận
//                        System.out.println(nd.getEmail());
////    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail
//                        mailMessage.setSubject("Thư viện bách khoa");
//                        mailMessage.setText("Phiếu mượn có mã: " + pm.getMaPhieuMuon() + " sắp đến ngày hẹn trả: " + pm.getNgayTra() + " mong bạn lưu ý trả sách đúng thời hạn, số ngày còn lại: " + ngayGuiMail);
//
//                        // Send mail
//                        Transport transport = getMailSession.getTransport("smtp");
//
//                        //Mail người gửi
//                        transport.connect("smtp.gmail.com", "ducnguyen871159@gmail.com", "uoduccexpojsgogy");
//                        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
//                        transport.close();
//                        System.err.println("Success");
//                    }
//                } catch (AddressException e) {
//                    System.err.println(e);
//                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public boolean save(JTextField txtNguoiMuon, JTextField txtNguoiTao, JTextField txtNgayMuon, JTextField txtNgayTra) {
        try {
            PhieuMuon model = new PhieuMuon();
            NhanVien nv = nhanVienDao.findByMaND(txtNguoiTao.getText());
            NguoiDoc nd = nguoiDocDao.findByMaND(txtNguoiMuon.getText());
            if (!UHelper.checkNull(txtNguoiMuon, "Người mượn")) {
                txtNguoiMuon.requestFocus();
                return false;
            }
            if (nd == null) {
                txtNguoiMuon.requestFocus();
                DialogHelper.alert(null, "Không tìm thấy người dùng");
                return false;
            }
            if (!UHelper.checkNull(txtNgayTra, "Ngày trả")) {
                txtNgayTra.requestFocus();
                return false;
            }
            Date ngayMuon = DateHelper.toDate(txtNgayMuon.getText());
            java.sql.Date sqlStartDate1 = new java.sql.Date(ngayMuon.getTime());
            model.setNgayMuon(sqlStartDate1);
            Date ngayTra = DateHelper.toDate(txtNgayTra.getText());
            java.sql.Date sqlStartDate2 = new java.sql.Date(ngayTra.getTime());
            model.setNgayTra(sqlStartDate2);
            model.setMaNguoiDoc(nd.getMaNguoiDoc());
            model.setMaNhanVien(nv.getMaNhanVien());
            dao.save(model);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean edit(JTable tbDSPM, int index, JTextField txtMaPM, JTextField txtNguoiMuon, JTextField txtNguoiTra, JTextField txtNgayMuon, JTextField txtNgayTra, JRadioButton rbChuaTra, JRadioButton rbDaTra) {
        try {
            Integer maPM = (Integer) tbDSPM.getValueAt(index, 0);
            PhieuMuon model = dao.findById(maPM);
            NguoiDoc ndoc = nguoiDocDao.findById(model.getMaNguoiDoc());
            NhanVien nv = nhanVienDao.findById(model.getMaNhanVien());
            NguoiDung nd = nguoiDungDao.findById(ndoc.getMaNguoiDung());
            NguoiDung ndnv = nguoiDungDao.findById(nv.getMaNguoiDung());
            txtMaPM.setText(String.valueOf(model.getMaPhieuMuon()));
            txtNguoiMuon.setText(nd.getMaNguoiDung());
            txtNguoiTra.setText(ndnv.getMaNguoiDung());
            txtNgayMuon.setText(DateHelper.toString(model.getNgayMuon()));
            txtNgayTra.setText(DateHelper.toString(model.getNgayTra()));
            if (model.isTrangThai()) {
                rbDaTra.setSelected(true);
            }
            if (!model.isTrangThai()) {
                rbChuaTra.setSelected(true);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(JTextField txtMaPM, JTextField txtNgayTra, JRadioButton rbChuaTra, JRadioButton rbDaTra) {
        PhieuMuon model = new PhieuMuon();
        try {
            if (!UHelper.checkNull(txtNgayTra, "Ngày trả")) {
                txtNgayTra.requestFocus();
                return false;
            }
            model.setMaPhieuMuon(Integer.parseInt(txtMaPM.getText()));
            Date ngayTra = DateHelper.toDate(txtNgayTra.getText());
            java.sql.Date sqlStartDate2 = new java.sql.Date(ngayTra.getTime());
            model.setNgayTra(sqlStartDate2);
            if (rbChuaTra.isSelected()) {
                model.setTrangThai(false);
            }
            if (rbDaTra.isSelected()) {
                model.setTrangThai(true);
            }
            dao.update(model);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(JTextField txtMaPM) {
        if (DialogHelper.confirm(null, "Bạn thực sự muốn xóa phiếu mượn này?")) {
            Integer maPM = Integer.valueOf(txtMaPM.getText());
            try {
                PhieuMuon model = dao.findById(maPM);
                dao.delete(model);
                return true;
            } catch (HeadlessException e) {
                return false;
            }
        }
        return false;
    }
}
