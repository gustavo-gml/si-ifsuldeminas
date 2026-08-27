package br.edu.ifsuldeminas.colabclass.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.edu.ifsuldeminas.colabclass.model.enums.StatusEvento;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private LocalDate data;

    private String tipo;

    private LocalDateTime dataPublicacao;

    private LocalDateTime ultimaEdicao;

    @Enumerated(EnumType.STRING)
    private StatusEvento status = StatusEvento.PENDENTE;


    @Column(length = 1000)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "turma_id")
    private Turma turma;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private Disciplina disciplina;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    public void atualizarStatusAutomatico() {

        if (status == StatusEvento.PENDENTE &&
            data.isBefore(LocalDate.now())) {

            status = StatusEvento.ATRASADO;
        }

    }

    public Evento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
    this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDateTime getDataPublicacao() {
    return dataPublicacao;
    }

    public void setDataPublicacao(LocalDateTime dataPublicacao) {
    this.dataPublicacao = dataPublicacao;
    }

    public Usuario getAutor() {
    return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public LocalDateTime getUltimaEdicao() {
    return ultimaEdicao;
}

    public void setUltimaEdicao(LocalDateTime ultimaEdicao) {
        this.ultimaEdicao = ultimaEdicao;
    }
}