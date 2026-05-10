package com.proyecto.algorithms;

import com.proyecto.core.SortAlgorithm;
import com.proyecto.core.SortingResult;

public class HeapSort implements SortAlgorithm {
    private long movements;

    @Override
    public String getName() {
        return "Heap Sort";
    }

    @Override
    public SortingResult sort(int[] array) {
        movements = 0;
        int n = array.length;
        long startTime = System.currentTimeMillis();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            swap(array, 0, i);
            heapify(array, i, 0);
        }

        long endTime = System.currentTimeMillis();
        return new SortingResult(getName(), endTime - startTime, movements);
    }

    private void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && array[left] > array[largest]) largest = left;
        if (right < n && array[right] > array[largest]) largest = right;

        if (largest != i) {
            swap(array, i, largest);
            heapify(array, n, largest);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        movements++;  // Movimiento
    }
}
