/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logico;

/**
 *
 * @author eallanosm
 */
public class Equipo {
    long id_equipo;

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    int precio;
    String marca, modelo;

    public long getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(long id_equipo) {
        this.id_equipo = id_equipo;
    }    

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}
