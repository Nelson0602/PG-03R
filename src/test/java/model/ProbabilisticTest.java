package model;

import model.Probabilistic;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

class ProbabilisticTest {

    @Test
    void millerRabinTest() {
        Probabilistic p = new Probabilistic();

        String[] numbers = {
                "29341",
                "131071",
                "214748",
                "483647",
                "2147483647",
                "2305843009213693951"
        };

        System.out.println("Miller-Rabin Test");

        for (String number : numbers) {
            System.out.println("Number: " + number);
            System.out.println(p.millerRabin(number));
            System.out.println("_".repeat(50));
        }
    }

    @Test
    void randomSearchTest() {
        Probabilistic p = new Probabilistic();
        Random random = new Random();

        int[] arr = new int[100];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        int value = random.nextInt(100) + 1;
        int attempts = 20;

        System.out.println("Random Search Test");
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Value to search: " + value);
        System.out.println("_".repeat(50));

        for (int i = 1; i <= 20; i++) {
            int[] result = p.randomSearch(arr, value, attempts);

            if (result[0] != -1) {
                System.out.println("Search " + i + ": Item [" + value + "] found in index: " + result[0]
                        + ". Attempts: " + result[1]
                        + ". Max attempts allowed: " + attempts);
            } else {
                System.out.println("Search " + i + ": Item [" + value + "] not found. Attempts: "
                        + result[1]
                        + ". Max attempts allowed: " + attempts);
            }
        }
    }
}