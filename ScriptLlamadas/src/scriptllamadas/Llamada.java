/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scriptllamadas;

/**
 *
 * @author Luisfemm
 */
public class Llamada {
    
    long fecha, tiempo, cliente, demografia, sim_card, plan_voz;
    int duracion_llamada;
    String nombre_operador, flag_roaming;

    public String getFlag_roaming() {
        return flag_roaming;
    }

    public void setFlag_roaming(String flag_roaming) {
        this.flag_roaming = flag_roaming;
    }
    
    public long getPlan_voz() {
        return plan_voz;
    }

    public void setPlan_voz(long plan_voz) {
        this.plan_voz = plan_voz;
    }

    public int getDuracion_llamada() {
        return duracion_llamada;
    }

    public void setDuracion_llamada(int duracion_llamada) {
        this.duracion_llamada = duracion_llamada;
    }
    

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }

    public long getCliente() {
        return cliente;
    }

    public void setCliente(long cliente) {
        this.cliente = cliente;
    }

    public long getDemografia() {
        return demografia;
    }

    public void setDemografia(long demografia) {
        this.demografia = demografia;
    }

    public long getSim_card() {
        return sim_card;
    }

    public void setSim_card(long sim_card) {
        this.sim_card = sim_card;
    }

    public String getNombre_operador() {
        return nombre_operador;
    }

    public void setNombre_operador(String nombre_operador) {
        this.nombre_operador = nombre_operador;
    }
    
}
