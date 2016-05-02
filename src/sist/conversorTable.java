/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sist;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 * Convierte un ResultSet en un DefaultTableModel
 * @author Chuidiang
 *
 */
public  class conversorTable
{
    /**
     * Rellena el DefaultTableModel con los datos del ResultSet.
     * Vacía el DefaultTableModel completamente y le mete los datos que hay
     * en el ResultSet.
     * @param rs El resultado de lac onsula a base de datos.
     * @param modelo El DefaultTableModel que queremos rellenar
     */
    public static void rellena(ResultSet rs, DefaultTableModel modelo)
    {
        configuraColumnas(rs, modelo);
        vaciaFilasModelo(modelo);
        anhadeFilasDeDatos(rs, modelo);
    }

    /**
     * Añade al DefaultTableModel las filas correspondientes al ResultSet.
     * @param rs El resultado de la consulta a base de datos
     * @param modelo El DefaultTableModel que queremos rellenar.
     */
    private static void anhadeFilasDeDatos(ResultSet rs,
            DefaultTableModel modelo)
    {
        Object ob;
        int numeroFila = 0;
        try
        {
            // Para cada registro de resultado en la consulta 
            while (rs.next())
            {
                // Se crea y rellena la fila para el modelo de la tabla.
                String[] datosFila = new String[modelo.getColumnCount()];
                for (int i = 0; i < modelo.getColumnCount(); i++){
                    ob = rs.getObject(i + 1);
                    if(ob == null)
                        datosFila[i]="";
                    else
                        datosFila[i]=ob.toString();
                    //System.out.println(datosFila[i]);
                }
                modelo.addRow(datosFila);
                numeroFila++;
            }
            rs.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Borra todas las filas del modelo.
     * @param modelo El modelo para la tabla.
     */
    private static void vaciaFilasModelo(final DefaultTableModel modelo)
    {
        // La llamada se hace in un invokeAndWait para que se ejecute en el
        // hilo de refresco de ventanas y evitar que salten excepciones
        // durante dicho refresco.
        try
        {
           
                    while (modelo.getRowCount() > 0)
                        modelo.removeRow(0);
                
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Pone en el modelo para la tabla tantas columnas como tiene el resultado
     * de la consulta a base de datos.
     * @param rs Resultado de consulta a base de datos.
     * @param modelo Modelo de la tabla.
     */
    public static void configuraColumnas(final ResultSet rs,
            final DefaultTableModel modelo)
    {
        try
        {
            // Se hace en un invokeAndWait para que este código se ejecute
            // en el hilo de refresco de ventanas, evitando que salten
            // excepciones.
            
            

                
                {
                    try
                    {
                        // Se obtiene los metadatos de la consulta. Con ellos
                        // podemos obtener el número de columnas y el nombre
                        // de las mismas.
                        ResultSetMetaData metaDatos = rs.getMetaData();
                        
                        // Se obtiene el numero de columnas.
                        int numeroColumnas = metaDatos.getColumnCount();
                        
                        // Se obtienen las etiquetas para cada columna
                        String etiquetas[] = new String[numeroColumnas];
                        for (int i = 0; i < numeroColumnas; i++)
                        {
                            etiquetas[i] = metaDatos.getColumnLabel(i + 1).toString();
                            System.out.println(etiquetas[i].toString());
                        }
                        
                        // Se meten las etiquetas en el modelo. El numero
                        // de columnas se ajusta automáticamente.
                        modelo.setColumnIdentifiers(etiquetas);
                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }

           
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

