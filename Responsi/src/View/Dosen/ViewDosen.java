package View.Dosen;

import Controller.ControllerDosen;
import Controller.ControllerTampil;
import Model.Dosen.ModelDosen;
import View.Login.guipilihan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewDosen extends JFrame{

    Integer baris;

    ControllerDosen controller;

    JLabel header = new JLabel("Selamat Datang!");
    JButton headerbtn = new JButton("⬅");
    JButton tombolTambah = new JButton("Tambah Dosen");
    JButton tombolEdit = new JButton("Edit Dosen");
    JButton tombolHapus = new JButton("Hapus Dosen");

    JTable table;
    DefaultTableModel tableModel;
    JScrollPane scrollPane;

    String namaKolom[] = {"ID", "Nama", "No HP", "Email"};

    public ViewDosen() {
        tableModel = new DefaultTableModel(namaKolom, 0);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);

        setTitle("Daftar Dosen");
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

        header.setBounds(235, 8, 440, 24);
        headerbtn.setBounds(20, 8, 50, 24);
        scrollPane.setBounds(20, 36, 512, 320);
        tombolTambah.setBounds(20, 370, 512, 40);
        tombolEdit.setBounds(20, 414, 512, 40);
        tombolHapus.setBounds(20, 456, 512, 40);

        controller = new ControllerDosen(this);
        controller.showAllDosen();

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
                new InputDosen();
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    ModelDosen DosenTerpilih = new ModelDosen();
                    
                    Integer id = (int) table.getValueAt(baris, 0);
                    String nama = table.getValueAt(baris, 1).toString();
                    String nohp = table.getValueAt(baris, 2).toString();
                    String email = table.getValueAt(baris, 3).toString();
                    
                    DosenTerpilih.setId(id);
                    DosenTerpilih.setNama(nama);
                    DosenTerpilih.setNohp(nohp);
                    DosenTerpilih.setEmail(email);

                    dispose();
                    new EditDosen(DosenTerpilih);
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });

        tombolHapus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (baris != null) {
                    controller.deleteDosen(baris);
                    
                    baris = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Data belum dipilih.");
                }
            }
        });
    }

    public JTable getTableDosen() {
        return table;
    }

}
