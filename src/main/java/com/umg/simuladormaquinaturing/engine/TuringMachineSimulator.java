/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.simuladormaquinaturing.engine;

/**
 *
 * @author Vielman Perez
 */


import com.umg.simuladormaquinaturing.model.Tape;
import com.umg.simuladormaquinaturing.model.Transition;
import com.umg.simuladormaquinaturing.model.TuringMachine;
import com.umg.simuladormaquinaturing.validation.MachineValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * Motor principal encargado de ejecutar la máquina de Turing.
 */
public class TuringMachineSimulator {

    private static final char BLANK_SYMBOL = '_';
    private static final int DEFAULT_MAX_STEPS = 10000;

    private final MachineValidator validator;

    public TuringMachineSimulator() {
        this.validator = new MachineValidator();
    }

    /**
     * Ejecuta una simulación utilizando el límite predeterminado.
     */
    public SimulationResult simulate(
            String input,
            TuringMachine machine) {

        return simulate(input, machine, DEFAULT_MAX_STEPS);
    }

    /**
     * Ejecuta la máquina de Turing con un límite de pasos.
     */
    public SimulationResult simulate(
            String input,
            TuringMachine machine,
            int maxSteps) {

        if (maxSteps <= 0) {
            throw new IllegalArgumentException(
                    "El límite de pasos debe ser mayor que cero.");
        }

        // Validar la definición de la máquina.
        List<String> machineErrors =
                validator.validateMachine(machine);

        if (!machineErrors.isEmpty()) {
            throw new IllegalArgumentException(
                    "Máquina inválida: "
                    + String.join("; ", machineErrors));
        }

        // Validar la cadena de entrada.
        List<String> inputErrors =
                validator.validateInput(input, machine);

        if (!inputErrors.isEmpty()) {
            throw new IllegalArgumentException(
                    "Entrada inválida: "
                    + String.join("; ", inputErrors));
        }

        // Crear la cinta e inicializar la simulación.
        Tape tape = new Tape(input, BLANK_SYMBOL);

        String currentState = machine.getInitialState();

        List<SimulationStep> history = new ArrayList<>();

        int steps = 0;

        // Ejecutar las transiciones de la máquina.
        while (steps < maxSteps) {

            // Comprobar si la máquina acepta.
            if (machine.isAcceptState(currentState)) {
                return createResult(
                        SimulationStatus.ACCEPTED,
                        currentState,
                        steps,
                        tape,
                        history
                );
            }

            // Comprobar si la máquina rechaza.
            if (machine.isRejectState(currentState)) {
                return createResult(
                        SimulationStatus.REJECTED,
                        currentState,
                        steps,
                        tape,
                        history
                );
            }

            // Leer el símbolo actual.
            char readSymbol = tape.read();

            // Buscar la transición correspondiente.
            Transition transition =
                    machine.getTransition(currentState, readSymbol);

            // Si no existe transición, detener la simulación.
            if (transition == null) {
                return createResult(
                        SimulationStatus.HALTED_NO_TRANSITION,
                        currentState,
                        steps,
                        tape,
                        history
                );
            }

            // Registrar el paso antes de ejecutar el movimiento.
            history.add(new SimulationStep(
                    steps,
                    currentState,
                    readSymbol,
                    transition.getWriteSymbol(),
                    tape.getHeadPosition(),
                    tape.getContents(),
                    transition.getMovement().name()
            ));

            // Escribir el nuevo símbolo.
            tape.write(transition.getWriteSymbol());

            // Mover la cabeza lectora.
            tape.move(transition.getMovement());

            // Cambiar al siguiente estado.
            currentState = transition.getNextState();

            steps++;
        }

        // Comprobar el estado alcanzado en el último paso.
        if (machine.isAcceptState(currentState)) {
            return createResult(
                    SimulationStatus.ACCEPTED,
                    currentState,
                    steps,
                    tape,
                    history
            );
        }

        if (machine.isRejectState(currentState)) {
            return createResult(
                    SimulationStatus.REJECTED,
                    currentState,
                    steps,
                    tape,
                    history
            );
        }

        // Se alcanzó el límite sin llegar a un estado final.
        return createResult(
                SimulationStatus.STEP_LIMIT_EXCEEDED,
                currentState,
                steps,
                tape,
                history
        );
    }

    /**
     * Construye el resultado final de la simulación.
     */
    private SimulationResult createResult(
            SimulationStatus status,
            String finalState,
            int totalSteps,
            Tape tape,
            List<SimulationStep> history) {

        return new SimulationResult(
                status,
                finalState,
                totalSteps,
                tape.getContents(),
                history
        );
    }
}
