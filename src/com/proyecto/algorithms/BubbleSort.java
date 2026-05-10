package com.proyecto.algorithms;

import com.proyecto.core.SortAlgorithm;
import com.proyecto.core.SortingResult;

public class BubbleSort implements SortAlgorithm {
    
    @Override
    public String getName() {
        return "Bubble Sort";
    }

    @Override
    public SortingResult sort(int[] numbers) {
        long movements = 0;
        long startTime = System.currentTimeMillis();

        boolean swappedSomething = true;
        while (swappedSomething) {
            swappedSomething = false;
            for (int i = 0; i < numbers.length - 1; i++) {
                if (numbers[i] > numbers[i + 1]) {
                    swappedSomething = true;
                    
                    int temp = numbers[i];
                    numbers[i] = numbers[i + 1];
                    numbers[i + 1] = temp;

                    movements++;  // Movimiento
                }
            }
        }

        long endTime = System.currentTimeMillis();
        return new SortingResult(getName(), endTime - startTime, movements);
    }
}
