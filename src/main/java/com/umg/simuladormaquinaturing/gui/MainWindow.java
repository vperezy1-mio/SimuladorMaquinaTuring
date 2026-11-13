/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.simuladormaquinaturing.gui;

/**
 *
 * @author Vielman Perez
 */


import com.umg.simuladormaquinaturing.controller.SimulationController;
import com.umg.simuladormaquinaturing.engine.SimulationResult;
import com.umg.simuladormaquinaturing.engine.SimulationStep;
import com.umg.simuladormaquinaturing.model.TuringMachine;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Ventana principal del simulador de máquina de Turing.
 */
public class MainWindow extends JFrame {

    private final SimulationController controller;

    private JLabel lblMachineStatus;
    private JLabel lblInput;
    private JTextField txtInput;
    private JTextArea txtResults;

    private JButton btnSimulate;
    private JButton btnNewInput;

    public MainWindow() {

        controller = new SimulationController();

        setTitle("Simulador de Máquina de Turing - UMG");
        setSize(950, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        createHeader();
        createControls();
        createResults();
    }

    /**
     * Construye el encabezado de la ventana.
     */
    private void createHeader() {

        JLabel title = new JLabel(
                "SIMULADOR DE MÁQUINA DE TURING",
                SwingConstants.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel university = new JLabel(
                "Universidad Mariano Gálvez de Guatemala",
                SwingConstants.CENTER
        );

        university.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel header = new JPanel(new GridLayout(2, 1, 5, 5));

        header.setBorder(
                BorderFactory.createEmptyBorder(15, 10, 10, 10)
        );

        header.add(title);
        header.add(university);

        add(header, BorderLayout.NORTH);
    }

    /**
     * Construye los controles de carga y ejecución.
     */
    private void createControls() {

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        );

        JButton btnLoad = new JButton("Cargar máquina");
        JButton btnExit = new JButton("Salir");

        lblMachineStatus = new JLabel(
                "Estado: no hay una máquina cargada."
        );

        lblInput = new JLabel("Cadena de entrada:");

        txtInput = new JTextField();
        txtInput.setEnabled(false);

        btnSimulate = new JButton("Ejecutar simulación");
        btnSimulate.setEnabled(false);

        btnNewInput = new JButton("Nueva cadena");
        btnNewInput.setEnabled(false);

        JPanel loadPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT)
        );

        loadPanel.add(btnLoad);
        loadPanel.add(btnExit);

        JPanel inputPanel = new JPanel(
                new BorderLayout(10, 5)
        );

        inputPanel.add(lblInput, BorderLayout.NORTH);
        inputPanel.add(txtInput, BorderLayout.CENTER);

