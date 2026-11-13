/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.simuladormaquinaturing.model;

/**
 *
 * @author Vielman Perez
 */

import java.util.*;

/**
 * Contiene la definición formal de una máquina de Turing.
 */
public class TuringMachine {

    private final Set<String> states;
    private final String initialState;
    private final String acceptState;
    private final String rejectState;
    private final Set<Character> alphabet;
    private final Map<String, Map<Character, Transition>> transitions;

    public TuringMachine(
            Set<String> states,
            String initialState,
            String acceptState,
            String rejectState,
            Set<Character> alphabet,
            List<Transition> transitionList) {

        this.states = new HashSet<>(states);
        this.initialState = initialState;
        this.acceptState = acceptState;
        this.rejectState = rejectState;
        this.alphabet = new HashSet<>(alphabet);

        this.transitions = new HashMap<>();

        // Organiza las transiciones por estado y símbolo leído.
        for (Transition transition : transitionList) {

            transitions.computeIfAbsent(
                    transition.getCurrentState(),
                    key -> new HashMap<>()
            ).put(transition.getReadSymbol(), transition);
        }
    }

    public Set<String> getStates() {
        return Collections.unmodifiableSet(states);
    }

    public String getInitialState() {
        return initialState;
    }

    public String getAcceptState() {
        return acceptState;
    }

    public String getRejectState() {
        return rejectState;
    }

    public Set<Character> getAlphabet() {
        return Collections.unmodifiableSet(alphabet);
    }

    // Busca una transición según el estado y símbolo actual.
    public Transition getTransition(String state, char symbol) {

        Map<Character, Transition> stateTransitions =
                transitions.get(state);

        if (stateTransitions == null) {
            return null;
        }

        return stateTransitions.get(symbol);
    }

    public boolean isAcceptState(String state) {
        return acceptState.equals(state);
    }

    public boolean isRejectState(String state) {
        return rejectState.equals(state);
    }
    
    /**
 * Obtiene todas las transiciones definidas en la máquina.
 */
    
    /**
 * Valida la estructura y las reglas de la máquina de Turing.
 *

 * @return lista de errores encontrados durante la validación.
 */
    
    public List<Transition> getAllTransitions() {

        List<Transition> result = new ArrayList<>();

        for (Map<Character, Transition> stateTransitions
                : transitions.values()) {

            result.addAll(stateTransitions.values());
        }

        return result;
    }
    
}

