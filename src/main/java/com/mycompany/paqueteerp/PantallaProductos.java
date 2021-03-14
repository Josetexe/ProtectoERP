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
 * Esta clase se encarga de cargar todo lo relacionado con la pantalla  de productos.
 * @author Jose Texeira
 */
public class PantallaProductos {
    static Button btn_guardar, btn_salir, btn_borrar;
    static TextField tf_cod_art, tf_descripcion, tf_costo, tf_impuestos, tf_margen, tf_pvp;
    static String cod_articulo;
    
    /**
     * El método se encaga de manejar todos los componentes de la pantalla y de las accciones del boton que contiene. 
     * @param window Se le pasa el stage donde se cargan todos los componentes de la pantalla.
     * @param llamante Se le pasa la interface para que se pueda comunicar con la ClasePrincipal.
     */
    static void mostrarPantalla(Stage ventana, ComunicacionPantallaProductos llamante) {
        try {
            File f=new File("recursos/pantalla_art.fxml");
            URL url_pantala_intro_productos=f.toURI().toURL();
            Parent root=FXMLLoader.load(url_pantala_intro_productos);
            Scene escena=new Scene(root);
            ventana.setTitle("Articulos");
            /*Componentes de la pantalla*/
            tf_cod_art=(TextField)escena.lookup("#tf_cod_art");
            tf_descripcion=(TextField)escena.lookup("#tf_descripcion");
            tf_costo=(TextField)escena.lookup("#tf_costo");
            tf_impuestos=(TextField)escena.lookup("#tf_impuestos");
            tf_margen=(TextField)escena.lookup("#tf_margen");
            tf_pvp=(TextField)escena.lookup("#tf_pvp");
            btn_salir=(Button)escena.lookup("#btn_salir");
            btn_guardar=(Button)escena.lookup("#btn_guardar");
            btn_borrar=(Button)escena.lookup("#btn_borrar");
            
            /*Acciones de los componentes*/
            
            tf_cod_art.setOnKeyPressed((KeyEvent t) -> {
                if (t.getCode() == KeyCode.ENTER){
                    tf_descripcion.setText("");
                    tf_costo.setText("");
                    tf_impuestos.setText("");
                    tf_margen.setText("");
                    tf_pvp.setText("");
                    cod_articulo=tf_cod_art.getText().toUpperCase();
                    if("".equals(cod_articulo)){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Error");
                        alert.setContentText("Introduzca un código de artículo válido.");
                        alert.showAndWait();
                    }else{
                        llamante.comprobarProducto(cod_articulo);
                    }             
                }
            });
            
            btn_salir.setOnAction((ActionEvent t) -> {
                llamante.volverPrincipal();
            });
            
            btn_guardar.setOnAction((ActionEvent t) -> {
                String cod_art=tf_cod_art.getText();
                String descr_art=tf_descripcion.getText();
                float costo_art=Float.parseFloat(tf_costo.getText());
                int imp_art=Integer.parseInt(tf_impuestos.getText());
                float margen_art=Float.parseFloat(tf_margen.getText());
                float pvp_art=0;
                Producto p = new Producto(cod_art, descr_art, costo_art, imp_art, margen_art, pvp_art);
                llamante.introducirProducto(p);
            });
            
            btn_borrar.setOnAction((ActionEvent t) -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Confirmación");
                alert.setContentText("¿Estas seguro de confirmar la acción?");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    String cod_art_borrar=tf_cod_art.getText();
                    llamante.borrarProducto(cod_art_borrar);
                }
            });
            
            ventana.setScene(escena);
            ventana.show();
        } catch (IOException ex) {
            Logger.getLogger(PantallaProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    static void mostrarArticulo(Producto p) {
        tf_cod_art.setText(p.getCodigo_art());
        tf_descripcion.setText(p.getDescri_art());
        tf_costo.setText(String.valueOf(p.getCosto_art()));
        tf_impuestos.setText(String.valueOf(p.getImpuesto_art()));
        tf_margen.setText(String.valueOf(p.getMargen()));
        tf_pvp.setText(String.valueOf(p.getPvp()));
    }

    static void ventanaInformacion(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    static void vaciarCampos() {
        tf_cod_art.setText("");
        tf_descripcion.setText("");
        tf_costo.setText("");
        tf_impuestos.setText("");
        tf_margen.setText("");
        tf_pvp.setText("");
    }

    
    public interface ComunicacionPantallaProductos{
        
        public void comprobarProducto(String cod_articulo);

        public void volverPrincipal();

        public void introducirProducto(Producto p);
        
        public void borrarProducto(String cod_art_borrar);
        
    }
}