/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.paqueteerp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Esta clase se encarga de cargar todo lo relacionado con la pantalla de clientes.
 * @author Jose Texeira
 */
public class PantallaClientes {
    
    static Button btn_salir, btn_guardar, btn_borrar;
    static TextField tf_nif_cliente, tf_nombre, tf_domicilio;
    static String nif_cliente;
    
    /**
     * El método se encaga de manejar todos los componentes de la pantalla y de las accciones de los botones que contienen.
     * @param window Se le pasa el stage donde se cargan todos los componentes de la pantalla.
     * @param llamante Se le pasa la interface para que se pueda comunicar con la ClasePrincipal.
     */
    public static void cargarPantalla(Stage window, ComunicacionClientes llamante) {
        File f=new File("recursos/pantalla_clientes.fxml");
        try {
            URL url_pantalla_cliente=f.toURI().toURL();
            Parent root=FXMLLoader.load(url_pantalla_cliente);
            Scene escena=new Scene(root);
            window.setTitle("Clientes");
            /*INICIALIZO OBJETOS*/
            tf_nif_cliente=(TextField)escena.lookup("#tf_nif_cliente");
            tf_nombre=(TextField)escena.lookup("#tf_nombre");
            tf_domicilio=(TextField)escena.lookup("#tf_domicilio");
            
            btn_salir=(Button)escena.lookup("#btn_salir");
            btn_guardar=(Button)escena.lookup("#btn_guardar");
            btn_borrar=(Button)escena.lookup("#btn_borrar");
            
            tf_nif_cliente.setOnKeyPressed((KeyEvent t) -> {
                if (t.getCode() == KeyCode.ENTER){
                    tf_nombre.setText("");
                    tf_domicilio.setText("");
                    nif_cliente=tf_nif_cliente.getText().toUpperCase();
                    if("".equals(nif_cliente)){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error");
                        alert.setContentText("Introduzca un código de artículo válido.");
                        alert.showAndWait();
                    }else{
                        llamante.comprobarCliente(nif_cliente);
                    }
                }
            });
            
            
            btn_salir.setOnAction((ActionEvent t) -> {
                llamante.volverPPrincipal();
            });
            
            
            btn_guardar.setOnAction((ActionEvent t) -> {
                String nif_cliente1=tf_nif_cliente.getText();
                String nombre_cliente=tf_nombre.getText();
                String domicilio_cliente=tf_domicilio.getText();
                Cliente c = new Cliente(nif_cliente1, domicilio_cliente, nombre_cliente);
                llamante.guardarCliente(c);
            });
            
            btn_borrar.setOnAction((ActionEvent t) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmación");
                alert.setContentText("¿Estas seguro de confirmar la acción?");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    String nif_cliente_borrar=tf_nif_cliente.getText();
                    llamante.borrarCliente(nif_cliente_borrar);
                }  
            });
            
            
            window.setScene(escena);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(PantallaClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    static void mostrarCliente(Cliente c) {
        tf_nif_cliente.setText(c.getNIFCliente());
        tf_nombre.setText(c.getNombreCliente());
        tf_domicilio.setText(c.getDomicilioCliente());
    }
    
    static void focus() {
        tf_nombre.requestFocus();
    }
    
    static void ventanaInformacion(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    static void vaciarCampos() {
        tf_nif_cliente.setText("");
        tf_nombre.setText("");
        tf_domicilio.setText("");
    }

    static void focusNIF() {
        tf_nif_cliente.requestFocus();
    }


    public interface ComunicacionClientes{

        public void volverPPrincipal();

        public void comprobarCliente(String nif_cliente);

        public void guardarCliente(Cliente c);

        public void borrarCliente(String nif_cliente_borrar);

    }
}