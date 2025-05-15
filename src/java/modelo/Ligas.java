/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author gabri
 */
public class Ligas {
    
    private String nombre;
    private int anio;
    private String campeon;
    private String subcampeon;
    private String pichichi;
    private String zamora;

    public Ligas(String nombre, int anio, String campeon, String subcampeon, String pichichi, String zamora) {
        this.nombre = nombre;
        this.anio = anio;
        this.campeon = campeon;
        this.subcampeon = subcampeon;
        this.pichichi = pichichi;
        this.zamora = zamora;
    }

    public int getAnio() {
        return anio;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCampeon() {
        return campeon;
    }

    public String getSubcampeon() {
        return subcampeon;
    }

    public String getPichichi() {
        return pichichi;
    }

    public String getZamora() {
        return zamora;
    }

    @Override
    public String toString() {
        return "Ligas{" + "anio=" + anio + ", nombre=" + nombre + ", campeon=" + campeon + ", subcampeon=" + subcampeon + ", pichichi=" + pichichi + ", zamora=" + zamora + '}';
    }
    
    
    
    
}
