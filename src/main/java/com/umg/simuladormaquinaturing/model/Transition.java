/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.simuladormaquinaturing.model;

/**
 *
 * @author Vielman Perez
 */


/**
 * Representa una regla de transición de la máquina de Turing.
 */
public class Transition {

    private final String currentState;
    private final char readSymbol;
    private final String nextState;
    private final char writeSymbol;
    private final Movement movement;

    public Transition(String currentState, char readSymbol,
                      String nextState, char writeSymbol,
                      Movement movement) {

        this.currentState = currentState;
        this.readSymbol = readSymbol;
        this.nextState = nextState;
        this.writeSymbol = writeSymbol;
        this.movement = movement;
    }

    public String getCurrentState() {
        return currentState;
    }

    public char getReadSymbol() {
        return readSymbol;
    }

    public String getNextState() {
        return nextState;
    }

    public char getWriteSymbol() {
        return writeSymbol;
    }

    public Movement getMovement() {
        return movement;
    }
}
