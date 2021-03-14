package com.mycompany.paqueteerp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase se encarga de toda la parte de comunicación con la base de datos para hacer distintas peticiones y comprobaciones. 
 * @author: Jose Texeira
 */

public class AccesoBD {
    private static Connection conexion=null;
    private static Statement stmt=null;
    
    /**
     * En este método se hace la conexión a la base de datos y se le llama en el momento en el arranca el programa.
     */
    public static void inicializarBD(){
        try {
            conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/dpklzj0pds",
                    "root", "");
            stmt= conexion.createStatement();
            System.out.println("Estas conectado!!");
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /*Login*/
    
    /**
     * Método encargado de hacer la comprobación pasandole a la línea SQL los parámetros que le llegan.
     * @param nombre Es el nombre que escribe el usuario en el programa.
     * @param passwd Es la contraseña que escribe el usuario en el programa.
     * @return El nombre que recoje de la base de datos.
     */
    public static String comprobarUsuario(String nombre, String passwd){
        try {
            
            String sql="SELECT `nombre` FROM login where `nombre`='"+nombre+"' AND `passwd`='"+passwd+"'";
            
            stmt=conexion.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next())
            {
                String nombre_comprobado=rs.getString("nombre");
                System.out.println("Bienvenido!!");
                return nombre_comprobado;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    /*Productos*/
    
    /**
     * En el método se comprueba que el articulo esta en la base de datos.
     * @param cod_articulo Es el código que introduce el usuario para comprobarlo.
     * @return El objeto Producto que se recoje de la base de datos. 
     */
    public static Producto comprobarArticulo(String cod_articulo) {
        try {
            
            String sql="SELECT * FROM articulos where `CodigoArt`='"+cod_articulo+"'";
            
            stmt=conexion.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next())
            {
                Producto p=new Producto(rs.getString("CodigoArt"), rs.getString("DescrArt"), rs.getFloat("CostoArt"), rs.getInt("ImpuestoArt"), rs.getFloat("Margen"), rs.getFloat("PvP"));
                return p;
                
            }
        } catch (SQLException ex) {
            
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
    
    /**
     * Se recoje toda la información que el usuario introduce para guardarla en la base de datos.
     * @param p Se le pasa un objeto Protucto donde se ha estado guardando la información hasta el momento.
     * @return Devuelve un boolean para comprobar que el guardado de la información fue correcta. 
     */
    public static boolean guardarProducto(Producto p) {
         try {
            String cod_art=p.getCodigo_art();
            String descr_art=p.getDescri_art();
            float costo_art=p.getCosto_art();
            int imp_art=p.getImpuesto_art();
            float margen_art=p.getMargen();
            float pvp_art=costo_art+((costo_art*imp_art)/100)+(((costo_art+((costo_art*imp_art)/100))*margen_art)/100);
            String sql="INSERT INTO articulos(`CodigoArt`, `DescrArt`, `CostoArt`, `ImpuestoArt`, `Margen`, `PvP`) values ('"+cod_art+"', '"+descr_art+"','"+costo_art+"','"+imp_art+"','"+margen_art+"','"+pvp_art+"')";
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            
            return false;
        }
    }
    
    /**
     * Se recoje toda la información que el usuario introduce para actualizar un artículo de la base de datos.
     * @param p Se le pasa un objeto Protucto donde se ha estado guardando la información hasta el momento.
     * @return Devuelve un boolean para comprobar que la actualización se completo.
     */
    public static boolean actualizarProducto(Producto p) {
        try {
            String cod_art=p.getCodigo_art();
            String descr_art=p.getDescri_art();
            float costo_art=p.getCosto_art();
            int imp_art=p.getImpuesto_art();
            float margen_art=p.getMargen();
            float pvp_art=costo_art+((costo_art*imp_art)/100)+(((costo_art+((costo_art*imp_art)/100))*margen_art)/100);
            String sql="UPDATE articulos SET DescrArt='"+descr_art+"', `CostoArt`="+costo_art+", `ImpuestoArt`="+imp_art+", `Margen`="+margen_art+", `PvP`="+pvp_art+" WHERE CodigoArt='"+cod_art+"'";
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            
            return false;
        }   
    }
    
    /**
     * Borra el artículo de la base de datos.
     * @param cod_art_borrar Es el código del artículo que desea borrar el usuario
     * @return Devuelve un boolean para comprobar que se completo. 
     */
    public static boolean borrarProducto(String cod_art_borrar) {
        try {
            String sql="DELETE FROM articulos WHERE articulos.CodigoArt = '"+cod_art_borrar+"'";
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    /*Clientes*/
    
    /**
     * Comprueba que el cliente existe en la base de datos con el DNI que inserta el usuario.
     * @param nif_cliente Es el DNI que el usuario quiere comprobar.
     * @return Se devuelve un objeto Cliente
     */
    public static Cliente comprobarCliente(String nif_cliente) {
        try {
            
            String sql="SELECT * FROM cliente where `NIFCliente`='"+nif_cliente+"'";
            
            stmt=conexion.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            if(rs.next())
            {
                Cliente c=new Cliente(rs.getString("NIFCliente"), rs.getString("DomicilioCliente"), rs.getString("NombreCliente"));
                return c;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Se recoje toda la información sobre el cliente que el usuario introduce para guardarla en la base de datos.
     * @param c Se le pasa un objeto Cliente donde se ha estado guardando la información hasta el momento.
     * @return Devuelve un boolean para comprobar que el guardado de la información fue correcta. 
     */
    public static boolean guardarCliente(Cliente c) {
        try {
            String nif_cliente=c.getNIFCliente();
            String nombre_cliente=c.getNombreCliente();
            String domicilio_cliente=c.getDomicilioCliente();
            String sql="INSERT INTO `cliente` (`NIFCliente`, `DomicilioCliente`, `NombreCliente`) VALUES ('"+nif_cliente+"', '"+domicilio_cliente+"', '"+nombre_cliente+"')";
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            
            return false;
        }
    }
    
    /**
     * Se recoje toda la información que el usuario introduce para actualizar cliente de la base de datos.
     * @param c Se le pasa un objeto Cliente donde se ha estado guardando la información hasta el momento.
     * @return Devuelve un boolean para comprobar que la actualización se completo.
     */
    public static boolean actualizarCliente(Cliente c) {
        try {
            String nif_cliente=c.getNIFCliente();
            String nombre_cliente=c.getNombreCliente();
            String domicilio_cliente=c.getDomicilioCliente();
            String sql="UPDATE cliente SET DomicilioCliente = '"+domicilio_cliente+"', NombreCliente='"+nombre_cliente+"' WHERE NIFCliente='"+nif_cliente+"'";
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            
            return false;
        }
    }
    
    /**
     * Borra de la base de datos el cliente que quiere el usuario.
     * @param nif_cliente_borrar Es el DNI del cliente que se quiere borrar.
     * @return Devuelve un boolean para comprobar que se completo. 
     */
    public static boolean borrarCliente(String nif_cliente_borrar) {
        
        try {
            String sql="DELETE FROM cliente WHERE cliente.NIFCliente = '"+nif_cliente_borrar+"'";
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccesoBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}