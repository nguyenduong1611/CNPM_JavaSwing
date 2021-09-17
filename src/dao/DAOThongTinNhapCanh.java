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
import process.NhapCanh;

/**
 *
 * @author ASUS
 */
public class DAOThongTinNhapCanh {
    public static List<NhapCanh> getallTT(String query) throws SQLException, ClassNotFoundException{
        List<NhapCanh> lstTTNhapCanh = new ArrayList<>();
        ResultSet rs = extensions.Ket_Noi.ket_noi("KhaiBaoYTe").createStatement().executeQuery(query);
        
        while(rs.next()){
            NhapCanh nc = new NhapCanh();
                nc.setId(rs.getString(1));
                nc.setNguoidung_id(rs.getString(2));
                nc.setCuakhau(rs.getString(3));
                nc.setThongtindilai(rs.getString(4));
                nc.setNgaykhoihanh(rs.getString(5));
                nc.setNgaynhapcanh(rs.getString(6));
                nc.setDiachikhoihanh(rs.getString(7));
                nc.setDiachiden(rs.getString(8));
                nc.setNoiosaucachly(rs.getString(9));
                nc.setSot(rs.getString(10));
                nc.setHo(rs.getString(11));
                nc.setKhotho(rs.getString(12));
                nc.setDauhong(rs.getString(13));
                nc.setNgaykhaibao(rs.getString(14));
            lstTTNhapCanh.add(nc);
        }
        return lstTTNhapCanh;
    }
    public static List<NhapCanh> ListHD() throws SQLException, ClassNotFoundException{
        String sql = "select * from KhaiBaoNhapCanh";
        return getallTT(sql);
    }
    public static List<NhapCanh> ListByID(String nguoidung_id)throws SQLException, ClassNotFoundException{
        String sql = "select * from KhaiBaoNhapCanh where nguoidung_id='"+nguoidung_id+"'";
        return getallTT(sql);
    }
}
