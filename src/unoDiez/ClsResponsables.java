/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoDiez;

import java.util.ArrayList;

/**
 *
 * @author keko
 */
public class ClsResponsables {
    public int idResponsable; 
    public String Nombre; 
    public String Apellido; 
    public String ClaveElectorIne; 
    public String NumTelefono; 
    public String ZonaGrupo; 
    public String Cargo; 
    public String Email;
    public ArrayList<ClsCiudadano>votantes;
    public String Configuracion[];

    
    ClsResponsables(String[] conf) 
    {
        conf = Configuracion;
                
    }
    
    public void Limpia() 
    {
        idResponsable = 0 ; 
        Nombre = ""; 
        Apellido = ""; 
        ClaveElectorIne = "";
        NumTelefono = ""; 
        ZonaGrupo = ""; 
        Cargo = ""; 
        Email = "";  
        
    }
    
    public boolean Nuevo()
    {
        return true;
    }
    
    public boolean Actualiza() 
    {
        return true;
    }
    
    public boolean Elimina() 
    {
        return true;
    }
}
