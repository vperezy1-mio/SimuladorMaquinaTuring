/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.simuladormaquinaturing.validation;

/**
 *
 * @author Vielman Perez
 */

import com.umg.simuladormaquinaturing.model.Transition;
import com.umg.simuladormaquinaturing.model.TuringMachine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Valida la definición formal de una máquina de Turing
 * y las cadenas que serán ingresadas en su cinta.
 */
public class MachineValidator {

    private static final char BLANK_SYMBOL = '_';

    /**
     * Comprueba que la definición de la máquina sea coherente.
     * Devuelve una lista con todos los errores encontrados.
     */
    
    /**
 * Valida la estructura y las reglas de la máquina de Turing.
 *
 * @param machine máquina que se desea validar.
 * @return lista de errores encontrados durante la validación.
 */
    
    public List<String> validateMachine(TuringMachine machine) {

        List<String> errors = new ArrayList<>();

        if (machine == null) {
            errors.add("La máquina de Turing no está definida.");
            return errors;
        }

        Set<String> states = machine.getStates();
        Set<Character> alphabet = machine.getAlphabet();

        // Comprueba que existan estados.
        if (states.isEmpty()) {
            errors.add("El conjunto de estados está vacío.");
        }

        // Comprueba que exista el alfabeto.
        if (alphabet.isEmpty()) {
            errors.add("El alfabeto de la máquina está vacío.");
        }

        // El símbolo blanco debe estar definido.
        if (!alphabet.contains(BLANK_SYMBOL)) {
            errors.add("El alfabeto debe incluir el símbolo blanco '_'.");
        }

        // Comprueba que el estado inicial exista.
        if (!states.contains(machine.getInitialState())) {
            errors.add("El estado inicial no pertenece al conjunto de estados.");
        }

        // Comprueba el estado de aceptación.
        if (!states.contains(machine.getAcceptState())) {
            errors.add("El estado de aceptación no está definido.");
        }

        // Comprueba el estado de rechazo.
        if (!states.contains(machine.getRejectState())) {
            errors.add("El estado de rechazo no está definido.");
        }

        // Los estados finales deben ser diferentes.
        if (machine.getAcceptState().equals(machine.getRejectState())) {
            errors.add("Los estados de aceptación y rechazo deben ser diferentes.");
        }

        // Los estados finales no pueden ser el estado inicial.
        if (machine.getInitialState().equals(machine.getAcceptState())
                || machine.getInitialState().equals(machine.getRejectState())) {
            errors.add("El estado inicial no puede ser un estado final.");
        }

        // Comprueba todas las transiciones.
        List<Transition> transitions = machine.getAllTransitions();

        if (transitions.isEmpty()) {
            errors.add("La máquina no contiene transiciones.");
        }

        Set<String> transitionKeys = new HashSet<>();

        for (Transition transition : transitions) {

            String currentState = transition.getCurrentState();
            String nextState = transition.getNextState();

            char readSymbol = transition.getReadSymbol();
            char writeSymbol = transition.getWriteSymbol();

            // Comprueba que los estados de la transición existan.
            if (!states.contains(currentState)) {
                errors.add("Estado de origen no definido: " + currentState);
            }

            if (!states.contains(nextState)) {
                errors.add("Estado de destino no definido: " + nextState);
            }

            // Comprueba que los símbolos pertenezcan al alfabeto.
            if (!alphabet.contains(readSymbol)) {
                errors.add("Símbolo leído no definido: " + readSymbol);
            }

            if (!alphabet.contains(writeSymbol)) {
                errors.add("Símbolo escrito no definido: " + writeSymbol);
            }

            // Comprueba que no existan transiciones duplicadas.
            String key = currentState + "|" + readSymbol;

            if (!transitionKeys.add(key)) {
                errors.add("Transición duplicada para estado "
                        + currentState + " y símbolo " + readSymbol);
            }
        }

        return errors;
    }

    /**
     * Comprueba que todos los símbolos de entrada
     * pertenezcan al alfabeto de la máquina.
     */
    
    /**
 * Valida la estructura y las reglas de la máquina de Turing.
 *
     * @param input
 * @param machine máquina que se desea validar.
 * @return lista de errores encontrados durante la validación.
 */
    public List<String> validateInput(
            String input,
            TuringMachine machine) {

        List<String> errors = new ArrayList<>();

        if (input == null) {
            errors.add("La cadena de entrada no puede ser nula.");
            return errors;
        }

        if (machine == null) {
            errors.add("Primero debe cargar una máquina de Turing.");
            return errors;
        }

        for (int i = 0; i < input.length(); i++) {

            char symbol = input.charAt(i);

            if (!machine.getAlphabet().contains(symbol)) {
                errors.add("Símbolo '" + symbol
                        + "' no permitido en la posición " + i + ".");
            }

            // El símbolo blanco se reserva para la cinta.
            if (symbol == BLANK_SYMBOL) {
                errors.add("El símbolo '_' está reservado para las celdas vacías.");
            }
        }

        return errors;
    }

    /**
     * Indica si una definición formal es válida.
     */
    
    /**
 * Valida la estructura y las reglas de la máquina de Turing.
 *
 * @param machine máquina que se desea validar.
 * @return lista de errores encontrados durante la validación.
 */
    public boolean isMachineValid(TuringMachine machine) {
        return validateMachine(machine).isEmpty();
    }

    /**
     * Indica si una cadena puede ingresar en la cinta.
     */
    
    /**
 * Valida la estructura y las reglas de la máquina de Turing.
 *
     * @param input
 * @param machine máquina que se desea validar.
 * @return lista de errores encontrados durante la validación.
 */
    public boolean isInputValid(
            String input,
            TuringMachine machine) {

        return validateInput(input, machine).isEmpty();
    }
}
