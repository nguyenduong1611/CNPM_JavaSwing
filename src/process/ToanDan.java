/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package process;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class ToanDan {
    private String id;
    private String nguoidung_id;
    private String dichuyen;
    private String trieuchung;
    private String nghinhiem;
    private String nuocbenh;
    private String bieuhien;
            
    public ToanDan(){
        
    }
    public ToanDan(String id, String nguoidung_id, String dichuyen, String trieuchung, String nghinhiem, String nuocbenh, String bieuhien){
        this.id = id;
        this.nguoidung_id = nguoidung_id;
        this.dichuyen = dichuyen;
        this.trieuchung = trieuchung;
        this.nghinhiem = nghinhiem;
        this.nuocbenh = nuocbenh;
        this.bieuhien = bieuhien;
    }

    public ToanDan(String nguoidung_id, String dichuyen, String trieuchung, String nghinhiem, String nuocbenh, String bieuhien) {
        
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
