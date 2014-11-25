/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dao.DaoTendenciaPlanVoz;
import Logico.TendenciaPlanVoz;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Juan Olaya O
 */
public class ControladorTendenciaPlanVoz {

    DaoTendenciaPlanVoz daoTendenciaPlanVoz;
    

    public ControladorTendenciaPlanVoz() {

        daoTendenciaPlanVoz = new DaoTendenciaPlanVoz();
    }

    public ArrayList<Integer> getPerfiles(String anioInicio) {
        int enero = 0, febrero = 0, marzo = 0, abril = 0, mayo = 0, junio = 0,
                julio = 0, agosto = 0, septiembre = 0, octubre = 0, noviembre = 0, diciembre = 0;
        int cant2000 = 0, cant2001 = 0, cant2002 = 0, cant2003 = 0, cant2004 = 0, cant2005 = 0,
                cant2006 = 0, cant2007 = 0, cant2008 = 0, cant2009 = 0, cant2010 = 0, cant2011 = 0,
                cant2012 = 0, cant2013 = 0, cant2014 = 0;
        
        ArrayList<Integer> llamadas = new ArrayList();

        TendenciaPlanVoz tendenciaPlanVoz = new TendenciaPlanVoz();
        tendenciaPlanVoz.setAnioInicio(anioInicio);

        String restriccionesClausulaWhere = daoTendenciaPlanVoz.prepararRestriccionesClausulaWherePerfiles(tendenciaPlanVoz);
        ArrayList<String[]> contratos = daoTendenciaPlanVoz.listaPerfiles(restriccionesClausulaWhere);

        if (!anioInicio.equals("Escoger una Opción")) {
            if (!contratos.isEmpty()) {
                for (int i = 0; i < contratos.size(); i++) {
                    int mes = (int) Double.parseDouble(contratos.get(i)[2]);
                    switch (mes) {
                        case 1:
                            enero++;
                            break;
                        case 2:
                            febrero++;
                            break;
                        case 3:
                            marzo++;
                            break;
                        case 4:
                            abril++;
                            break;
                        case 5:
                            mayo++;
                            break;
                        case 6:
                            junio++;
                            break;
                        case 7:
                            julio++;
                            break;
                        case 8:
                            agosto++;
                            break;
                        case 9:
                            septiembre++;
                            break;
                        case 10:
                            octubre++;
                            break;
                        case 11:
                            noviembre++;
                            break;
                        case 12:
                            diciembre++;
                            break;
                        default:
                            System.out.println("Mes desconocido!");
                    }
                }
                llamadas.add(enero);
                llamadas.add(febrero);
                llamadas.add(marzo);
                llamadas.add(abril);
                llamadas.add(mayo);
                llamadas.add(junio);
                llamadas.add(julio);
                llamadas.add(agosto);
                llamadas.add(septiembre);
                llamadas.add(octubre);
                llamadas.add(noviembre);
                llamadas.add(diciembre);

            } else {
                JOptionPane.showMessageDialog(null, "No se encontró tendencia en los años seleccionados");
            }
        } else {
            if (!contratos.isEmpty()) {
                for (int i = 0; i < contratos.size(); i++) {
                    int anio = (int) Double.parseDouble(contratos.get(i)[1]);
                    switch (anio) {
                        case 2000:
                            cant2000++;
                            break;
                        case 2001:
                            cant2001++;
                            break;
                        case 2002:
                            cant2002++;
                            break;
                        case 2003:
                            cant2003++;
                            break;
                        case 2004:
                            cant2004++;
                            break;
                        case 2005:
                            cant2005++;
                            break;
                        case 2006:
                            cant2006++;
                            break;
                        case 2007:
                            cant2007++;
                            break;
                        case 2008:
                            cant2008++;
                            break;
                        case 2009:
                            cant2009++;
                            break;
                        case 2010:
                            cant2010++;
                            break;
                        case 2011:
                            cant2011++;
                            break;
                        case 2012:
                            cant2012++;
                            break;
                        case 2013:
                            cant2013++;
                            break;
                        case 2014:
                            cant2014++;
                            break;
                        default:
                            System.out.println("Anio desconocido!");
                    }
                }
                llamadas.add(cant2000);
                llamadas.add(cant2001);
                llamadas.add(cant2002);
                llamadas.add(cant2003);
                llamadas.add(cant2004);
                llamadas.add(cant2005);
                llamadas.add(cant2006);
                llamadas.add(cant2007);
                llamadas.add(cant2008);
                llamadas.add(cant2009);
                llamadas.add(cant2010);
                llamadas.add(cant2011);
                llamadas.add(cant2012);
                llamadas.add(cant2013);
                llamadas.add(cant2014);

            } else {
                JOptionPane.showMessageDialog(null, "No se encontró tendencia en los años seleccionados");
            }

        }
        return llamadas;
    }

}
