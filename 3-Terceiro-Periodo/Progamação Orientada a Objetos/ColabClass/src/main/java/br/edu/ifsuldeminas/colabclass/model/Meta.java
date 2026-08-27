package br.edu.ifsuldeminas.colabclass.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "metas")
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 1000)
    private String descricao;

    private LocalDate dataLimite;

    private Boolean concluida = false;
    
    public Boolean getConcluida() {
    return concluida;
    }

    public void setConcluida(Boolean concluida) {
        this.concluida = concluida;
    }

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}