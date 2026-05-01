package model;

import java.math.BigInteger;
import java.util.Random;

/**
 * Busqueda aleatoria en un arreglo por medio de induces aleatorios
 * Se utiliza algoritmos probabilisticos
 * Devuelve un arreglo con el indice del valor buscado y el número de intentos realizadps
 * Si el indice es -1, el valor no se encontró el elemento total  de intentos realizados
 * */

public class Probabilistic {

    private final Random random = new Random();

    public int[] randomSearch(int[] arr, int value, int attempts) {
        int[] result = {-1, 0};

        if (arr == null || arr.length == 0 || attempts <= 0) {
            return result;
        }

        for (int count = 1; count <= attempts; count++) {
            int index = random.nextInt(arr.length);

            if (arr[index] == value) {
                result[0] = index;
                result[1] = count;
                return result;
            }
        }

        result[0] = -1;
        result[1] = attempts;
        return result;
    }

    public String millerRabin(String bigNumber) {
        bigNumber = bigNumber.replaceAll("\\s+", "");
        BigInteger number = new BigInteger(bigNumber);
        int k = 5;

        if (isProbablePrime(number, k)) {
            return "The big number: " + number + " is probably prime.";
        } else {
            return "The big number: " + number + " is not prime.";
        }
    }

    private boolean isProbablePrime(BigInteger n, int k) {
        if (n.compareTo(BigInteger.ONE) <= 0) {
            return false;
        }

        if (n.equals(BigInteger.TWO) || n.equals(BigInteger.valueOf(3))) {
            return true;
        }

        if (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            return false;
        }

        BigInteger d = n.subtract(BigInteger.ONE);
        int r = 0;

        while (d.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.TWO);
            r++;
        }

        for (int i = 0; i < k; i++) {
            BigInteger a;

            do {
                a = new BigInteger(n.bitLength(), random);
            } while (a.compareTo(BigInteger.TWO) < 0 || a.compareTo(n.subtract(BigInteger.TWO)) > 0);

            BigInteger x = a.modPow(d, n);

            if (x.equals(BigInteger.ONE) || x.equals(n.subtract(BigInteger.ONE))) {
                continue;
            }

            boolean validRound = false;

            for (int j = 0; j < r - 1; j++) {
                x = x.modPow(BigInteger.TWO, n);

                if (x.equals(n.subtract(BigInteger.ONE))) {
                    validRound = true;
                    break;
                }
            }

            if (!validRound) {
                return false;
            }
        }

        return true;
    }
}
