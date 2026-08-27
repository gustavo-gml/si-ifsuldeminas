package br.edu.ifsuldeminas.colabclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifsuldeminas.colabclass.model.LinkRapido;
import br.edu.ifsuldeminas.colabclass.model.Turma;
import br.edu.ifsuldeminas.colabclass.repository.LinkRapidoRepository;

@Service
public class LinkRapidoService {

    private final LinkRapidoRepository repository;

    public LinkRapidoService(
            LinkRapidoRepository repository) {

        this.repository = repository;
    }

    public LinkRapido salvar(
            LinkRapido linkRapido) {

        return repository.save(linkRapido);
    }

    public List<LinkRapido> listarPorTurma(
            Turma turma) {

        return repository.findByTurma(turma);
    }

    public long quantidadeLinks() {

    return repository.count();
    }

    public LinkRapido buscarPorId(Long id){

        return repository
                .findById(id)
                .orElse(null);

    }

    public void excluir(Long id){

        repository.deleteById(id);

    }

    public List<LinkRapido> listarTodos(){

        return repository.findAll();

    }
}