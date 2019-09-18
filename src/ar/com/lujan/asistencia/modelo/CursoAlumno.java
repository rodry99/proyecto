/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.lujan.asistencia.modelo;

/**
 *
 * @author geragonzalez
 */
public class CursoAlumno {
    private int idCursoAlumno;
    private Curso curso;
    private Alumno alumno;
    private String estado;

    public int getIdCursoAlumno() {
        return idCursoAlumno;
    }

    public void setIdCursoAlumno(int idCursoAlumno) {
        this.idCursoAlumno = idCursoAlumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    } 
}
