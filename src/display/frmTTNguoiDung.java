/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import extensions.Ket_Noi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import process.NguoiDung;
import process.ThongTinNguoiDung;

/**
 *
 * @author ASUS
 */
public class frmTTNguoiDung extends javax.swing.JInternalFrame {

    Connection conn;
    int index;
    List<ThongTinNguoiDung> list = new ArrayList<>();
    List<NguoiDung> listNguoiDung = new ArrayList<>();

    /**
     * Creates new form frmNguoiDung
     */
    public frmTTNguoiDung() {
        initComponents();
        conn = Ket_Noi.ket_noi("KhaiBaoYTe");
        if (conn != null) {
            LoadDataToListTTNguoiDung();
            fillToTableTTNguoiDung();
            LoadDataToListNguoiDung();
            fillToTableNguoiDung();
        } else {
            System.out.println("Lỗi kết nối !!");
        }
    }

    //Lấy danh sách
    private void LoadDataToListTTNguoiDung() {
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
                list.add(new ThongTinNguoiDung(id, nguoidung_id, hoten, namsinh, diachi, cccd, gioitinh, email, quoctich));
            }
            st.close();
            rs.close();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi load data thong tin nguoi dung to list ");
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
    

    public void fillToTableTTNguoiDung() {
        DefaultTableModel model = (DefaultTableModel) tblTTNguoiDung.getModel();
        model.setRowCount(0);

        for (ThongTinNguoiDung ttnd : list) {
            model.addRow(new Object[]{
                ttnd.getId(),
                ttnd.getNguoidung_id(),
                ttnd.getHoten(),
                ttnd.getNamsinh(),
                ttnd.getDiachi(),
                ttnd.getCccd(),
                ttnd.getGioitinh(),
                ttnd.getEmail(),
                ttnd.getQuoctich(),});
        }
    }
    
    public void fillToTableNguoiDung() {
        DefaultTableModel model = (DefaultTableModel) tblNguoiDung.getModel();
        model.setRowCount(0);

        for (NguoiDung nd : listNguoiDung) {
            model.addRow(new Object[]{
                nd.getId(),
                nd.getSdt(),
                nd.getPassword(),
                nd.getRole(),});
        }
    }
    

