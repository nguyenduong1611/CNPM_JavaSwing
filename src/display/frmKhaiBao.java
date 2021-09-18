/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package display;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import extensions.Ket_Noi;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import process.NhapCanh;
import process.NoiDia;
import process.ToanDan;

/**
 *
 * @author ASUS
 */
public class frmKhaiBao extends javax.swing.JInternalFrame {

    Connection conn;
    List<ToanDan> list = new ArrayList<>();
    List<NoiDia> listNoiDia = new ArrayList<>();
    List<NhapCanh> listNhapCanh = new ArrayList<>();
    String QRToanDan;
    String QRNoiDia;
    String QRNhapCanh;

    /**
     * Creates new form frmKhaiBao
     */
    public frmKhaiBao() {
        initComponents();
        conn = Ket_Noi.ket_noi("KhaiBaoYTe");
        if (conn != null) {
            loadDataToListToanDan();
            loadDataToListNoiDia();
            loadDataToListNhapCanh();
            System.out.println("Kết nối thành công");
        } else {
            System.out.println("Lỗi kết nối");
        }
    }

    private void loadDataToListToanDan() {
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
                list.add(new ToanDan(id, nguoidung_id, dichuyen, trieuchung, nghinhiem, nuocbenh, bieuhien, ngaykhaibao));
            }
            st.close();
            rs.close();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi load data khai bao toan dan to list ");
        }
    }

    private void loadDataToListNoiDia() {
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
            JOptionPane.showMessageDialog(this, "Lỗi load data khai bao noi dia to list ");
        }
    }

    private void loadDataToListNhapCanh() {
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
            JOptionPane.showMessageDialog(this, "Lỗi load data khai bao nhap canh to list ");
        }
    }

    public void themToanDan() {
        try {
            String nguoidung_id = frmLogin.ID;
            String dichuyen;
            if (rdoCoDiChuyenToanDan.isSelected()) {
                dichuyen = "có";
            } else {
                dichuyen = "không";
            }
            String trieuchung;
            if (rdoCoTrieuChungToanDan.isSelected()) {
                trieuchung = "có";
            } else {
                trieuchung = "không";
            }
            String nghinhiem;
            if (rdoCoNghiNhiemToanDan.isSelected()) {
                nghinhiem = "có";
            } else {
                nghinhiem = "không";
            }
            String nuocbenh;
            if (rdoCoNuocBenhToanDan.isSelected()) {
                nuocbenh = "có";
            } else {
                nuocbenh = "không";
            }
            String bieuhien;
            if (rdoCoBieuHienToanDan.isSelected()) {
                bieuhien = "có";
            } else {
                bieuhien = "không";
            }
            QRToanDan ="Mã người dùng: "+nguoidung_id+" /Di chuyển:"+dichuyen+"\n"
                    + " /Triệu chứng:"+trieuchung+" /Nghi nhiễm: "+nghinhiem+"\n"
                    + " /Nước bệnh: "+nuocbenh+" /Biểu hiện: "+bieuhien+"";
            list.add(new ToanDan(nguoidung_id, dichuyen, trieuchung, nghinhiem, nuocbenh, bieuhien));
            //them vao database
            String sql = "insert into KhaiBaoToanDan(nguoidung_id,dichuyen,trieuchung,nghinhiem,nuocbenh,bieuhien)\n"
                    + " values('" + nguoidung_id + "',N'" + dichuyen + "',N'" + trieuchung + "','" + nghinhiem + "','" + nuocbenh + "','" + bieuhien + "')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Thêm tờ khai thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm tờ khai không thành công");
        }
    }

    public void themNoiDia() {
        try {
            String nguoidung_id = frmLogin.ID;
            String phuongtien = txtPhuongTienDiLai.getText();
            String mahieuphuongtien = txtMaHieuPhuongTien.getText();
            String noidi = txtNoiDi.getText();
            String noiden = txtNoiDen.getText();
            String ngaykhoihanh = txtNgayKhoiHanh.getText();
            String dichuyen;
            if (rdoCoDiChuyenNoiDia.isSelected()) {
                dichuyen = "có";
            } else {
                dichuyen = "không";
            }
            String trieuchung;
            if (rdoCoTrieuChungNoiDia.isSelected()) {
                trieuchung = "có";
            } else {
                trieuchung = "không";
            }
            String nghinhiem;
            if (rdoCoNghiNhiemNoiDia.isSelected()) {
                nghinhiem = "có";
            } else {
                nghinhiem = "không";
            }
            String nuocbenh;
            if (rdoCoNuocBenhNoiDia.isSelected()) {
                nuocbenh = "có";
            } else {
                nuocbenh = "không";
            }
            String bieuhien;
            if (rdoCoBieuHienNoiDia.isSelected()) {
                bieuhien = "có";
            } else {
                bieuhien = "không";
            }
            QRNoiDia ="Mã người dùng: "+nguoidung_id+" /Phương tiện:"+phuongtien+"\n"
                    + " /Mã hiệu phương tiện:"+mahieuphuongtien+" /Nơi đi:"+noidi+"\n"
                    + " /Nơi đến:"+noiden+" /Ngày khởi hành"+ngaykhoihanh+"/Di chuyển:"+dichuyen+"\n"
                    + " /Triệu chứng:"+trieuchung+" /Nghi nhiễm: "+nghinhiem+"\n"
                    + " /Nước bệnh: "+nuocbenh+" /Biểu hiện: "+bieuhien+"";
            listNoiDia.add(new NoiDia(nguoidung_id, phuongtien, mahieuphuongtien, noidi, noiden, ngaykhoihanh, dichuyen, trieuchung, nghinhiem, nuocbenh, bieuhien));
//            System.out.println("them vao list thanh cong");
            // them vao database
            String sql = "insert into KhaiBaoNoiDia(nguoidung_id,phuongtien\n"
                    + ",mahieuphuongtien,noidi,noiden,ngaykhoihanh,dichuyen\n"
                    + ",trieuchung,nghinhiem,nuocbenh,bieuhien)\n"
                    + " values('" + nguoidung_id + "',N'" + phuongtien + "'\n"
                    + ",'" + mahieuphuongtien + "',N'" + noidi + "',N'" + noiden + "'\n"
                    + ",'" + ngaykhoihanh + "',N'" + dichuyen + "',N'" + trieuchung + "'\n"
                    + ",N'" + nghinhiem + "',N'" + nuocbenh + "',N'" + bieuhien + "')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Thêm tờ khai thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm tờ khai không thành công");
        }
    }

    public void themNhapCanh() {
        String nguoidung_id = frmLogin.ID;
        String cuakhau = txtCuaKhau.getText();
        String thongtindilai = txtPhuongTienDiLai.getText();
        String ngaykhoihanh = txtNgayKHNhapCanh.getText();
        String ngaynhapcanh = txtNgayNhapCanhNC.getText();
        String diachikhoihanh = txtDiaChiKhoiHanh.getText();
        String diachiden = txtDiaChiDen.getText();
        String noiosaucachly = txtNoiOSauCachLy.getText();
        String sot;
        if (rdoCoSot.isSelected()) {
            sot = "có";
        } else {
            sot = "không";
        }
        String ho;
        if (rdoCoHo.isSelected()) {
            ho = "có";
        } else {
            ho = "không";
        }
        String khotho;
        if (rdoCoKhoTho.isSelected()) {
            khotho = "có";
        } else {
            khotho = "không";
        }
        String dauhong;
        if (rdoCoDauHong.isSelected()) {
            dauhong = "có";
        } else {
            dauhong = "không";
        }
        QRNhapCanh ="Mã người dùng: "+nguoidung_id+" /Cửa khẩu:"+cuakhau+"\n"
                    + " /Thông tin đi lại:"+thongtindilai+" /Ngày khởi hành:"+ngaykhoihanh+"\n"
                    + " /Ngày nhập cảnh:"+ngaynhapcanh+" /Địa chỉ khởi hành"+diachikhoihanh+"\n"
                    + "/Địa chỉ đến:"+diachiden+"\n"
                    + " /Nơi ở sau cách ly:"+noiosaucachly+" /Sốt: "+sot+"\n"
                    + " /Ho: "+ho+" /Khó thở: "+khotho+" /Đau họng:"+dauhong+"";
        listNhapCanh.add(new NhapCanh(nguoidung_id, cuakhau, thongtindilai, ngaykhoihanh, ngaynhapcanh, diachikhoihanh, diachiden, noiosaucachly, sot, ho, khotho, dauhong));
        //them vao data base
        try {
            String sql = "insert into KhaiBaoNhapCanh(nguoidung_id,cuakhau,thongtindilai\n"
                    + ",ngaykhoihanh,ngaynhapcanh,diachikhoihanh,diachiden,noiosaucachly\n"
                    + ",sot,ho,khotho,dauhong)\n"
                    + " values('" + nguoidung_id + "',N'" + cuakhau + "',N'" + thongtindilai + "'\n"
                    + ",'" + ngaykhoihanh + "','" + ngaynhapcanh + "',N'" + diachikhoihanh + "'\n"
                    + ",N'" + diachiden + "',N'" + noiosaucachly + "',N'" + sot + "',N'" + ho + "'\n"
                    + ",N'" + khotho + "',N'" + dauhong + "')";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Thêm tờ khai thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm tờ khai không thành công");
        }
    }
    public void XuatQRCode(String QR){
        try {
            String QrCodeData = QR; 
            String filepath = "C:\\Users\\ASUS\\Documents\\cnpm\\QR.png";
            String charset = "utf8";
            
            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            
            BitMatrix matrix = new MultiFormatWriter().encode(
            new String(QrCodeData.getBytes(),charset),
                        BarcodeFormat.QR_CODE,400,400,hintMap);
            MatrixToImageWriter.writeToFile(matrix, filepath.substring(filepath.lastIndexOf(".")+1), new File(filepath));
            System.out.println("qr da xong");
            
            JFrame frame = new JFrame();
            ImageIcon icon = new ImageIcon("C:\\Users\\ASUS\\Documents\\cnpm\\QR.png");
            JLabel label = new JLabel(icon);
            frame.add(label);
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            frame.setLocationRelativeTo(this);
        }catch(Exception e){
            System.out.println(e);
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

        btgDiChuyen = new javax.swing.ButtonGroup();
        btgTrieuChung = new javax.swing.ButtonGroup();
        btgNghiNhiem = new javax.swing.ButtonGroup();
        btgNuocBenh = new javax.swing.ButtonGroup();
        btgBieuHien = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jpnToanDan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rdoCoNghiNhiemToanDan = new javax.swing.JRadioButton();
        rdoKhongNghiNhiemToanDan = new javax.swing.JRadioButton();
        rdoCoNuocBenhToanDan = new javax.swing.JRadioButton();
        rdoKhongNuocBenhToanDan = new javax.swing.JRadioButton();
        rdoCoBieuHienToanDan = new javax.swing.JRadioButton();
        rdoKhongBieuHienToanDan = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rdoCoDiChuyenToanDan = new javax.swing.JRadioButton();
        rdoKhongDiChuyenToanDan = new javax.swing.JRadioButton();
        rdoCoTrieuChungToanDan = new javax.swing.JRadioButton();
        rdoKhongTrieuChungToanDan = new javax.swing.JRadioButton();
        btnGuiToanDan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jpnNoiDia = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNgayKhoiHanh = new javax.swing.JTextField();
        txtMaHieuPhuongTien = new javax.swing.JTextField();
        txtPhuongTienDiLai = new javax.swing.JTextField();
        txtNoiDen = new javax.swing.JTextField();
        txtNoiDi = new javax.swing.JTextField();
        rdoCoDiChuyenNoiDia = new javax.swing.JRadioButton();
        rdoKhongDiChuyenNoiDia = new javax.swing.JRadioButton();
        btnGuiNoiDia = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        rdoCoTrieuChungNoiDia = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        rdoCoNghiNhiemNoiDia = new javax.swing.JRadioButton();
        rdoKhongNghiNhiemNoiDia = new javax.swing.JRadioButton();
        rdoCoNuocBenhNoiDia = new javax.swing.JRadioButton();
        rdoKhongNuocBenhNoiDia = new javax.swing.JRadioButton();
        rdoCoBieuHienNoiDia = new javax.swing.JRadioButton();
        rdoKhongBieuHienNoiDia = new javax.swing.JRadioButton();
        jLabel20 = new javax.swing.JLabel();
        rdoKhongTrieuChungNoiDia = new javax.swing.JRadioButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jpnNhapCanh = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtCuaKhau = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtNgayKHNhapCanh = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtNoiOSauCachLy = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtDiaChiDen = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        txtDiaChiKhoiHanh = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        txtNgayNhapCanhNC = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        rdoCoSot = new javax.swing.JRadioButton();
        rdoKhongSot = new javax.swing.JRadioButton();
        jLabel32 = new javax.swing.JLabel();
        rdoCoHo = new javax.swing.JRadioButton();
        rdoKhongHo = new javax.swing.JRadioButton();
        jLabel33 = new javax.swing.JLabel();
        rdoCoKhoTho = new javax.swing.JRadioButton();
        rdoKhongKhoTho = new javax.swing.JRadioButton();
        jLabel34 = new javax.swing.JLabel();
        rdoCoDauHong = new javax.swing.JRadioButton();
        rdoKhongDauHong = new javax.swing.JRadioButton();
        btnGuiNhapCanh = new javax.swing.JButton();
        txtPhuongTienNhapCanh = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jpnToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Trong vòng 14 ngày qua, Anh/Chị có đến tỉnh/thành phố, quốc gia/vùng lãnh thổ nào (Có thể đi qua nhiều nơi)");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Trong vòng 14 ngày qua, Anh/Chị có thấy xuất hiện ít nhất 1 trong các dấu hiệu: sốt, ho, khó thở, viêm phổi, đau họng, mệt mỏi không?");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Người bệnh hoặc nghi ngờ, mắc bệnh COVID-19 (*)");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Người từ nước có bệnh COVID-19 (*)");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Người có biểu hiện (Sốt, ho, khó thở , Viêm phổi) (*)");

        rdoCoNghiNhiemToanDan.setBackground(new java.awt.Color(204, 204, 204));
        rdoCoNghiNhiemToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoNghiNhiemToanDan.setText("Có");

        rdoKhongNghiNhiemToanDan.setBackground(new java.awt.Color(204, 204, 204));
        rdoKhongNghiNhiemToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongNghiNhiemToanDan.setText("Không");
        rdoKhongNghiNhiemToanDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKhongNghiNhiemToanDanActionPerformed(evt);
            }
        });

        rdoCoNuocBenhToanDan.setBackground(new java.awt.Color(204, 204, 204));
        rdoCoNuocBenhToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoNuocBenhToanDan.setText("Có");

        rdoKhongNuocBenhToanDan.setBackground(new java.awt.Color(204, 204, 204));
        rdoKhongNuocBenhToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongNuocBenhToanDan.setText("Không");

        rdoCoBieuHienToanDan.setBackground(new java.awt.Color(204, 204, 204));
        rdoCoBieuHienToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoBieuHienToanDan.setText("Có");

        rdoKhongBieuHienToanDan.setBackground(new java.awt.Color(204, 204, 204));
        rdoKhongBieuHienToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongBieuHienToanDan.setText("Không");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoCoNghiNhiemToanDan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdoCoNuocBenhToanDan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdoCoBieuHienToanDan, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoKhongNghiNhiemToanDan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdoKhongNuocBenhToanDan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdoKhongBieuHienToanDan, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rdoCoNghiNhiemToanDan)
                    .addComponent(rdoKhongNghiNhiemToanDan))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(rdoCoNuocBenhToanDan)
                    .addComponent(rdoKhongNuocBenhToanDan))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(rdoCoBieuHienToanDan)
                    .addComponent(rdoKhongBieuHienToanDan))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Trong vòng 14 ngày qua, Anh/Chị có tiếp xúc với ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("THÔNG TIN KHAI BÁO Y TẾ");

        btgDiChuyen.add(rdoCoDiChuyenToanDan);
        rdoCoDiChuyenToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoDiChuyenToanDan.setText("Có ");

        btgDiChuyen.add(rdoKhongDiChuyenToanDan);
        rdoKhongDiChuyenToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongDiChuyenToanDan.setText("Không");

        rdoCoTrieuChungToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoTrieuChungToanDan.setText("Có");

        rdoKhongTrieuChungToanDan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongTrieuChungToanDan.setText("Không");

        btnGuiToanDan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnGuiToanDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Upload.png"))); // NOI18N
        btnGuiToanDan.setText("Gửi");
        btnGuiToanDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiToanDanActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("( PHÒNG CHỐNG DỊCH COVID-19 )");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Khuyến cáo: Khai báo thông tin sai là vi phạm pháp luật Việt Nam và có thể xử lý hình sự");

        javax.swing.GroupLayout jpnToanDanLayout = new javax.swing.GroupLayout(jpnToanDan);
        jpnToanDan.setLayout(jpnToanDanLayout);
        jpnToanDanLayout.setHorizontalGroup(
            jpnToanDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jpnToanDanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnToanDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnToanDanLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112)
                        .addComponent(btnGuiToanDan, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addGroup(jpnToanDanLayout.createSequentialGroup()
                        .addGroup(jpnToanDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoCoDiChuyenToanDan)
                            .addComponent(rdoCoTrieuChungToanDan))
                        .addGap(18, 18, 18)
                        .addGroup(jpnToanDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoKhongDiChuyenToanDan, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rdoKhongTrieuChungToanDan, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jpnToanDanLayout.setVerticalGroup(
            jpnToanDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnToanDanLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnToanDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoCoDiChuyenToanDan)
                    .addComponent(rdoKhongDiChuyenToanDan))
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnToanDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoCoTrieuChungToanDan)
                    .addComponent(rdoKhongTrieuChungToanDan))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGroup(jpnToanDanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnToanDanLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnGuiToanDan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jpnToanDanLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Khai báo toàn dân", jpnToanDan);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Phương tiện đi lại");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Mã hiệu phương tiện");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Nơi đi");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Nơi đến");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Ngày khởi hành");

        btgDiChuyen.add(rdoCoDiChuyenNoiDia);
        rdoCoDiChuyenNoiDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoDiChuyenNoiDia.setText("Có ");

        btgDiChuyen.add(rdoKhongDiChuyenNoiDia);
        rdoKhongDiChuyenNoiDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongDiChuyenNoiDia.setText("Không");

        btnGuiNoiDia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnGuiNoiDia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Upload.png"))); // NOI18N
        btnGuiNoiDia.setText("Gửi");
        btnGuiNoiDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiNoiDiaActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("Trong vòng 14 ngày qua, Anh/Chị có đến tỉnh/thành phố, quốc gia/vùng lãnh thổ nào (Có thể đi qua nhiều nơi)");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Trong vòng 14 ngày qua, Anh/Chị có thấy xuất hiện ít nhất 1 trong các dấu hiệu: sốt, ho, khó thở, viêm phổi, đau họng, mệt mỏi không?");

        rdoCoTrieuChungNoiDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoTrieuChungNoiDia.setText("Có");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setForeground(new java.awt.Color(204, 204, 204));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Người bệnh hoặc nghi ngờ, mắc bệnh COVID-19 (*)");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Người từ nước có bệnh COVID-19 (*)");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setText("Người có biểu hiện (Sốt, ho, khó thở , Viêm phổi) (*)");

        rdoCoNghiNhiemNoiDia.setBackground(new java.awt.Color(204, 204, 204));
        rdoCoNghiNhiemNoiDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoNghiNhiemNoiDia.setText("Có");

        rdoKhongNghiNhiemNoiDia.setBackground(new java.awt.Color(204, 204, 204));
        rdoKhongNghiNhiemNoiDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongNghiNhiemNoiDia.setText("Không");
        rdoKhongNghiNhiemNoiDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoKhongNghiNhiemNoiDiaActionPerformed(evt);
            }
        });

        rdoCoNuocBenhNoiDia.setBackground(new java.awt.Color(204, 204, 204));
        rdoCoNuocBenhNoiDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoNuocBenhNoiDia.setText("Có");

        rdoKhongNuocBenhNoiDia.setBackground(new java.awt.Color(204, 204, 204));
        rdoKhongNuocBenhNoiDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongNuocBenhNoiDia.setText("Không");

        rdoCoBieuHienNoiDia.setBackground(new java.awt.Color(204, 204, 204));
        rdoCoBieuHienNoiDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoBieuHienNoiDia.setText("Có");

        rdoKhongBieuHienNoiDia.setBackground(new java.awt.Color(204, 204, 204));
        rdoKhongBieuHienNoiDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongBieuHienNoiDia.setText("Không");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoCoNghiNhiemNoiDia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdoCoNuocBenhNoiDia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdoCoBieuHienNoiDia, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdoKhongNghiNhiemNoiDia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdoKhongNuocBenhNoiDia, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rdoKhongBieuHienNoiDia, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(rdoCoNghiNhiemNoiDia)
                    .addComponent(rdoKhongNghiNhiemNoiDia))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(rdoCoNuocBenhNoiDia)
                    .addComponent(rdoKhongNuocBenhNoiDia))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(rdoCoBieuHienNoiDia)
                    .addComponent(rdoKhongBieuHienNoiDia))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel20.setText("Trong vòng 14 ngày qua, Anh/Chị có tiếp xúc với ");

        rdoKhongTrieuChungNoiDia.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongTrieuChungNoiDia.setText("Không");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("THÔNG TIN KHAI BÁO DI CHUYỂN NỘI ĐỊA");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("( PHÒNG CHỐNG DỊCH COVID-19 )");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 0, 0));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Khuyến cáo: Khai báo thông tin sai là vi phạm pháp luật Việt Nam và có thể xử lý hình sự");

        javax.swing.GroupLayout jpnNoiDiaLayout = new javax.swing.GroupLayout(jpnNoiDia);
        jpnNoiDia.setLayout(jpnNoiDiaLayout);
        jpnNoiDiaLayout.setHorizontalGroup(
            jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                        .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                        .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                                .addComponent(txtPhuongTienDiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                                        .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNoiDi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel12))
                                        .addGap(55, 55, 55)
                                        .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel14)
                                            .addComponent(txtNgayKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel15)
                            .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                                .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoCoDiChuyenNoiDia)
                                    .addComponent(rdoCoTrieuChungNoiDia))
                                .addGap(18, 18, 18)
                                .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoKhongDiChuyenNoiDia, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(rdoKhongTrieuChungNoiDia, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(156, 156, 156)
                                .addComponent(btnGuiNoiDia, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16)
                            .addComponent(jLabel20)
                            .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                                .addComponent(txtMaHieuPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtNoiDen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(20, Short.MAX_VALUE))))
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnNoiDiaLayout.setVerticalGroup(
            jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNoiDiaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhuongTienDiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoiDi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addGap(8, 8, 8)
                .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHieuPhuongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNoiDen, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoCoDiChuyenNoiDia)
                    .addComponent(rdoKhongDiChuyenNoiDia))
                .addGap(27, 27, 27)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoCoTrieuChungNoiDia)
                    .addComponent(rdoKhongTrieuChungNoiDia))
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(jpnNoiDiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpnNoiDiaLayout.createSequentialGroup()
                        .addComponent(btnGuiNoiDia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)))
                .addGap(42, 42, 42))
        );

        jTabbedPane1.addTab("Khai báo di chuyển nội địa", jpnNoiDia);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Phương tiện đi lại:");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setText("Cửa khẩu:");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setText("Ngày khởi hành:");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Nơi ở sau cách ly:");

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Địa chỉ đến:");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Địa chỉ khởi hành:");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Ngày nhập cảnh:");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("Sốt");

        rdoCoSot.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoSot.setText("Có");

        rdoKhongSot.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongSot.setText("Không");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setText("Ho");

        rdoCoHo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoHo.setText("Có");

        rdoKhongHo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongHo.setText("Không");

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setText("Khó thở");

        rdoCoKhoTho.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoKhoTho.setText("Có");

        rdoKhongKhoTho.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongKhoTho.setText("Không");

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel34.setText("Đau họng");

        rdoCoDauHong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoCoDauHong.setText("Có");

        rdoKhongDauHong.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        rdoKhongDauHong.setText("Không");

        btnGuiNhapCanh.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnGuiNhapCanh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Upload.png"))); // NOI18N
        btnGuiNhapCanh.setText("Gửi");
        btnGuiNhapCanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiNhapCanhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoCoSot)
                            .addComponent(rdoCoHo)
                            .addComponent(rdoCoKhoTho)
                            .addComponent(rdoCoDauHong))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoKhongDauHong)
                            .addComponent(rdoKhongSot)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoKhongKhoTho)
                                    .addComponent(rdoKhongHo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                                .addComponent(btnGuiNhapCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(72, 72, 72))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoCoSot)
                    .addComponent(rdoKhongSot))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel32)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoCoHo)
                            .addComponent(rdoKhongHo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdoCoKhoTho)
                            .addComponent(rdoKhongKhoTho)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(btnGuiNhapCanh, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoCoDauHong)
                    .addComponent(rdoKhongDauHong))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("TỜ KHAI Y TẾ ĐỐI VỚI NGƯỜI NHẬP CẢNH");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("ĐÂY LÀ TÀI LIỆU QUAN TRỌNG, THÔNG TIN CỦA ANH/CHỊ SẼ GIÚP CƠ QUAN Y TẾ LIÊN LẠC KHI CẦN THIẾT ĐỂ PHÒNG CHỐNG DỊCH BỆNH TRUYỀN NHIỄM");

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 51, 51));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Khuyến cáo: Khai báo thông tin sai là vi phạm pháp luật Việt Nam và có thể xử lý hình sự");

        javax.swing.GroupLayout jpnNhapCanhLayout = new javax.swing.GroupLayout(jpnNhapCanh);
        jpnNhapCanh.setLayout(jpnNhapCanhLayout);
        jpnNhapCanhLayout.setHorizontalGroup(
            jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNhapCanhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addGroup(jpnNhapCanhLayout.createSequentialGroup()
                        .addGroup(jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPhuongTienNhapCanh, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtDiaChiKhoiHanh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCuaKhau, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNgayKHNhapCanh, javax.swing.GroupLayout.Alignment.LEADING))
                                .addComponent(jLabel29)))
                        .addGap(18, 18, 18)
                        .addGroup(jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27)
                            .addComponent(jLabel30)
                            .addComponent(txtNgayNhapCanhNC)
                            .addComponent(jLabel28)
                            .addComponent(txtDiaChiDen)
                            .addComponent(txtNoiOSauCachLy, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpnNhapCanhLayout.setVerticalGroup(
            jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpnNhapCanhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addGap(89, 89, 89)
                .addGroup(jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jpnNhapCanhLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhuongTienNhapCanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnNhapCanhLayout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addGap(26, 26, 26))
                            .addComponent(txtDiaChiDen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jpnNhapCanhLayout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDiaChiKhoiHanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jpnNhapCanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jpnNhapCanhLayout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgayKHNhapCanh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCuaKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jpnNhapCanhLayout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNgayNhapCanhNC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNoiOSauCachLy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Khai báo nhập cảnh", jpnNhapCanh);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rdoKhongNghiNhiemToanDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKhongNghiNhiemToanDanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoKhongNghiNhiemToanDanActionPerformed

    private void btnGuiToanDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiToanDanActionPerformed
        // TODO add your handling code here:
        themToanDan();
        XuatQRCode(QRToanDan);
    }//GEN-LAST:event_btnGuiToanDanActionPerformed

    private void btnGuiNoiDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiNoiDiaActionPerformed
        // TODO add your handling code here:
        themNoiDia();
        XuatQRCode(QRNoiDia);
    }//GEN-LAST:event_btnGuiNoiDiaActionPerformed

    private void rdoKhongNghiNhiemNoiDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoKhongNghiNhiemNoiDiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoKhongNghiNhiemNoiDiaActionPerformed

    private void btnGuiNhapCanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiNhapCanhActionPerformed
        // TODO add your handling code here:
        themNhapCanh();
        XuatQRCode(QRNhapCanh);
    }//GEN-LAST:event_btnGuiNhapCanhActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgBieuHien;
    private javax.swing.ButtonGroup btgDiChuyen;
    private javax.swing.ButtonGroup btgNghiNhiem;
    private javax.swing.ButtonGroup btgNuocBenh;
    private javax.swing.ButtonGroup btgTrieuChung;
    private javax.swing.JButton btnGuiNhapCanh;
    private javax.swing.JButton btnGuiNoiDia;
    private javax.swing.JButton btnGuiToanDan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jpnNhapCanh;
    private javax.swing.JPanel jpnNoiDia;
    private javax.swing.JPanel jpnToanDan;
    private javax.swing.JRadioButton rdoCoBieuHienNoiDia;
    private javax.swing.JRadioButton rdoCoBieuHienToanDan;
    private javax.swing.JRadioButton rdoCoDauHong;
    private javax.swing.JRadioButton rdoCoDiChuyenNoiDia;
    private javax.swing.JRadioButton rdoCoDiChuyenToanDan;
    private javax.swing.JRadioButton rdoCoHo;
    private javax.swing.JRadioButton rdoCoKhoTho;
    private javax.swing.JRadioButton rdoCoNghiNhiemNoiDia;
    private javax.swing.JRadioButton rdoCoNghiNhiemToanDan;
    private javax.swing.JRadioButton rdoCoNuocBenhNoiDia;
    private javax.swing.JRadioButton rdoCoNuocBenhToanDan;
    private javax.swing.JRadioButton rdoCoSot;
    private javax.swing.JRadioButton rdoCoTrieuChungNoiDia;
    private javax.swing.JRadioButton rdoCoTrieuChungToanDan;
    private javax.swing.JRadioButton rdoKhongBieuHienNoiDia;
    private javax.swing.JRadioButton rdoKhongBieuHienToanDan;
    private javax.swing.JRadioButton rdoKhongDauHong;
    private javax.swing.JRadioButton rdoKhongDiChuyenNoiDia;
    private javax.swing.JRadioButton rdoKhongDiChuyenToanDan;
    private javax.swing.JRadioButton rdoKhongHo;
    private javax.swing.JRadioButton rdoKhongKhoTho;
    private javax.swing.JRadioButton rdoKhongNghiNhiemNoiDia;
    private javax.swing.JRadioButton rdoKhongNghiNhiemToanDan;
    private javax.swing.JRadioButton rdoKhongNuocBenhNoiDia;
    private javax.swing.JRadioButton rdoKhongNuocBenhToanDan;
    private javax.swing.JRadioButton rdoKhongSot;
    private javax.swing.JRadioButton rdoKhongTrieuChungNoiDia;
    private javax.swing.JRadioButton rdoKhongTrieuChungToanDan;
    private javax.swing.JTextField txtCuaKhau;
    private javax.swing.JTextField txtDiaChiDen;
    private javax.swing.JTextField txtDiaChiKhoiHanh;
    private javax.swing.JTextField txtMaHieuPhuongTien;
    private javax.swing.JTextField txtNgayKHNhapCanh;
    private javax.swing.JTextField txtNgayKhoiHanh;
    private javax.swing.JTextField txtNgayNhapCanhNC;
    private javax.swing.JTextField txtNoiDen;
    private javax.swing.JTextField txtNoiDi;
    private javax.swing.JTextField txtNoiOSauCachLy;
    private javax.swing.JTextField txtPhuongTienDiLai;
    private javax.swing.JTextField txtPhuongTienNhapCanh;
    // End of variables declaration//GEN-END:variables
}
