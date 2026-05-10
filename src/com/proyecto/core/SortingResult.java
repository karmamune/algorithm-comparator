package com.proyecto.core;

public class SortingResult {
    private final String algorithmName;
    private final long totalMs;
    private final long movements;

    public SortingResult(String algorithmName, long totalMs, long movements) {
        this.algorithmName = algorithmName;
        this.totalMs = totalMs;
        this.movements = movements;
    }

    // Getters para la GUI
    public String getAlgorithmName() {return algorithmName;}
    public long getTotalMs() {return totalMs;}
    public long getMovements() {return movements;}
    
    // Formato de tiempo
    public String getFormattedTime() {
        long minutes = (totalMs / 1000) / 60;
        long seconds = (totalMs / 1000) % 60;
        long millis = totalMs % 1000;

        return String.format(
            "Total: %d ms\nMinutos: %d\nSegundos: %d\nMilisegundos: %d",
            totalMs, minutes, seconds, millis
        );
    }
}
