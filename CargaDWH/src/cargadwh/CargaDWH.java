/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cargadwh;

import DAO.ScriptClientes;
import DAO.ScriptDemografia;
import DAO.ScriptEquipo;
import DAO.ScriptLlamada;
import DAO.ScriptLocalizacion;
import DAO.ScriptOficina;
import DAO.ScriptPlanDatos;
import DAO.ScriptPlanVoz;
import DAO.ScriptRetiro;
import DAO.ScriptVentas;
import DAO.ScriptSimCard;

/**
 *
 * @author Juan Olaya O
 */
public class CargaDWH {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //ScriptClientes
        ScriptClientes clientes = new ScriptClientes();

        System.out.println("-Inicia Extraccion de Clientes");
        clientes.extraerDatos();
        System.out.println("*Termina Extraccion de Clientes");

        System.out.println("--Inicia Carga de Clientes");
        clientes.cargarDatos();
        System.out.println("**Termina Carga de Clientes");

        //ScriptLocalizacion
        ScriptLocalizacion localizacion = new ScriptLocalizacion();
        System.out.println("--Inicia Carga de Localizacion");
        localizacion.cargarSentenciaSQL();
        System.out.println("**Termina Carga de Localizacion");
        
        //ScriptOficina
        ScriptOficina oficina = new ScriptOficina();

        System.out.println("-Inicia Extraccion de Oficina");
        oficina.extraerDatos();
        System.out.println("*Termina Extraccion de Oficina");

        System.out.println("--Inicia Carga de Oficina");
        oficina.cargarDatos();
        System.out.println("**Termina Carga de Oficina");
                    
        //ScriptEquipo
        ScriptEquipo equipo = new ScriptEquipo();

        System.out.println("-Inicia Extraccion de Equipo");
        equipo.extraerDatos();
        System.out.println("*Termina Extraccion de Equipo");

        System.out.println("--Inicia Carga de Equipo");
        equipo.cargarDatos();
        System.out.println("**Termina Carga de Equipo");

        //ScriptPlanVoz
        ScriptPlanVoz planvoz = new ScriptPlanVoz();

        System.out.println("-Inicia Extraccion de PlanVoz");
        planvoz.extraerDatos();
        System.out.println("*Termina Extraccion de PlanVoz");

        System.out.println("--Inicia Carga de PlanVoz");
        planvoz.cargarDatos();
        System.out.println("**Termina Carga de PlanVoz");
        
        //ScriptPlanDatos
        ScriptPlanDatos plandatos = new ScriptPlanDatos();

        System.out.println("-Inicia Extraccion de PlanDatos");
        plandatos.extraerDatos();
        System.out.println("*Termina Extraccion de PlanDatos");

        System.out.println("--Inicia Carga de PlanDatos");
        plandatos.cargarDatos();
        System.out.println("**Termina Carga de PlanDatos");

        //ScriptSimCard
        ScriptSimCard simcard = new ScriptSimCard();

        System.out.println("-Inicia Extraccion de SimCard");
        simcard.extraerDatos();
        System.out.println("*Termina Extraccion de SimCard");

        System.out.println("--Inicia Carga de SimCard");
        simcard.cargarDatos();
        System.out.println("**Termina Carga de SimCard");

        //ScriptDemografia
        ScriptDemografia demografia = new ScriptDemografia();
        System.out.println("--Inicia Carga de Demografia");
        demografia.cargarSentenciaSQL();
        System.out.println("**Termina Carga de Demografia");

        //ScriptRetiro
        ScriptRetiro script = new ScriptRetiro();

        System.out.println("-Inicia Extraccion de Retiro");
        script.extraerDatos();
        System.out.println("*Termina Extraccion de Retiro");

        System.out.println("--Inicia Carga de Retiro");
        script.cargarDatos();
        System.out.println("**Termina Carga de Retiro");

        //ScriptLlamadas
        ScriptLlamada llamada = new ScriptLlamada();
        System.out.println("--Inicia Carga de Llamadas");
        llamada.cargarSentenciaSQL();
        System.out.println("**Termina Carga de Llamadas");
        
        //ScriptVentas
        ScriptVentas ventas = new ScriptVentas();

        System.out.println("-Inicia Extraccion de Ventas");
        ventas.extraerDatos();
        System.out.println("*Termina Extraccion de Ventas");

        System.out.println("--Inicia Carga de Ventas");
        ventas.cargarDatos();
        System.out.println("**Termina Carga de Ventas");
        
    }

}
