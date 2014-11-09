/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logico;

import java.sql.Time;

/**
 *
 * @author Luisfemm
 */
public class Tiempo {
    Time tiempo;
    int minuto, hora;
    String cuarto_de_hora, dia_noche, periodo_del_dia;

    public Time getTiempo() {
        return tiempo;
    }

    public void setTiempo(Time tiempo) {
        this.tiempo = tiempo;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getCuarto_de_hora() {
        return cuarto_de_hora;
    }

    public void setCuarto_de_hora(String cuarto_de_hora) {
        this.cuarto_de_hora = cuarto_de_hora;
    }

    public String getDia_noche() {
        return dia_noche;
    }

    public void setDia_noche(String dia_noche) {
        this.dia_noche = dia_noche;
    }

    public String getPeriodo_del_dia() {
        return periodo_del_dia;
    }

    public void setPeriodo_del_dia(String periodo_del_dia) {
        this.periodo_del_dia = periodo_del_dia;
    }
    
     
}