    public void showDetail(int index) throws SQLException, ClassNotFoundException {
        ThongTinNguoiDung ttnd = dao.DAOThongTinNguoiDung.ListHD().get(index);
        txtID.setText(ttnd.getId());
        txtNguoiDung_ID.setText(ttnd.getNguoidung_id());
        txtHoTen.setText(ttnd.getHoten());
        txtNamSinh.setText(ttnd.getNamsinh());
        txtDiachi.setText(ttnd.getDiachi());
        txtCCCD.setText(ttnd.getCccd());
        if (ttnd.getGioitinh().equals("nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtEmail.setText(ttnd.getEmail());
        txtQuocTich.setText(ttnd.getQuoctich());
        tblTTNguoiDung.setRowSelectionInterval(index, index);
    }
    
    public void showDetailNguoiDung(int index) throws SQLException, ClassNotFoundException {
        NguoiDung nd = dao.DAONguoiDung.listND().get(index);
        txtIDNguoiDung.setText(nd.getId());
        txtSDT.setText(nd.getSdt());
        txtMK.setText(nd.getPassword());
        txtRole.setText(nd.getRole());
        tblNguoiDung.setRowSelectionInterval(index, index);
    }
    

    public void clearForm() {
        try {
            txtID.setText("");
            txtNguoiDung_ID.setText("");
            txtHoTen.setText("");
            txtNamSinh.setText("");
            txtDiachi.setText("");
            txtCCCD.setText("");
            rdoNam.setSelected(true);
            txtEmail.setText("");
            txtQuocTich.setText("");
        } catch (Exception e) {

        }
    }
    
    public void clearFormNguoiDung() {
        try {
            txtIDNguoiDung.setText("");
            txtSDT.setText("");
            txtMK.setText("");
            txtRole.setText("");
        } catch (Exception e) {

        }
    }

    // load lai table
    public void loadForm() {
        try {
            bll.ViewTTNguoiDung.HienThiTTNguoiDung(tblTTNguoiDung);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void loadFormNguoiDung() {
        try {
            bll.ViewNguoiDung.HienThiNguoiDung(tblNguoiDung);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void Them() {
        try {
            String nguoidung_id = txtNguoiDung_ID.getText();
            String hoten = txtHoTen.getText();
            String namsinh = txtNamSinh.getText();
            String diachi = txtDiachi.getText();
            String cccd = txtCCCD.getText();
            String gioitinh;
            if (rdoNam.isSelected()) {
                gioitinh = "nam";
            } else {
                gioitinh = "nữ";
            }
            String email = txtEmail.getText();
            String quoctich = txtQuocTich.getText();
            list.add(new ThongTinNguoiDung(nguoidung_id, hoten, namsinh, diachi, cccd, gioitinh, email, quoctich));
            //them vao database
            String sql = "insert into ThongTinNguoiDung(nguoidung_id,hoten,namsinh,diachi,cccd,gioitinh,email,quoctich)\n"
                    + " values('" + nguoidung_id + "',N'" + hoten + "','" + namsinh + "','" + diachi + "','" + cccd + "',N'" + gioitinh + "','" + email + "','" + quoctich + "')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Thêm thông tin người dùng thành công");
            fillToTableTTNguoiDung();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm thông tin người dùng không thành công");
        }
    }
    
    public void ThemNguoiDung() {
        try {
            String sdt = txtSDT.getText();
            String password = txtMK.getText();
            String role = txtRole.getText();

            listNguoiDung.add(new NguoiDung(sdt, password, role));
            //them vao database
            String sql = "insert into NguoiDung(sdt,password,role)\n"
                    + " values('" + sdt + "','" + password + "','" + role + "')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Thêm thông tin người dùng thành công");
            fillToTableNguoiDung();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm thông tin người dùng không thành công");
        }
    }

    public void xoa() {
        try {
            if (list.size() <= 0) {
                JOptionPane.showMessageDialog(this, "Không còn dữ liệu để xóa");
                return;
            }
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            // xoa trong list
            index = tblTTNguoiDung.getSelectedRow();
            list.remove(index);

            // xoa trong csdl
            String sql = "delete from ThongTinNguoiDung\n"
                    + "where id ='" + txtID.getText() + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Xóa dữ liệu thành công");
            fillToTableTTNguoiDung();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa dữ liệu không thành công");
        }
    }
    
    public void xoaNguoiDung() {
        try {
            if (listNguoiDung.size() <= 0) {
                JOptionPane.showMessageDialog(this, "Không còn dữ liệu để xóa");
                return;
            }
            int hoi = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            }
            // xoa trong list
            index = tblNguoiDung.getSelectedRow();
            listNguoiDung.remove(index);

            // xoa trong csdl
            String sql = "delete from NguoiDung\n"
                    + "where id ='" + txtIDNguoiDung.getText() + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Xóa dữ liệu thành công");
            fillToTableNguoiDung();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Xóa dữ liệu không thành công");
        }
    }

    public void sua() {
        try {
            String id = txtID.getText();
            String nguoidung_id = txtNguoiDung_ID.getText();
            String hoten = txtHoTen.getText();
            String namsinh = txtNamSinh.getText();
            String diachi = txtDiachi.getText();
            String cccd = txtCCCD.getText();
            String gioitinh;
            if (rdoNam.isSelected()) {
                gioitinh = "nam";
            } else {
                gioitinh = "nữ";
            }
            String email = txtEmail.getText();
            String quoctich = txtQuocTich.getText();
            list.set(index, new ThongTinNguoiDung(id, nguoidung_id, hoten, namsinh, diachi, cccd, gioitinh, email, quoctich));
            // sua vao csdl
            String sql = "update ThongTinNguoiDung set nguoidung_id='" + nguoidung_id + "'\n"
                    + ",hoten=N'" + hoten + "',namsinh='" + namsinh + "',diachi=N'" + diachi + "'\n"
                    + ",cccd='" + cccd + "',gioitinh=N'" + gioitinh + "',email='" + email + "'\n"
                    + ",quoctich=N'" + quoctich + "'\n"
                    + "where id='" + id + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Sửa dữ liệu thành công");
            fillToTableTTNguoiDung();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sửa dữ liệu không thành công");
        }
    }
    
    public void suaNguoiDung() {
        try {
            String id = txtIDNguoiDung.getText();
            String sdt = txtSDT.getText();
            String password = txtMK.getText();
            String role = txtRole.getText();
            listNguoiDung.set(index, new NguoiDung(id, sdt, password, role));

            String sql = "update NguoiDung set sdt ='" + sdt + "'\n"
                    + ", password = '" + password + "'\n"
                    + ",role ='" + role + "' where id = '" + id + "'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Sửa dữ liệu thành công");
            fillToTableNguoiDung();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Sửa dữ liệu không thành công");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTTNguoiDung = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        rdoNu = new javax.swing.JRadioButton();
        btnThemTTNguoiDung = new javax.swing.JButton();
        btnSuaTTNguoiDung = new javax.swing.JButton();
        btnXoaTTNguoiDung = new javax.swing.JButton();
        txtEmail = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtQuocTich = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNguoiDung_ID = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        txtNamSinh = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        txtDiachi = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        btnLamMoiTTNguoiDung = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNguoiDung = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtIDNguoiDung = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtMK = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtRole = new javax.swing.JTextField();
        btnSuaNguoiDung = new javax.swing.JButton();
        btnThemNguoiDung = new javax.swing.JButton();
        btnXoaNguoiDung = new javax.swing.JButton();
        btnLamMoiNguoiDung = new javax.swing.JButton();

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tblTTNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã người dùng", "Họ tên", "Năm sinh", "Địa chỉ", "CCCD", "Giới tính", "Email", "Quốc tịch"
            }
        ));
        tblTTNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTTNguoiDungMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTTNguoiDung);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin người dùng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        buttonGroup1.add(rdoNu);
        rdoNu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoNu.setText("Nữ");

