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
 * @author impztz
 */
public class MateriaTableModel extends AbstractTableModel {
    
    private List<Materia> materia;
    private static  String[] COLUMNAS={"idMateria", "Materia"};
    
    
    public MateriaTableModel (List<Materia> materia){
        super();
        this.materia = materia;
    }
    
    @Override
    public int getRowCount() {
        return materia.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object retorno  = null;
        Materia c = this.materia.get(rowIndex);
        switch (columnIndex){
            case 0: retorno = c.getIdMateria(); break;
            case 1: retorno = c.getDescripcion(); break;
        }
        return retorno;
        
    }
    
    @Override
    public String getColumnName(int index){
         return COLUMNAS[index];
    }
}
