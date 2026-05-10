package com.proyecto.algorithms;

import com.proyecto.core.SortAlgorithm;
import com.proyecto.core.SortingResult;

public class InsertionSort implements SortAlgorithm {

    @Override
    public String getName() {
        return "Insertion Sort";
    }

    @Override
    public SortingResult sort(int[] array) {
        long movements = 0;
        long startTime = System.currentTimeMillis();

        for (int i = 1; i < array.length; i++) {
            int currentValue = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > currentValue) {
                array[j + 1] = array[j];
                movements++;  // Movimiento (desplazamiento)
                j--;
            }
            array[j + 1] = currentValue;
            movements++; // Movimiento (inserción)
        }
        
        long endTime = System.currentTimeMillis();
        return new SortingResult(getName(), endTime - startTime, movements);
    }
}
