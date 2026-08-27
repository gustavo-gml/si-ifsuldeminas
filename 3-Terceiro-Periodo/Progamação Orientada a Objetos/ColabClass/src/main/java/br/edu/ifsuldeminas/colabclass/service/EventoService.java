package br.edu.ifsuldeminas.colabclass.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.edu.ifsuldeminas.colabclass.model.Evento;
import br.edu.ifsuldeminas.colabclass.model.Turma;
import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.repository.EventoRepository;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final EntregaEventoService entregaEventoService;

    public EventoService(
            EventoRepository eventoRepository,
            EntregaEventoService entregaEventoService) {

        this.eventoRepository = eventoRepository;
        this.entregaEventoService = entregaEventoService;
    }

    public Evento salvar(Evento evento) {

        if (evento.getDataPublicacao() == null) {
            evento.setDataPublicacao(LocalDateTime.now());
        }

        return eventoRepository.save(evento);
    }

    public List<Evento> listarPorTurma(Turma turma) {
        return eventoRepository.findByTurmaOrderByDataAsc(turma);
    }

    public long quantidadeEventos() {
        return eventoRepository.count();
    }

    public Evento buscarPorId(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        eventoRepository.deleteById(id);
    }

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Evento criarEvento(Evento evento) {

        if (evento.getDataPublicacao() == null) {
            evento.setDataPublicacao(LocalDateTime.now());
        }

        Evento salvo = eventoRepository.save(evento);

        if ("TRABALHO".equalsIgnoreCase(salvo.getTipo())) {
            entregaEventoService.criarEntregas(salvo);
        }

        return salvo;
    }
}