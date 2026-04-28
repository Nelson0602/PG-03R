package model;

import static org.junit.jupiter.api.Assertions.*;

import model.Primo;
import org.junit.jupiter.api.Test;



class PrimoTest {

    @Test
    void PrimoDel1Al100() {
        for (int numero = 1; numero <= 100; numero++) {
            String resultado = Primo.esPrimo(numero) ? "primo" : "no primo";
            System.out.println(numero + " -> " + resultado);
        }

    }
}