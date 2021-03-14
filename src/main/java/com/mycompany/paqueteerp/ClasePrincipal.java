/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.paqueteerp;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * La ClasePrincipal es el main de este programa donde se centraliza todo la llamada a los distintos métodos.
 * @author: Jose Texeira
 */

public class ClasePrincipal extends Application implements PantallaLogin.ComunicacionLogin, PantallaProductos.ComunicacionPantallaProductos, PantallaPrincipal.ComunicacionPPrincipal, PantallaClientes.ComunicacionClientes{
    static Stage ventana;
    static int id_usuario;
    

    public static void main(String[] args) {
        launch(args);    
    }

    @Override
    public void start(Stage stage) throws Exception {
        ventana=stage;
        ventana.getIcons().add(new Image("https://ehorus.com/wp-content/uploads/2018/10/Que-es-ERP-featured-05.png"));
        /*ventana.getIcons().add(new Image("recursos/logo.png"));*/
        AccesoBD.inicializarBD();
        PantallaLogin.cargarPantallaLogin(ventana, this);
    }
    
    
    
    /*Pantalla Login*/
    
    /**
     * Se encarga de llamar al metodo que comprueba el usuario de la base de datos. Y si es correcto da acceso a la pantalla principal.
     * @param nombre Es el nombre de usuario que introduce el usuario.
     * @param password Es la contraseña que el usuario introduce.
     */
    @Override
    public void comprobarUsuario(String nombre, String password) {
       String nombre_comprobado=AccesoBD.comprobarUsuario(nombre, password);
       if(nombre_comprobado==null)
       {
           PantallaLogin.indicarError();
       }
       else
       {
           PantallaPrincipal.cargarPantallaPrincipal(ventana,this);
           
       }
    }
    
    /*Pantalla Articulos*/
    
    /**
     * Este metodo se encarga de guardar o actualizar el Producto según si ya existe dicho artículo en la base de datos o no.
     * @param p Es el Producto que se quiere guardar o actualizar en la base de datos
     */
    @Override
    public void introducirProducto(Producto p) {
        if(p==null){
            String msg_vacio="Es obligatorio rellenar los campos";
            PantallaProductos.ventanaInformacion(msg_vacio);
            PantallaProductos.mostrarPantalla(ventana, this);
        }else{
            boolean guardado = AccesoBD.guardarProducto(p);
            if(guardado==true){
                String msg_guardado="El articulo se ha guardado correctamente.";
                PantallaProductos.ventanaInformacion(msg_guardado);
                PantallaProductos.vaciarCampos();
            }else{
                
                boolean actualizado=AccesoBD.actualizarProducto(p);
                Producto p_actualizado=AccesoBD.comprobarArticulo(p.getCodigo_art());
                PantallaProductos.mostrarArticulo(p_actualizado);
                if (actualizado==true) {
                    String msg_actualizado="El articulo se ha actualizo correctamente.";
                    PantallaProductos.ventanaInformacion(msg_actualizado);
                }else{
                    String msg_error="Hubo algun fallo, intentelo de nuevo.";
                    PantallaProductos.ventanaInformacion(msg_error);
                }
            }
        }
    }
    /**
     * Comprueba que el articulo que contiene el código de artiulo que el usuario busca existe en la base de datos. Muestra un mensaje de información si existe o no.
     * @param cod_articulo Es el código del artículo que se quiere comprobar.
     */
    @Override
    public void comprobarProducto(String cod_articulo) {
        Producto p=AccesoBD.comprobarArticulo(cod_articulo);
        if (p==null) {
            String msg_comprobado="Este articulo no existe.";
            PantallaProductos.ventanaInformacion(msg_comprobado);
            
        }else{
            PantallaProductos.mostrarArticulo(p);
        }
        
    }
    
    /**
     * Se encarga de comunicarse con la clase AccesoBD para borrar el producto que el usuario desea. Muestra un mensaje de información si ha borrado o no.
     * @param cod_art_borrar Es el código del artículo que se quiere borrar.
     */
    @Override
    public void borrarProducto(String cod_art_borrar) {
        boolean borrado=AccesoBD.borrarProducto(cod_art_borrar);
        if (borrado==true) {
            String msg_borrado="El articulo se ha borrado correctamente.";
            PantallaProductos.ventanaInformacion(msg_borrado);
            PantallaProductos.vaciarCampos();
        }else{
            String msg_error="Hubo algun fallo, intentelo de nuevo.";
            PantallaProductos.ventanaInformacion(msg_error);
        }
    }
    
