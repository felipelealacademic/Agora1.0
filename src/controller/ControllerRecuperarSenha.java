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
import java.security.NoSuchAlgorithmException;
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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author luisf
 */
public class ControllerRecuperarSenha implements Initializable {

    @FXML
    private Text txtRecuperarSenhaTitulo;
    @FXML
    private Button btnVoltarLogin;
    @FXML
    private TextField txtEmailRecSenha;
    @FXML
    private TextField txtCpfRecSenha;
    @FXML
    private PasswordField pswConfirmaSenhaNova;
    @FXML
    private Button btnRecSenha;
    @FXML
    private PasswordField pswSenhaNova;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void voltarLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/telas/Login.fxml"));
        Scene scene = new Scene(root);
        main.Main.myStage.setScene(scene);
    }

    @FXML
    private void cadastrarNovaSenha(ActionEvent event) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException, NoSuchAlgorithmException, IOException {
        /*DAOUsuario dao = new DAOUsuario();
        String cpf = txtCpfRecSenha.getText();
        String senha = pswSenhaNova.getText();
        String email = txtEmailRecSenha.getText();
        String confSenha = pswConfirmaSenhaNova.getText();
        // if para verificar se os campos estão vazios ou nao
        if ((txtEmailRecSenha.getText().isEmpty()) || (txtCpfRecSenha.getText().isEmpty()) || (pswSenhaNova.getText().isEmpty()) || (pswConfirmaSenhaNova.getText().isEmpty())) {
            System.out.println("Erro, Campo vazio");
            Parent root = FXMLLoader.load(getClass().getResource("/telas/ErroRetornoVazioRecSenha.fxml"));
            Scene scene = new Scene(root);
            main.Main.myStage.setScene(scene);
            return;
        }
        Usuario usuario = dao.verificacaoUsuarioRecuperaSenha(email, cpf);
        //confirmando os dados digitados
        System.out.printf("email: %s\n cpf: %s\n  senha: %s\n confsenha: %s\n", email, cpf, senha, confSenha);
        //confirmando os dados que vem do banco, porém está dando erro ai está comentado
        //System.out.printf("email: %s\n cpf: %s\n  senha: %s\n", usuario.getEmail(), usuario.getCpf(),usuario.getSenha() );
        // se o usuario for null é pq ta certo
        if (usuario != null) {
            System.out.println("caiu no if usuario == null");
            //senhas iguais cairão nesse if
            if  (senha.equals(confSenha)) {
                dao.atualizaSenha(usuario);
                System.out.println("caiu no if de senha igual");
                Parent root = FXMLLoader.load(getClass().getResource("/telas/InfoRecSenhaSucesso.fxml"));
                Scene scene = new Scene(root);
                main.Main.myStage.setScene(scene);
                return;
            }
            // senhas diferentes cairão nesse if
            if(!senha.equals(confSenha)){
                System.out.println("caiu no if senha diferente");
                Parent root = FXMLLoader.load(getClass().getResource("/telas/ErroSenhasDiferentesRecSenha.fxml"));
                Scene scene = new Scene(root);
                main.Main.myStage.setScene(scene);
            }
            return;
        }
        else{
            //erro que os dados não batem com o banco
            Parent root = FXMLLoader.load(getClass().getResource("/telas/ErroCpfEmailIncorretosRecSenha.fxml"));
            Scene scene = new Scene(root);
            main.Main.myStage.setScene(scene);
        }*/
        
        DAOUsuario dao = new DAOUsuario();

        String email = txtEmailRecSenha.getText();
        String cpf = txtCpfRecSenha.getText();
        String senha = pswSenhaNova.getText();
        String confSenha = pswConfirmaSenhaNova.getText();
        
        if((txtEmailRecSenha.getText().isEmpty()) || (txtCpfRecSenha.getText().isEmpty()) || (pswSenhaNova.getText().isEmpty()) || (pswConfirmaSenhaNova.getText().isEmpty())){
            Parent root = FXMLLoader.load(getClass().getResource("/telas/ErroRetornoVazioRecSenha.fxml"));
            Scene scene = new Scene(root);
            main.Main.myStage.setScene(scene);
        }
        Usuario usuario = dao.verificacaoUsuarioRecuperaSenha(email, cpf);
        if (usuario != null && senha.equals(confSenha)) {
            dao.atualizaSenha(usuario, confSenha);
            Parent root = FXMLLoader.load(getClass().getResource("/telas/InfoRecSenhaSucesso.fxml"));
            Scene scene = new Scene(root);
            main.Main.myStage.setScene(scene);
        }
        if(!senha.equals(confSenha)){
            Parent root = FXMLLoader.load(getClass().getResource("/telas/ErroSenhasDiferentesRecSenha.fxml"));
            Scene scene = new Scene(root);
            main.Main.myStage.setScene(scene);
           }
        else {
            Parent root = FXMLLoader.load(getClass().getResource("/telas/ErroCpfEmailIncorretosRecSenha.fxml"));
            Scene scene = new Scene(root);
            main.Main.myStage.setScene(scene);
        }
    } 
}
        

