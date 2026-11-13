/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.simuladormaquinaturing.engine;

/**
 *
 * @author Vielman Perez
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Almacena el resultado completo de una simulación.
 */
public class SimulationResult {

    private final SimulationStatus status;
    private final String finalState;
    private final int totalSteps;
    private final String finalTape;
    private final List<SimulationStep> history;

    public SimulationResult(
            SimulationStatus status,
            String finalState,
            int totalSteps,
            String finalTape,
            List<SimulationStep> history) {

        this.status = status;
        this.finalState = finalState;
        this.totalSteps = totalSteps;
        this.finalTape = finalTape;

        this.history = new ArrayList<>(history);
    }

    public SimulationStatus getStatus() {
        return status;
    }

    public String getFinalState() {
        return finalState;
    }

    public int getTotalSteps() {
        return totalSteps;
    }

    public String getFinalTape() {
        return finalTape;
    }

    public List<SimulationStep> getHistory() {
        return Collections.unmodifiableList(history);
    }

    public boolean isAccepted() {
        return status == SimulationStatus.ACCEPTED;
    }
}

