/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sist;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author keko
 */
public class clsAspirantesResponsables {
    
    Connection con = null;
    Statement stat = null;
    
    public int idaspirante;
    public String Nombre;
    public String Apellido; 
    public String Calle; 
    public String Numero; 
    public String Colonia; 
    public String Agencia; 
    public String Seccion; 
    public String SenasParticulares; 
    public String Telefono;
    public String Email; 
    public boolean barda; 
    public boolean lona; 
    public boolean banderin; 
    public boolean reunion; 
    public boolean gestion; 
    public String PSocial; 
    public String PInfraestructura; 
    public Date FechaReunion;
    public String Observaciones; 
    
    public void clsAspirantesResponsables() 
    {
        
    }
    
    public void Limpia()
    {
        idaspirante = 0; 
        Nombre = ""; 
        Apellido = ""; 
        Calle = "" ;
        Numero = ""; 
        Colonia = "" ; 
        Agencia = "";
        Seccion = ""; 
        SenasParticulares = ""; 
        Telefono = ""; 
        Email = ""; 
        barda = false; 
        lona = false; 
        banderin = false; 
        reunion = false; 
        gestion = false; 
        PSocial = ""; 
        //FechaReunion  =  ;
        Observaciones = "";
        
    }
    
    
    @SuppressWarnings("empty-statement")
    public boolean Nuevo() throws ClassNotFoundException 
    {
        try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemamonitoreo","root", "1234");
                stat = con.createStatement();
                
                String SQL = "INSERT INTO aspiranteresponsable (Nombre,Apellido,Calle,Numero,Colonia,Agencia,Seccion,SenasParticulares,"
                           + "Telefono,Email,Barda,Lona,Banderin,Reunion,Gestion,PSocial,PInfraestructura,FechaReunion,Observaciones)"
                           + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setString (1, Nombre);
                preparedStmt.setString (2, Apellido);
                preparedStmt.setString (3, Calle);
                preparedStmt.setString (4, Numero);
                preparedStmt.setString (5, Colonia);
                preparedStmt.setString (6, Agencia);                
                preparedStmt.setString (7, Seccion);
                preparedStmt.setString (8, SenasParticulares);
                preparedStmt.setString (9, Telefono);
                preparedStmt.setString (10, Email);
                preparedStmt.setBoolean(11, barda);
                preparedStmt.setBoolean(12, lona);
                preparedStmt.setBoolean(13, banderin);
                preparedStmt.setBoolean(14, reunion);
                preparedStmt.setBoolean(15, gestion);
                preparedStmt.setString (16, Numero);
                preparedStmt.setString (17, Colonia);
                preparedStmt.setDate (18, FechaReunion);
                preparedStmt.setString (19, Observaciones);
                
                int retorno = preparedStmt.executeUpdate();
                
            }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            
        }
        
        return true;    
    }
    
    public boolean Modifica()
    {
         try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemamonitoreo","root", "1234");
                stat = con.createStatement();
                
                String SQL = "UPDATE aspiranteresponsable SET Nombre = ?,Apellido = ?,Calle = ?,Numero = ?,Colonia = ?,Agencia = ?,Seccion = ?,"
                           + "SenasParticulares = ?,Telefono = ?,Email = ?,Barda = ?,Lona = ?,Banderin = ?,Reunion = ?,Gestion = ?,PSocial = ?,"
                           + "PInfraestructura = ?,FechaReunion = ?,Observaciones = ? WHERE idaspirante = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setString (1, Nombre);
                preparedStmt.setString (2, Apellido);
                preparedStmt.setString (3, Calle);
                preparedStmt.setString (4, Numero);
                preparedStmt.setString (5, Colonia);
                preparedStmt.setString (6, Agencia);                
                preparedStmt.setString (7, Seccion);
                preparedStmt.setString (8, SenasParticulares);
                preparedStmt.setString (9, Telefono);
                preparedStmt.setString (10, Email);
                preparedStmt.setBoolean(11, barda);
                preparedStmt.setBoolean(12, lona);
                preparedStmt.setBoolean(13, banderin);
                preparedStmt.setBoolean(14, reunion);
                preparedStmt.setBoolean(15, gestion);
                preparedStmt.setString (16, Numero);
                preparedStmt.setString (17, Colonia);
                preparedStmt.setDate (18, FechaReunion);
                preparedStmt.setString (19, Observaciones);
                preparedStmt.setInt(20,idaspirante);
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
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemamonitoreo","root", "1234");
                stat = con.createStatement();
                
                String SQL = "DELETE FROM aspiranteresponsable WHERE idaspirante = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setInt (1, idaspirante);
          
                int retorno = preparedStmt.executeUpdate();
            }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
            return false;
        } finally {
            
        }
        
        return true;    
    }
}
