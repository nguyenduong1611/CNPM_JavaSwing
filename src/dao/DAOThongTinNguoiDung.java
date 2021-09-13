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
import process.ThongTinNguoiDung;

/**
 *
 * @author ASUS
 */
public class DAOThongTinNguoiDung {
    public static List<ThongTinNguoiDung> getallTT(String query) throws SQLException, ClassNotFoundException{


        List<ThongTinNguoiDung> lstTTNGuoiDung = new ArrayList<>();
        ResultSet rs = extensions.Ket_Noi.ket_noi("KhaiBaoYTe").createStatement().executeQuery(query);
        // xu ly ket qua tra ve;
        while(rs.next()){
            ThongTinNguoiDung ttnd=new ThongTinNguoiDung();

                ttnd.setId(rs.getString(1));
                ttnd.setNguoidung_id(rs.getString(2));
                ttnd.setHoten(rs.getString(3));
                ttnd.setNamsinh(rs.getString(4));
                ttnd.setDiachi(rs.getString(5));
                ttnd.setCccd(rs.getString(6));
                ttnd.setGioitinh(rs.getString(7));
                ttnd.setEmail(rs.getString(8));
                ttnd.setQuoctich(rs.getString(9));
               

            lstTTNGuoiDung.add(ttnd);
        }
        return lstTTNGuoiDung;
    }
    public static List<ThongTinNguoiDung> ListHD() throws SQLException, ClassNotFoundException {
        String sql = "select * from ThongTinNguoiDung";
        return getallTT(sql);
    }
}
