
package Logico;

/**
 *
 * @author johnmm
 */
public class Simcard {
    
    String numeroSerie = "", numeroTelefono="";
    int id_sim_card = 0;

    public int getId_sim_card() {
        return id_sim_card;
    }

    public void setId_sim_card(int id_sim_card) {
        this.id_sim_card = id_sim_card;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

}
