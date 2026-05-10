package com.proyecto.algorithms;

import com.proyecto.core.SortAlgorithm;
import com.proyecto.core.SortingResult;

public class MergeSort implements SortAlgorithm {
    private long movements;
    
    @Override
    public String getName() {
        return "Merge Sort";
    }

    @Override
    public SortingResult sort(int[] array) {
        movements = 0;
        long startTime = System.currentTimeMillis();

        recursiveMergeSort(array);

        long endTime = System.currentTimeMillis();
        return new SortingResult(getName(), endTime - startTime, movements);
    }

    private void recursiveMergeSort(int[] inputArray) {
        int inputLength = inputArray.length;
        if (inputLength < 2) return;

        int midIndex = inputLength / 2;
        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputLength - midIndex];

        System.arraycopy(inputArray, 0, leftHalf, 0, midIndex);
        System.arraycopy(inputArray, midIndex, rightHalf, 0, inputLength - midIndex);

        recursiveMergeSort(leftHalf);
        recursiveMergeSort(rightHalf);

        merge(inputArray, leftHalf, rightHalf);
    }

    private void merge(int[] inputArray, int[] leftHalf, int[] rightHalf) {
        int i = 0, j = 0, k = 0;

        while (i < leftHalf.length && j < rightHalf.length) {
            if (leftHalf[i] <= rightHalf[j]) {
                inputArray[k] = leftHalf[i];
                i++;
            }
            else {
                inputArray[k] = rightHalf[j];
                j++;
            }
            movements++; // Movimiento (arreglo original)
            k++;
        }

        while (i < leftHalf.length) {
            inputArray[k] = leftHalf[i];
            i++; k++; movements++;  // Movimiento
        }

        while (j < rightHalf.length) {
            inputArray[k] = rightHalf[j];
            j++; k++; movements++;  // Movimiento
        }
    }
}
