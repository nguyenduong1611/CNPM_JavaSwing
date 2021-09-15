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
public class NoiDia {

    private String id;
    private String nguoidung_id;
    private String phuongtien;
    private String mahieuphuongtien;
    private String noidi;
    private String noiden;
    private String ngaykhoihanh;
    private String dichuyen;
    private String trieuchung;
    private String nghinhiem;
    private String nuocbenh;
    private String bieuhien;

    public NoiDia() {

    }

    public NoiDia(String id, String nguoidung_id, String phuongtien, String mahieuphuongtien, String noidi, String noiden, String ngaykhoihanh, String dichuyen, String trieuchung, String nghinhiem, String nuocbenh, String bieuhien) {
        this.id = id;
        this.nguoidung_id = nguoidung_id;
        this.phuongtien = phuongtien;
        this.mahieuphuongtien = mahieuphuongtien;
        this.noidi = noidi;
        this.noiden = noiden;
        this.ngaykhoihanh = ngaykhoihanh;
        this.dichuyen = dichuyen;
        this.trieuchung = trieuchung;
        this.nghinhiem = nghinhiem;
        this.nuocbenh = nuocbenh;
        this.bieuhien = bieuhien;
    }

    public NoiDia(String nguoidung_id, String phuongtien, String mahieuphuongtien, String noidi, String noiden, String ngaykhoihanh, String dichuyen, String trieuchung, String nghinhiem, String nuocbenh, String bieuhien) {
        
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

    public String getPhuongtien() {
        return phuongtien;
    }

    public void setPhuongtien(String phuongtien) {
        this.phuongtien = phuongtien;
    }

    public String getMahieuphuongtien() {
        return mahieuphuongtien;
    }

    public void setMahieuphuongtien(String mahieuphuongtien) {
        this.mahieuphuongtien = mahieuphuongtien;
    }

    public String getNoidi() {
        return noidi;
    }

    public void setNoidi(String noidi) {
        this.noidi = noidi;
    }

    public String getNoiden() {
        return noiden;
    }

    public void setNoiden(String noiden) {
        this.noiden = noiden;
    }

    public String getNgaykhoihanh() {
        return ngaykhoihanh;
    }

    public void setNgaykhoihanh(String ngaykhoihanh) {
        this.ngaykhoihanh = ngaykhoihanh;
    }

    public String getDichuyen() {
        return dichuyen;
    }

    public void setDichuyen(String dichuyen) {
        this.dichuyen = dichuyen;
    }

    public String getTrieuchung() {
        return trieuchung;
    }

    public void setTrieuchung(String trieuchung) {
        this.trieuchung = trieuchung;
    }

    public String getNghinhiem() {
        return nghinhiem;
    }

    public void setNghinhiem(String nghinhiem) {
        this.nghinhiem = nghinhiem;
    }

    public String getNuocbenh() {
        return nuocbenh;
    }

    public void setNuocbenh(String nuocbenh) {
        this.nuocbenh = nuocbenh;
    }

    public String getBieuhien() {
        return bieuhien;
    }

    public void setBieuhien(String bieuhien) {
        this.bieuhien = bieuhien;
    }

    
    
}
