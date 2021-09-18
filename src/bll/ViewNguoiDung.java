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
import process.NguoiDung;

/**
 *
 * @author ASUS
 */
public class ViewNguoiDung {

    public static void loadTable(JTable tbl, List list) {
        List<NguoiDung> lstNguoiDung = list;
        Object[] colName = new Object[]{"Mã người dung", "Số điện thoại", "Mật khẩu", "Vai trò"};
        Object[][] data = new Object[lstNguoiDung.size()][4];
        int i = 0; 
        for(NguoiDung nd : lstNguoiDung){
            data[i][0] = nd.getId();
            data[i][1] = nd.getSdt();
            data[i][2] = nd.getPassword();
            data[i][3] = nd.getRole();
            i++;
        }
        TableModel tableModel = new DefaultTableModel(data, colName);
        tbl.setModel(tableModel);
    }
    public static void HienThiNguoiDung(JTable tbl)throws SQLException, ClassNotFoundException{
        List<NguoiDung> lstNguoiDung = dao.DAONguoiDung.listND();
        loadTable(tbl, lstNguoiDung);
    }
}
