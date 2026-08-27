package br.edu.ifsuldeminas.colabclass.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifsuldeminas.colabclass.model.Aviso;
import br.edu.ifsuldeminas.colabclass.model.Turma;
import br.edu.ifsuldeminas.colabclass.repository.AvisoRepository;

@Service
public class AvisoService {

    private final AvisoRepository avisoRepository;

    public AvisoService(
            AvisoRepository avisoRepository) {

        this.avisoRepository = avisoRepository;
    }

    public Aviso salvar(Aviso aviso){

        if(aviso.getDataCriacao() == null){

            aviso.setDataCriacao(LocalDateTime.now());

        }

        return avisoRepository.save(aviso);
    }

    public List<Aviso> listarPorTurma(
            Turma turma) {

        return avisoRepository.findByTurma(turma);
    }

    public long quantidadeAvisos() {

    return avisoRepository.count();

    }

    public Aviso buscarPorId(Long id){

    return avisoRepository
            .findById(id)
            .orElse(null);
    }

    public void excluir(Long id){

    avisoRepository.deleteById(id);
    }

    public List<Aviso> listarTodos(){

    return avisoRepository.findAll();

    }

    
}