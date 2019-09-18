/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.modelo;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author geragonzalez
 */
public class CursoAlumnosTableModel  extends AbstractTableModel{
    private List<CursoAlumno> cursoAlumnos;
    private static  String[] COLUMNAS={"ID", "ID", "APELLIDO", "NOMBRE", "DNI"};

    public CursoAlumnosTableModel (List<CursoAlumno> cursoAlumnos){
        super();
        this.cursoAlumnos = cursoAlumnos;
    }

    @Override
    public int getRowCount() {
        return this.cursoAlumnos.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length; }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retorno  = null;
        CursoAlumno ca = this.cursoAlumnos.get(rowIndex);
        switch (columnIndex){
            case 0: retorno = ca.getCurso().getIdCurso(); break;
            case 1: retorno = ca.getAlumno().getID(); break;
            case 2: retorno = ca.getAlumno().getApellido(); break;
            case 3: retorno = ca.getAlumno().getNombre(); break;
            case 4: retorno = ca.getAlumno().getDNI(); break;
        }
        return retorno;
    }
    
    @Override
    public String getColumnName(int index){
         return COLUMNAS[index];
    }

}
