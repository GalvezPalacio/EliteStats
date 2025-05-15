/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gabri
 */
public class Conexion {

    // atributos
    private static final String URL = "jdbc:mysql://localhost:3306/ligas";
    private static final String USER = "USUARIO";  // Escribe aquí tu usuario
    private static final String PASS = "CONTRASEÑA";  // Escribe aquí tu contraseña

    // constructor
    public Conexion() {

    }

    public static Connection obtenerConexion() {

        Connection conexion = null;
        
        try {
            

            // 1 cargar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2 establecemos la conexion
            conexion = DriverManager.getConnection(URL, USER, PASS);

            // retornamos la conexion
            System.out.println("Conectado");
            return conexion;
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }
    
    public static void cerrarConexion(Connection conexion){
        
        if(conexion != null){
            try {
                conexion.close();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
