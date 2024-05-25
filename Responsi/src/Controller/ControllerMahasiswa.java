/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Mahasiswa.*;
import View.Mahasiswa.*;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Hafizh
 */
public class ControllerMahasiswa {
    ViewMahasiswa tabelMahasiswa;
    InputMahasiswa inputMahasiswa;
    EditMahasiswa editMahasiswa;
    
    InterfaceDAOMahasiswa daoMahasiswa;
    
    List<ModelMahasiswa> listMahasiswa;

    public ControllerMahasiswa(ViewMahasiswa tabelMahasiswa) {
        this.tabelMahasiswa = tabelMahasiswa;
        this.daoMahasiswa = new DAOMahasiswa();
    }

    public ControllerMahasiswa(InputMahasiswa inputMahasiswa) {
        this.inputMahasiswa = inputMahasiswa;
        this.daoMahasiswa = new DAOMahasiswa();
    }

    public ControllerMahasiswa(EditMahasiswa editMahasiswa) {
        this.editMahasiswa = editMahasiswa;
        this.daoMahasiswa = new DAOMahasiswa();
    }
    
    public void showAllMahasiswa(){
        listMahasiswa = daoMahasiswa.getAll();
        ModelTableMahasiswa tabel = new ModelTableMahasiswa(listMahasiswa);
        tabelMahasiswa.getTableMahasiswa().setModel(tabel);
    }
    
    public void insertMahasiswa(){
    try{
        ModelMahasiswa maba = new ModelMahasiswa();
        
        String nama = inputMahasiswa.getInputNama();
        String nim = inputMahasiswa.getInputNIM();
        String email = inputMahasiswa.getInputEmail(); // Mendapatkan nilai email dari inputMahasiswa
        String password = inputMahasiswa.getInputPassword(); // Mendapatkan nilai password dari inputMahasiswa
        String angkatan = inputMahasiswa.getInputAngkatan(); // Mendapatkan nilai angkatan dari inputMahasiswa
        
        // Periksa apakah ada nilai yang kosong
        if("".equals(nama) || "".equals(nim) || "".equals(email) || "".equals(password) || "".equals(angkatan)){
            throw new Exception("Nama, NIM, Email, Password, atau Angkatan tidak boleh kosong");
        }
        
        // Set nilai maba
        maba.setNama(nama);
        maba.setNim(nim);
        maba.setEmail(email); // Set email
        maba.setPassword(password); // Set password
        maba.setAngkatan(angkatan); // Set angkatan
        
        daoMahasiswa.insert(maba);
        JOptionPane.showMessageDialog(null, "Berhasil menambahkan maba");
        inputMahasiswa.dispose();
        showAllMahasiswa(); // Tampilkan kembali semua data mahasiswa setelah menambahkan
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

public void editMahasiswa(int id){
    try{
        ModelMahasiswa mahasiswaDiedit = new ModelMahasiswa();
        
        String nama = editMahasiswa.getInputNama();
        String nim = editMahasiswa.getInputNIM();
        String email = editMahasiswa.getInputEmail(); // Mendapatkan nilai email dari editMahasiswa
        String password = editMahasiswa.getInputPassword(); // Mendapatkan nilai password dari editMahasiswa
        String angkatan = editMahasiswa.getInputAngkatan(); // Mendapatkan nilai angkatan dari editMahasiswa
        
        // Periksa apakah ada nilai yang kosong
        if("".equals(nama) || "".equals(nim) || "".equals(email) || "".equals(password) || "".equals(angkatan)){
            throw new Exception("Nama, NIM, Email, Password, atau Angkatan tidak boleh kosong");
        }
        
        // Set nilai mahasiswaDiedit
        mahasiswaDiedit.setId(id);
        mahasiswaDiedit.setNama(nama);
        mahasiswaDiedit.setNim(nim);
        mahasiswaDiedit.setEmail(email); // Set email
        mahasiswaDiedit.setPassword(password); // Set password
        mahasiswaDiedit.setAngkatan(angkatan); // Set angkatan
        
        daoMahasiswa.update(mahasiswaDiedit);
        
        JOptionPane.showMessageDialog(null, "Berhasil mengubah data mahasiswa");
        
        editMahasiswa.dispose();
        showAllMahasiswa(); // Tampilkan kembali semua data mahasiswa setelah mengedit
    } catch(Exception e){
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

    public void deleteMahasiswa(Integer baris) {
        Integer id = (int) tabelMahasiswa.getTableMahasiswa().getValueAt(baris, 0);
        String nama = tabelMahasiswa.getTableMahasiswa().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Mahasiswa",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            daoMahasiswa.delete(id);
            
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            showAllMahasiswa();
        }
    }
}