/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import extensions.Ket_Noi;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import javafx.scene.control.Cell;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import process.NhapCanh;
import process.NoiDia;
import process.ToanDan;

/**
 *
 * @author ASUS
 */
public class frmQLToKhai extends javax.swing.JInternalFrame {

    Connection conn;
    int index;
    List<ToanDan> listToanDan = new ArrayList<>();
    List<NoiDia> listNoiDia = new ArrayList<>();
    List<NhapCanh> listNhapCanh = new ArrayList<>();

    /**
     * Creates new form QLToKhai
     */
    public frmQLToKhai() {
        initComponents();
        conn = Ket_Noi.ket_noi("KhaiBaoYTe");
        if (conn != null) {
            LoadDataToListToanDan();
            fillToTableToanDan();
            LoadDataToListNoiDia();
            fillToTableNoiDia();
            LoadDataToListNhapCanh();
            fillToTableNhapCanh();
        } else {
            System.out.println("Lỗi kết nối!!");
        }
    }

    //lay danh sach
    private void LoadDataToListToanDan() {
        try {
            String sql = "select * from KhaiBaoToanDan";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String id = rs.getString("id");
                String nguoidung_id = rs.getString("nguoidung_id");
                String dichuyen = rs.getString("dichuyen");
                String trieuchung = rs.getString("trieuchung");
                String nghinhiem = rs.getString("nghinhiem");
                String nuocbenh = rs.getString("nuocbenh");
                String bieuhien = rs.getString("bieuhien");
                String ngaykhaibao = rs.getString("ngaykhaibao");
                listToanDan.add(new ToanDan(id, nguoidung_id, dichuyen, trieuchung, nghinhiem, nuocbenh, bieuhien, ngaykhaibao));
            }
            st.close();
            rs.close();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi load data thong tin khai bao toan dan to list ");
        }
    }
    private void LoadDataToListNoiDia(){
        try {
            String sql = "select * from KhaiBaoNoiDia";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
             while (rs.next()) {
                String id = rs.getString("id");
                String nguoidung_id = rs.getString("nguoidung_id");
                String phuongtien = rs.getString("phuongtien");
                String mahieuphuongtien = rs.getString("mahieuphuongtien");
                String noidi = rs.getString("noidi");
                String noiden = rs.getString("noiden");
                String ngaykhoihanh = rs.getString("ngaykhoihanh");
                String dichuyen = rs.getString("dichuyen");
                String trieuchung = rs.getString("trieuchung");
                String nghinhiem = rs.getString("nghinhiem");
                String nuocbenh = rs.getString("nuocbenh");
                String bieuhien = rs.getString("bieuhien");
                String ngaykhaibao = rs.getString("ngaykhaibao");
                listNoiDia.add(new NoiDia(id, nguoidung_id, phuongtien, mahieuphuongtien, noidi, noiden, ngaykhoihanh, dichuyen, trieuchung, nghinhiem, nuocbenh, bieuhien, ngaykhaibao));
             }
             st.close();
             rs.close();
             return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi load data thong tin khai bao noi dia to list ");
        }
    }
    public void LoadDataToListNhapCanh(){
        try {
            String sql = "select * from KhaiBaoNhapCanh";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
             while (rs.next()) {
                String id = rs.getString("id");
                String nguoidung_id = rs.getString("nguoidung_id");
                String cuakhau = rs.getString("cuakhau");
                String thongtindilai = rs.getString("thongtindilai");
                String ngaykhoihanh = rs.getString("ngaykhoihanh");
                String ngaynhapcanh = rs.getString("ngaynhapcanh");
                String diachikhoihanh = rs.getString("diachikhoihanh");
                String diachiden = rs.getString("diachiden");
                String noiosaucachly = rs.getString("noiosaucachly");
                String sot = rs.getString("sot");
                String ho = rs.getString("ho");
                String khotho = rs.getString("khotho");
                String dauhong = rs.getString("dauhong");
                String ngaykhaibao = rs.getString("ngaykhaibao");
                listNhapCanh.add(new NhapCanh(id, nguoidung_id, cuakhau, thongtindilai, ngaykhoihanh, ngaynhapcanh, diachikhoihanh, diachiden, noiosaucachly, sot, ho, khotho, dauhong, ngaykhaibao));
             }
             st.close();
             rs.close();
             return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi load data thong tin khai bao nhap canh to list ");
        }
    }
    

    public void fillToTableToanDan() {
        DefaultTableModel model = (DefaultTableModel) tblQLToanDan.getModel();
        model.setRowCount(0);

        for (ToanDan td : listToanDan) {
            model.addRow(new Object[]{
                td.getId(),
                td.getNguoidung_id(),
                td.getDichuyen(),
                td.getTrieuchung(),
                td.getNghinhiem(),
                td.getNuocbenh(),
                td.getBieuhien(),
                td.getNgaykhaibao(),});
        }
    }
    public void fillToTableNoiDia(){
        DefaultTableModel model = (DefaultTableModel) tblQLNoiDia.getModel();
        model.setRowCount(0);
        
        for(NoiDia nd : listNoiDia){
            model.addRow(new Object[]{
            nd.getId(),
            nd.getNguoidung_id(),
            nd.getPhuongtien(),
            nd.getMahieuphuongtien(),
            nd.getNoidi(),
            nd.getNoiden(),
            nd.getNgaykhoihanh(),
            nd.getDichuyen(),
            nd.getTrieuchung(),
            nd.getNghinhiem(),
            nd.getNuocbenh(),
            nd.getBieuhien(),
            nd.getNgaykhaibao(),
        });
        }
    }
    public void fillToTableNhapCanh(){
        DefaultTableModel model = (DefaultTableModel) tblQLNhapCanh.getModel();
        model.setRowCount(0);
        
        for(NhapCanh nc : listNhapCanh){
            model.addRow(new Object[]{
                nc.getId(),
                nc.getNguoidung_id(),
                nc.getCuakhau(),
                nc.getThongtindilai(),
                nc.getNgaykhoihanh(),
                nc.getNgaynhapcanh(),
                nc.getDiachikhoihanh(),
                nc.getDiachiden(),
                nc.getNoiosaucachly(),
                nc.getSot(),
                nc.getHo(),
                nc.getKhotho(),
                nc.getDauhong(),
                nc.getNgaykhaibao(),
            });
        }
    }

    public void loadFormToanDan() {
        try {
            bll.ViewToanDan.HienThiTKToanDan(tblQLToanDan);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void loadFormNoiDia(){
        try {
            bll.ViewNoiDia.HienThiTKNoiDia(tblQLNoiDia);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void loadFormNhapCanh(){
        try {
            bll.ViewNhapCanh.HienThiTKNhapCanh(tblQLNhapCanh);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpnToanDan = new javax.swing.JPanel();
        jscrollPane = new javax.swing.JScrollPane();
        tblQLToanDan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtTimKiemToanDan = new javax.swing.JTextField();
        btnLamMoiToanDan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnTimKiemToanDan = new javax.swing.JButton();
        btnInToanDan = new javax.swing.JButton();
        jpnNoiDia = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLNoiDia = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtTimKiemNoiDia = new javax.swing.JTextField();
        btnTimKiemNoiDia = new javax.swing.JButton();
        btnLamMoiNoiDia = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnInNoiDia = new javax.swing.JButton();
        jpnNhapCanh = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblQLNhapCanh = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTimKiemNhapCanh = new javax.swing.JTextField();
        btnTimKiemNhapCanh = new javax.swing.JButton();
        btnLamMoiNhapCanh = new javax.swing.JButton();
        btnInNhapCanh = new javax.swing.JButton();

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        tblQLToanDan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Mã người dùng", "Di chuyển", "Triệu chứng", "Nghi nhiễm", "Nước bẹnh", "Biểu Hiện", "Ngày khai báo"
            }
        ));
        jscrollPane.setViewportView(tblQLToanDan);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Tìm Kiếm");

        btnLamMoiToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLamMoiToanDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        btnLamMoiToanDan.setText("Làm mới");
        btnLamMoiToanDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiToanDanActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("QUẢN LÝ KHAI BÁO Y TẾ TOÀN DÂN");

        btnTimKiemToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTimKiemToanDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        btnTimKiemToanDan.setText("Tìm kiếm");
        btnTimKiemToanDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemToanDanActionPerformed(evt);
            }
        });

        btnInToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInToanDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        btnInToanDan.setText("In");
        btnInToanDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInToanDanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnToanDanLayout = new javax.swing.GroupLayout(jpnToanDan);
        jpnToanDan.setLayout(jpnToanDanLayout);
        jpnToanDanLayout.setHorizontalGroup(
            jpnToanDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnToanDanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnToanDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jscrollPane)
                    .addGroup(jpnToanDanLayout.createSequentialGroup()
                        .addGroup(jpnToanDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jpnToanDanLayout.createSequentialGroup()
                                .addComponent(txtTimKiemToanDan, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(btnTimKiemToanDan)
                                .addGap(66, 66, 66)
                                .addComponent(btnLamMoiToanDan)
                                .addGap(38, 38, 38)
                                .addComponent(btnInToanDan, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 296, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnToanDanLayout.setVerticalGroup(
            jpnToanDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnToanDanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnToanDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiemToanDan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoiToanDan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTimKiemToanDan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInToanDan, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jscrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("QL Tờ khai toàn dân", jpnToanDan);

        tblQLNoiDia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã người dùng", "Phương tiện", "Mã hiệu phương tiện", "Nơi đi", "Nơi đến", "Ngày khởi hành", "Di chuyển", "Triệu chứng", "Nghi nhiễm", "Nước bẹnh", "Biểu hiện", "Ngày khai bao"
            }
        ));
        jScrollPane1.setViewportView(tblQLNoiDia);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Tìm kiếm:");

        btnTimKiemNoiDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTimKiemNoiDia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        btnTimKiemNoiDia.setText("Tìm Kiếm");
        btnTimKiemNoiDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemNoiDiaActionPerformed(evt);
            }
        });

        btnLamMoiNoiDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLamMoiNoiDia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        btnLamMoiNoiDia.setText("Làm mới");
        btnLamMoiNoiDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNoiDiaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("QUẢN LÝ TỜ KHAI Y TẾ DI CHUYỂN NỘI ĐỊA");

        btnInNoiDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInNoiDia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        btnInNoiDia.setText("In");
        btnInNoiDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInNoiDiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnNoiDiaLayout = new javax.swing.GroupLayout(jpnNoiDia);
        jpnNoiDia.setLayout(jpnNoiDiaLayout);
        jpnNoiDiaLayout.setHorizontalGroup(
            jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                        .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtTimKiemNoiDia, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(87, 87, 87)
                        .addComponent(btnTimKiemNoiDia)
                        .addGap(76, 76, 76)
                        .addComponent(btnLamMoiNoiDia)
                        .addGap(55, 55, 55)
                        .addComponent(btnInNoiDia, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1088, Short.MAX_VALUE)
        );
        jpnNoiDiaLayout.setVerticalGroup(
            jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiemNoiDia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTimKiemNoiDia, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLamMoiNoiDia, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                            .addGap(1, 1, 1)
                            .addComponent(btnInNoiDia, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("QL Tờ khai di chuyển nội địa", jpnNoiDia);

        tblQLNhapCanh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã nguoif dùng", "Cửa khẩu", "Phương tiện đi lại", "Ngày khởi hành", "Ngày nhập cảnh", "Địa chỉ khởi hành", "Địa chỉ đến", "Nơi ở sau cách ly", "Sốt", "Ho", "Khó thở", "Đau họng", "Ngày khai báo"
            }
        ));
        jScrollPane2.setViewportView(tblQLNhapCanh);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("QUẢN LÝ TỜ KHAI Y TẾ NHẬP CẢNH");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Tìm kiếm:");

        btnTimKiemNhapCanh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTimKiemNhapCanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Search.png"))); // NOI18N
        btnTimKiemNhapCanh.setText("Tìm kiếm");
        btnTimKiemNhapCanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemNhapCanhActionPerformed(evt);
            }
        });

        btnLamMoiNhapCanh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnLamMoiNhapCanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Refresh.png"))); // NOI18N
        btnLamMoiNhapCanh.setText("Làm mới");
        btnLamMoiNhapCanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiNhapCanhActionPerformed(evt);
            }
        });

        btnInNhapCanh.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnInNhapCanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Print.png"))); // NOI18N
        btnInNhapCanh.setText("In");
        btnInNhapCanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInNhapCanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpnNhapCanhLayout = new javax.swing.GroupLayout(jpnNhapCanh);
        jpnNhapCanh.setLayout(jpnNhapCanhLayout);
        jpnNhapCanhLayout.setHorizontalGroup(
            jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnNhapCanhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
                    .addGroup(jpnNhapCanhLayout.createSequentialGroup()
                        .addGroup(jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jpnNhapCanhLayout.createSequentialGroup()
                                .addComponent(txtTimKiemNhapCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(btnTimKiemNhapCanh)
                                .addGap(42, 42, 42)
                                .addComponent(btnLamMoiNhapCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(btnInNhapCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jpnNhapCanhLayout.setVerticalGroup(
            jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNhapCanhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTimKiemNhapCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnLamMoiNhapCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jpnNhapCanhLayout.createSequentialGroup()
                            .addComponent(btnInNhapCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14)))
                    .addComponent(txtTimKiemNhapCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("QL Tờ khai nhập cảnh", jpnNhapCanh);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLamMoiToanDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiToanDanActionPerformed
        // TODO add your handling code here:
        loadFormToanDan();
    }//GEN-LAST:event_btnLamMoiToanDanActionPerformed

    private void btnTimKiemToanDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemToanDanActionPerformed
        // TODO add your handling code here:
        try {
            bll.ViewToanDan.HienthiTKByID(tblQLToanDan, txtTimKiemToanDan.getText());
        } catch (Exception e) {
            Logger.getLogger(frmQLToKhai.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnTimKiemToanDanActionPerformed

    private void btnLamMoiNoiDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiNoiDiaActionPerformed
        // TODO add your handling code here:
        loadFormNoiDia();
    }//GEN-LAST:event_btnLamMoiNoiDiaActionPerformed

    private void btnTimKiemNoiDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemNoiDiaActionPerformed
        // TODO add your handling code here:
        try {
            bll.ViewNoiDia.HienThiTKByID(tblQLNoiDia, txtTimKiemNoiDia.getText());
        } catch (Exception e) {
            Logger.getLogger(frmQLToKhai.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnTimKiemNoiDiaActionPerformed

    private void btnTimKiemNhapCanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemNhapCanhActionPerformed
        // TODO add your handling code here:
        try {
            bll.ViewNhapCanh.HienThiTKByID(tblQLNhapCanh,txtTimKiemNhapCanh.getText());
        } catch (Exception e) {
            Logger.getLogger(frmQLToKhai.class.getName()).log(Level.SEVERE, null, e);
        }
    }//GEN-LAST:event_btnTimKiemNhapCanhActionPerformed

    private void btnLamMoiNhapCanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiNhapCanhActionPerformed
        // TODO add your handling code here:
        loadFormNhapCanh();
    }//GEN-LAST:event_btnLamMoiNhapCanhActionPerformed

    private void btnInToanDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInToanDanActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();
            
            if(saveFile!= null){
                saveFile = new File(saveFile.toString()+".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("customer");
                
                Row rowCol = sheet.createRow(0);
                for(int i=0;i<tblQLToanDan.getColumnCount();i++){
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblQLToanDan.getColumnName(i));
                }
                for (int j=0;j<tblQLToanDan.getRowCount();j++){
                    Row row = sheet.createRow(j);
                    for (int k=0;k<tblQLToanDan.getColumnCount();k++){
                        Cell cell = row.createCell(k);
                        if(tblQLToanDan.getValueAt(j, k)!=null){
                            cell.setCellValue(tblQLToanDan.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
//                openfile(saveFile.toString());
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }catch (IOException io){
            System.out.println(io);
        }

    }//GEN-LAST:event_btnInToanDanActionPerformed

    private void btnInNoiDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInNoiDiaActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();
            
            if(saveFile!= null){
                saveFile = new File(saveFile.toString()+".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("customer");
                
                Row rowCol = sheet.createRow(0);
                for(int i=0;i<tblQLNoiDia.getColumnCount();i++){
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblQLNoiDia.getColumnName(i));
                }
                for (int j=0;j<tblQLNoiDia.getRowCount();j++){
                    Row row = sheet.createRow(j);
                    for (int k=0;k<tblQLNoiDia.getColumnCount();k++){
                        Cell cell = row.createCell(k);
                        if(tblQLNoiDia.getValueAt(j, k)!=null){
                            cell.setCellValue(tblQLNoiDia.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
//                openfile(saveFile.toString());
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }catch (IOException io){
            System.out.println(io);
        }
    }//GEN-LAST:event_btnInNoiDiaActionPerformed

    private void btnInNhapCanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInNhapCanhActionPerformed
        // TODO add your handling code here:
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();
            
            if(saveFile!= null){
                saveFile = new File(saveFile.toString()+".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("customer");
                
                Row rowCol = sheet.createRow(0);
                for(int i=0;i<tblQLNhapCanh.getColumnCount();i++){
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblQLNhapCanh.getColumnName(i));
                }
                for (int j=0;j<tblQLNhapCanh.getRowCount();j++){
                    Row row = sheet.createRow(j);
                    for (int k=0;k<tblQLNhapCanh.getColumnCount();k++){
                        Cell cell = row.createCell(k);
                        if(tblQLNhapCanh.getValueAt(j, k)!=null){
                            cell.setCellValue(tblQLNhapCanh.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
//                openfile(saveFile.toString());
            }else{
                JOptionPane.showMessageDialog(null, "Error");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }catch (IOException io){
            System.out.println(io);
        }
    }//GEN-LAST:event_btnInNhapCanhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInNhapCanh;
    private javax.swing.JButton btnInNoiDia;
    private javax.swing.JButton btnInToanDan;
    private javax.swing.JButton btnLamMoiNhapCanh;
    private javax.swing.JButton btnLamMoiNoiDia;
    private javax.swing.JButton btnLamMoiToanDan;
    private javax.swing.JButton btnTimKiemNhapCanh;
    private javax.swing.JButton btnTimKiemNoiDia;
    private javax.swing.JButton btnTimKiemToanDan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpnNhapCanh;
    private javax.swing.JPanel jpnNoiDia;
    private javax.swing.JPanel jpnToanDan;
    private javax.swing.JScrollPane jscrollPane;
    private javax.swing.JTable tblQLNhapCanh;
    private javax.swing.JTable tblQLNoiDia;
    private javax.swing.JTable tblQLToanDan;
    private javax.swing.JTextField txtTimKiemNhapCanh;
    private javax.swing.JTextField txtTimKiemNoiDia;
    public javax.swing.JTextField txtTimKiemToanDan;
    // End of variables declaration//GEN-END:variables
}
