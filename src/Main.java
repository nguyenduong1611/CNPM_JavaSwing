
import display.frmLogin;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Main {
    public static void main(String[] args) throws SQLException{
        String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=KhaiBaoYTe;user=sa;password=Nguyenthaiduong1";
        Connection conn = DriverManager.getConnection(dbURL);
        if(conn != null)
            System.out.println("Kết nối thành công");
//        frmLogin login = new frmLogin();
    }
}
