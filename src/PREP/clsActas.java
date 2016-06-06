/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PREP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author keko
 */
public class clsActas {
    
    Connection con = null;
    Statement stat = null;
    //---------------DataBase Conf------------------
    
    String dbName;   
    String dbPath;
    String dbUsr;
    String dbPw;
    
    public int idActas;
    public String RepresentanteGeneral;
    public String Ruta;
    public String Seccion;
    public int Casilla; 
    public int PAN;
    public int PRI; 
    public int PRD;
    public int MORENA; 
    public int VERDE; 
    public int PANAL; 
    public int PAN_PRD;
    public int PRI_VERDE_PANAL;
    
    
    public clsActas(String[] conf)
    {
        dbName = conf[0];
        dbPath = conf[1];
        dbUsr = conf[2];
        dbPw = conf[3];
        Limpia();
    }
    
    public void Limpia() 
    {
        idActas = 0; 
        RepresentanteGeneral= ""; 
        Ruta =""; 
        Seccion = "";
        Casilla =0; 
        PAN =0; 
        PRI = 0; 
        PRD = 0; 
        MORENA = 0; 
        VERDE = 0; 
        PANAL = 0;
        PAN_PRD = 0;
        PRI_VERDE_PANAL = 0;
    }
    
        public int Nuevo() 
    {
        int Afected;
        try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
                stat = con.createStatement();
                
                String SQL = "INSERT INTO actas (RepresentanteGeneral,Ruta,Seccion,Casilla,PAN,PRI,PRD,MORENA,VERDE,PANAL,PAN_PRD,PRI_VERDE_PANAL)"
                           + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL,new String[]{"idActas"});
                preparedStmt.setString(1, RepresentanteGeneral);
                preparedStmt.setString(2, Ruta);
                preparedStmt.setString(3, Seccion);
                preparedStmt.setInt(4, Casilla);
                preparedStmt.setInt(5, PAN);
                preparedStmt.setInt(6, PRI);                
                preparedStmt.setInt(7, PRD);
                preparedStmt.setInt(8, MORENA);                
                preparedStmt.setInt(9, VERDE);                
                preparedStmt.setInt(10, PANAL);
                preparedStmt.setInt(11, PAN_PRD);                
                preparedStmt.setInt(12, PRI_VERDE_PANAL);
                 

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
        idActas = Afected;
        return Afected;    
    }
        
    public boolean Elimina() 
    {
         try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw);
                stat = con.createStatement();
                
                String SQL = "DELETE FROM actas WHERE idActas = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setInt (1, idActas);
          
                int retorno = preparedStmt.executeUpdate();
            }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            
            try { if (stat != null) stat.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
            try { if (con != null) con.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
        }
        
        return true;    
    }    
    
    
    
    
}
