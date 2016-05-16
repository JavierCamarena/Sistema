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
import java.util.ArrayList;

/**
 *
 * @author keko
 */
public class ClsResponsables {
    
    Connection con = null;
    Statement stat = null;
    //---------------DataBase Conf------------------
    
    String dbName;   
    String dbPath;
    String dbUsr;
    String dbPw;
    
    public int idResponsable; 
    public String Nombre; 
    public String Apellido; 
    public String ClaveElectorIne; 
    public String NumTelefono; 
    public String ZonaGrupo; 
    public String Cargo; 
    public String Email;
    
    public ArrayList<ClsCiudadano>votantes;
    
    
    ClsResponsables(String[] conf) 
    {
        dbName = conf[0];
        dbPath = conf[1];
        dbUsr = conf[2];
        dbPw = conf[3];                
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
    
    public int Nuevo()
    {
        int Afected;
        try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
                stat = con.createStatement();
                
                String SQL = "INSERT INTO responsables (Nombre,Apellido,ClaveElectorIne,NumTelefono,ZonaGrupo,Cargo,Email)"
                           + " VALUES (?,?,?,?,?,?,?)";
                
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL,new String[]{"idResponsable"});
                preparedStmt.setString (1, Nombre);
                preparedStmt.setString (2, Apellido);
                preparedStmt.setString (3, ClaveElectorIne);
                preparedStmt.setString (4, NumTelefono);
                preparedStmt.setString (5, ZonaGrupo);
                preparedStmt.setString (6, Cargo);                
                preparedStmt.setString (7, Email);
  

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
        
        return Afected;    
    }
    
    public boolean Actualiza() 
    {
          try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw);
                stat = con.createStatement();
                
                String SQL = "UPDATE responsables SET Nombre = ?,Apellido = ?,ClaveElectorIne = ?,NumTelefono = ?,ZonaGrupo = ?"
                           + ",Cargo = ?,Email = ? WHERE idResponsables = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setString (1, Nombre);
                preparedStmt.setString (2, Apellido);
                preparedStmt.setString (3, ClaveElectorIne);
                preparedStmt.setString (4, NumTelefono);
                preparedStmt.setString (5, ZonaGrupo);
                preparedStmt.setString (6, Cargo);                
                preparedStmt.setString (7, Email);
                preparedStmt.setInt(8, idResponsable);
                
                int retorno = preparedStmt.executeUpdate();
                
            }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            
        }
        
        return true;    
    }
    
    public boolean Elimina() 
    {
         try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw);
                stat = con.createStatement();
                
                String SQL = "DELETE FROM responsables WHERE idResponsables = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setInt (1, idResponsable);
          
                int retorno = preparedStmt.executeUpdate();
            }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            
        }
        
        return true;    
    }
}
