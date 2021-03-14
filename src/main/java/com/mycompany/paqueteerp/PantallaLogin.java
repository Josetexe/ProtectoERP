/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.paqueteerp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Esta clase se encarga de cargar todo lo relacionado con la pantalla del login.
 * @author Jose Texeira
 */
public class PantallaLogin {

    static String nombre, password;
    static Label lbl_error;
    static Button btn_comprobar;
    static TextField txt_nombre ;
    static PasswordField txt_pwd;
    
    /**
     * El método se encaga de manejar todos los componentes de la pantalla y de las accciones del boton que contiene.
     * @param window Se le pasa el stage donde se cargan todos los componentes de la pantalla.
     * @param llamante Se le pasa la interface para que se pueda comunicar con la ClasePrincipal.
     */
    static void cargarPantallaLogin(Stage window, ComunicacionLogin llamante) {
       
       File f=new File("recursos/pantalla_inicio.fxml");
        try {
            URL url_pantalla_login=f.toURI().toURL();
            Parent root=FXMLLoader.load(url_pantalla_login);
            Scene escena=new Scene(root);
            window.setTitle("Inicio sesión");
            /*INICIALIZO OBJETOS*/
            txt_nombre=(TextField)escena.lookup("#txt_nombre");
            txt_pwd=(PasswordField)escena.lookup("#txt_passwd");
            btn_comprobar=(Button)escena.lookup("#btn_iniciar");
            
            EventHandler<ActionEvent> oyente_btn=(ActionEvent t) -> {
                nombre=txt_nombre.getText();
                password=txt_pwd.getText();
                
                llamante.comprobarUsuario(nombre, password);
            };
            btn_comprobar.setOnAction(oyente_btn);
            window.setScene(escena);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(PantallaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void indicarError(){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText("El usuario o la contraseña no coinciden. Intentelo de nuevo.");
        alert.showAndWait();
        
        txt_nombre.setText("");
        txt_pwd.setText("");
        
    }
  public interface ComunicacionLogin
  {
      public void comprobarUsuario(String nombre, String password);
  }
}
