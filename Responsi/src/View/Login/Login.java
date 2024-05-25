package View.Login;

import Controller.ControllerLogin;
import Controller.ControllerTampil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login extends JFrame {

    JLabel header = new JLabel("Login");
    JLabel labelUsername = new JLabel("Username");
    JLabel labelPassword = new JLabel("Passowrd");
    JTextField inputUsername = new JTextField();
    JTextField inputPassword = new JTextField();
    JButton tombolLogin = new JButton("Login");
    ControllerLogin controller;

    public Login(ControllerLogin cl) {
        this.setVisible(true);
        controller = cl;
        setTitle("Input Mahasiswa");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(480, 240);
        setLocationRelativeTo(null);

        add(header);
        add(labelUsername);
        add(labelPassword);
        add(inputUsername);
        add(inputPassword);
        add(tombolLogin);

        header.setBounds(20, 8, 440, 24);
        labelUsername.setBounds(20, 32, 440, 24);
        inputUsername.setBounds(18, 56, 440, 36);
        labelPassword.setBounds(20, 96, 440, 24);
        inputPassword.setBounds(18, 120, 440, 36);
        tombolLogin.setBounds(240, 160, 215, 40);
        
        tombolLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.inputLogin(inputUsername.getText(), inputPassword.getText());
            }
        });
    }


    public String getinputUsername() {
        return inputUsername.getText();
    }
    public String getinputPassword() {
        return inputPassword.getText();
    }

}
