/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.vista;

import ar.com.lujan.asistencia.database.CarreraDAO;
import ar.com.lujan.asistencia.database.CarreraMateriaDAO;
import ar.com.lujan.asistencia.database.CursoAlumnosDAO;
import ar.com.lujan.asistencia.database.CursoDAO;
import ar.com.lujan.asistencia.database.CursoDiaDAO;
import ar.com.lujan.asistencia.modelo.Carrera;
import ar.com.lujan.asistencia.modelo.CarreraMateria;
import ar.com.lujan.asistencia.modelo.Curso;
import ar.com.lujan.asistencia.modelo.CursoAlumnosTableModel;
import ar.com.lujan.asistencia.modelo.CursoDiaTableModel;
import ar.com.lujan.asistencia.modelo.CursoDias;
import ar.com.lujan.asistencia.modelo.CursoTableModel;
import ar.com.lujan.asistencia.service.CursoDiaRN;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author carlos cerrizuela
 */
public class ABMCursoDia extends javax.swing.JDialog {

    /**
     * Creates new form ABMCursoDia
     */
    
    
    CursoDiaRN  cursodiaRN = new CursoDiaRN();
    String materia;
    String profesor;
    
    
    public ABMCursoDia(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        cmbCarrera.setModel(new DefaultComboBoxModel( (new CarreraDAO()).getCarreras().toArray()));
        cmbCarrera.insertItemAt("Seleccione una Carrera", 0);
        cmbCarrera.setSelectedIndex(0); 
        jtCursos.setModel(new CursoTableModel( new ArrayList<>()));
        
        jtCursos.removeColumn(jtCursos.getColumnModel().getColumn(1));
        jtCursos.removeColumn(jtCursos.getColumnModel().getColumn(7));
        jtCursos.removeColumn(jtCursos.getColumnModel().getColumn(3));
        jtCursos.removeColumn(jtCursos.getColumnModel().getColumn(5));
       // jtCursos.removeColumn(jtCursos.getColumnModel().getColumn(7));
       // jtCursos.removeColumn(jtCursos.getColumnModel().getColumn(9));
        //jtCursos.removeColumn(jtCursos.getColumnModel().getColumn(0));
    }
    
    
    private void cargarGrilla(){
       jtCursoDia.setModel(new CursoDiaTableModel((new CursoDiaDAO()).getCursoDia()));
       
   }
   
     private CursoDias armarObjetoCursoDia(int r){
        CursoDias c = new CursoDias();
        Curso cs = new Curso();
        //c.setIdCursoDia((int) jtCursoDia.getModel().getValueAt(r,0));
        c.setIdCurso((int)jtCursos.getModel().getValueAt(r,0));
        //c.setMateria((String)jtCursos.getModel().getValueAt(r,1));
        //c.setM((String)jtCursos.getModel().getValueAt(r,4));
         //c.setProfesor((String)jtCursos.getModel().getValueAt(r,6));
        //c.setDni(Integer.parseInt(jtProfesores.getModel().getValueAt(r,3).toString()));
        
        return c;
    } 
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtCursoDia = new javax.swing.JTable();
        cmbCarrera = new javax.swing.JComboBox<>();
        cmbMateria = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtCursos = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("AMBCursoDia");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("     Curso Dia");

        jButton1.setText("Agregar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Modificar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jtCursoDia.setModel(new CursoDiaTableModel((new CursoDiaDAO()).getCursoDia()));
        jtCursoDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtCursoDiaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtCursoDia);

