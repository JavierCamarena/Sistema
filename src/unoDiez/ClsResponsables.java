/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoDiez;

/**
 *
 * @author keko
 */
public class ClsResponsables {
    public int idResponsable; 
    public String Nombre; 
    public String Apellido; 
    public String ClaveElectorIfe; 
    public String NumTelefono; 
    public String ZonaGrupo; 
    public String Cargo; 
    public String Email;
    public String Seccion; 
    public boolean Voto;
    public String FolioPadron;
    
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
        ClaveElectorIfe = "";
        NumTelefono = ""; 
        ZonaGrupo = ""; 
        Cargo = ""; 
        Email = ""; 
        Seccion = ""; 
        Voto = false; 
        FolioPadron = "";
        
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
