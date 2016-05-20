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
    public String Seccion; 
    public String Cargo; 
    public String Email;
    public String Colonia;
    
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
        Seccion = ""; 
        Cargo = ""; 
        Email = "";  
        Colonia = "";
    }
    
    public int Nuevo()
    {
        int Afected;
        try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
                stat = con.createStatement();
                
                String SQL = "INSERT INTO responsables (Nombre,Apellido,ClaveElectorIne,NumTelefono,Seccion,Cargo,Email,Colonia)"
                           + " VALUES (?,?,?,?,?,?,?,?)";
                
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL,new String[]{"idResponsable"});
                preparedStmt.setString (1, Nombre);
                preparedStmt.setString (2, Apellido);
                preparedStmt.setString (3, ClaveElectorIne);
                preparedStmt.setString (4, NumTelefono);
                preparedStmt.setString (5, Seccion);
                preparedStmt.setString (6, Cargo);                
                preparedStmt.setString (7, Email);                
                preparedStmt.setString (8, Colonia);
  

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
                
                String SQL = "UPDATE responsables SET Nombre = ?,Apellido = ?,ClaveElectorIne = ?,NumTelefono = ?,Seccion = ?"
                           + ",Cargo = ?,Email = ?, Colonia = ? WHERE idResponsables = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setString (1, Nombre);
                preparedStmt.setString (2, Apellido);
                preparedStmt.setString (3, ClaveElectorIne);
                preparedStmt.setString (4, NumTelefono);
                preparedStmt.setString (5, Seccion);
                preparedStmt.setString (6, Cargo);                
                preparedStmt.setString (7, Email);
                preparedStmt.setString (8, Colonia);
                preparedStmt.setInt(9, idResponsable);
                
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
        eliminaCiudadanos();
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
    
    public boolean eliminaCiudadanos(){
                 try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw);
                stat = con.createStatement();
                
                
                String SQL = "DELETE FROM ciudadanos WHERE idResponsable = ?";
                
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
    
    public boolean Busca() 
    {
        
         try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw);
                stat = con.createStatement();
                
                String SQL = "SELECT * FROM responsables WHERE idResponsables = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setInt(1,idResponsable);
                
                ResultSet rs = preparedStmt.executeQuery();
                
                System.out.println("Buscando id = "+ idResponsable);
                
                if(rs.next())
                {
                    idResponsable = rs.getInt("idResponsables");
                    Nombre = rs.getString("Nombre");
                    Apellido = rs.getString("Apellido");
                    ClaveElectorIne = rs.getString("ClaveElectorIne");
                    NumTelefono = rs.getString("NumTelefono"); 
                    Seccion = rs.getString("Seccion"); 
                    Cargo = rs.getString("Cargo"); 
                    Email = rs.getString("Email");
                    Colonia = rs.getString("Colonia");
                }
                else
                {
                    System.out.println("No encontro nada :c");
                    return false ;
                }
                
            }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            
        }
        
        return true;    
    }
}
