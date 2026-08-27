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

import br.edu.ifsuldeminas.colabclass.model.Aviso;
import br.edu.ifsuldeminas.colabclass.repository.AvisoRepository;

public class AvisoServiceTest {

    @Mock
    private AvisoRepository repository;

    private AvisoService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new AvisoService(repository);
    }

    @Test
    void deveSalvarAviso() {

        Aviso aviso = new Aviso();

        when(repository.save(aviso)).thenReturn(aviso);

        Aviso resultado = service.salvar(aviso);

        assertNotNull(resultado);
        verify(repository, times(1)).save(aviso);
    }

    @Test
    void deveListarTodosAvisos() {

        List<Aviso> lista = Arrays.asList(
                new Aviso(),
                new Aviso());

        when(repository.findAll()).thenReturn(lista);

        List<Aviso> resultado = service.listarTodos();

        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void deveBuscarAvisoPorId() {

        Aviso aviso = new Aviso();

        when(repository.findById(1L))
                .thenReturn(Optional.of(aviso));

        Aviso resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        verify(repository).findById(1L);
    }

    @Test
    void deveRetornarNullQuandoNaoEncontrar() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        Aviso resultado = service.buscarPorId(1L);

        assertNull(resultado);
    }

    @Test
    void deveExcluirAviso() {

        service.excluir(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void deveContarAvisos() {

        when(repository.count()).thenReturn(10L);

        long quantidade = service.quantidadeAvisos();

        assertEquals(10, quantidade);
    }

}