package br.edu.ifsuldeminas.colabclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifsuldeminas.colabclass.model.Meta;
import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.repository.MetaRepository;

@Service
public class MetaService {

    private final MetaRepository repository;

    public MetaService(MetaRepository repository) {

        this.repository = repository;

    }

    public Meta salvar(Meta meta){

        return repository.save(meta);

    }

    public List<Meta> listarPorUsuario(Usuario usuario){

        return repository.findByUsuario(usuario);

    }

    public Meta buscarPorId(Long id){

        return repository.findById(id).orElse(null);

    }

    public void excluir(Long id){

        repository.deleteById(id);

    }

}