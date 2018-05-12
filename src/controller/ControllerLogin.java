/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DAOUsuario;
import entidades.Usuario;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author luisf
 */
public class ControllerLogin implements Initializable {

    @FXML
    private Button btnContaGoogle;
    @FXML
    private Button btnTelaCadastro;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField pswSenha;
    @FXML
    private Button btnEsqueciSenha;
    @FXML
    private Button btnEntrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void acessarTelaCadastro(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/telas/Cadastro.fxml"));
        Scene scene = new Scene(root);
        main.Main.myStage.setScene(scene);
    }
    
    @FXML
    private void recuperarSenha(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/telas/RecuperarSenha.fxml"));
        Scene scene = new Scene(root);
        main.Main.myStage.setScene(scene);
    }

    @FXML
    private void acessarTelaHome(ActionEvent event) throws IOException, ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        
        DAOUsuario dao = new DAOUsuario();

        String email = txtEmail.getText();
        String senha = pswSenha.getText();
        
        if((txtEmail.getText().isEmpty()) || (pswSenha.getText().isEmpty())){
            Parent root = FXMLLoader.load(getClass().getResource("/telas/ErroRetornoVazioLogin.fxml"));
            Scene scene = new Scene(root);
            main.Main.myStage.setScene(scene);
        }else{
            Usuario usuario = dao.verificacaoUsuarioLogin(email, senha);
            if (usuario != null) {
                Parent root = FXMLLoader.load(getClass().getResource("/telas/Player.fxml"));
                Scene scene = new Scene(root);
                main.Main.myStage.setScene(scene);
            }else {
                Parent root = FXMLLoader.load(getClass().getResource("/telas/ErroEmailSenhaIncorretosLogin.fxml"));
                Scene scene = new Scene(root);
                main.Main.myStage.setScene(scene);
            }
        } 
    }
    
}
