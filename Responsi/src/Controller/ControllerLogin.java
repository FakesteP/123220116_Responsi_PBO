/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connector.Connector;
import View.Login.guipilihan;
import View.Login.Login;
import javax.swing.JOptionPane;


/**
 *
 * @author user
 */
public class ControllerLogin {
    Login viewLogin;

    public void showViewLogin() {
        viewLogin = new Login(this);
    }

    public void inputLogin(String username, String password) {
        // Cek apakah username atau password kosong
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username dan password tidak boleh kosong", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        Connector connect = new Connector(username, password);
        if (connect.checkLogin()) {
            ControllerTampil ct = new ControllerTampil();
            ct.showTampilPage(connect);
        } else {
            JOptionPane.showMessageDialog(null, "Login gagal, username atau password salah", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }


}
