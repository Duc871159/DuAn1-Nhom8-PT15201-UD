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
import duan1.nhom8.dao.SachDao;
import duan1.nhom8.dao.TrangThaiSachDao;
import duan1.nhom8.helper.DateHelper;
import duan1.nhom8.helper.DialogHelper;
import duan1.nhom8.helper.JdbcHelper;
import duan1.nhom8.helper.UHelper;
import duan1.nhom8.i.PhieuMuonInterface;
import duan1.nhom8.model.NguoiDoc;
import duan1.nhom8.model.NguoiDung;
import duan1.nhom8.model.NhanVien;
import duan1.nhom8.model.PhieuMuon;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
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
    TrangThaiSachDao ttsDao = new TrangThaiSachDao();
    SachDao sachDao = new SachDao();

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

    @Override
    public void setStatus() {
        List<PhieuMuon> list = dao.select();
        try {
            for (PhieuMuon pm : list) {
                String tongSo = "Select COUNT(maPhieuMuon) as tongso from trangThaiSach where maPhieuMuon = ?";
                String hienTai = "Select COUNT(maPhieuMuon) as hientai from trangThaiSach where maPhieuMuon = ? and trangThai = 1";

                ResultSet rs = JdbcHelper.executeQuery(tongSo, pm.getMaPhieuMuon());
                ResultSet rs1 = JdbcHelper.executeQuery(hienTai, pm.getMaPhieuMuon());
                while (rs.next() && rs1.next()) {
                    if (rs.getInt("tongso") == rs1.getInt("hientai")) {
                        dao.updateTrue(pm);
                    }
                    if (rs.getInt("tongso") != rs1.getInt("hientai")) {
                        dao.updateFalse(pm);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public void sendMail() {
        try {
            List<PhieuMuon> list = dao.select();
            for (PhieuMuon pm : list) {
                NguoiDoc ndoc = nguoiDocDao.findById(pm.getMaNguoiDoc());
                NguoiDung nd = nguoiDungDao.findById(ndoc.getMaNguoiDung());
                long ngay = ((new Date()).getTime() - pm.getNgayTra().getTime()) / (24 * 3600 * 1000);
                long ngayGuiMail = ((pm.getNgayTra().getTime()) - (new Date()).getTime()) / (24 * 3600 * 1000) + 1;
                System.out.println("So ngay muon: " + ngay);
                System.out.println("So con lai: " + ngayGuiMail);
                Properties mailServerProperties;
                Session getMailSession;
                MimeMessage mailMessage;

                //Setup Mail server
                mailServerProperties = System.getProperties();
                mailServerProperties.put("mail.smtp.port", "587");
                mailServerProperties.put("mail.smtp.auth", "true");
                mailServerProperties.put("mail.smtp.starttls.enable", "true");
                //Get Mail Session
                getMailSession = Session.getDefaultInstance(mailServerProperties, null);
                mailMessage = new MimeMessage(getMailSession);

                mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(nd.getEmail())); //Mail người nhận
                System.out.println(nd.getEmail());
//                  generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail
                mailMessage.setSubject("Thư viện bách khoa");
                if (!pm.isTrangThai() && ngay == 3 && pm.getGuiMail() == 4) {
                    mailMessage.setText("Phiếu mượn có mã: " + pm.getMaPhieuMuon() + "\n"
                            + "Ngày hẹn trả: " + pm.getNgayTra() + "\n"
                            + "Bạn đã quá hạn trả sách: " + ngay + " ngày \n"
                            + "Số tiền phạt trả muộn: " + ngay * 10000 + "đ");
                    dao.updateGuiMail(pm);
                    // Send mail
                    Transport transport = getMailSession.getTransport("smtp");

                    //Mail người gửi
                    transport.connect("smtp.gmail.com", "ducnguyen871159@gmail.com", "uoduccexpojsgogy");
                    transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
                    transport.close();
                    System.err.println("Success");
                }
                if (!pm.isTrangThai() && ngay == 1 && pm.getGuiMail() == 3) {
                    mailMessage.setText("Phiếu mượn có mã: " + pm.getMaPhieuMuon() + "\n"
                            + "Ngày hẹn trả: " + pm.getNgayTra() + "\n"
                            + "Bạn đã quá hạn trả sách: " + ngay + " ngày \n"
                            + "Số tiền phạt trả muộn: " + ngay * 10000 + "đ");
                    dao.updateGuiMail(pm);
                    // Send mail
                    Transport transport = getMailSession.getTransport("smtp");

                    //Mail người gửi
                    transport.connect("smtp.gmail.com", "ducnguyen871159@gmail.com", "uoduccexpojsgogy");
                    transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
                    transport.close();
                    System.err.println("Success");
                }
                if (!pm.isTrangThai() && ngay == 0 && pm.getGuiMail() == 2) {
                    mailMessage.setText("Phiếu mượn có mã: " + pm.getMaPhieuMuon() + "\n"
                            + "Ngày hẹn trả (hôm nay): " + pm.getNgayTra() + "\n"
                            + "Mong bạn lưu ý trả sách đúng thời hạn, số ngày còn lại: " + ngay);
                    dao.updateGuiMail(pm);
                    // Send mail
                    Transport transport = getMailSession.getTransport("smtp");

                    //Mail người gửi
                    transport.connect("smtp.gmail.com", "ducnguyen871159@gmail.com", "uoduccexpojsgogy");
                    transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
                    transport.close();
                    System.err.println("Success");
                }
                if (!pm.isTrangThai() && ngayGuiMail == 1 && pm.getGuiMail() == 1) {
                    mailMessage.setText("Phiếu mượn có mã: " + pm.getMaPhieuMuon() + "\n"
                            + "Ngày hẹn trả: " + pm.getNgayTra() + "\n"
                            + "Mong bạn lưu ý trả sách đúng thời hạn, số ngày còn lại: " + ngayGuiMail);
                    dao.updateGuiMail(pm);
                    // Send mail
                    Transport transport = getMailSession.getTransport("smtp");

                    //Mail người gửi
                    transport.connect("smtp.gmail.com", "ducnguyen871159@gmail.com", "uoduccexpojsgogy");
                    transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
                    transport.close();
                    System.err.println("Success");
                }
                if (!pm.isTrangThai() && ngayGuiMail == 5 && pm.getGuiMail() == 0) {
                    mailMessage.setText("Phiếu mượn có mã: " + pm.getMaPhieuMuon() + "\n"
                            + "Ngày hẹn trả: " + pm.getNgayTra() + "\n"
                            + "Mong bạn lưu ý trả sách đúng thời hạn, số ngày còn lại: " + ngayGuiMail);
                    dao.updateGuiMail(pm);
                    // Send mail
                    Transport transport = getMailSession.getTransport("smtp");

                    //Mail người gửi
                    transport.connect("smtp.gmail.com", "ducnguyen871159@gmail.com", "uoduccexpojsgogy");
                    transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
                    transport.close();
                    System.err.println("Success");
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
