package models;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class EstoqueTest {
    Estoque estoque;

    void setUp(){
        estoque = new Estoque(100);
    }
    @Test
    void testCriarEstoqueSaldoPositivo(){
        Estoque estoque2 = new Estoque(120);
        assertEquals(120, estoque2.getQuantidade(), "O valor de inicialização deveria ser 120");
    }

    @Test
    void testCriarEstoqueSaldoNegativo(){
        assertThrows(IllegalArgumentException.class, () -> new Estoque(-1000), "Não deveria ser possivel inicializar um estoque com valor negativo");
    }

    @Test
    void testAdicionarValorPositivo(){
        int valorInicial = estoque.getQuantidade();
        estoque.adicionar(100);
        assertEquals(valorInicial + 100, estoque.getQuantidade(), "A quantidade deveria ser 100 + o valor inicial da conta ("+(valorInicial+100) +")");

    }

    @Test
    void testRemoverValorNegativo(){
        assertThrows(IllegalArgumentException.class, () -> estoque.remover(-1000), "Não deveria ser possível realizar remoções negativas");
    }

    @Test
    void testRemoverValorValido(){
        int valorInicial = estoque.getQuantidade();
        estoque.remover(100);
        assertEquals(valorInicial - 100, estoque.getQuantidade(), "A quantidade deveria ser o valor inicial - 100 ("+(valorInicial+100) +")");
    }

}
