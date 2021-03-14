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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Esta clase se encarga de cargar todo lo relacionado con la pantalla principal.
 * @author Jose Texeira
 */

class PantallaPrincipal {
    
    static Button btn_articulos;
    static Button btn_clientes;
    static Button btn_proveedores;
    static Button btn_cerrar_sesion;
    
    
    /**
     * El método se encaga de manejar todos los componentes de la pantalla y de las accciones del boton que contiene. Estos botones sirven para llamar a las demás pantallas.
     * @param window Se le pasa el stage donde se cargan todos los componentes de la pantalla.
     * @param llamante Se le pasa la interface para que se pueda comunicar con la ClasePrincipal.
     */
    static void cargarPantallaPrincipal(Stage window, ComunicacionPPrincipal llamante) {
       
       File f=new File("recursos/pantalla_principal.fxml");
        try {
            URL url_pantalla=f.toURI().toURL();
            Parent root=FXMLLoader.load(url_pantalla);
            Scene escena=new Scene(root);
            window.setTitle("Menu");
            /*INICIALIZO OBJETOS*/
            btn_articulos=(Button)escena.lookup("#btn_articulos");
            btn_clientes=(Button)escena.lookup("#btn_clientes");
            btn_proveedores=(Button)escena.lookup("#btn_proveedores");
            btn_cerrar_sesion=(Button)escena.lookup("#btn_cerrar_sesion");
            
            btn_articulos.setOnAction((ActionEvent t) -> {
                llamante.cargarPantallaArticulos();
            });
            
            btn_clientes.setOnAction((ActionEvent t) -> {
                llamante.cargarPantallaClientes();
            });
            
            btn_proveedores.setOnAction((ActionEvent t) -> {
                llamante.cargarPantallaProveedores();
            });
            
            btn_cerrar_sesion.setOnAction((ActionEvent t) -> {
                llamante.cerrarSesion();
            });
            
            window.setScene(escena);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(PantallaLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

  public interface ComunicacionPPrincipal
  {

        public void cerrarSesion();

        public void cargarPantallaArticulos();

        public void cargarPantallaClientes();

        public void cargarPantallaProveedores();
      
  }
}
