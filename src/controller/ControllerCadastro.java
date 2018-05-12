package controller;

import dao.DAOUsuario;
import entidades.Usuario;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import mascaraFormater.TextFieldFormatter;

/**
 * FXML Controller class
 *
 * @author luisf
 */
public class ControllerCadastro implements Initializable {

    @FXML
    private Button btnContaGoogle;
    @FXML
    private TextField txtNomeCadastro;
    @FXML
    private TextField txtEmailCadastro;
    @FXML
    private TextField txtCpfCadastro;
    @FXML
    private TextField txtLoginCadastro;
    @FXML
    private PasswordField pswSenhaCadastro;
    @FXML
    private Button btnCadastrar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void verificacaoInputs() throws ClassNotFoundException, IllegalAccessException, SQLException, InstantiationException, IOException, NoSuchAlgorithmException{
        Usuario usuarios = new Usuario(null, null, null, null, null);
        usuarios.setNome(txtNomeCadastro.getText());
        usuarios.setEmail(txtEmailCadastro.getText());
        usuarios.setCpf(txtCpfCadastro.getText());
        usuarios.setLogin(txtLoginCadastro.getText());
        usuarios.setSenha(pswSenhaCadastro.getText());
        
        // fazendo a validação dos dados
        if ((txtNomeCadastro.getText().isEmpty()) || (txtEmailCadastro.getText().isEmpty()) || (txtCpfCadastro.getText().isEmpty()) || (txtLoginCadastro.getText().isEmpty()) || (pswSenhaCadastro.getText().isEmpty())) {
            Parent root = FXMLLoader.load(getClass().getResource("/telas/ErroRetornoVazioCadastro.fxml"));
            Scene scene = new Scene(root);
            main.Main.myStage.setScene(scene);
        }
        else {
            // instanciando a classe DAOUsuario do pacote dao e criando seu objeto dao
            DAOUsuario dao = new DAOUsuario();
            dao.inserirPessoa(usuarios);
            Parent root = FXMLLoader.load(getClass().getResource("/telas/InfoCadastroComSucesso.fxml"));
            Scene scene = new Scene(root);
            main.Main.myStage.setScene(scene);
        }
    }
    
    @FXML
    private void cadastrarUsuario(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        try {
            verificacaoInputs();
        } catch (ClassNotFoundException | IllegalAccessException | SQLException | InstantiationException ex) {
            Logger.getLogger(ControllerCadastro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void voltarLogin(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        Parent root = FXMLLoader.load(getClass().getResource("/telas/Login.fxml"));
        Scene scene = new Scene(root);
        main.Main.myStage.setScene(scene);
    }
    
    /*
    @FXML
    private void txtCpfCadastro(){
        TextFieldFormatter tff2 = new TextFieldFormatter();
        tff2.setMask("###.###.###-##");
        tff2.setCaracteresValidos("0123456789");
        tff2.setTf(txtCpfCadastro);
        tff2.formatter();
    }*/
    
}
