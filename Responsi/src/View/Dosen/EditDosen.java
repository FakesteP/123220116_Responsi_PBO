package View.Dosen;

import Controller.ControllerDosen;
import Model.Dosen.ModelDosen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class EditDosen extends JFrame {
    ControllerDosen controller;
    
    JLabel header = new JLabel("Edit Dosen");
    JLabel labelInputNama = new JLabel("Nama");
    JLabel labelInputNohp = new JLabel("No HP");
    JLabel labelInputEmail = new JLabel("Email"); // Menambahkan label untuk Email
    JTextField inputNama = new JTextField();
    JTextField inputNohp = new JTextField();
    JTextField inputEmail = new JTextField(); // Menambahkan JTextField untuk Email
    JButton tombolEdit = new JButton("Edit Dosen");
    JButton tombolKembali = new JButton("Kembali");

    public EditDosen(ModelDosen dosen) {
        setTitle("Edit Dosen");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(480, 480); // Diperbesar untuk menampung input tambahan
        setLocationRelativeTo(null);

        add(header);
        add(labelInputNama);
        add(labelInputNohp);
        add(labelInputEmail);
        add(inputNama);
        add(inputNohp);
        add(inputEmail);
        add(tombolEdit);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        labelInputNama.setBounds(20, 32, 440, 24);
        inputNama.setBounds(18, 56, 440, 36);
        labelInputNohp.setBounds(20, 96, 440, 24);
        inputNohp.setBounds(18, 120, 440, 36);
        labelInputEmail.setBounds(20, 160, 440, 24); // Penempatan komponen untuk Email
        inputEmail.setBounds(18, 184, 440, 36); // Penempatan komponen untuk Email
        tombolKembali.setBounds(20, 232, 215, 40);
        tombolEdit.setBounds(240, 232, 215, 40);
        
        // Set nilai input dari objek ModelDosen
        inputNama.setText(dosen.getNama());
        inputNohp.setText(dosen.getNohp());
        inputEmail.setText(dosen.getEmail()); // Menetapkan nilai email
        
        controller = new ControllerDosen(this);

        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ViewDosen();
            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.editDosen(dosen.getId());
            }
        });
    }
    
    public String getInputNama() {
        return inputNama.getText();
    }
    
    public String getInputNohp() {
        return inputNohp.getText();
    }
    
    public String getInputEmail() {
        return inputEmail.getText();
    }
}
