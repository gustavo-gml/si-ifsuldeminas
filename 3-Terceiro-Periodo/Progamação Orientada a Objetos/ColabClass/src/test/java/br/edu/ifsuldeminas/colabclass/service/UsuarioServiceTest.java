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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import br.edu.ifsuldeminas.colabclass.model.Role;
import br.edu.ifsuldeminas.colabclass.model.Usuario;
import br.edu.ifsuldeminas.colabclass.repository.UsuarioRepository;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    private UsuarioService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new UsuarioService(repository);
    }

    @Test
    void deveSalvarUsuario() {

        Usuario usuario = new Usuario();

        when(repository.save(usuario)).thenReturn(usuario);

        Usuario resultado = service.salvar(usuario);

        assertNotNull(resultado);
        verify(repository).save(usuario);
    }

    @Test
    void deveListarTodosUsuarios() {

        List<Usuario> lista = Arrays.asList(
                new Usuario(),
                new Usuario());

        when(repository.findAll()).thenReturn(lista);

        List<Usuario> resultado = service.listarTodos();

        assertEquals(2, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void deveBuscarUsuarioPorId() {

        Usuario usuario = new Usuario();

        when(repository.findById(1L))
                .thenReturn(Optional.of(usuario));

        Usuario resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        verify(repository).findById(1L);
    }

    @Test
    void deveBuscarUsuarioPorEmail() {

        Usuario usuario = new Usuario();

        when(repository.findByEmail("teste@email.com"))
                .thenReturn(Optional.of(usuario));

        Usuario resultado = service.buscarPorEmail("teste@email.com");

        assertNotNull(resultado);
        verify(repository).findByEmail("teste@email.com");
    }

    @Test
    void deveRetornarNullQuandoEmailNaoExistir() {

        when(repository.findByEmail("teste@email.com"))
                .thenReturn(Optional.empty());

        Usuario resultado = service.buscarPorEmail("teste@email.com");

        assertNull(resultado);
    }

    @Test
    void deveContarUsuarios() {

        when(repository.count()).thenReturn(20L);

        long quantidade = service.quantidadeUsuarios();

        assertEquals(20, quantidade);
    }

    @Test
    void deveTornarRepresentante() {

        Usuario usuario = new Usuario();

        when(repository.findById(1L))
                .thenReturn(Optional.of(usuario));

        service.tornarRepresentante(1L);

        assertEquals(Role.REPRESENTANTE, usuario.getRole());
        verify(repository).save(usuario);
    }

    @Test
    void deveTornarAdmin() {

        Usuario usuario = new Usuario();

        when(repository.findById(1L))
                .thenReturn(Optional.of(usuario));

        service.tornarAdmin(1L);

        assertEquals(Role.ADMIN, usuario.getRole());
        verify(repository).save(usuario);
    }

    @Test
    void deveTornarAluno() {

        Usuario usuario = new Usuario();

        when(repository.findById(1L))
                .thenReturn(Optional.of(usuario));

        service.tornarAluno(1L);

        assertEquals(Role.ALUNO, usuario.getRole());
        verify(repository).save(usuario);
    }

}