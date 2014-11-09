
package Controlador;

import Dao.DaoPerfilAbandonoColmovil;

public class ControlAbandonoColmovil {
    
     DaoPerfilAbandonoColmovil daoPerfilAbandonanColmovil;

    public ControlAbandonoColmovil() {
        daoPerfilAbandonanColmovil = new DaoPerfilAbandonoColmovil();
    }

    public void getPerfiles() {

        daoPerfilAbandonanColmovil.listaPerfiles();
    }
}
