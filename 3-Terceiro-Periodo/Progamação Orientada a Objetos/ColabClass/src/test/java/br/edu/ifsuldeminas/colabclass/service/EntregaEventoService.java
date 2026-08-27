package br.edu.ifsuldeminas.colabclass.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.edu.ifsuldeminas.colabclass.model.EntregaEvento;
import br.edu.ifsuldeminas.colabclass.model.Evento;
import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.model.enums.StatusEvento;
import br.edu.ifsuldeminas.colabclass.repository.EntregaEventoRepository;
import br.edu.ifsuldeminas.colabclass.repository.UsuarioRepository;

@Service
public class EntregaEventoService {

    private final EntregaEventoRepository entregaRepository;
    private final UsuarioRepository usuarioRepository;

    public EntregaEventoService(
            EntregaEventoRepository entregaRepository,
            UsuarioRepository usuarioRepository) {

        this.entregaRepository = entregaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public void criarEntregas(Evento evento) {

        List<Usuario> alunos =
                usuarioRepository.findByTurma(evento.getTurma());

        for (Usuario aluno : alunos) {

            EntregaEvento entrega = new EntregaEvento();

            entrega.setEvento(evento);
            entrega.setUsuario(aluno);
            entrega.setStatus(StatusEvento.PENDENTE);

            entregaRepository.save(entrega);
        }
    }

    public void marcarComoEntregue(
            Evento evento,
            Usuario usuario) {

        EntregaEvento entrega =
                entregaRepository
                        .findByEventoAndUsuario(evento, usuario)
                        .orElse(null);

        if (entrega == null) {
            return;
        }

        entrega.setStatus(StatusEvento.ENTREGUE);
        entrega.setDataEntrega(LocalDate.now());

        entregaRepository.save(entrega);
    }

    public List<EntregaEvento> listarPorUsuario(
            Usuario usuario) {

        return entregaRepository.findByUsuario(usuario);
    }

    public Map<Long, EntregaEvento> listarMapaPorUsuario(
            Usuario usuario) {

        List<EntregaEvento> entregas =
                entregaRepository.findByUsuario(usuario);

        Map<Long, EntregaEvento> mapa = new HashMap<>();

        for (EntregaEvento entrega : entregas) {

            mapa.put(
                    entrega.getEvento().getId(),
                    entrega);
        }

        return mapa;
    }
}