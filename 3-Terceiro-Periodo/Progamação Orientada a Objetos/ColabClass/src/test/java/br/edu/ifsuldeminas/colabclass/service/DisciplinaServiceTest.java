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

import br.edu.ifsuldeminas.colabclass.model.Disciplina;
import br.edu.ifsuldeminas.colabclass.repository.DisciplinaRepository;

public class DisciplinaServiceTest {

    @Mock
    private DisciplinaRepository repository;

    private DisciplinaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new DisciplinaService(repository);
    }

    @Test
    void deveSalvarDisciplina() {

        Disciplina disciplina = new Disciplina();

        when(repository.save(disciplina))
                .thenReturn(disciplina);

        Disciplina resultado = service.salvar(disciplina);

        assertNotNull(resultado);
        verify(repository, times(1)).save(disciplina);
    }

    @Test
    void deveListarTodasDisciplinas() {

        List<Disciplina> lista = Arrays.asList(
                new Disciplina(),
                new Disciplina()
        );

        when(repository.findAll()).thenReturn(lista);

        List<Disciplina> resultado = service.listarTodas();

        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void deveBuscarDisciplinaPorId() {

        Disciplina disciplina = new Disciplina();

        when(repository.findById(1L))
                .thenReturn(Optional.of(disciplina));

        Disciplina resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        verify(repository).findById(1L);
    }

    @Test
    void deveRetornarNullQuandoNaoEncontrar() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        Disciplina resultado = service.buscarPorId(1L);

        assertNull(resultado);
    }

    @Test
    void deveExcluirDisciplina() {

        service.excluir(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void deveContarDisciplinas() {

        when(repository.count()).thenReturn(8L);

        long quantidade = service.quantidadeDisciplinas();

        assertEquals(8, quantidade);
    }

}