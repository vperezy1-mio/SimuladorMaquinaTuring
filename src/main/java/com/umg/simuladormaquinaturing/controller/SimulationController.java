/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.simuladormaquinaturing.controller;

/**
 *
 * @author Vielman Perez
 */


import com.umg.simuladormaquinaturing.engine.SimulationResult;
import com.umg.simuladormaquinaturing.engine.TuringMachineSimulator;
import com.umg.simuladormaquinaturing.loader.MachineFileLoader;
import com.umg.simuladormaquinaturing.model.TuringMachine;
import com.umg.simuladormaquinaturing.validation.MachineValidator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Controlador que coordina la carga, validación
 * y ejecución de una máquina de Turing.
 */
public class SimulationController {

    private final MachineFileLoader loader;
    private final MachineValidator validator;
    private final TuringMachineSimulator simulator;

    private TuringMachine currentMachine;

    public SimulationController() {
        this.loader = new MachineFileLoader();
        this.validator = new MachineValidator();
        this.simulator = new TuringMachineSimulator();
        this.currentMachine = null;
    }

    /**
     * Carga y valida una máquina desde un archivo.
     *
     * @param filePath ruta del archivo de definición.
     * @return máquina cargada correctamente.
     * @throws IOException si ocurre un problema al leer el archivo.
     */
    public TuringMachine loadMachine(Path filePath)
            throws IOException {

        TuringMachine machine = loader.loadMachine(filePath);

        List<String> errors = validator.validateMachine(machine);

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(
                    String.join("\n", errors)
            );
        }

        currentMachine = machine;

        return currentMachine;
    }

    /**
     * Ejecuta la máquina cargada con una cadena de entrada.
     *
     * @param input cadena que se desea procesar.
     * @return resultado de la simulación.
     */
    public SimulationResult simulate(String input) {

        if (currentMachine == null) {
            throw new IllegalStateException(
                    "Primero debe cargar una máquina de Turing."
            );
        }

        return simulator.simulate(input, currentMachine);
    }

    /**
     * Comprueba si existe una máquina cargada.
     *
     * @return true si hay una máquina disponible.
     */
    public boolean isMachineLoaded() {
        return currentMachine != null;
    }

    /**
     * Obtiene la máquina actualmente cargada.
     *
     * @return máquina cargada o null.
     */
    public TuringMachine getCurrentMachine() {
        return currentMachine;
    }

    /**
     * Descarga la máquina actual.
     */
    public void unloadMachine() {
        currentMachine = null;
    }
}