        cmbCarrera.insertItemAt("0 - Todas las Carreras", 0);
        cmbCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCarreraActionPerformed(evt);
            }
        });

        cmbMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMateriaActionPerformed(evt);
            }
        });

        jLabel2.setText("Carrera:");

        jLabel3.setText("Materia:");

        jtCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtCursosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jtCursos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbCarrera, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cmbMateria, 0, 102, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(162, 162, 162)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCarrera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );

        cmbCarrera.setSelectedIndex(0);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

         int r = jtCursos.getSelectedRow();
        CursoDias c;
        Curso cs;
        if (r != -1){
            //armar el objeto  con los datos de la fila
            CursoDias cursodias=this.armarObjetoCursoDia(r);
        
     AgregarCursoDia AgregarCursoDia = new AgregarCursoDia(null,true,"AGREGAR",cursodias);//el primero es la herencia, o sea, extiende de jFrame. el segundo es si es modal o no
        AgregarCursoDia.setLocationRelativeTo(null);
        AgregarCursoDia.setOperacion("AGREGAR");
        AgregarCursoDia.setVisible(true);
        this.cargarGrilla();

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

  int r = jtCursos.getSelectedRow();
        CursoDias c;
        Curso cs;
        if (r != -1){
            //armar el objeto con los datos de la fila
            CursoDias cursodia=this.armarObjetoCursoDia(r);
         //   Curso curso=this.armarObjetoCursoDia(r);
            //instanciar la ventana y pasarle el objeto 
            AgregarCursoDia editarCursoDia = new AgregarCursoDia(null,true,cursodia,"EDITAR");//el primero es la herencia, o sea, extiende de jFrame. el segundo es si es modal o no, si esta por encima de todo
            editarCursoDia.setLocationRelativeTo(null); 
            editarCursoDia.setVisible(true);
            if (editarCursoDia.isGrabo()) {
                this.cargarGrilla();
            }
        }
                                           





        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

int row = jtCursos.getSelectedRow();
        
        if (row != -1){//Arma el objeto alumno con los datos de la fila
            CursoDias c = this.armarObjetoCursoDia(row);
            
            //Ejecuta el borrar alumno
            cursodiaRN.borrarCursoDIa(c);
            this.cargarGrilla();


        }


        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jtCursoDiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCursoDiaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jtCursoDiaMouseClicked

    private void cmbCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCarreraActionPerformed

  if (cmbCarrera.getSelectedIndex() != 0) {
            Carrera carrera = (Carrera) cmbCarrera.getSelectedItem();    
            cmbMateria.setModel(new DefaultComboBoxModel( (new CarreraMateriaDAO()).getMateriasByCarrera(carrera).toArray()));
            cmbMateria.insertItemAt("Seleccione una Materia", 0);
            cmbMateria.setSelectedIndex(0);
        } else {
            cmbMateria.setModel(new DefaultComboBoxModel( ));
            jtCursos.setModel(new CursoTableModel( new ArrayList<>()));
            jtCursos.removeColumn(jtCursos.getColumnModel().getColumn(1));
            jtCursos.removeColumn(jtCursos.getColumnModel().getColumn(2));
           // jtCursos.removeColumn(jtCursos.getColumnModel().getColumn(0));
           
        }

     //   jLabel3.setText("Total de Inscriptos: " + jtCursoAlumnos.getModel().getRowCount());
       // jLabel4.setText("Total de Alumnos: " + jtAlumnos.getModel().getRowCount());
        






        // TODO add your handling code here:
    }//GEN-LAST:event_cmbCarreraActionPerformed

    private void cmbMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMateriaActionPerformed


 if (cmbMateria.getSelectedIndex() != 0) {            
            CarreraMateria carreraMateria = (CarreraMateria) cmbMateria.getSelectedItem();
            jtCursos.setModel(new CursoTableModel( (new CursoDAO()).getCursosByCarreraMateria(carreraMateria) ));
         
        } else {
            jtCursos.setModel(new CursoTableModel( new ArrayList<>()));
           
        }
       
        jtCursos.removeColumn(jtCursos.getColumnModel().getColumn(1));
        jtCursos.removeColumn(jtCursos.getColumnModel().getColumn(2));
        //jtCursos.removeColumn(jtCursos.getColumnModel().getColumn(0));
       


        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMateriaActionPerformed

    private void jtCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtCursosMouseClicked

        String materia;
        String profesor;
        
int r = jtCursos.getSelectedRow();
        if (r  != -1 ){
            Curso curso = new Curso();
            curso.setIdCurso((int) jtCursos.getModel().getValueAt(r, 0));
            CursoAlumnosDAO cursoAlumnoDAO = new CursoAlumnosDAO();
         
            
            materia = (String) jtCursos.getModel().getValueAt(r,2);
            System.out.println("materia " +materia);
            
            profesor = (String) jtCursos.getModel().getValueAt(r,6);
             System.out.println("profesor " +profesor);
             
          //AgregarCursoDia cursod = new AgregarCursoDia(materia,profesor);   
            //c.setMateria((String)jtCursos.getModel().getValueAt(r,1));
        //c.setM((String)jtCursos.getModel().getValueAt(r,4));
         //c.setProfesor((String)jtCursos.getModel().getValueAt(r,6));
        //c.setDni(Integer.parseInt(jtProfesores.getModel().getValueAt(r,3).toString()));
            

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jtCursosMouseClicked

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
            java.util.logging.Logger.getLogger(ABMCursoDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ABMCursoDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ABMCursoDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ABMCursoDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ABMCursoDia dialog = new ABMCursoDia(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbCarrera;
    private javax.swing.JComboBox<String> cmbMateria;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jtCursoDia;
    private javax.swing.JTable jtCursos;
    // End of variables declaration//GEN-END:variables

   
}