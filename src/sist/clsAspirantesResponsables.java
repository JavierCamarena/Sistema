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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

/**
 *
 * @author keko
 */
public class clsAspirantesResponsables {
    
    Connection con = null;
    Statement stat = null;
    //---------------DataBase Conf------------------
    String dbName  = "com.mysql.jdbc.Driver";   
    String dbPath  = "jdbc:mysql://localhost:3306/sistemamonitoreo";
    String dbUsr     = "root";
    String dbPw    = "1234";//"1234";
    //---------------DataBase Conf------------------
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
    public String Clave;
    
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
        Clave = "";
        
    }
    
    

    public int Nuevo()  // Regresa -1 cuando ha sucedido un error, y si no, regresa el id del que acaba de insertar
    {
        int Afected;
        try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
                stat = con.createStatement();
                
                String SQL = "INSERT INTO aspiranteresponsable (Nombre,Apellido,Calle,Numero,Colonia,Agencia,Seccion,SenasParticulares,"
                           + "Telefono,Email,Barda,Lona,Banderin,Reunion,Gestion,PSocial,PInfraestructura,FechaReunion,Observaciones,Clave)"
                           + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL,new String[]{"isaspirante"});
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
                preparedStmt.setString (16, PSocial);
                preparedStmt.setString (17, PInfraestructura);
                preparedStmt.setDate (18, FechaReunion);
                preparedStmt.setString (19, Observaciones);
                preparedStmt.setString(20, Clave);

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
    
    public boolean Modifica()
    {
         try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw);
                stat = con.createStatement();
                
                String SQL = "UPDATE aspiranteresponsable SET Nombre = ?,Apellido = ?,Calle = ?,Numero = ?,Colonia = ?,Agencia = ?,Seccion = ?,"
                           + "SenasParticulares = ?,Telefono = ?,Email = ?,Barda = ?,Lona = ?,Banderin = ?,Reunion = ?,Gestion = ?,PSocial = ?,"
                           + "PInfraestructura = ?,FechaReunion = ?,Observaciones = ?,Clave = ? WHERE idaspirante = ?";
                
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
                preparedStmt.setString(20, Clave);
                preparedStmt.setInt(21,idaspirante);
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
    
    public boolean Busca(int id)
    {
     
                try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw);
                stat = con.createStatement();
                
                String SQL = "SELECT * FROM aspiranteresponsable WHERE idaspirante = ?";
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL);
                preparedStmt.setInt(1,id);
                
                ResultSet rs = preparedStmt.executeQuery();
                
                System.out.println("Buscando id = "+ id);
                
                if(rs.next())
                {
                    idaspirante = rs.getInt("idaspirante");
                    Nombre = rs.getString("Nombre");
                    Apellido = rs.getString("Apellido");
                    Calle = rs.getString("Calle"); 
                    Numero = rs.getString("Numero"); 
                    Colonia = rs.getString("Colonia"); 
                    Agencia = rs.getString("Agencia"); 
                    Seccion = rs.getString("Seccion"); 
                    SenasParticulares = rs.getString("SenasParticulares"); 
                    Telefono = rs.getString("Telefono"); 
                    Email= rs.getString("Email"); 
                    barda = rs.getBoolean("Barda"); 
                    lona = rs.getBoolean("Lona"); 
                    banderin = rs.getBoolean("Banderin"); 
                    reunion = rs.getBoolean("Reunion"); 
                    gestion = rs.getBoolean("Gestion");
                    PSocial = rs.getString("PSocial"); 
                    PInfraestructura = rs.getString("PInfraestructura"); 
                    FechaReunion = rs.getDate("FechaReunion"); 
                    Observaciones = rs.getString("Observaciones");
                    System.out.println("Khe");
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
