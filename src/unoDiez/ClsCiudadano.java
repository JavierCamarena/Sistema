/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unoDiez;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author EsGis02
 */
public class ClsCiudadano {
    
    Connection con = null;
    Statement stat = null;
    //---------------DataBase Conf------------------
    
    String dbName;   
    String dbPath;
    String dbUsr;
    String dbPw;
    
    
    public int idCiudadano;
    public int idResponsable;
    public String Nombres;
    public String Apellidos;
    public String ClaveIne;
    public String Mail;
    public String Telefono;
    public String Seccion;
    public boolean Voto;
    public String FolioPadron;

    public ClsCiudadano(String[] conf ) {
        dbName = conf[0];
        dbPath = conf[1];
        dbUsr = conf[2];
        dbPw = conf[3]; 
    }
    
    public void Limpia() 
    {
        idCiudadano = 0 ; 
        idResponsable = 0 ; 
        Nombres = ""; 
        Apellidos = ""; 
        ClaveIne = ""; 
        Mail = "" ; 
        Telefono = ""; 
        Seccion = ""; 
        Voto = false ; 
        FolioPadron = "";
    }
    
    public int Nuevo() 
    {
        int Afected;
        try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
                stat = con.createStatement();
                
                String SQL = "INSERT INTO responsables (Nombre,Apellido,ClaveIne,Email,Telefono,Voto,FolioPadron,Seccion,idResponsable)"
                           + " VALUES (?,?,?,?,?,?,?,?,?)";
                
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL,new String[]{"idResponsable"});
                preparedStmt.setString (1, Nombres);
                preparedStmt.setString (2, Apellidos);
                preparedStmt.setString (3, ClaveIne);
                preparedStmt.setString (4, Mail);
                preparedStmt.setString (5, Telefono);
                preparedStmt.setBoolean(6, Voto);                
                preparedStmt.setString (7, FolioPadron);
                preparedStmt.setString (8, Seccion);                
                preparedStmt.setInt(9, idResponsable);
                 

                preparedStmt.executeUpdate();
                ResultSet rs = preparedStmt.getGeneratedKeys();
                
                if (rs.next()) {
                    Afected = rs.getInt(1);
                 } 
                else 
                {
                    return -1;
                }
       
                
            }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
            return -1;
        } finally {
            
        }
        idCiudadano = Afected;
        return Afected;    
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
