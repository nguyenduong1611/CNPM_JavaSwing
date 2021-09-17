/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import process.ToanDan;
import display.frmQLToKhai;
import extensions.Ket_Noi;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author ASUS
 */
public class DAOThongTinToanDan {
    public static List<ToanDan> getallTT(String query) throws SQLException, ClassNotFoundException {
        List<ToanDan> lstTTToanDan = new ArrayList<>();
        ResultSet rs = extensions.Ket_Noi.ket_noi("KhaiBaoYTe").createStatement().executeQuery(query);
        //xu ly ket qua tra ve
        while (rs.next()) {
            ToanDan td = new ToanDan();

                td.setId(rs.getString(1));
                td.setNguoidung_id(rs.getString(2));
                td.setDichuyen(rs.getString(3));
                td.setTrieuchung(rs.getString(4));
                td.setNghinhiem(rs.getString(5));
                td.setNuocbenh(rs.getString(6));
                td.setBieuhien(rs.getString(7));
                td.setNgaykhaibao(rs.getString(8));
            lstTTToanDan.add(td);
        }
        return lstTTToanDan;
    }
    public static List<ToanDan> ListHD()throws SQLException, ClassNotFoundException{
        String sql = "select * from KhaiBaoToanDan";
        return getallTT(sql);
    }
    public static List<ToanDan> ListByID(String nguoidung_id) throws SQLException, ClassNotFoundException{
        String sql = "select * from KhaiBaoToanDan where nguoidung_id = '"+nguoidung_id+"' ";
        return getallTT(sql);
    }
    
    
}
