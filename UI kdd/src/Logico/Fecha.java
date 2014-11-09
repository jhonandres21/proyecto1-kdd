/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logico;

import java.sql.Date;

/**
 *
 * @author Luisfemm
 */
public class Fecha {
  Date fecha, inicio_de_mes, fin_de_mes;
  int anio, mes, dia, dia_de_anio;
  String nombre_mes, nombre_dia, mes_del_anio, festivos_colombia,
          semana_del_anio, fin_de_semana, periodo;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getInicio_de_mes() {
        return inicio_de_mes;
    }

    public void setInicio_de_mes(Date inicio_de_mes) {
        this.inicio_de_mes = inicio_de_mes;
    }

    public Date getFin_de_mes() {
        return fin_de_mes;
    }

    public void setFin_de_mes(Date fin_de_mes) {
        this.fin_de_mes = fin_de_mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getDia_de_anio() {
        return dia_de_anio;
    }

    public void setDia_de_anio(int dia_de_anio) {
        this.dia_de_anio = dia_de_anio;
    }

    public String getNombre_mes() {
        return nombre_mes;
    }

    public void setNombre_mes(String nombre_mes) {
        this.nombre_mes = nombre_mes;
    }

    public String getNombre_dia() {
        return nombre_dia;
    }

    public void setNombre_dia(String nombre_dia) {
        this.nombre_dia = nombre_dia;
    }

    public String getMes_del_anio() {
        return mes_del_anio;
    }

    public void setMes_del_anio(String mes_del_anio) {
        this.mes_del_anio = mes_del_anio;
    }

    public String getFestivos_colombia() {
        return festivos_colombia;
    }

    public void setFestivos_colombia(String festivos_colombia) {
        this.festivos_colombia = festivos_colombia;
    }

    public String getSemana_del_anio() {
        return semana_del_anio;
    }

    public void setSemana_del_anio(String semana_del_anio) {
        this.semana_del_anio = semana_del_anio;
    }

    public String getFin_de_semana() {
        return fin_de_semana;
    }

    public void setFin_de_semana(String fin_de_semana) {
        this.fin_de_semana = fin_de_semana;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
  
  
}
