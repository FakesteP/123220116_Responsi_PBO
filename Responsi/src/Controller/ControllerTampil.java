package Controller;

import Connector.Connector;
import View.Login.guipilihan;


public class ControllerTampil {
    guipilihan view;
    Connector connect;
    
    public void showTampilPage(Connector connect){
        if(!connect.checkLogin()){
            return;
        }
        view = new guipilihan(this);
        this.connect = connect;
    }
}
