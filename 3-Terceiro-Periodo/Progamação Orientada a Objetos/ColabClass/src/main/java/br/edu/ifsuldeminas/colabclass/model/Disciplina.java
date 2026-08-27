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


@Table(name = "disciplinas")
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Integer periodo;

    private Boolean ativa;

    private String professor;

    private String emailProfessor;

    private String codigoClassroom;

    private String horario;

    @Column(length = 5000)
    private String ementa;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    public Disciplina() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
    this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

    public String getProfessor() {
    return professor;
}

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getEmailProfessor() {
        return emailProfessor;
    }

    public void setEmailProfessor(String emailProfessor) {
        this.emailProfessor = emailProfessor;
    }

    public String getCodigoClassroom() {
        return codigoClassroom;
    }

    public void setCodigoClassroom(String codigoClassroom) {
        this.codigoClassroom = codigoClassroom;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
    public String getEmenta() {
    return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }
}