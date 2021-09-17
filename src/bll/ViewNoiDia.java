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
import process.NoiDia;

/**
 *
 * @author ASUS
 */
public class ViewNoiDia {
    public static void loadTable(JTable tbl, List list){
        List<NoiDia> lstNoiDia = list;
        Object[] colName = new Object[]{"ID", "Mã người dùng", "Phương tiện", "Mã hiệu phương tiện", "Nơi đi", "Nơi đến", "Ngày khởi hành", "Di chuyển", "Triệu chứng", "Nghi nhiễm", "Nước bệnh", "Biểu hiện", "Ngày khai báo"};
        Object[][] data = new Object[lstNoiDia.size()][13];
        int i = 0;
        for(NoiDia nd : lstNoiDia){
            data[i][0] = nd.getId();
            data[i][1] = nd.getNguoidung_id();
            data[i][2] = nd.getPhuongtien();
            data[i][3] = nd.getMahieuphuongtien();
            data[i][4] = nd.getNoidi();
            data[i][5] = nd.getNoiden();
            data[i][6] = nd.getNgaykhoihanh();
            data[i][7] = nd.getDichuyen();
            data[i][8] = nd.getTrieuchung();
            data[i][9] = nd.getNghinhiem();
            data[i][10] = nd.getNuocbenh();
            data[i][11] = nd.getBieuhien();
            data[i][12] = nd.getNgaykhaibao();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, colName);
        tbl.setModel(tableModel);
    }
    public static void HienThiTKNoiDia(JTable tbl) throws SQLException, ClassNotFoundException{
        List<NoiDia> lstNoiDia = dao.DAOThongTinNoiDia.ListHD();
        loadTable(tbl, lstNoiDia);
    }
    public static void HienThiTKByID(JTable tbl, String nguoidung_id)throws SQLException, ClassNotFoundException{
        List<NoiDia> lstNoiDiaByID = dao.DAOThongTinNoiDia.ListByID(nguoidung_id);
        loadTable(tbl, lstNoiDiaByID);
    }
}
