package ar.com.lujan.asistencia.vista;


import ar.com.lujan.asistencia.database.CursoDAO;
import ar.com.lujan.asistencia.service.ClaseRN;
import ar.com.lujan.asistencia.modelo.Clase;
import ar.com.lujan.asistencia.modelo.Curso;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lucho
 */
public class AgregarClaseUI extends javax.swing.JDialog {

    /**
     * Creates new form test
     */
    //instanciar la regla de negocios
    ClaseRN claseRN = new ClaseRN();
    
    public AgregarClaseUI(){
        
        
    }
    
    public AgregarClaseUI(java.awt.Frame parent, boolean modal,String operacion) {
        super(parent, modal);
        initComponents();
        this.setOperacion(operacion);
        ocultarLabel();
        jcbbCurso.insertItemAt("Seleccione...",0);
        jcbbCurso.setSelectedIndex(0);
        jtfIdClase.setVisible(false);
    }
    
    public AgregarClaseUI(java.awt.Frame parent, boolean modal,String operacion,Clase cl) {
        super(parent, modal);
        initComponents();
        //ID
        jtfIdClase.setText(String.valueOf(cl.getIdClase())); jtfIdClase.setVisible(false);
        this.setOperacion(operacion);
        //Curso
        jcbbCurso.setEnabled(false);
        ArrayList<Curso> curso = new ArrayList<Curso>();
        curso.add(cl.getCurso());
        jcbbCurso.setModel((new DefaultComboBoxModel(curso.toArray())));
        jcbbCurso.insertItemAt("Seleccione...",0);
        jcbbCurso.setSelectedIndex(1);
        jcbbCurso.setSelectedItem((Object)cl.getCurso());        
        //Fecha
        jdFecha.setDate(cl.getFecha());        
        //Tema
        jtaTema.setText(cl.getTema());
        //Estado
        if (cl.getEstado().equals("DN"))
            jcbbEstado.setSelectedItem("Dictada Normalmente");
        if (cl.getEstado().equals("A"))
            jcbbEstado.setSelectedItem("Ausencia");
        if (cl.getEstado().equals("F"))
            jcbbEstado.setSelectedItem("Feriado");
        if (cl.getEstado().equals("P"))
            jcbbEstado.setSelectedItem("Paro");
        //Labels
        jbGrabar.setText("Actualizar");
        this.setTitle("Editar Clase");
        jlAgregarClase.setText("Editar clase");
        jlAnio.setText(String.valueOf(cl.getCurso().getAnioLectivo()));
        jlProfesor.setText(cl.getCurso().getProfesor().toString());
        jlCarrera.setText(cl.getCurso().getCarreraMaterias().getCarrera().getDescripcion());
    }

