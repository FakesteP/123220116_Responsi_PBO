/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Dosen.*;
import View.Dosen.*;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Hafizh
 */
public class ControllerDosen {
    ViewDosen tabelDosen;
    InputDosen inputDosen;
    EditDosen editDosen;
    
    InterfaceDAODosen daoDosen;
    
    List<ModelDosen> listDosen;

    public ControllerDosen(ViewDosen tabelDosen) {
        this.tabelDosen = tabelDosen;
        this.daoDosen = new DAODosen();
    }

    public ControllerDosen(InputDosen inputDosen) {
        this.inputDosen = inputDosen;
        this.daoDosen = new DAODosen();
    }

    public ControllerDosen(EditDosen editDosen) {
        this.editDosen = editDosen;
        this.daoDosen = new DAODosen();
    }
    
    public void showAllDosen(){
        listDosen = daoDosen.getAll();
        ModelTableDosen tabel = new ModelTableDosen(listDosen);
        tabelDosen.getTableDosen().setModel(tabel);
    }
    
    public void insertDosen(){
        try{
            ModelDosen dosenBaru = new ModelDosen();
            
            String nama = inputDosen.getInputNama();
            String nohp = inputDosen.getInputNohp();
            String email = inputDosen.getInputEmail();
            
            if("".equals(nama) || "".equals(nohp)|| "".equals(email)){
                throw new Exception("Nama atau Nohp tidak boleh kosong");
            }
            dosenBaru.setNama(nama);
            dosenBaru.setNohp(nohp);
            dosenBaru.setNohp(email);
            
            daoDosen.insert(dosenBaru);
            JOptionPane.showMessageDialog(null, "Berhasil menambahkan dosenBaru");
            inputDosen.dispose();
            new ViewDosen();
            }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    public void editDosen(int id){
        try{
        ModelDosen DosenDiedit = new ModelDosen();
        
        String nama = editDosen.getInputNama();
        String nohp = editDosen.getInputNohp();
        String email = editDosen.getInputEmail();
        
        if("".equals(nama) || "".equals(nohp) || "".equals(email)){
            throw new Exception("Nama atau nohp tidak boleh kosong");
        }
        
        DosenDiedit.setId(id);
        DosenDiedit.setNama(nama);
        DosenDiedit.setNohp(nohp);
        DosenDiedit.setEmail(email);
        
        daoDosen.update(DosenDiedit);
        
        JOptionPane.showMessageDialog(null, "Berhasil mengubah data Dosen");
        
        editDosen.dispose();
        new ViewDosen();
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    
    
    public void deleteDosen(int baris){
        Integer id = (int) tabelDosen.getTableDosen().getValueAt(baris, 0);
        String nama = tabelDosen.getTableDosen().getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Dosen",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            daoDosen.delete(id);
            
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");

            showAllDosen();
        }
    }
    
    
    
}
