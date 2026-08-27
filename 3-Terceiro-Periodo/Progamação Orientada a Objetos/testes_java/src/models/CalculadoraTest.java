package models;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {
    private Calculadora calc;

    @BeforeEach
    void setUp(){
        calc = new Calculadora();
    }

    @Test
    void testSomaPositivos(){
        int resultado = calc.somar(5,3);
        assertEquals(8, resultado, "A soma de 2+3 deveria ser 8, meu senhor");
    }

    @Test
    void testSubtracaoNegativos(){
        int resultado = calc.subtrair(2,5);
        assertEquals(-3, resultado, "A soma de  deveria ser -3, meu senhor");
    }

    @Test
    void testMultZero(){
        int resultado = calc.multiplicar(0,10);
        assertEquals(0, resultado, "A multiplicação de 0*10 deveria ser 0");
    }

    @Test
    void testDivExata(){
        int resultado = calc.dividir(10,2);
        assertEquals(5, resultado, "A multiplicação de 0*10 deveria ser 0");
    }

    @Test
    void testDivZero(){
        assertThrows( IllegalArgumentException.class, ()-> calc.dividir(10, 0), "A multiplicação de 0*10 deveria ser 0");
    }

    @Test
    void testMulNegativos(){
        int resultado = calc.multiplicar(-2,-3);
        assertEquals(6, resultado, "O resultado de -2 * -3 deveria ser 6");
    }

    @Test
    void testDivNegativos(){
        int resultado = calc.dividir(-10,-2);
        assertEquals(5, resultado, "O resultado de -2 * -3 deveria ser 6");
    }


}
