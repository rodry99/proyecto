/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.vista;
import ar.com.lujan.asistencia.modelo.CursoDias;
import ar.com.lujan.asistencia.modelo.Curso;
import ar.com.lujan.asistencia.service.CursoDiaRN;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author carlos cerrizuela
 */
public class AgregarCursoDia extends javax.swing.JDialog {

    /**
     * Creates new form AgregarCursoDia
     */
    
     //CursoDIaRN cursodiaRN = new CursoDIaRN();
    CursoDiaRN cursodiaRN = new CursoDiaRN();
    CursoDias cursodia= new CursoDias();
    private CursoDias c;
    
    public AgregarCursoDia(java.awt.Frame parent, boolean modal,String operacion,CursoDias c) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        
 
        //jtfIdCurso.setText(String.valueOf(c.getIdCurso()));
        //jtfDia.setText(String.valueOf(c.getDia()));
        jtfIdCurso.setText(String.valueOf(c.getIdCurso()));
        //jtfDia.setText(String.valueOf(c.getDia()));
        //jTfProfesor.setText(String.valueOf(c.getProfesor()));
        //jTFMateria.setText(String.valueOf(c.getMateria()));
        jtfIdCurso.setEditable(false);
        jTfProfesor.setEditable(false);
        jTFMateria.setEditable(false);
        Cursodias = c;
        
       
          
        
    }

    public AgregarCursoDia(java.awt.Frame parent, boolean modal,CursoDias c,String operacion) {
    super(parent, modal);
    initComponents();
   
// jtfIdCursoDia.setText(String.valueOf(c.getIdCursoDia()));
        
        jtfIdCurso.setText(String.valueOf(c.getIdCurso()));
        //jtfDia.setText(String.valueOf(c.getDia()));
       // jtfIdCurso.setText(String.valueOf(c.getIdCurso()));
        //jtfDia.setText(String.valueOf(c.getDia()));
        //jTfProfesor.setText(String.valueOf(c.getProfesor()));
        //jTFMateria.setText(String.valueOf(c.getMateria()));
        jtfIdCurso.setEditable(false);
        jTfProfesor.setEditable(false);
        jTFMateria.setEditable(false);
        jbGrabar.setText("Actualizar");
        setTitle("Editar Curso Dia");
        jlAgregarCursoDia.setText("Editar Curso Dia");
        
        
        
       // Cursodias = c;
        this.setOperacion(operacion);
        this.c = c;
    
    }

    private AgregarCursoDia(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    AgregarCursoDia(Object object, boolean b, String editar, CursoDias cursodia) {
      
    }

   /* AgregarCursoDia(String materia, String profesor) {
    String mat = materia;
    String prof = profesor;
    
    jTfProfesor.setText(String.valueOf(prof));
    jTFMateria.setText(String.valueOf(mat));
        
        
        
    }*/
    
    


    private int grabarCursoDia(String operacion){
        //comprobar los datos obligatorios
            int r=0;
            if ( jtfIdCurso.getText().equals("")){
               JOptionPane.showMessageDialog(this,"Debe completar los campos obligatorios");
            }
            else
            {
            //comprobar que los tipos de dato sean correctos
                if (this.isNumeric(jtfIdCurso.getText())){ 
                    //arma el objeto y se lo mando a RN
                    
                    CursoDias c = new CursoDias();
                    
                    int i= jCBDia.getSelectedIndex();
        
            if(i==1){
                c.setDia(0);
//jtfDia.setText(String.valueOf(0));
            //case 0: retorno = cursodia.getIdCursoDia();break;
            }else
            if(i==2){
             c.setDia(1);;
            //case 0: retorno = cursodia.getIdCursoDia();break;
           }else
            if(i==3){
                c.setDia(2);
            }else
            if(i==4){
                c.setDia(3);
            }else
            if(i==5){
                c.setDia(4);
            }else
            if(i==6){
                c.setDia(5);
            }
        
                    
                    
                    
                  /*  if (this.operacion=="EDITAR"){
                        p.setID_Profesor(Integer.parseInt(jtfID.getText()));
                    }
                    */
                 /* if (this.operacion=="EDITAR"){
                        c.setIdCursoDia(Integer.parseInt(jtfIdCursoDia.getText()));
                    }*/
                    
                    //c.setIdCursoDia(Integer.parseInt(jtfIdCursoDia.getText()));
                    //jtfIdCurso.setText(String.valueOf(c.getIdCurso()));
                    c.setIdCurso(Integer.parseInt(jtfIdCurso.getText()));
                    //c.setDia(Integer.parseInt(jtfDia.getText()));
                   
            
                    
                    //graba
                    r=cursodiaRN.agregarEditarCursoDia(c,operacion);
                }
                else {
                        JOptionPane.showMessageDialog(this,"Error en el tipo de datos");
                } 
            }
        return r;
        }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlAgregarCursoDia = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jtfIdCurso = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jbGrabar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFMateria = new javax.swing.JTextField();
        jTfProfesor = new javax.swing.JTextField();
        jCBDia = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jlAgregarCursoDia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jlAgregarCursoDia.setText("AGREGAR CURSO DIA");

        jLabel3.setText("id_curso:");

        jtfIdCurso.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setText("dia:");

        jbGrabar.setText("Grabar");
        jbGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGrabarActionPerformed(evt);
            }
        });

        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Materia");

        jLabel6.setText("Profesor");

        jTFMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTFMateriaActionPerformed(evt);
            }
        });

        jCBDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un dia", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" }));
        jCBDia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBDiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfIdCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCBDia, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTfProfesor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                        .addComponent(jTFMateria, javax.swing.GroupLayout.Alignment.LEADING)))
                .addGap(151, 151, 151))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jlAgregarCursoDia, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jbGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlAgregarCursoDia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jtfIdCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTFMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jTfProfesor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jCBDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(87, 87, 87)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbGrabar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    cerrar();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jbGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGrabarActionPerformed

        
        
                if (operacion.equals("AGREGAR")){ 
                if (this.grabarCursoDia("AGREGAR") == 1) { //comprobar si grabó
                this.setGrabo(true);
                this.cerrar();
                } 
  
        }
        if (operacion.equals("EDITAR")){
            if (this.grabarCursoDia("EDITAR") == 1) { //comprobar si grabó
                this.setGrabo(true);
                this.cerrar();
            } 
        }


      
        
        

        // TODO add your handling code here:
    }//GEN-LAST:event_jbGrabarActionPerformed

    private void jTFMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTFMateriaActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_jTFMateriaActionPerformed

    private void jCBDiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBDiaActionPerformed

        // TODO add your handling code here:
  /*switch(jCBDia.getSelectedIndex()){
            
            //case 0: retorno = cursodia.getIdCursoDia();break;
            case 0:jtfDia.setText(String.valueOf(0)); 
            case 1:jtfDia.setText(String.valueOf(1));
            case 2:jtfDia.setText(String.valueOf(2));
            case 3:jtfDia.setText(String.valueOf(3));
            case 4:jtfDia.setText(String.valueOf(4));
            case 5:jtfDia.setText(String.valueOf(5));
            
  }*/


        
        
    }//GEN-LAST:event_jCBDiaActionPerformed

    
    private void cerrar(){

        this.dispose();
    }
    
    private boolean isNumeric(String s){
        boolean r = false;
        try{
            Integer.parseInt(s);
            r=true;
        }
        catch(Exception ex){
            r=false;
        }
        return r;
        
    }
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(AgregarCursoDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarCursoDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarCursoDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarCursoDia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AgregarCursoDia dialog = new AgregarCursoDia(new javax.swing.JFrame(), true);
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

    
  private CursoDias Cursodias;  
  private boolean grabo = false;
  private String operacion;
   

    
     public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
    
      public boolean isGrabo() {
        return grabo;
    }
     
     
    public void setGrabo(boolean Grabo) {
        this.grabo = Grabo;
    }
    
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    public javax.swing.JComboBox<String> jCBDia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField jTFMateria;
    private javax.swing.JTextField jTfProfesor;
    private javax.swing.JButton jbGrabar;
    private javax.swing.JLabel jlAgregarCursoDia;
    private javax.swing.JTextField jtfIdCurso;
    // End of variables declaration//GEN-END:variables

    

    
    
}
