/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bingo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import unoDiez.ClsCiudadano;

/**
 *
 * @author keko
 */
public class Bingo extends javax.swing.JFrame {
    
    String Configuracion[];
    String sqlcode = "";
    Connection con = null;
    Statement stat = null;
    ResultSet   rs = null;
    public DefaultTableModel modelo;
    private ClsCiudadano Ciudadano;
     String [] titulos = {"id","Apellidos", "Nombre", "Seccion"}; 
     
    
    public Bingo() {
        initComponents();
    }
    
    public Bingo(String[] conf) {
        initComponents();
        Configuracion = conf ;
        setLocationRelativeTo(null);
        LimpiaCampos();
        Ciudadano = new ClsCiudadano(Configuracion);
    }
    
    private void LimpiaCampos() 
    {
       
        jTextSeccion .setText("");
        jTextFolio   .setText("");
        jComboCasilla.setSelectedIndex(0);
        
        jLNombre  .setText("---");
        jLApellido.setText("---");
        jLClave   .setText("---");
        jLCorreo  .setText("---");
        jLTelefono.setText("---");
        jLSeccion .setText("---");
        jLFolio   .setText("---");
        jLCasilla .setText("---");
        jLColonia .setText("---");
        jLabelVotado.setText("...");
        modelo = new DefaultTableModel(null, titulos);
    }
    
    public void AsignaSqlCiudadano() 
    {        
        String comp = "";
        int cas = jComboCasilla.getSelectedIndex();
        if(cas > 0){
            comp +=cas;
        }
        sqlcode = " WHERE Seccion LIKE '%" + jTextSeccion.getText() + "%' AND FolioPadron LIKE '%" 
                + jTextFolio.getText() + "%' AND Casilla LIKE '%" + comp +"%'";
    }
    
    public void PresentaDatos()
    {
        jLNombre.setText(Ciudadano.Nombres);
        jLApellido.setText(Ciudadano.Apellidos);
        jLClave.setText(Ciudadano.ClaveIne);
        jLCorreo.setText(Ciudadano.Mail);
        jLTelefono.setText(Ciudadano.Telefono);
        jLSeccion.setText(Ciudadano.Seccion);
        jLFolio.setText(Ciudadano.FolioPadron);
        jLCasilla.setText(Integer.toString(Ciudadano.Casilla));
        jLColonia.setText(Ciudadano.Colonia);
        if(Ciudadano.Voto == false)
        {
            
            jLabelVotado.setText("No ha votado.");
        }
        else
        {
            jLabelVotado.setText("Ya voto.");
        }
    }
    
