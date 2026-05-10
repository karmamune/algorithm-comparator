package com.proyecto.algorithms;

import java.util.Random;

import com.proyecto.core.SortAlgorithm;
import com.proyecto.core.SortingResult;

public class QuickSort implements SortAlgorithm {
    private long movements;

    @Override
    public String getName() {
        return "Quick Sort";
    }

    @Override
    public SortingResult sort(int[] array) {
        movements = 0;
        long startTime = System.currentTimeMillis();

        quickSort(array, 0, array.length - 1);

        long endTime = System.currentTimeMillis();
        return new SortingResult(getName(), endTime - startTime, movements);
    }

    private void quickSort(int[] array, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) return;

        int pivotIndex = new Random().nextInt(highIndex - lowIndex + 1) + lowIndex;
        int pivot = array[pivotIndex];
        swap(array, pivotIndex, highIndex);

        int leftPointer = partition(array, lowIndex, highIndex, pivot);

        quickSort(array, lowIndex, leftPointer - 1);
        quickSort(array, leftPointer + 1, highIndex);
    }

    private int partition(int[] array, int lowIndex, int highIndex, int pivot) {
        int leftPointer = lowIndex;
        int rightPointer = highIndex - 1;

        while (leftPointer <= rightPointer) {
            while (leftPointer <= rightPointer && array[leftPointer] < pivot) leftPointer++;
            while (leftPointer <= rightPointer && array[rightPointer] > pivot) rightPointer--;

            if (leftPointer <= rightPointer) {
                swap(array, leftPointer, rightPointer);
                leftPointer++;
                rightPointer--;
            }
        }
        swap(array, leftPointer, highIndex);

        return leftPointer;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        movements++;  // Movimiento
    }
}
