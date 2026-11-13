/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.simuladormaquinaturing.engine;

/**
 *
 * @author Vielman Perez
 */


/**
 * Representa un paso individual de la simulación.
 */
public class SimulationStep {

    private final int stepNumber;
    private final String currentState;
    private final char readSymbol;
    private final char writtenSymbol;
    private final int headPosition;
    private final String tapeContents;
    private final String movement;

    public SimulationStep(
            int stepNumber,
            String currentState,
            char readSymbol,
            char writtenSymbol,
            int headPosition,
            String tapeContents,
            String movement) {

        this.stepNumber = stepNumber;
        this.currentState = currentState;
        this.readSymbol = readSymbol;
        this.writtenSymbol = writtenSymbol;
        this.headPosition = headPosition;
        this.tapeContents = tapeContents;
        this.movement = movement;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public String getCurrentState() {
        return currentState;
    }

    public char getReadSymbol() {
        return readSymbol;
    }

    public char getWrittenSymbol() {
        return writtenSymbol;
    }

    public int getHeadPosition() {
        return headPosition;
    }

    public String getTapeContents() {
        return tapeContents;
    }

    public String getMovement() {
        return movement;
    }
}
