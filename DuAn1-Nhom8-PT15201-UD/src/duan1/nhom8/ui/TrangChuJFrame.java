/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duan1.nhom8.ui;

import duan1.nhom8.dao.NguoiDocDao;
import duan1.nhom8.helper.DialogHelper;
import duan1.nhom8.helper.ShareHelper;
import duan1.nhom8.i.SachInterface;
import duan1.nhom8.impl.SachImpl;
import duan1.nhom8.model.NguoiDoc;

/**
 *
 * @author Admin
 */
public class TrangChuJFrame extends javax.swing.JFrame {

    /**
     * Creates new form TrangChuJFrame
     */
    public TrangChuJFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        if (!ShareHelper.authenticated()) {
            jToolBar1.setVisible(false);
            jMenu4.setVisible(false);
            jMenu2.setVisible(false);
            mnDangXuat.setVisible(false);
        }
        if (ShareHelper.authenticated()) {
            if (ShareHelper.USER.isVaiTro() == true) {
                btnPhieuMuonCuaToi.setVisible(false);
                jToolBar1.setVisible(true);
                mnDangNhap.setVisible(false);
                jMenu4.setVisible(true);
                jMenu2.setVisible(true);
            }
            if (ShareHelper.USER.isVaiTro() == false) {
                btnPhieuMuon.setVisible(false);
                btnSach.setVisible(false);
                jToolBar1.setVisible(true);
                mnDangNhap.setVisible(false);
                jMenu4.setVisible(false);
                jMenu2.setVisible(false);
            }
        }
        s.loadTrangChu(tbSach, txtTimKiem);
    }

    SachInterface s = new SachImpl();
    NguoiDocDao ndDao = new NguoiDocDao();

    void login() {
        new DangNhapJDialog(this, true).setVisible(true);
    }

    void logoff() {
        ShareHelper.logoff();
        this.login();
    }

    void openPhieuMuon() {
        if (ShareHelper.authenticated()) {
            PhieuMuonJFrame pm = new PhieuMuonJFrame();
            pm.setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }
    
    void openSach(){
        if (ShareHelper.authenticated()) {
            SachJFrame s = new SachJFrame();
            s.setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }
    void openNguoiDung(){
        if (ShareHelper.authenticated()) {
            NguoiDungJFrame nd = new NguoiDungJFrame();
            nd.setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }
    
    void openNhanVien(){
        if (ShareHelper.authenticated()) {
            NhanVienJFrame nv = new NhanVienJFrame();
            nv.setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }
    
    void openPhieuMuonCuaToi() {
        if (ShareHelper.authenticated()) {
            NguoiDoc nd = ndDao.findByMaND(ShareHelper.USER.getMaNguoiDung());
            PhieuMuonCuaToiJFrame pmct = new PhieuMuonCuaToiJFrame(nd.getMaNguoiDoc());
            pmct.setVisible(true);
        } else {
            DialogHelper.alert(this, "Vui lòng đăng nhập!");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jToolBar1 = new javax.swing.JToolBar();
        btnDangXuat = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btnPhieuMuonCuaToi = new javax.swing.JButton();
        btnPhieuMuon = new javax.swing.JButton();
        btnSach = new javax.swing.JButton();
        btHuongDan = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btTimKiem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSach = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnHeThong = new javax.swing.JMenu();
        mnDangNhap = new javax.swing.JMenuItem();
        mnDangXuat = new javax.swing.JMenuItem();
        mnDoiMatKhau = new javax.swing.JMenuItem();
        mnKetThuc = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mtTaiKhoan = new javax.swing.JMenuItem();
        mnNhanVien = new javax.swing.JMenuItem();
        mnPhieuMuon = new javax.swing.JMenuItem();
        mnSach = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 204));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("THƯ VIỆN BÁCH KHOA");

        jToolBar1.setRollover(true);

        btnDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/Log out.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setFocusable(false);
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDangXuat);

        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/Clien list.png"))); // NOI18N
        jButton1.setText("Cá nhân");
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton1);

        btnPhieuMuonCuaToi.setForeground(new java.awt.Color(255, 255, 255));
        btnPhieuMuonCuaToi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/Lists.png"))); // NOI18N
        btnPhieuMuonCuaToi.setText("Phiếu mượn của tôi");
        btnPhieuMuonCuaToi.setFocusable(false);
        btnPhieuMuonCuaToi.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPhieuMuonCuaToi.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPhieuMuonCuaToi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuMuonCuaToiActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPhieuMuonCuaToi);

        btnPhieuMuon.setForeground(new java.awt.Color(255, 255, 255));
        btnPhieuMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/Certificate.png"))); // NOI18N
        btnPhieuMuon.setText("Phiếu mượn");
        btnPhieuMuon.setFocusable(false);
        btnPhieuMuon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPhieuMuon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhieuMuonActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPhieuMuon);

        btnSach.setForeground(new java.awt.Color(255, 255, 255));
        btnSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/Sach.png"))); // NOI18N
        btnSach.setText("Sách");
        btnSach.setFocusable(false);
        btnSach.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSach.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSachActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSach);

        btHuongDan.setForeground(new java.awt.Color(255, 255, 255));
        btHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/Globe.png"))); // NOI18N
        btHuongDan.setText("Hướng dẫn");
        btHuongDan.setFocusable(false);
        btHuongDan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btHuongDan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btHuongDan);

        btTimKiem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/icons8-search-50.png"))); // NOI18N
        btTimKiem.setText("Tìm");
        btTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTimKiemActionPerformed(evt);
            }
        });

        tbSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sách", "Tên sách", "Thể loại", "Tác giả", "Nhà xuất bản", "Vị trí", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbSach);
        if (tbSach.getColumnModel().getColumnCount() > 0) {
            tbSach.getColumnModel().getColumn(0).setMinWidth(0);
            tbSach.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        jDesktopPane1.setLayer(jToolBar1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txtTimKiem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btTimKiem, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(423, 423, 423)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(btTimKiem)
                .addContainerGap(480, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                .addContainerGap())
        );

        mnHeThong.setText("Hệ Thống");

        mnDangNhap.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_K, java.awt.event.InputEvent.CTRL_MASK));
        mnDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/Home.png"))); // NOI18N
        mnDangNhap.setText("Đăng nhập");
        mnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDangNhapActionPerformed(evt);
            }
        });
        mnHeThong.add(mnDangNhap);

        mnDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        mnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/Log out.png"))); // NOI18N
        mnDangXuat.setText("Đăng Xuất");
        mnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDangXuatActionPerformed(evt);
            }
        });
        mnHeThong.add(mnDangXuat);

        mnDoiMatKhau.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mnDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/Refresh.png"))); // NOI18N
        mnDoiMatKhau.setText("Đổi Mật Khẩu");
        mnDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnDoiMatKhauActionPerformed(evt);
            }
        });
        mnHeThong.add(mnDoiMatKhau);

        mnKetThuc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        mnKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/Stop_1.png"))); // NOI18N
        mnKetThuc.setText("Kết Thúc");
        mnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnKetThucActionPerformed(evt);
            }
        });
        mnHeThong.add(mnKetThuc);

        jMenuBar1.add(mnHeThong);

        jMenu2.setText("Quản Lý");

        mtTaiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/Conference.png"))); // NOI18N
        mtTaiKhoan.setText("Người dùng");
        mtTaiKhoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mtTaiKhoanActionPerformed(evt);
            }
        });
        jMenu2.add(mtTaiKhoan);

        mnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/User group.png"))); // NOI18N
        mnNhanVien.setText("Nhân viên");
        mnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnNhanVienActionPerformed(evt);
            }
        });
        jMenu2.add(mnNhanVien);

        mnPhieuMuon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/Certificate.png"))); // NOI18N
        mnPhieuMuon.setText("Phiếu Mượn");
        mnPhieuMuon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPhieuMuonActionPerformed(evt);
            }
        });
        jMenu2.add(mnPhieuMuon);

        mnSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/duan1/nhom8/icon/Lists.png"))); // NOI18N
        mnSach.setText("Sách");
        mnSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSachActionPerformed(evt);
            }
        });
        jMenu2.add(mnSach);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Thông kê");

        jMenuItem10.setText("Bạn đọc mượn sách");
        jMenu4.add(jMenuItem10);

        jMenuItem11.setText("Sách Được Mượn");
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        jMenu3.setText("Trợ giúp");

        jMenuItem12.setText("Hướng Dẫn Sử Dụng");
        jMenu3.add(jMenuItem12);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jDesktopPane1)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDesktopPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDangNhapActionPerformed
        this.login();
        this.dispose();
    }//GEN-LAST:event_mnDangNhapActionPerformed

    private void mnPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPhieuMuonActionPerformed
        this.openPhieuMuon();
    }//GEN-LAST:event_mnPhieuMuonActionPerformed

    private void mnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnNhanVienActionPerformed
        this.openNhanVien();
    }//GEN-LAST:event_mnNhanVienActionPerformed

    private void btnPhieuMuonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuMuonActionPerformed
        this.openPhieuMuon();
    }//GEN-LAST:event_btnPhieuMuonActionPerformed

    private void btTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTimKiemActionPerformed
        s.loadTrangChu(tbSach, txtTimKiem);
    }//GEN-LAST:event_btTimKiemActionPerformed

    private void btnSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSachActionPerformed
        this.openSach();
    }//GEN-LAST:event_btnSachActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        this.logoff();       
        this.dispose();
        new TrangChuJFrame().setVisible(true);
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void mnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDangXuatActionPerformed
        this.logoff();
        this.dispose();
        new TrangChuJFrame().setVisible(true);
    }//GEN-LAST:event_mnDangXuatActionPerformed

    private void btnPhieuMuonCuaToiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhieuMuonCuaToiActionPerformed
        this.openPhieuMuonCuaToi();
    }//GEN-LAST:event_btnPhieuMuonCuaToiActionPerformed

    private void mnDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnDoiMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnDoiMatKhauActionPerformed

    private void mnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnKetThucActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnKetThucActionPerformed

    private void mtTaiKhoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mtTaiKhoanActionPerformed
        this.openNguoiDung();
    }//GEN-LAST:event_mtTaiKhoanActionPerformed

    private void mnSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSachActionPerformed
        this.openSach();
    }//GEN-LAST:event_mnSachActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrangChuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrangChuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrangChuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrangChuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TrangChuJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btHuongDan;
    private javax.swing.JButton btTimKiem;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnPhieuMuon;
    private javax.swing.JButton btnPhieuMuonCuaToi;
    private javax.swing.JButton btnSach;
    private javax.swing.JButton jButton1;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem mnDangNhap;
    private javax.swing.JMenuItem mnDangXuat;
    private javax.swing.JMenuItem mnDoiMatKhau;
    private javax.swing.JMenu mnHeThong;
    private javax.swing.JMenuItem mnKetThuc;
    private javax.swing.JMenuItem mnNhanVien;
    private javax.swing.JMenuItem mnPhieuMuon;
    private javax.swing.JMenuItem mnSach;
    private javax.swing.JMenuItem mtTaiKhoan;
    private javax.swing.JTable tbSach;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
