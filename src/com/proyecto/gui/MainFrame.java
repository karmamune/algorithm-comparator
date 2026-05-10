package com.proyecto.gui;

import com.proyecto.core.SortAlgorithm;
import com.proyecto.core.SortingResult;
import com.proyecto.algorithms.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class MainFrame extends JFrame {
    private JTextField txtSize;
    private JButton btnGenerate, btnStart;
    private JTable resultTable;
    private DefaultTableModel tableModel;
    private JLabel lblStatus;

    private int[] originalList;
    private final SortAlgorithm[] algorithms;

    public MainFrame() {
        algorithms = new SortAlgorithm[] {
            new BubbleSort(),
            new InsertionSort(),
            new MergeSort(),
            new QuickSort(),
            new HeapSort()
        };

        setupGUI();
    }

    private void setupGUI() {
        setTitle("Comparador de Algoritmos de Ordenamiento - Proyecto de investigación");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout(10, 10));

        // Panel superior (Controles)
        JPanel panelNorth = new JPanel();
        panelNorth.add(new JLabel("Tamaño de lista:"));
        txtSize = new JTextField("10000", 10);
        btnGenerate = new JButton("Generar lista");
        btnStart = new JButton("Comenzar ordenamiento");
        btnStart.setEnabled(false);

        panelNorth.add(txtSize);
        panelNorth.add(btnGenerate);
        panelNorth.add(btnStart);
        add(panelNorth, BorderLayout.NORTH);

        // Panel central (Tabla de resultados)
        String[] columns = {"Algoritmo", "Tiempo (ms)", "Formato extendido", "Movimientos"};
        tableModel = new DefaultTableModel(columns, 0);
        resultTable = new JTable(tableModel);
        add(new JScrollPane(resultTable), BorderLayout.CENTER);

        // Panel inferior (Estado)
        lblStatus = new JLabel("Ingrese un tamaño y genere la lista para comenzar.");
        add(lblStatus, BorderLayout.SOUTH);

        // Eventos
        btnGenerate.addActionListener(e -> generateList());
        btnStart.addActionListener(e -> runComparison());

        setLocationRelativeTo(null);
    }

    private void generateList() {
        try {
            int size = Integer.parseInt(txtSize.getText());
            originalList = new int[size];
            Random rand = new Random();
            for (int i = 0; i < size; i++) {
                originalList[i] = rand.nextInt(1000000);
            }
            tableModel.setRowCount(0);
            btnStart.setEnabled(true);
            lblStatus.setText(" Lista de " + size + " elementos creada. Lista para ordenar.");
            JOptionPane.showMessageDialog(this, "Lista generada con éxito.");
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un número válido.");
        }
    }

    private void runComparison() {
        btnStart.setEnabled(false);
        btnGenerate.setEnabled(false);
        lblStatus.setText(" Ejecutando algoritmos secuencialmente...");

        SwingWorker<Void, SortingResult> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                for (SortAlgorithm alg : algorithms) {
                    int[] copy = originalList.clone();
                    SortingResult result = alg.sort(copy);
                    publish(result);
                }
                return null;
            }

            @Override
            protected void process(List<SortingResult> chunks) {
                for (SortingResult res : chunks) {
                    tableModel.addRow(new Object[] {
                        res.getAlgorithmName(),
                        res.getTotalMs(),
                        "Min: " + (res.getTotalMs() / 1000) / 60 + " seg: " + (res.getTotalMs() / 1000) % 60 + " ms: " + res.getTotalMs() % 1000,
                        String.format("%,d", res.getMovements())
                    });
                }
            }

            @Override
            protected void done() {
                btnStart.setEnabled(true);
                btnGenerate.setEnabled(true);
                lblStatus.setText(" Comparación finalizada.");
            }
        };

        worker.execute();
    }
}
