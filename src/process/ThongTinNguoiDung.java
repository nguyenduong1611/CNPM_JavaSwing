/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

/**
 *
 * @author ASUS
 */
public class ThongTinNguoiDung {
    private String id;
    private String nguoidung_id;
    private String hoten;
    private String namsinh;
    private String diachi;
    private String cccd;
    private String gioitinh;
    private String email;
    private String quoctich;
    
    public ThongTinNguoiDung(){
        
    }
    
    public ThongTinNguoiDung(String id, String nguoidung_id, String hoten, String namsinh, String diachi, String cccd, String gioitinh, String email, String quoctich){
        this.id = id;
        this.nguoidung_id = nguoidung_id;
        this.hoten = hoten;
        this.namsinh = namsinh;
        this.diachi = diachi;
        this.cccd = cccd;
        this.gioitinh = gioitinh;
        this.email = email;
        this.quoctich = quoctich;
    }

    public ThongTinNguoiDung(String nguoidung_id, String hoten, String namsinh, String diachi, String cccd, String gioitinh, String email, String quoctich) {
       
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNguoidung_id() {
        return nguoidung_id;
    }

    public void setNguoidung_id(String nguoidung_id) {
        this.nguoidung_id = nguoidung_id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(String namsinh) {
        this.namsinh = namsinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQuoctich() {
        return quoctich;
    }

    public void setQuoctich(String quoctich) {
        this.quoctich = quoctich;
    }

    
    
}