        JPanel actionPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT)
        );

        actionPanel.add(btnSimulate);
        actionPanel.add(btnNewInput);

        panel.add(loadPanel);
        panel.add(lblMachineStatus);
        panel.add(Box.createVerticalStrut(15));
        panel.add(inputPanel);
        panel.add(actionPanel);

        add(panel, BorderLayout.CENTER);

        btnLoad.addActionListener(e -> loadMachine());

        btnSimulate.addActionListener(e -> executeSimulation());

        btnNewInput.addActionListener(e -> clearInput());

        btnExit.addActionListener(e -> System.exit(0));
    }

    /**
     * Construye el área de resultados.
     */
    private void createResults() {

        txtResults = new JTextArea();

        txtResults.setEditable(false);

        txtResults.setFont(
                new Font("Monospaced", Font.PLAIN, 13)
        );

        JScrollPane scroll = new JScrollPane(txtResults);

        scroll.setBorder(
                BorderFactory.createTitledBorder(
                        "Resultados y pasos de ejecución"
                )
        );

        add(scroll, BorderLayout.SOUTH);

        scroll.setPreferredSize(new Dimension(900, 350));
    }

    /**
     * Permite seleccionar y cargar un archivo de máquina.
     */
    private void loadMachine() {

        JFileChooser chooser = new JFileChooser();

        chooser.setDialogTitle(
                "Seleccionar máquina de Turing"
        );

        chooser.setFileFilter(
                new FileNameExtensionFilter(
                        "Archivos de texto (*.txt)", "txt"
                )
        );

        int selection = chooser.showOpenDialog(this);

        if (selection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File file = chooser.getSelectedFile();

        try {

            TuringMachine machine =
                    controller.loadMachine(file.toPath());

            lblMachineStatus.setText(
                    "Máquina cargada correctamente: "
                    + file.getName()
            );

            StringBuilder output = new StringBuilder();

            output.append("MÁQUINA CARGADA CORRECTAMENTE\n\n");

            output.append("Estado inicial: ")
                    .append(machine.getInitialState())
                    .append("\n");

            output.append("Estado de aceptación: ")
                    .append(machine.getAcceptState())
                    .append("\n");

            output.append("Estado de rechazo: ")
                    .append(machine.getRejectState())
                    .append("\n");

            output.append("Estados: ")
                    .append(machine.getStates())
                    .append("\n");

            output.append("Alfabeto: ")
                    .append(machine.getAlphabet())
                    .append("\n\n");

            output.append(
                    "Introduzca una cadena para iniciar."
            );

            txtResults.setText(output.toString());

            txtInput.setEnabled(true);
            btnSimulate.setEnabled(true);
            btnNewInput.setEnabled(true);

            txtInput.requestFocus();

        } catch (IOException | IllegalArgumentException exception) {

            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Error al cargar la máquina",
                    JOptionPane.ERROR_MESSAGE
            );

            lblMachineStatus.setText(
                    "Estado: error al cargar la máquina."
            );
        }
    }

    /**
     * Ejecuta la simulación de la cadena ingresada.
     */
    private void executeSimulation() {

        String input = txtInput.getText();

        try {

            SimulationResult result =
                    controller.simulate(input);

            StringBuilder output = new StringBuilder();

            output.append("========== SIMULACIÓN ==========\n\n");

            output.append("Cadena de entrada: ")
                    .append(input)
                    .append("\n\n");

            output.append("========== HISTORIAL ==========\n\n");

            for (SimulationStep step : result.getHistory()) {

                output.append("Paso: ")
                        .append(step.getStepNumber())
                        .append("\n");

                output.append("Estado: ")
                        .append(step.getCurrentState())
                        .append("\n");

                output.append("Símbolo leído: ")
                        .append(step.getReadSymbol())
                        .append("\n");

                output.append("Símbolo escrito: ")
                        .append(step.getWrittenSymbol())
                        .append("\n");

                output.append("Posición de cabeza: ")
                        .append(step.getHeadPosition())
                        .append("\n");

                output.append("Movimiento: ")
                        .append(step.getMovement())
                        .append("\n");

                output.append("Cinta: ")
                        .append(step.getTapeContents())
                        .append("\n");

                output.append("------------------------------\n");
            }

            output.append("\n========== RESULTADO ==========\n\n");

            output.append("Resultado: ")
                    .append(getStatusMessage(result))
                    .append("\n");

            output.append("Estado final: ")
                    .append(result.getFinalState())
                    .append("\n");

            output.append("Cinta final: ")
                    .append(result.getFinalTape())
                    .append("\n");

            output.append("Total de pasos: ")
                    .append(result.getTotalSteps())
                    .append("\n");

            txtResults.setText(output.toString());

            txtResults.setCaretPosition(0);

        } catch (IllegalArgumentException | IllegalStateException exception) {

            JOptionPane.showMessageDialog(
                    this,
                    exception.getMessage(),
                    "Error de simulación",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Convierte el estado técnico en un mensaje para el usuario.
     */
    private String getStatusMessage(SimulationResult result) {

        return switch (result.getStatus()) {

            case ACCEPTED ->
                "CADENA ACEPTADA";

            case REJECTED ->
                "CADENA RECHAZADA";

            case HALTED_NO_TRANSITION ->
                "NO ACEPTADA: no existe una transición aplicable";

            case STEP_LIMIT_EXCEEDED ->
                "SIMULACIÓN DETENIDA: se alcanzó el límite de pasos";
        };
    }

    /**
     * Limpia la entrada para ejecutar una nueva cadena.
     */
    private void clearInput() {

        txtInput.setText("");

        txtResults.setText(
                "Ingrese una nueva cadena para ejecutar la máquina."
        );

        txtInput.requestFocus();
    }
}
