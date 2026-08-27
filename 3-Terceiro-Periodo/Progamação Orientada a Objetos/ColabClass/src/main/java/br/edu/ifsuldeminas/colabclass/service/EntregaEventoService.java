package br.edu.ifsuldeminas.colabclass.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.edu.ifsuldeminas.colabclass.model.EntregaEvento;
import br.edu.ifsuldeminas.colabclass.model.Evento;
import br.edu.ifsuldeminas.colabclass.model.Turma;
import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.model.enums.StatusEvento;
import br.edu.ifsuldeminas.colabclass.repository.EntregaEventoRepository;
import br.edu.ifsuldeminas.colabclass.repository.UsuarioRepository;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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

    public void marcarComoEntregue(Evento evento, Usuario usuario){

    EntregaEvento entrega =
            entregaRepository
                    .findByEventoAndUsuario(evento, usuario)
                    .orElse(null);

    if(entrega == null){

   
        entrega = new EntregaEvento();
        entrega.setEvento(evento);
        entrega.setUsuario(usuario);
        entrega.setStatus(StatusEvento.ENTREGUE);
        entrega.setDataEntrega(LocalDate.now());

        entregaRepository.save(entrega);
        return;
    }

    entrega.setStatus(StatusEvento.ENTREGUE);
    entrega.setDataEntrega(LocalDate.now());

    entregaRepository.save(entrega);
}
public List<EntregaEvento> listarPorUsuario(
        Usuario usuario){

    return entregaRepository.findByUsuario(usuario);

}
 public EntregaEvento buscarPorEventoEUsuario(Evento evento, Usuario usuario) {
    return entregaRepository
            .findByEventoAndUsuario(evento, usuario)
            .orElse(null);
}
public Map<Long, EntregaEvento> mapPorUsuarioETurma(
        Usuario usuario,
        Turma turma,
        List<Evento> eventos) {

    List<EntregaEvento> entregas =
            entregaRepository.findByUsuario(usuario);

    Map<Long, EntregaEvento> map = new HashMap<>();

    for (Evento e : eventos) {
        map.put(e.getId(), null);
    }

  
    for (EntregaEvento entrega : entregas) {
        map.put(
                entrega.getEvento().getId(),
                entrega
        );
    }

    return map;
}
}