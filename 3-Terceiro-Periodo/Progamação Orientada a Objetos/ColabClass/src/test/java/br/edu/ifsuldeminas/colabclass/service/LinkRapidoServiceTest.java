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

import br.edu.ifsuldeminas.colabclass.model.LinkRapido;
import br.edu.ifsuldeminas.colabclass.repository.LinkRapidoRepository;

public class LinkRapidoServiceTest {

    @Mock
    private LinkRapidoRepository repository;

    private LinkRapidoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new LinkRapidoService(repository);
    }

    @Test
    void deveSalvarLinkRapido() {

        LinkRapido link = new LinkRapido();

        when(repository.save(link))
                .thenReturn(link);

        LinkRapido resultado = service.salvar(link);

        assertNotNull(resultado);
        verify(repository, times(1)).save(link);
    }

    @Test
    void deveListarTodosLinksRapidos() {

        List<LinkRapido> lista = Arrays.asList(
                new LinkRapido(),
                new LinkRapido()
        );

        when(repository.findAll()).thenReturn(lista);

        List<LinkRapido> resultado = service.listarTodos();

        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void deveBuscarLinkRapidoPorId() {

        LinkRapido link = new LinkRapido();

        when(repository.findById(1L))
                .thenReturn(Optional.of(link));

        LinkRapido resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        verify(repository).findById(1L);
    }

    @Test
    void deveRetornarNullQuandoNaoEncontrar() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        LinkRapido resultado = service.buscarPorId(1L);

        assertNull(resultado);
    }

    @Test
    void deveExcluirLinkRapido() {

        service.excluir(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void deveContarLinksRapidos() {

        when(repository.count()).thenReturn(7L);

        long quantidade = service.quantidadeLinks();

        assertEquals(7, quantidade);
    }

}