        btnThemTTNguoiDung.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThemTTNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Add.png"))); // NOI18N
        btnThemTTNguoiDung.setText("Thêm");
        btnThemTTNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemTTNguoiDungActionPerformed(evt);
            }
        });

        btnSuaTTNguoiDung.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSuaTTNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Edit.png"))); // NOI18N
        btnSuaTTNguoiDung.setText("Sửa");
        btnSuaTTNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaTTNguoiDungActionPerformed(evt);
            }
        });

        btnXoaTTNguoiDung.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoaTTNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        btnXoaTTNguoiDung.setText("Xóa");
        btnXoaTTNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaTTNguoiDungActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Mã người dùng:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Họ tên:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Năm sinh:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Địa chỉ:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("CCCD:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Giới tính:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Email:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Quốc tịch:");

        buttonGroup1.add(rdoNam);
        rdoNam.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoNam.setText("Nam");

        btnLamMoiTTNguoiDung.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLamMoiTTNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        btnLamMoiTTNguoiDung.setText("Làm Mới");
        btnLamMoiTTNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiTTNguoiDungActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(txtNguoiDung_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addGap(44, 44, 44)
                                .addComponent(rdoNu)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemTTNguoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSuaTTNguoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoaTTNguoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoiTTNguoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemTTNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNguoiDung_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)
                            .addComponent(btnSuaTTNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaTTNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuocTich, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLamMoiTTNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtDiachi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("QUẢN LÝ NGƯỜI DÙNG");

        tblNguoiDung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Số điện thoại", "Mật khẩu", "Vai trò"
            }
        ));
        tblNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNguoiDungMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNguoiDung);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tài khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Mã người dùng:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Số điện thoại:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Mật khẩu:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Vai trò:");

        btnSuaNguoiDung.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSuaNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Edit.png"))); // NOI18N
        btnSuaNguoiDung.setText("Sửa");
        btnSuaNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNguoiDungActionPerformed(evt);
            }
        });

        btnThemNguoiDung.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnThemNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Add.png"))); // NOI18N
        btnThemNguoiDung.setText("Thêm");
        btnThemNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNguoiDungActionPerformed(evt);
            }
        });

        btnXoaNguoiDung.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnXoaNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Delete.png"))); // NOI18N
        btnXoaNguoiDung.setText("Xóa");
        btnXoaNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNguoiDungActionPerformed(evt);
            }
        });

        btnLamMoiNguoiDung.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLamMoiNguoiDung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        btnLamMoiNguoiDung.setText("Làm mới");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)
                        .addComponent(jLabel9)
                        .addComponent(jLabel14)
                        .addComponent(txtIDNguoiDung)
                        .addComponent(txtSDT)
                        .addComponent(txtMK, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                    .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLamMoiNguoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnThemNguoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                        .addComponent(btnSuaNguoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnXoaNguoiDung, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(jLabel8)
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRole, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiNguoiDung, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tblTTNguoiDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTTNguoiDungMouseClicked
        // TODO add your handling code here:
        index = tblTTNguoiDung.getSelectedRow();
        try {
            showDetail(index);
        } catch (Exception ex) {
            Logger.getLogger(frmTTNguoiDung.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblTTNguoiDungMouseClicked

    private void btnThemTTNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemTTNguoiDungActionPerformed
        // TODO add your handling code here:
        Them();
        loadForm();
    }//GEN-LAST:event_btnThemTTNguoiDungActionPerformed

    private void btnLamMoiTTNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiTTNguoiDungActionPerformed
        // TODO add your handling code here:
        clearForm();
        LoadDataToListTTNguoiDung();
        fillToTableTTNguoiDung();
        loadForm();
    }//GEN-LAST:event_btnLamMoiTTNguoiDungActionPerformed

    private void btnSuaTTNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaTTNguoiDungActionPerformed
        // TODO add your handling code here:
        sua();
        loadForm();
    }//GEN-LAST:event_btnSuaTTNguoiDungActionPerformed

    private void btnXoaTTNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaTTNguoiDungActionPerformed
        // TODO add your handling code here:
        xoa();
        loadForm();
    }//GEN-LAST:event_btnXoaTTNguoiDungActionPerformed

    private void tblNguoiDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNguoiDungMouseClicked
        // TODO add your handling code here:
        index = tblNguoiDung.getSelectedRow();
        try {
            showDetailNguoiDung(index);
        } catch (Exception ex) {
            Logger.getLogger(frmTTNguoiDung.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblNguoiDungMouseClicked

    private void btnSuaNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNguoiDungActionPerformed
        // TODO add your handling code here:
        suaNguoiDung();
        loadFormNguoiDung();
    }//GEN-LAST:event_btnSuaNguoiDungActionPerformed

    private void btnThemNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNguoiDungActionPerformed
        // TODO add your handling code here:
        ThemNguoiDung();
        loadFormNguoiDung();
    }//GEN-LAST:event_btnThemNguoiDungActionPerformed

    private void btnXoaNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNguoiDungActionPerformed
        // TODO add your handling code here:
        xoaNguoiDung();
        loadFormNguoiDung();
    }//GEN-LAST:event_btnXoaNguoiDungActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiNguoiDung;
    private javax.swing.JButton btnLamMoiTTNguoiDung;
    private javax.swing.JButton btnSuaNguoiDung;
    private javax.swing.JButton btnSuaTTNguoiDung;
    private javax.swing.JButton btnThemNguoiDung;
    private javax.swing.JButton btnThemTTNguoiDung;
    private javax.swing.JButton btnXoaNguoiDung;
    private javax.swing.JButton btnXoaTTNguoiDung;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblNguoiDung;
    private javax.swing.JTable tblTTNguoiDung;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiachi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDNguoiDung;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtNamSinh;
    private javax.swing.JTextField txtNguoiDung_ID;
    private javax.swing.JTextField txtQuocTich;
    private javax.swing.JTextField txtRole;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
