/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoDiez;

/**
 *
 * @author EsGis02
 */
public class ClsCiudadano {
    public int idCiudadano;
    public int idResponsable;
    public String nombres;
    public String apellidos;
    public String claveIne;
    public String mail;
    public String telefono;
    public String seccion;
    public boolean voto;
    public String folioPadron;
    
    public ClsCiudadano(){
        voto = false;
    }
    
    public void limpia(){
        idCiudadano     = 0;
        idResponsable   = 0;
        nombres         = "";
        apellidos       = "";
        claveIne        = "";
        mail            = "";
        telefono        = "";
        seccion         = "";
        voto            = false;
        folioPadron     = "";
    }
}
