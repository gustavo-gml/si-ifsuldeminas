package br.edu.ifsuldeminas.colabclass.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import br.edu.ifsuldeminas.colabclass.model.Evento;
import br.edu.ifsuldeminas.colabclass.repository.EventoRepository;

public class EventoServiceTest {

    @Mock
    private EventoRepository repository;

    @Mock
    private EntregaEventoService entregaEventoService;

    private EventoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        service = new EventoService(
        repository,
        entregaEventoService
);
    }

    @Test
    void deveSalvarEvento() {

        Evento evento = new Evento();

        when(repository.save(evento))
                .thenReturn(evento);

        Evento resultado = service.salvar(evento);

        assertNotNull(resultado);
        verify(repository, times(1)).save(evento);
    }

    @Test
    void deveListarTodosEventos() {

        List<Evento> lista = Arrays.asList(
                new Evento(),
                new Evento()
        );

        when(repository.findAll()).thenReturn(lista);

        List<Evento> resultado = service.listarTodos();

        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void deveBuscarEventoPorId() {

        Evento evento = new Evento();

        when(repository.findById(1L))
                .thenReturn(Optional.of(evento));

        Evento resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        verify(repository).findById(1L);
    }

    @Test
    void deveRetornarNullQuandoNaoEncontrar() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        Evento resultado = service.buscarPorId(1L);

        assertNull(resultado);
    }

    @Test
    void deveExcluirEvento() {

        service.excluir(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void deveContarEventos() {

        when(repository.count()).thenReturn(5L);

        long quantidade = service.quantidadeEventos();

        assertEquals(5, quantidade);
    }

}