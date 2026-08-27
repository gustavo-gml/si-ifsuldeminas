package br.edu.ifsuldeminas.colabclass.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "professor_turma")
public class ProfessorTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario professor;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @Column(nullable = false)
    private boolean mostrarNaHome = true;
    

    public ProfessorTurma() {
    }

    public Long getId() {
        return id;
    }

    public Usuario getProfessor() {
        return professor;
    }

    public void setProfessor(Usuario professor) {
        this.professor = professor;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public boolean isMostrarNaHome() {
    return mostrarNaHome;
    }

    public void setMostrarNaHome(boolean mostrarNaHome) {
        this.mostrarNaHome = mostrarNaHome;
    }

    
}