    private AgregarClaseUI(JFrame jFrame, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    private boolean comprobarDatosObligatorios(){
        boolean retorno = false;
        //comprobar los datos obligatorios
        if (jcbbCurso.getSelectedIndex()>0 && jdFecha.getDate()!=null){
            retorno=true;
        }
        
        System.out.println(jdFecha.getDate());
        return retorno;
    }
    
    private Clase armarObjeto(){
        Clase cl = new Clase();
        Curso cu = (Curso)jcbbCurso.getSelectedItem();;
        if (this.operacion.equals("EDITAR")){
            cl.setIdClase(Integer.parseInt(jtfIdClase.getText()));
        }
        cl.setCurso(cu);
        cl.setFecha(jdFecha.getDate());
        cl.setTema(jtaTema.getText());
        String estado = jcbbEstado.getSelectedItem().toString();
        if (estado.equals("Dictada Normalmente")){
            cl.setEstado("DN");
        }
        if (estado.equals("Ausencia")){
            cl.setEstado("A");
        }            
        if (estado.equals("Feriado")){
            cl.setEstado("F");
        }
        if (estado.equals("Paro")){
            cl.setEstado("P");
        }
        return cl;
    }
    
    private int grabarDatos(String operacion) throws ParseException{
        int r=0;
        String mensaje;
        String mensaje2;
        if (this.operacion.equals("AGREGAR")){
            mensaje="agregado";
            mensaje2="agregar";
        }
        else{
            mensaje="editado";
            mensaje2="editar";
            }            
        //comprueba datos obligatorios
        if (this.comprobarDatosObligatorios()){
            //graba
            r=claseRN.agregarEditarClase(this.armarObjeto(), operacion); //arma el objeto, y se lo manda a RN con la operacion
            if (r==1){
                JOptionPane.showMessageDialog(this, "Se ha " + mensaje + " exitosamente la clase");
                this.setGrabo(true);
                this.cerrar();
            }
            else{
                JOptionPane.showMessageDialog(this, "Error al guardar clase","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Error al intentar " + mensaje2 + ", debe completar los campos obligatorios");
        }
    return r;
    }

    private void grabar(){
        try {
            this.grabarDatos(operacion);
        }
        catch (ParseException ex) {
            Logger.getLogger(AgregarClaseUI.class.getName()).log(Level.SEVERE, null, ex);
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

        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField1 = new javax.swing.JTextField();
        jbSalir = new javax.swing.JButton();
        jbGrabar = new javax.swing.JButton();
        jlFecha = new javax.swing.JLabel();
        jlAgregarClase = new javax.swing.JLabel();
        jlNombre = new javax.swing.JLabel();
        jlEstado = new javax.swing.JLabel();
        jcbbCurso = new javax.swing.JComboBox<>();
        jlCurso = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaTema = new javax.swing.JTextArea();
        jcbbEstado = new javax.swing.JComboBox<>();
        jlAnio1 = new javax.swing.JLabel();
        jlAnio = new javax.swing.JLabel();
        jlProfesor1 = new javax.swing.JLabel();
        jlProfesor = new javax.swing.JLabel();
        jlCarrera = new javax.swing.JLabel();
        jtfIdClase = new javax.swing.JTextField();
        jdFecha = new com.toedter.calendar.JDateChooser();

        jCheckBox1.setText("jCheckBox1");

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Clase");
        setAlwaysOnTop(true);
        setModal(true);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        jbGrabar.setText("Grabar");
        jbGrabar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGrabarActionPerformed(evt);
            }
        });

        jlFecha.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlFecha.setText("Fecha");

        jlAgregarClase.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlAgregarClase.setText("Agregar Clase");

        jlNombre.setText("Tema");

        jlEstado.setText("Estado");

        jcbbCurso.setModel(new DefaultComboBoxModel((new CursoDAO()).getCursos().toArray()) );
        jcbbCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbCursoActionPerformed(evt);
            }
        });

        jlCurso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlCurso.setText("Curso");

        jtaTema.setColumns(20);
        jtaTema.setLineWrap(true);
        jtaTema.setRows(5);
        jScrollPane1.setViewportView(jtaTema);

        jcbbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dictada Normalmente", "Ausencia", "Feriado", "Paro" }));
        jcbbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbbEstadoActionPerformed(evt);
            }
        });

        jlAnio1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlAnio1.setText("Año: ");

        jlAnio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlAnio.setText("XXXX");

        jlProfesor1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jlProfesor1.setText("Profesor: ");

        jlProfesor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jlProfesor.setText("NombreDelProfesor");

        jlCarrera.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jlCarrera.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlCarrera.setText("NombreDeLaCarrera");

        jtfIdClase.setText("XXXXX");
        jtfIdClase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfIdClaseActionPerformed(evt);
            }
        });

        jdFecha.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbGrabar)
                        .addGap(18, 18, 18)
                        .addComponent(jbSalir)
                        .addGap(165, 165, 165))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jtfIdClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jlEstado)
                                        .addGap(18, 18, 18)
                                        .addComponent(jcbbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jlFecha)
                                            .addComponent(jlNombre)
                                            .addComponent(jlCurso))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jcbbCurso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                            .addComponent(jlCarrera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jdFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(30, 30, 30))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jlProfesor1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jlProfesor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlAnio1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlAnio))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(jlAgregarClase)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlAnio1)
                    .addComponent(jlAnio)
                    .addComponent(jlProfesor1)
                    .addComponent(jlProfesor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jlCarrera)
                .addGap(18, 18, 18)
                .addComponent(jlAgregarClase)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbbCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlCurso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jlFecha)
                    .addComponent(jdFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlNombre)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlEstado)
                    .addComponent(jcbbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jtfIdClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbGrabar)
                    .addComponent(jbSalir))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed

        // TODO add your handling code here:
        this.cerrar();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jbGrabarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGrabarActionPerformed
            // TODO add your handling code here:
            this.grabar();
    }//GEN-LAST:event_jbGrabarActionPerformed

    private void jcbbCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbCursoActionPerformed
        // TODO add your handling code here:
        
       if (jcbbCurso.getSelectedIndex()==0){
        ocultarLabel();
           
       }
       if (jcbbCurso.getSelectedIndex()!=0)
       {
           mostrarLabel((Curso)jcbbCurso.getSelectedItem());
       }
       
       
       
    }//GEN-LAST:event_jcbbCursoActionPerformed

    private void jcbbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbbEstadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbbEstadoActionPerformed

    private void jtfIdClaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfIdClaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfIdClaseActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();// used to consumed the key in the keytyped event
        }
        
    }//GEN-LAST:event_formKeyPressed

    /**
     * @param args the command line arguments
     */
    
    private void ocultarLabel(){
        jlProfesor.setVisible(false);
        jlProfesor1.setVisible(false);
        jlAnio.setVisible(false);
        jlAnio1.setVisible(false);
        jlCarrera.setVisible(false);
    }
    
    
    private void mostrarLabel(Curso c){
        
        jlProfesor.setVisible(true);
        jlProfesor1.setVisible(true);
        jlAnio.setVisible(true);
        jlAnio1.setVisible(true);
        jlCarrera.setVisible(true);
        jlProfesor.setText(c.getProfesor().toString());
        jlAnio.setText(String.valueOf(c.getAnioLectivo()));
        jlCarrera.setText(c.getCarreraMaterias().getCarrera().getDescripcion());
    }
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

    private boolean isDate(String d){
            boolean r=true;
            if (!d.equals("")){
                try {
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
                    formatoFecha.setLenient(false);
                    formatoFecha.parse(d);
                } catch (ParseException e) {
                    return false;
                }
            }
            return r;
    }
    
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
            java.util.logging.Logger.getLogger(AgregarClaseUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarClaseUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarClaseUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarClaseUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AgregarClaseUI dialog = new AgregarClaseUI(new javax.swing.JFrame(), true);
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
    //Variables Personalizadas
    private boolean grabo = false;
    private String operacion;
   


    public String getOperacion() {
        return operacion;
    }

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
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbGrabar;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<String> jcbbCurso;
    private javax.swing.JComboBox<String> jcbbEstado;
    private com.toedter.calendar.JDateChooser jdFecha;
    private javax.swing.JLabel jlAgregarClase;
    private javax.swing.JLabel jlAnio;
    private javax.swing.JLabel jlAnio1;
    private javax.swing.JLabel jlCarrera;
    private javax.swing.JLabel jlCurso;
    private javax.swing.JLabel jlEstado;
    private javax.swing.JLabel jlFecha;
    private javax.swing.JLabel jlNombre;
    private javax.swing.JLabel jlProfesor;
    private javax.swing.JLabel jlProfesor1;
    private javax.swing.JTextArea jtaTema;
    private javax.swing.JTextField jtfIdClase;
    // End of variables declaration//GEN-END:variables
    
    
    
}
