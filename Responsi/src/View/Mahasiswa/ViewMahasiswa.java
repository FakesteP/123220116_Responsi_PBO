package View.Mahasiswa;

import Controller.ControllerMahasiswa;
import Controller.ControllerTampil;
import Model.Mahasiswa.ModelMahasiswa;
import View.Login.guipilihan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewMahasiswa extends JFrame {

    Integer baris;

    ControllerMahasiswa controller;

    JLabel header = new JLabel("Selamat Datang!");
    JButton headerbtn = new JButton("⬅");
    JButton tombolTambah = new JButton("Tambah Mahasiswa");
    JButton tombolEdit = new JButton("Edit Mahasiswa");
    JButton tombolHapus = new JButton("Hapus Mahasiswa");
    JTextField fieldCari = new JTextField();
    JButton tombolCari = new JButton("Cari");

    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    String namaKolom[] = {"ID", "Nama", "NIM", "Nohp", "Email"};

    public ViewMahasiswa() {
        tableModel = new DefaultTableModel(namaKolom, 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        setTitle("Daftar Mahasiswa");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(552, 540);
        setLocationRelativeTo(null);

        add(header);
        add(headerbtn);
        add(scrollPane);
        add(tombolTambah);
        add(tombolEdit);
        add(tombolHapus);
        add(fieldCari);
        add(tombolCari);

        fieldCari.setBounds(20, 500, 400, 30);
        tombolCari.setBounds(430, 500, 100, 30);
        header.setBounds(235, 8, 440, 24);
        headerbtn.setBounds(20, 8, 50, 24);
        scrollPane.setBounds(20, 36, 512, 320);
        tombolTambah.setBounds(20, 370, 512, 40);
        tombolEdit.setBounds(20, 414, 512, 40);
        tombolHapus.setBounds(20, 456, 512, 40);

        controller = new ControllerMahasiswa(this);
        controller.showAllMahasiswa();

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                baris = table.getSelectedRow();
            }
        });
        
        ControllerTampil ct = new ControllerTampil();
        headerbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new guipilihan(ct);
            }
        });
        
        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new InputMahasiswa();
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    ModelMahasiswa mahasiswaTerpilih = new ModelMahasiswa();
                    
                    Integer id = (int) table.getValueAt(baris, 0);
                    String nama = table.getValueAt(baris, 1).toString();
                    String nim = table.getValueAt(baris, 2).toString();
                    
                    mahasiswaTerpilih.setId(id);
                    mahasiswaTerpilih.setNama(nama);
                    mahasiswaTerpilih.setNim(nim);

                    dispose();
                    new EditMahasiswa(mahasiswaTerpilih);
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });

        tombolHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    controller.deleteMahasiswa(baris);
                    
                    baris = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });
    }
    

    public JTable getTableMahasiswa() {
        return table;
    }
}
