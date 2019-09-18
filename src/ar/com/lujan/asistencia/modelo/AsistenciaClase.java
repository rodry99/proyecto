/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.modelo;

/**
 *
 * @author Papa
 */
public class AsistenciaClase {
    private Integer idAsistencia;
    private CursoAlumno cursoAlumno;
    private Clase clase;
    private Boolean asistencia;

    public Integer getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Integer idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public CursoAlumno getCursoAlumno() {
        return cursoAlumno;
    }

    public void setCursoAlumno(CursoAlumno cursoAlumno) {
        this.cursoAlumno = cursoAlumno;
    }

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Boolean getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Boolean asistencia) {
        this.asistencia = asistencia;
    }
    
}
