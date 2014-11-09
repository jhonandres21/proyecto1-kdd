
package Controlador;

import Dao.DaoPerfilAbandonoColmovil;

public class ControlAbandonoColmovil {
    
    DaoPerfilAbandonoColmovil daoPerfilAbandonanColmovil;

    public ControlAbandonoColmovil() {
        daoPerfilAbandonanColmovil = new DaoPerfilAbandonoColmovil();
    }
    
    public void consulta(int numDocIdCandidato, String nombre_Proceso/*Paramatros dinamicos*/) {

    }

    public void desconectar() {
        daoPerfilAbandonanColmovil.desconectar();
    }
}
