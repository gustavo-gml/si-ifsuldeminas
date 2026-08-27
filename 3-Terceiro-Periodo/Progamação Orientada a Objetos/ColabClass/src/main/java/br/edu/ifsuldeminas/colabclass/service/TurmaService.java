package br.edu.ifsuldeminas.colabclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifsuldeminas.colabclass.model.Turma;
import br.edu.ifsuldeminas.colabclass.repository.DisciplinaRepository;
import br.edu.ifsuldeminas.colabclass.repository.TurmaRepository;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final DisciplinaRepository disciplinaRepository;
    

    public TurmaService(
            TurmaRepository turmaRepository,
            DisciplinaRepository disciplinaRepository) {

        this.turmaRepository = turmaRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    public Turma salvar(Turma turma){
        return turmaRepository.save(turma);
    }

    public List<Turma> listarTodas() {
        return turmaRepository.findAll();
    }

    public Turma buscarPorCodigo(String codigo) {
    return turmaRepository
            .findByCodigo(codigo)
            .orElse(null);
    }

    public long quantidadeTurmas() {

    return turmaRepository.count();
    }

    public boolean excluir(Long id) {

        Turma turma = turmaRepository.findById(id).orElse(null);

        if (turma == null) {
            return false;
        }

        if (disciplinaRepository.countByTurma(turma) > 0) {
            return false;
        }

        turmaRepository.delete(turma);

        return true;
    }

    public Turma buscarPorId(Long id){

    return turmaRepository
            .findById(id)
            .orElse(null);
    }

    
}