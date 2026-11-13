/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.simuladormaquinaturing.model;

/**
 *
 * @author Vielman Perez
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Representa la cinta de la máquina de Turing.
 * Permite almacenar símbolos en posiciones positivas y negativas.
 */
public class Tape {

    private final Map<Integer, Character> cells;
    private final char blankSymbol;
    private int headPosition;

    public Tape(String input, char blankSymbol) {
        this.cells = new HashMap<>();
        this.blankSymbol = blankSymbol;
        this.headPosition = 0;

        // Coloca la cadena inicial en la cinta.
        for (int i = 0; i < input.length(); i++) {
            cells.put(i, input.charAt(i));
        }
    }

    // Lee el símbolo de la posición actual.
    public char read() {
        return cells.getOrDefault(headPosition, blankSymbol);
    }

    // Escribe un símbolo en la posición actual.
    public void write(char symbol) {
        if (symbol == blankSymbol) {
            cells.remove(headPosition);
        } else {
            cells.put(headPosition, symbol);
        }
    }

    // Desplaza la cabeza según el movimiento indicado.
    public void move(Movement movement) {
        switch (movement) {
            case D -> headPosition++;
            case I -> headPosition--;
            case N -> {
                // La cabeza permanece en su posición.
            }
        }
    }

    public int getHeadPosition() {
        return headPosition;
    }

    public char getBlankSymbol() {
        return blankSymbol;
    }

    // Obtiene el contenido de una posición específica.
    public char getSymbolAt(int position) {
        return cells.getOrDefault(position, blankSymbol);
    }

    // Devuelve una representación de la cinta para visualizarla.
    public String getVisibleTape(int radius) {
        StringBuilder result = new StringBuilder();

        for (int i = headPosition - radius;
             i <= headPosition + radius; i++) {

            result.append(getSymbolAt(i));

            if (i < headPosition + radius) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    // Devuelve el contenido no vacío de la cinta.
    public String getContents() {
        if (cells.isEmpty()) {
            return String.valueOf(blankSymbol);
        }

        int min = cells.keySet().stream()
                .mapToInt(Integer::intValue).min().orElse(0);

        int max = cells.keySet().stream()
                .mapToInt(Integer::intValue).max().orElse(0);

        StringBuilder result = new StringBuilder();

        for (int i = min; i <= max; i++) {
            result.append(getSymbolAt(i));
        }

        return result.toString();
    }
}
