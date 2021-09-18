/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import static display.frmLogin.ID;
import extensions.Ket_Noi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import process.NguoiDung;
import process.ThongTinNguoiDung;

/**
 *
 * @author ASUS
 */
public class frmDangKy extends javax.swing.JFrame {

    public static String ID_user;
    Connection conn;
    Statement st;
    ResultSet rs;
    List<NguoiDung> listNguoiDung = new ArrayList<>();
    List<ThongTinNguoiDung> listThongTinNguoiDung = new ArrayList<>();

    /**
     * Creates new form frmDangKy
     */
    public frmDangKy() {
        initComponents();
        setLocationRelativeTo(this);
        rdoNam.setSelected(true);
        conn = Ket_Noi.ket_noi("KhaiBaoYTe");
        if (conn != null) {
            LoadDataToListNguoiDung();
            LoadDataToListThongTinNguoiDung();
        } else {
            System.out.println("Lỗi kết nối!!");
        }
    }

    private void LoadDataToListNguoiDung() {
        try {
            String sql = "select * from NguoiDung";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("id");
                String sdt = rs.getString("sdt");
                String password = rs.getString("password");
                String role = rs.getString("role");
                listNguoiDung.add(new NguoiDung(id, sdt, password, role));
            }
            st.close();
            rs.close();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi load data nguoi dung to list ");
        }
    }

    private void LoadDataToListThongTinNguoiDung() {
        try {
            String sql = "select * from ThongTinNguoiDung";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("id");
                String nguoidung_id = rs.getString("nguoidung_id");
                String hoten = rs.getString("hoten");
                String namsinh = rs.getString("namsinh");
                String diachi = rs.getString("diachi");
                String cccd = rs.getString("cccd");
                String gioitinh = rs.getString("gioitinh");
                String email = rs.getString("email");
                String quoctich = rs.getString("quoctich");
                listThongTinNguoiDung.add(new ThongTinNguoiDung(id, nguoidung_id, hoten, namsinh, diachi, cccd, gioitinh, email, quoctich));
            }
            st.close();
            rs.close();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi load data thong tin nguoi dung to list ");
        }
    }
    
    public boolean checkSDT() {
        String SDT = txtSDT.getText();
        for (NguoiDung nd : listNguoiDung) {
            if (nd.getSdt().equalsIgnoreCase(SDT)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkMK() {
        String matkhau = new String(txtMatKhau.getPassword());
        String nhaplaimk = new String(txtNhapLaiMatKhau.getPassword());
        if (matkhau.equalsIgnoreCase(nhaplaimk)) {
            return true;
        }
        return false;
    }

    public void getIdFromUser() {
        try {
            String sql = "select id from NguoiDung where sdt=? and password=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            String sdt = txtSDT.getText();
            String password = new String(txtMatKhau.getPassword());

            pstm.setString(1, sdt);
            pstm.setString(2, password);

            rs = pstm.executeQuery();
            while (rs.next()) {
                ID_user = rs.getString("id");
            }
            pstm.close();
            rs.close();
            System.out.println(ID_user);
        } catch (Exception e) {
        }
    }

    public void themnguoidung() {
        try {
            String sdt = txtSDT.getText();
            String password = new String(txtMatKhau.getPassword());
            String role = "user";
            listNguoiDung.add(new NguoiDung(sdt, password, role));
            //them vao database
            String sql = "insert into NguoiDung(sdt,password,role)\n"
                    + " values('" + sdt + "','" + password + "','" + role + "')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void themThongTinNguoiDung() {
        try {
            String nguoidung_id = ID_user;
            String hoten = txtHoTen.getText();
            String namsinh = txtNamSinh.getText();
            String diachi = txtDiaChi.getText();
            String cccd = txtCCCD.getText();
            String gioitinh;
            if (rdoNam.isSelected()) {
                gioitinh = "nam";
            } else {
                gioitinh = "nữ";
            }
            String email = txtEmail.getText();
            String quoctich = txtQuocTich.getText();
            listThongTinNguoiDung.add(new ThongTinNguoiDung(nguoidung_id, hoten, namsinh, diachi, cccd, gioitinh, email, quoctich));
            //them vao database
            String sql = "insert into ThongTinNguoiDung(nguoidung_id,hoten,namsinh,diachi,cccd,gioitinh,email,quoctich)\n"
                    + " values('" + nguoidung_id + "',N'" + hoten + "','" + namsinh + "',N'" + diachi + "','" + cccd + "',N'" + gioitinh + "','" + email + "',N'" + quoctich + "')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Đăng ký tài khoản thành công");
            this.dispose();
            frmLogin login = new frmLogin();
            login.setVisible(true);
        } catch (Exception e) {
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNamSinh = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtQuocTich = new javax.swing.JTextField();
        btnDangky = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        txtNhapLaiMatKhau = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ĐĂNG KÝ");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Số điện thoại:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Mật khẩu:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Họ tên:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Năm sinh");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Địa chỉ:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("CCCD:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Giới tính:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Email:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Quốc tịch:");

        btnDangky.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnDangky.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Unlock.png"))); // NOI18N
        btnDangky.setText("Đăng ký");
        btnDangky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangkyActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Exit button.png"))); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoNu.setText("Nữ");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Nhập lại mật khẩu:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnDangky)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtQuocTich)
                    .addComponent(txtEmail)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamSinh, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9)
                                .addComponent(rdoNam))
                            .addGap(42, 42, 42)
                            .addComponent(rdoNu)))
                    .addComponent(txtMatKhau)
                    .addComponent(txtNhapLaiMatKhau))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(11, 11, 11)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNhapLaiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(txtQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDangky, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDangkyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangkyActionPerformed
        // TODO add your handling code here:
        if (checkSDT()) {
            if (checkMK()) {
                themnguoidung();
                getIdFromUser();
                themThongTinNguoiDung();
            } else {
                JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không khớp");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Số điện thoại đã tồn tại");
        }
    }//GEN-LAST:event_btnDangkyActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        // TODO add your handling code here:
        this.dispose();
        frmLogin login = new frmLogin();
        login.setVisible(true);
    }//GEN-LAST:event_btnThoatActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangky;
    private javax.swing.JButton btnThoat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JPasswordField txtMatKhau;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JPasswordField txtNhapLaiMatKhau;
    private javax.swing.JTextField txtQuocTich;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
