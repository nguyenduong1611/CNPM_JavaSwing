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
import process.ThongTinNguoiDung;

/**
 *
 * @author ASUS
 */
public class ViewNguoiDung {
    public static void loadTable(JTable tbl, List list){
        List<ThongTinNguoiDung> lstTTNguoiDung = list;
        Object[] colName = new Object[]{"ID", "Mã người dùng", "Họ tên", "Năm sinh", "Địa chỉ", "CCCD", "Giới tính", "Email", "Quốc tịch"};
        Object[][] data = new Object[lstTTNguoiDung.size()][9];
        int i = 0;
        for(ThongTinNguoiDung ttnd : lstTTNguoiDung){
            data[i][0] = ttnd.getId();
            data[i][1] = ttnd.getNguoidung_id();
            data[i][2] = ttnd.getHoten();
            data[i][3] = ttnd.getNamsinh();
            data[i][4] = ttnd.getDiachi();
            data[i][5] = ttnd.getCccd();
            data[i][6] = ttnd.getGioitinh();
            data[i][7] = ttnd.getEmail();
            data[i][8] = ttnd.getQuoctich();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, colName);
        tbl.setModel(tableModel);
    }
    public static void HienThiTTNguoiDung(JTable tbl)throws SQLException, ClassNotFoundException{
        List<ThongTinNguoiDung> lstTTNguoiDung = dao.DAOThongTinNguoiDung.ListHD();
        loadTable(tbl, lstTTNguoiDung);
    }
}
