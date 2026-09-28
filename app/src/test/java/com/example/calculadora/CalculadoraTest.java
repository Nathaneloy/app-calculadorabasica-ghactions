package com.example.calculadora;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculadoraTest {
    Calculadora sut = new Calculadora();

    @Test
    public void testSumar(){
        sut.setOper1(4d);
        sut.setOper2(5d);
        assertEquals(9.0d, sut.opera(), 0.001d);
    }
}
