/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import com.mysql.cj.jdbc.PreparedStatementWrapper;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Conexion;
import modelo.Ligas;

/**
 *
 * @author gabri
 */
public class Consultas {

    public static String realizarConsultas(String liga, String anio, String dato) {

        String resultado = "";
        String tabla = "";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = Conexion.obtenerConexion();

            String columna = "";

            switch (dato) {
                case "Campeón":
                    columna = "campeon";
                    break;
                case "Subcampeón":
                    columna = "subcampeon";
                    break;
                case "Pichichi":
                    columna = "pichichi";
                    break;
                case "Zamora":
                    columna = "zamora";
                    break;
                default:
                    return "Dato no válido";
            }

            switch (liga) {
                case "Liga EA (1929-Act)":
                    tabla = "liga_ea";
                    break;
                case "Premier League (1889-Act)":
                    tabla = "premier_league";
                    break;
                case "Serie A (1898-Act)":
                    tabla = "serie_A";
                    break;
                case "Bundesliga (1964-Act)":
                    tabla = "bundesliga";
                    break;
                case "Ligue 1 (1933-Act)":
                    tabla = "ligue_1";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Liga no válida.");
                    return "Error";
            }

            String sql = "SELECT " + columna + " FROM " + tabla + " WHERE anio = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, anio);

            rs = ps.executeQuery();

            if (rs.next()) {
                resultado = rs.getString(1);
            } else {
                resultado = "No se encontró información.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Conexion.cerrarConexion(conn);
        }
        return resultado;

    }

    public static Ligas mostrarFilaEnTabla(String liga, String anio) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String tabla = "";

        switch (liga) {
            case "Liga EA (1929-Act)":
                tabla = "liga_ea";
                break;
            case "Premier League (1889-Act)":
                tabla = "premier_league";
                break;
            case "Serie A (1898-Act)":
                tabla = "serie_a";
                break;
            case "Bundesliga (1964-Act)":
                tabla = "bundesliga";
                break;
            case "Ligue 1 (1933-Act)":
                tabla = "ligue_1";
                break;
            default:
                return null;
        }

        try {
            conn = Conexion.obtenerConexion();
            String sql = "SELECT * FROM " + tabla + " WHERE anio = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, anio);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Ligas(
                        liga,
                        rs.getInt("anio"),
                        rs.getString("campeon"),
                        rs.getString("subcampeon"),
                        rs.getString("pichichi"),
                        rs.getString("zamora")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion(conn);
        }

        return null; // si no hay datos
    }

}
