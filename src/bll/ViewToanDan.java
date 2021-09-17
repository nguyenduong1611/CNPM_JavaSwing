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
import process.ToanDan;

/**
 *
 * @author ASUS
 */
public class ViewToanDan {
    public static void loadTable(JTable tbl, List list){
        List<ToanDan> lstToanDan = list;
        Object[] colName = new Object[]{"ID","Mã người dùng","Di chuyển","Triệu chứng","Nghi nhiễm","Nước bệnh","Biểu hiện","Ngày khai báo"};
        Object[][] data = new Object[lstToanDan.size()][8];
        int i = 0;
        for(ToanDan td : lstToanDan){
            data[i][0] = td.getId();
            data[i][1] = td.getNguoidung_id();
            data[i][2] = td.getDichuyen();
            data[i][3] = td.getTrieuchung();
            data[i][4] = td.getNghinhiem();
            data[i][5] = td.getNuocbenh();
            data[i][6] = td.getBieuhien();
            data[i][7] = td.getNgaykhaibao();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, colName);
        tbl.setModel(tableModel);
    }
    public static void HienThiTKToanDan(JTable tbl) throws SQLException, ClassNotFoundException{
        List<ToanDan> lstToanDan = dao.DAOThongTinToanDan.ListHD();
        loadTable(tbl, lstToanDan);
    }
    public static void HienthiTKByID(JTable tbl, String nguoidung_id) throws SQLException, ClassNotFoundException{
        List<ToanDan> lstToanDanByID = dao.DAOThongTinToanDan.ListByID(nguoidung_id);
        loadTable(tbl, lstToanDanByID);
    }
}
