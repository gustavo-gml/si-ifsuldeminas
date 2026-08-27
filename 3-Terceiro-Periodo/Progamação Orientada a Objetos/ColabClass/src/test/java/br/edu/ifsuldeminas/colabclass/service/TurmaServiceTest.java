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

import br.edu.ifsuldeminas.colabclass.model.Turma;
import br.edu.ifsuldeminas.colabclass.repository.DisciplinaRepository;
import br.edu.ifsuldeminas.colabclass.repository.TurmaRepository;

public class TurmaServiceTest {

    @Mock
    private TurmaRepository repository;

    @Mock
    private DisciplinaRepository disciplinaRepository;

    private TurmaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new TurmaService(repository, disciplinaRepository);
    }

    @Test
    void deveSalvarTurma() {

        Turma turma = new Turma();

        when(repository.save(turma))
                .thenReturn(turma);

        Turma resultado = service.salvar(turma);

        assertNotNull(resultado);
        verify(repository, times(1)).save(turma);
    }

    @Test
    void deveListarTodasTurmas() {

        List<Turma> lista = Arrays.asList(
                new Turma(),
                new Turma()
        );

        when(repository.findAll()).thenReturn(lista);

        List<Turma> resultado = service.listarTodas();

        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void deveBuscarTurmaPorId() {

        Turma turma = new Turma();

        when(repository.findById(1L))
                .thenReturn(Optional.of(turma));

        Turma resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        verify(repository).findById(1L);
    }

    @Test
    void deveRetornarNullQuandoNaoEncontrar() {

        when(repository.findById(1L))
                .thenReturn(Optional.empty());

        Turma resultado = service.buscarPorId(1L);

        assertNull(resultado);
    }

    @Test
    void deveBuscarTurmaPorCodigo() {

        Turma turma = new Turma();

        when(repository.findByCodigo("SI2026"))
                .thenReturn(Optional.of(turma));

        Turma resultado = service.buscarPorCodigo("SI2026");

        assertNotNull(resultado);
        verify(repository).findByCodigo("SI2026");
    }

    @Test
    void deveRetornarNullQuandoCodigoNaoExistir() {

        when(repository.findByCodigo("SI2026"))
                .thenReturn(Optional.empty());

        Turma resultado = service.buscarPorCodigo("SI2026");

        assertNull(resultado);
    }

    @Test
    void deveExcluirTurma() {

        Turma turma = new Turma();

        when(repository.findById(1L))
                .thenReturn(Optional.of(turma));

        when(disciplinaRepository.countByTurma(turma))
                .thenReturn(0L);

        boolean resultado = service.excluir(1L);

        assertEquals(true, resultado);

        verify(repository).delete(turma);

    }

    @Test
    void deveContarTurmas() {

        when(repository.count()).thenReturn(3L);

        long quantidade = service.quantidadeTurmas();

        assertEquals(3, quantidade);
    }

    @Test
    void naoDeveExcluirTurmaComDisciplinas() {

        Turma turma = new Turma();

        when(repository.findById(1L))
                .thenReturn(Optional.of(turma));

        when(disciplinaRepository.countByTurma(turma))
                .thenReturn(5L);

        boolean resultado = service.excluir(1L);

        assertEquals(false, resultado);

        verify(repository, times(0)).delete(turma);

    }

}