      public void BuscaCiudadano()
    {
        AsignaSqlCiudadano();
        System.out.println("Buscando y llenando tabla");
        try{    
            Class.forName(Configuracion[0]);
            
            con = DriverManager.getConnection(Configuracion[1],Configuracion[2],Configuracion[3]); // OJO esta linea depende de tu base de datos, el 1234 es la contrasenia
            stat = con.createStatement();
            System.out.println("preparando statement :"+sqlcode);
            rs = stat.executeQuery("select * from ciudadanos "+sqlcode+" Order by Apellidos");
            System.out.println("Datos obtenidos configurando tabla");
          
           
            String [] registros = new String[4];
            
            
            modelo = new DefaultTableModel(null, titulos);
            while(rs.next())
                {
                    registros[0]= rs.getString("idciudadanos");
                    registros[1]= rs.getString("Apellidos");
                    registros[2]= rs.getString("Nombres");
                    registros[3]= rs.getString("Seccion");

                    modelo.addRow(registros);
                }
            jTableCiudadano.setModel(modelo);
            rs.close();
            
            //asignaSql();
                }catch ( ClassNotFoundException | SQLException e ){
            System.out.println("Error: " + e.getMessage());
           
        } finally {
            
            try { if (rs != null) rs.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
            try { if (stat != null) stat.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
            try { if (con != null) con.close(); } catch (Exception e) {System.out.println("Error:" + e.getMessage());}
            
             jTableCiudadano.getSelectionModel().addListSelectionListener( new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                   if (!e.getValueIsAdjusting())
                        {
                                boolean rowsAreSelected = jTableCiudadano.getSelectedRowCount() > 0;
                                if(rowsAreSelected)
                                {
                                    int a = jTableCiudadano.getSelectedRow();
                                    int aux = Integer.parseInt(jTableCiudadano.getValueAt(a , 0).toString() );
                                    Ciudadano.Busca(aux);
                                    PresentaDatos();
                                }
                        }
                    
                }
            } );
            jTableCiudadano.setDefaultEditor(Object.class, null);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextSeccion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCiudadano = new javax.swing.JTable();
        jButtonBuscar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLNombre = new javax.swing.JLabel();
        jLApellido = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLClave = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLCorreo = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLTelefono = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLSeccion = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLFolio = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLCasilla = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLColonia = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabelVotado = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboCasilla = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextFolio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Busqueda De Ciudadanos");

        jLabel4.setText("Seccion:");

        jTextSeccion.setText("jTextField1");

        jTableCiudadano.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableCiudadano);

        jButtonBuscar.setText("Buscar");
        jButtonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscarActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Nombres");

        jLNombre.setText("---");

        jLApellido.setText("---");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Apellidos");

        jLClave.setText("---");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Clave INE");

        jLCorreo.setText("---");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Correo");

        jLTelefono.setText("---");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Telefono");

        jLSeccion.setText("---");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Seccion");

        jLFolio.setText("---");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("Folio Padron");

        jLCasilla.setText("---");

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Casilla");

        jLColonia.setText("---");

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("Colonia");

        jLabelVotado.setText("jLabel6");

        jButton1.setText("Voto!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboCasilla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CASILLA", "BASICA", "CONTIGUA 1", "CONTIGUA 2", "CONTIGUA 3", "CONTIGUA 4", "CONTIGUA 5", "CONTIGUA 6", "CONTIGUA 7", "CONTIGUA 8", "CONTIGUA 9", "CONTIGUA 10", "CONTIGUA 11", "CONTIGUA 12", "CONTIGUA 13", "CONTIGUA 14", "CONTIGUA 15", "CONTIGUA 16", "CONTIGUA 17", "CONTIGUA 18", "CONTIGUA 19", "CONTIGUA 20", "CONTIGUA 21", "CONTIGUA 22", "CONTIGUA 23", "CONTIGUA 24", "CONTIGUA 25", "CONTIGUA 26", "CONTIGUA 27", "CONTIGUA 28", "CONTIGUA 29", "CONTIGUA 30" }));

        jLabel6.setText("Folio:");

        jTextFolio.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jComboCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(185, 185, 185)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLNombre)
                            .addComponent(jLabel8)
                            .addComponent(jLApellido)
                            .addComponent(jLabel10)
                            .addComponent(jLClave)
                            .addComponent(jLabel12)
                            .addComponent(jLCorreo)
                            .addComponent(jLabel14)
                            .addComponent(jLTelefono)
                            .addComponent(jLabel16)
                            .addComponent(jLSeccion)
                            .addComponent(jLabel18)
                            .addComponent(jLFolio)
                            .addComponent(jLabel20)
                            .addComponent(jLCasilla)
                            .addComponent(jLabel22)
                            .addComponent(jLColonia)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonBuscar)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(157, 157, 157)
                                .addComponent(jLabelVotado, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(82, 82, 82)))))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jComboCasilla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextSeccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonBuscar)
                    .addComponent(jLabel6)
                    .addComponent(jTextFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLNombre)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLApellido)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLClave)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLCorreo)
                .addGap(18, 18, 18)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLTelefono)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLSeccion)
                .addGap(18, 18, 18)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLFolio)
                .addGap(18, 18, 18)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLCasilla)
                .addGap(18, 18, 18)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLColonia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabelVotado)
                .addGap(31, 31, 31)
                .addComponent(jButton1)
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscarActionPerformed
        // TODO add your handling code here:
        BuscaCiudadano();
    }//GEN-LAST:event_jButtonBuscarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (Ciudadano.Votacion(!Ciudadano.Voto)) 
        {
            JOptionPane.showMessageDialog(null, "Se ha actualizado.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
            Ciudadano.Voto = !Ciudadano.Voto;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error.", "Cuidado.", JOptionPane.INFORMATION_MESSAGE);
        }
        PresentaDatos();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Bingo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bingo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bingo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bingo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bingo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JComboBox<String> jComboCasilla;
    private javax.swing.JLabel jLApellido;
    private javax.swing.JLabel jLCasilla;
    private javax.swing.JLabel jLClave;
    private javax.swing.JLabel jLColonia;
    private javax.swing.JLabel jLCorreo;
    private javax.swing.JLabel jLFolio;
    private javax.swing.JLabel jLNombre;
    private javax.swing.JLabel jLSeccion;
    private javax.swing.JLabel jLTelefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelVotado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCiudadano;
    private javax.swing.JTextField jTextFolio;
    private javax.swing.JTextField jTextSeccion;
    // End of variables declaration//GEN-END:variables
}
