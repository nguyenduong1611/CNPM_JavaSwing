/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import java.sql.SQLException;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import process.NhapCanh;

/**
 *
 * @author ASUS
 */
public class ViewNhapCanh {

    public static void loadTable(JTable tbl, List list) {
        List<NhapCanh> lstNhapCanh = list;
        Object[] colName = new Object[]{"ID", "Mã người dùng", "Cửa khẩu", "Phương tiện đi lại", "Ngày khởi hành", "Ngày nhập cảnh", "Địa chỉ khởi hành", "Địa chỉ đến", "Nơi ở sau cách ly", "Sốt", "Ho", "Khó thở", "Đau họng", "Ngày khai báo"};
        Object[][] data = new Object[lstNhapCanh.size()][14];
        int i = 0;
        for(NhapCanh nc : lstNhapCanh){
            data[i][0] = nc.getId();
            data[i][1] = nc.getNguoidung_id();
            data[i][2] = nc.getCuakhau();
            data[i][3] = nc.getThongtindilai();
            data[i][4] = nc.getNgaykhoihanh();
            data[i][5] = nc.getNgaynhapcanh();
            data[i][6] = nc.getDiachikhoihanh();
            data[i][7] = nc.getDiachiden();
            data[i][8] = nc.getNoiosaucachly();
            data[i][9] = nc.getSot();
            data[i][10] = nc.getHo();
            data[i][11] = nc.getKhotho();
            data[i][12] = nc.getDauhong();
            data[i][13] = nc.getNgaykhaibao();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, colName);
        tbl.setModel(tableModel);
    }
    public static void HienThiTKNhapCanh(JTable tbl) throws SQLException, ClassNotFoundException{
        List<NhapCanh> lstNhapCanh = dao.DAOThongTinNhapCanh.ListHD();
        loadTable(tbl, lstNhapCanh);
    }
    public static void HienThiTKByID(JTable tbl, String nguoidung_id) throws SQLException, ClassNotFoundException{
        List<NhapCanh> lstNhapCanhByID = dao.DAOThongTinNhapCanh.ListByID(nguoidung_id);
        loadTable(tbl, lstNhapCanhByID);
    }
}
