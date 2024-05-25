/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Mahasiswa;

import Connector.Connector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hafizh
 */
public class DAOMahasiswa implements InterfaceDAOMahasiswa {

    @Override
    public void insert(ModelMahasiswa mahasiswa) {
        try{
        // Perintah query disimpan ke dalam variabel "query"
            String query = "INSERT INTO mahasiswa (nama, nim, email, password, angkatan) VALUES (?, ?, ?, ?, ?);";
            
            /* 
              Memasukkan nama dan nim dari input user ke dalam query untuk 
              mengisi bagian "?, ?" (dalam hal ini berarti nama dan nim)
            */
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setString(1, mahasiswa.getNama());
            statement.setString(2, mahasiswa.getNim());
            statement.setString(3, mahasiswa.getEmail());
            statement.setString(4, mahasiswa.getPassword());
            statement.setString(5, mahasiswa.getAngkatan());
            
            // Menjalankan query untuk memasukkan data mahasiswa baru
            statement.executeUpdate();
            
            // Menutup koneksi untuk menghemat penggunaan memory.
            statement.close();
        } catch (SQLException e) {
            // Menampilkan pesan error ketika gagal input data.
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public void update(ModelMahasiswa mahasiswa) {
    try {
        String query = "UPDATE mahasiswa SET nama=?, nim=?, email=?, password=?, angkatan=? WHERE id=?;";
            
        PreparedStatement statement;
        statement = Connector.Connect().prepareStatement(query);
        statement.setString(1, mahasiswa.getNama());
        statement.setString(2, mahasiswa.getNim());
        statement.setString(3, mahasiswa.getEmail());
        statement.setString(4, mahasiswa.getPassword());
        statement.setString(5, mahasiswa.getAngkatan());
        statement.setInt(6, mahasiswa.getId());
        
        statement.executeUpdate();
        
        statement.close();
    } catch (SQLException e) {
        System.out.println("update Failed! (" + e.getMessage() + ")");
    }
}

    @Override
    public void delete(int id) {
        try {
            String query = "DELETE FROM mahasiswa WHERE id=?;";
            
            PreparedStatement statement;
            statement = Connector.Connect().prepareStatement(query);
            statement.setInt(1, id);
            
            statement.executeUpdate();
            
            statement.close();
        } catch (SQLException e) {
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<ModelMahasiswa> getAll() {
        List<ModelMahasiswa> listMahasiswa = null;

        try {
            listMahasiswa = new ArrayList<>();
            
            Statement statement = Connector.Connect().createStatement();
            
            String query = "SELECT * FROM mahasiswa;";
            
            ResultSet resultSet = statement.executeQuery(query);
            
            while (resultSet.next()) {
                ModelMahasiswa mhs = new ModelMahasiswa();
                
                mhs.setId(resultSet.getInt("id"));
                mhs.setNama(resultSet.getString("nama"));
                mhs.setNim(resultSet.getString("nim"));
                mhs.setEmail(resultSet.getString("email"));
                mhs.setPassword(resultSet.getString("password"));
                mhs.setAngkatan(resultSet.getString("angkatan"));
                
                listMahasiswa.add(mhs);
            }
            
            statement.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listMahasiswa;
    }
    
}

