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
    public String Colonia;
    public int Casilla;

    public ClsCiudadano(String[] conf ) {
        dbName = conf[0];
        dbPath = conf[1];
        dbUsr = conf[2];
        dbPw = conf[3]; 
        Voto = false;
        Limpia();
        
    }
    
    public boolean Busca(int id)
    {
     
                try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw);
                stat = con.createStatement();
                
                String SQL = "SELECT * FROM ciudadanos WHERE idciudadanos = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setInt(1,id);
                
                ResultSet rs = preparedStmt.executeQuery();
                
                System.out.println("Buscando id = "+ id);
                
                if(rs.next())
                {
                    idCiudadano   = rs.getInt("idciudadanos");
                    Nombres       = rs.getString("Nombres");
                    Apellidos     = rs.getString("Apellidos");
                    ClaveIne      = rs.getString("ClaveIne"); 
                    Mail          = rs.getString("Email"); 
                    Telefono      = rs.getString("Telefono"); 
                    Voto          = rs.getBoolean("Voto");
                    FolioPadron   = rs.getString("FolioPadron"); 
                    Seccion       = rs.getString("Seccion"); 
                    idResponsable = Integer.parseInt(rs.getString("idResponsable")); 
                    Colonia       = rs.getString("Colonia");
                    Casilla       = rs.getInt("Casilla");
                    System.out.println("Casilla encontrada "+Casilla);
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
    
    public void Limpia() 
    {
        idCiudadano = 0 ; 
        //idResponsable = 0 ; 
        Nombres = ""; 
        Apellidos = ""; 
        ClaveIne = ""; 
        Mail = "" ; 
        Telefono = ""; 
        Seccion = ""; 
        Voto = false ; 
        FolioPadron = "";
        Colonia = "";
        Casilla =0;
    }
    
    public int Nuevo() 
    {
        int Afected;
        try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
                stat = con.createStatement();
                
                String SQL = "INSERT INTO ciudadanos (Nombres,Apellidos,ClaveIne,Email,Telefono,Voto,FolioPadron,Seccion,Colonia,idResponsable,Casilla)"
                           + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL,new String[]{"idCiudadano"});
                preparedStmt.setString (1, Nombres);
                preparedStmt.setString (2, Apellidos);
                preparedStmt.setString (3, ClaveIne);
                preparedStmt.setString (4, Mail);
                preparedStmt.setString (5, Telefono);
                preparedStmt.setBoolean(6, Voto);                
                preparedStmt.setString (7, FolioPadron);
                preparedStmt.setString (8, Seccion);                
                preparedStmt.setString (9, Colonia);                
                preparedStmt.setInt(10, idResponsable);
                preparedStmt.setInt(11, Casilla);
                 

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
          try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw);
                stat = con.createStatement();
                
                String SQL = "UPDATE ciudadanos SET Nombres = ?,Apellidos = ?,ClaveIne = ?,Email = ?, Telefono = ?,FolioPadron = ?"
                           + ",Seccion = ?,Colonia = ?, Casilla = ? WHERE idciudadanos = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setString (1, Nombres);
                preparedStmt.setString (2, Apellidos);
                preparedStmt.setString (3, ClaveIne);
                preparedStmt.setString (4, Mail);
                preparedStmt.setString (5, Telefono);
                preparedStmt.setString (6, FolioPadron);                
                preparedStmt.setString (7, Seccion);                
                preparedStmt.setString (8, Colonia);
                preparedStmt.setInt(9, Casilla);
                preparedStmt.setInt(10, idCiudadano);
                
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
                
                String SQL = "DELETE FROM ciudadanos WHERE idciudadanos = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setInt (1, idCiudadano);
          
                int retorno = preparedStmt.executeUpdate();
            }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            
        }
        
        return true;    
    }
    
    public boolean Votacion(boolean A) 
    {
              try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw);
                stat = con.createStatement();
                
                String SQL = "UPDATE ciudadanos SET Voto = ? WHERE idciudadanos = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setBoolean(1, A);
                preparedStmt.setInt(2,idCiudadano);
                System.out.println(A);
                int retorno = preparedStmt.executeUpdate();
                
            }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            
        }
        
        return true;    
    }
}
