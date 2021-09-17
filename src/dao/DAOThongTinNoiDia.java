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
import process.NoiDia;

/**
 *
 * @author ASUS
 */
public class DAOThongTinNoiDia {
    public  static List<NoiDia> getallTT(String query) throws SQLException, ClassNotFoundException{
        List<NoiDia> lstTTNoiDia = new ArrayList<>();
        ResultSet rs = extensions.Ket_Noi.ket_noi("KhaiBaoYTe").createStatement().executeQuery(query);
        
        while(rs.next()){
            NoiDia nd = new NoiDia();
                nd.setId(rs.getString(1));
                nd.setNguoidung_id(rs.getString(2));
                nd.setPhuongtien(rs.getString(3));
                nd.setMahieuphuongtien(rs.getString(4));
                nd.setNoidi(rs.getString(5));
                nd.setNoiden(rs.getString(6));
                nd.setNgaykhoihanh(rs.getString(7));
                nd.setDichuyen(rs.getString(8));
                nd.setTrieuchung(rs.getString(9));
                nd.setNghinhiem(rs.getString(10));
                nd.setNuocbenh(rs.getString(11));
                nd.setBieuhien(rs.getString(12));
                nd.setNgaykhaibao(rs.getString(13));
            lstTTNoiDia.add(nd);
        }
        return lstTTNoiDia;
    }
    public static List<NoiDia> ListHD() throws SQLException, ClassNotFoundException{
        String sql = "select * from KhaiBaoNoiDia";
        return getallTT(sql);
    }
    public static List<NoiDia> ListByID(String nguoidung_id) throws SQLException, ClassNotFoundException{
        String sql = "select * from KhaiBaoNoiDia where nguoidung_id = '"+nguoidung_id+"'";
        return getallTT(sql);
    }
}
