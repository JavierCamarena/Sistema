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
public class clsPrep {
    
    Connection con = null;
    Statement stat = null;
    //---------------DataBase Conf------------------
    
    String dbName;   
    String dbPath;
    String dbUsr;
    String dbPw;
    
    public int idPrep;
    public String Seccion;
    public int Casillas;
    public int Votos;
    public int PAN;
    public int PRI;
    public int PRD;
    public int PVEM;
    public int PT;
    public int PMC;
    public int PUP;
    public int PNA;
    public int PSD;
    public int PAN_PRD;
    public int PRI_PVEM;
    public int CNR;
    public int Nulos;
    
    public clsPrep(String[] conf)
    {
        dbName = conf[0];
        dbPath = conf[1];
        dbUsr = conf[2];
        dbPw = conf[3];
        Limpia();
    }
    
    public void Limpia() 
    {
        idPrep = 0; 
        Seccion = "";
        Casillas = 0; 
        Votos = 0;
        PAN = 0; 
        PRI = 0; 
        PRD = 0; 
        PVEM = 0; 
        PT = 0;
        PMC = 0; 
        PUP = 0; 
        PNA = 0; 
        PSD = 0; 
        PAN_PRD = 0; 
        PRI_PVEM = 0; 
        CNR = 0; 
        Nulos = 0;        
    }
    
        public int Nuevo() 
    {
        int Afected;
        try {
                Class.forName(dbName);
                con = DriverManager.getConnection(dbPath,dbUsr, dbPw); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
                stat = con.createStatement();
                
                String SQL = "INSERT INTO prep (Seccion,Casillas,Votos,PAN,PRI,PRD,PVEM,PT,PMC,PUP,PNA,PSD,PAN_PRD,PRI_PVEM,CNR,Nulos)"
                           + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                
                
                PreparedStatement preparedStmt = con.prepareStatement(SQL,new String[]{"idCiudadano"});
                preparedStmt.setString(1, Seccion);
                preparedStmt.setInt(2, Casillas);
                preparedStmt.setInt(3, Votos);
                preparedStmt.setInt(4, PAN);
                preparedStmt.setInt(5, PRI);
                preparedStmt.setInt(6, PRD);                
                preparedStmt.setInt(7, PVEM);
                preparedStmt.setInt(8, PT);                
                preparedStmt.setInt(9, PMC);                
                preparedStmt.setInt(10, PUP);
                preparedStmt.setInt(11, PNA);
                preparedStmt.setInt(11, PSD);
                preparedStmt.setInt(11, PAN_PRD);
                preparedStmt.setInt(11, PRI_PVEM);
                preparedStmt.setInt(11, CNR);
                preparedStmt.setInt(11, Nulos);
                 

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
        idPrep = Afected;
        return Afected;    
    }
}
