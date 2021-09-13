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
import process.NguoiDung;

/**
 *
 * @author ASUS
 */
public class DAONguoiDung {
    public static List<NguoiDung> getallTT(String query) throws SQLException, ClassNotFoundException{
        List<NguoiDung> lstNguoiDung = new ArrayList<>();
        ResultSet rs = extensions.Ket_Noi.ket_noi("KhaiBaoYTe").createStatement().executeQuery(query);
        // xu ly ket qua tra ve
        while(rs.next()){
            NguoiDung nd = new NguoiDung();
            
                nd.getId(rs.getInt(1));
                nd.getSdt(rs.getString(2));
                nd.getPassword(rs.getString(3));
                nd.getRole(rs.getString(4));
            
            lstNguoiDung.add(nd);
        }
        return lstNguoiDung;
    }
    public static List<NguoiDung> ListND() throws SQLException, ClassNotFoundException{
        String sql = "select * from NguoiDung";
        return getallTT(sql);
    }
}
