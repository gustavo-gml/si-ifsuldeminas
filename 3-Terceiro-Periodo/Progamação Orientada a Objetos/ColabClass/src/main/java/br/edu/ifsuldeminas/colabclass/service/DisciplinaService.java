package br.edu.ifsuldeminas.colabclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifsuldeminas.colabclass.model.Disciplina;
import br.edu.ifsuldeminas.colabclass.model.Turma;
import br.edu.ifsuldeminas.colabclass.repository.DisciplinaRepository;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(
            DisciplinaRepository disciplinaRepository) {

        this.disciplinaRepository = disciplinaRepository;
    }

    public Disciplina salvar(Disciplina disciplina){
        return disciplinaRepository.save(disciplina);
    }

    public List<Disciplina> listarTodas(){
        return disciplinaRepository.findAll();
    }

    public List<Disciplina> listarPorTurma(
        Turma turma) {

    return disciplinaRepository
            .findByTurmaAndAtivaTrue(turma);
    }

    public long quantidadeDisciplinas() {

    return disciplinaRepository.count();

    }

    public Disciplina buscarPorId(
        Long id){

    return disciplinaRepository
            .findById(id)
            .orElse(null);
    }

    public void excluir(
        Long id){

    disciplinaRepository
            .deleteById(id);
    }

    
}