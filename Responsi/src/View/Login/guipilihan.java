/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Login;

import Connector.Connector;
import Controller.ControllerTampil;
import View.Dosen.ViewDosen;
import View.Mahasiswa.ViewMahasiswa;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Hafizh
 */
public class guipilihan extends JFrame implements ActionListener{
    JLabel header = new JLabel("Selamat Datang!");
    JLabel desc = new JLabel("Pilih Data!");
    JButton tombolMahasiswa = new JButton("Mahasiswa");
    JButton tombolDosen = new JButton("Dosen");
    ControllerTampil controller;
    public guipilihan(ControllerTampil ct){
        controller = ct;
        setTitle("Pilih Menu");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(480, 480);
        setLocationRelativeTo(null);

        add(header);
        add(desc);
        add(tombolMahasiswa);
        add(tombolDosen);
        
        tombolMahasiswa.addActionListener(this);
        tombolDosen.addActionListener(this);

        header.setBounds(20, 10, 440, 24);
        desc.setBounds(20, 30, 440, 24);
        tombolMahasiswa.setBounds(20, 70, 425, 40);
        tombolDosen.setBounds(20, 120, 425, 40);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==tombolMahasiswa){
            new ViewMahasiswa();
            this.dispose();
        } else if(e.getSource()==tombolDosen){
            new ViewDosen();
            this.dispose();
        }
    }

    public void showTampilPage(Connector connect) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
