/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author luisf
 */
public class ControllerErroEmailSenhaIncorretosRecSenha implements Initializable {

    @FXML
    private Button btnVoltarLogin;
    @FXML
    private Button btnVoltarRecSenha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void VoltarTelaLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/telas/Login.fxml"));
        Scene scene = new Scene(root);
        main.Main.myStage.setScene(scene);
    }

    @FXML
    private void telaRecuperarSenha(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/telas/RecuperarSenha.fxml"));
        Scene scene = new Scene(root);
        main.Main.myStage.setScene(scene);
    }
    
}