    /**
     * Vuelve desde la pantalla articulos a la pantalla principal.
     */
    @Override
    public void volverPrincipal() {
        PantallaPrincipal.cargarPantallaPrincipal(ventana,this);
    }
    
    
    /*Pantalla Principal*/
    
    /**
     * Es llamada desde la pantalla principal para ir a la pantalla de articulos.
     */
    @Override
    public void cargarPantallaArticulos() {
        PantallaProductos.mostrarPantalla(ventana, this);
    }
    
    /**
     * Es llamada desde la pantalla principal para ir a la pantalla de clientes.
     */
    @Override
    public void cargarPantallaClientes() {
        PantallaClientes.cargarPantalla(ventana, this);
    }
    
    /**
     * 
     */
    @Override
    public void cargarPantallaProveedores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Cierra la sesión del usuario que se logueo al principio.
     */
    @Override
    public void cerrarSesion() {
        PantallaLogin.cargarPantallaLogin(ventana, this);
    }
    
    /*Pantalla clientes*/
    
    /**
     * Vuelve desde la pantalla de clientes hasta la pantalla principal.
     */
    @Override
    public void volverPPrincipal() {
        PantallaPrincipal.cargarPantallaPrincipal(ventana,this);
    }
    
    
    /**
     * Comprueba si existe el cliente en la base de datos y muestra un mensaje de información al usuario.
     * @param nif_cliente Es el DNI que el usuario quiere comprobar.
     */
    @Override
    public void comprobarCliente(String nif_cliente) {
        Cliente c=AccesoBD.comprobarCliente(nif_cliente);
        if (c==null) {
            String msg_comprobado="Este cliente no existe.";
            PantallaClientes.ventanaInformacion(msg_comprobado);
            PantallaClientes.focus();
        }else{
            PantallaClientes.mostrarCliente(c);
        }
    }
    
    
    /**
     * Este metodo se encarga de guardar o actualizar el Cliente según si ya existe dicho cliente en la base de datos o no.
     * @param c Es el Cliente que se quiere guardar o actualizar en la base de datos
     */
    @Override
    public void guardarCliente(Cliente c) {
        if(c==null){
            String msg_vacio="Es obligatorio rellenar los campos";
            PantallaClientes.ventanaInformacion(msg_vacio);
            PantallaClientes.cargarPantalla(ventana, this);
        }else{
            boolean guardado = AccesoBD.guardarCliente(c);
            if(guardado==true){
                String msg_guardado="El cliente se ha guardado correctamente.";
                PantallaClientes.ventanaInformacion(msg_guardado);
                PantallaClientes.vaciarCampos();
                PantallaClientes.focusNIF();
            }else{
                
                boolean actualizado=AccesoBD.actualizarCliente(c);
                Cliente c_actualizado=AccesoBD.comprobarCliente(c.getNIFCliente());
                PantallaClientes.mostrarCliente(c_actualizado);
                if (actualizado==true) {
                    String msg_actualizado="El cliente se ha actualizo correctamente.";
                    PantallaClientes.ventanaInformacion(msg_actualizado);
                }else{
                    String msg_error="Hubo algun fallo, intentelo de nuevo.";
                    PantallaClientes.ventanaInformacion(msg_error);
                }
            }
        }
    }
    
    
    /**
     * Se encarga de llamar al metodo de borrarCliente de la clase AccesoBD. Dependiendo si se ha completado se muestra un mensaje.
     * @param nif_cliente_borrar Es el DNI que escribe el usuario en el programa.
     */
    @Override
    public void borrarCliente(String nif_cliente_borrar) {
        boolean borrado=AccesoBD.borrarCliente(nif_cliente_borrar);
        if (borrado==true) {
            String msg_borrado="El cliente se ha borrado correctamente.";
            PantallaClientes.ventanaInformacion(msg_borrado);
            PantallaClientes.vaciarCampos();
        }else{
            String msg_error="Hubo algun fallo, intentelo de nuevo.";
            PantallaClientes.ventanaInformacion(msg_error);
        }
    }
    
}
