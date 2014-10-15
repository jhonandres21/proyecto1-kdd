/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package scriptplanvoz;

/**
 *
 * @author Juan Olaya O
 */
public class PlanVoz {
    
    long id_plan;
    String nombre_plan, es_corporativo, tipo_plan;

    public String getTipo_plan() {
        return tipo_plan;
    }

    public void setTipo_plan(String tipo_plan) {
        this.tipo_plan = tipo_plan;
    }
    
    public long getId_plan() {
        return id_plan;
    }

    public void setId_plan(long id_plan) {
        this.id_plan = id_plan;
    }

    public String getNombre_plan() {
        return nombre_plan;
    }

    public void setNombre_plan(String nombre_plan) {
        this.nombre_plan = nombre_plan;
    }
    
    public String getEs_corporativo() {
        return es_corporativo;
    }

    public void setEs_corporativo(String es_corporativo) {
        this.es_corporativo = es_corporativo;
    }
    
}
