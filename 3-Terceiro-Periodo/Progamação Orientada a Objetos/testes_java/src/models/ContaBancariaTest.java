package models;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class ContaBancariaTest {
    private ContaBancaria conta;

    @BeforeEach
    void setUp(){
        conta = new ContaBancaria(100);
    }

    @Test
    void testCriarContaSaldoPositivo(){
        ContaBancaria conta2 = new ContaBancaria(2000);
        assertEquals(2000, conta2.getSaldo(), "O valor de inicialização deveria ser 2000");
    }

    @Test
    void testCriarContaSaldoNegativo(){
        assertThrows(IllegalArgumentException.class, () -> new ContaBancaria(-1000), "Não deveria ser possivel inicializar uma conta com valor negativo");
    }

    @Test
    void testDepositarValorPositivo(){
        double valorInicial = conta.getSaldo();
        conta.depositar(100.00);
        assertEquals(valorInicial + 100, conta.getSaldo(), "O saldo deveria ser 100 + o valor inicial da conta ("+(valorInicial+100) +")");

    }

    @Test
    void testDepositarValorNegativo(){
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(-1000), "Não deveria ser possível realizar depósitos negativos");
    }

    @Test
    void testSacarValorValido(){
        double valorInicial = conta.getSaldo();
        conta.saque(100.00);
        assertEquals(valorInicial - 100, conta.getSaldo(), "O saldo deveria ser 100 - o valor inicial da conta ("+(valorInicial+100) +")");
    }

    @Test
    void testSacarSaldoInsuficiente(){
        assertThrows(IllegalStateException.class, () -> conta.saque(150), "O valor de saque não pode ser maior que o saldo");
    }

    @Test
    void testSacarValorNegativo(){
        assertThrows(IllegalArgumentException.class, () -> conta.saque(-1000), "Não deveria ser possível realizar saques negativos");
    }

    @Test
    void testDepositarValorZero() {
        assertThrows(IllegalArgumentException.class, () -> conta.depositar(0),
                "Não deveria ser possível realizar depósitos com valor zero");
    }

    @Test
    void testCriarContaSaldoZero() {
        ContaBancaria contaZero = new ContaBancaria(0);
        assertEquals(0.0, contaZero.getSaldo(), "A conta deveria ser inicializada com saldo zero");
    }

}
