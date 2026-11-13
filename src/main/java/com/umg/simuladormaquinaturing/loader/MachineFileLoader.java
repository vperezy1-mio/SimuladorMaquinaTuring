/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.umg.simuladormaquinaturing.loader;

/**
 *
 * @author Vielman Perez
 */

import com.umg.simuladormaquinaturing.model.Movement;
import com.umg.simuladormaquinaturing.model.Transition;
import com.umg.simuladormaquinaturing.model.TuringMachine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Carga e interpreta archivos de texto que contienen
 * la definición formal de una máquina de Turing.
 *
 * Formato:
 * Estados(q0, q1, q2)
 * Inicial(q0)
 * Ha(q1)
 * He(q2)
 * Alfabeto(0, 1, _)
 * MT(q0, 0) = (q0, 0, D)
 */
public class MachineFileLoader {

    private static final Pattern TRANSITION_PATTERN =
            Pattern.compile(
                "^MT\\s*\\(\\s*([^,]+)\\s*,\\s*(.)\\s*\\)"
                + "\\s*=\\s*\\(\\s*([^,]+)\\s*,\\s*(.)"
                + "\\s*,\\s*([DIN])\\s*\\)$"
            );

    /**
     * Lee el archivo y construye una máquina de Turing.
     * @param filePath
     * @return 
     * @throws java.io.IOException
     */
    public TuringMachine loadMachine(Path filePath) throws IOException {

        if (!Files.exists(filePath)) {
            throw new IllegalArgumentException(
                    "El archivo seleccionado no existe."
            );
        }

        List<String> lines = Files.readAllLines(filePath);

        Set<String> states = new HashSet<>();
        Set<Character> alphabet = new HashSet<>();
        List<Transition> transitions = new ArrayList<>();

        String initialState = null;
        String acceptState = null;
        String rejectState = null;

        boolean statesFound = false;
        boolean alphabetFound = false;
        boolean initialFound = false;
        boolean acceptFound = false;
        boolean rejectFound = false;

        int lineNumber = 0;

        for (String originalLine : lines) {

            lineNumber++;

            String line = originalLine.trim();

            // Ignora líneas vacías y comentarios.
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            try {

                if (line.startsWith("Estados(")) {

                    if (statesFound) {
                        throw new IllegalArgumentException(
                                "Definición de Estados duplicada."
                        );
                    }

                    states.addAll(parseNames(
                            extractContent(line, "Estados")
                    ));

                    statesFound = true;

                } else if (line.startsWith("Inicial(")) {

                    if (initialFound) {
                        throw new IllegalArgumentException(
                                "Definición Inicial duplicada."
                        );
                    }

                    initialState = extractContent(line, "Inicial").trim();
                    initialFound = true;

                } else if (line.startsWith("Ha(")) {

                    if (acceptFound) {
                        throw new IllegalArgumentException(
                                "Definición Ha duplicada."
                        );
                    }

                    acceptState = extractContent(line, "Ha").trim();
                    acceptFound = true;

                } else if (line.startsWith("He(")) {

                    if (rejectFound) {
                        throw new IllegalArgumentException(
                                "Definición He duplicada."
                        );
                    }

                    rejectState = extractContent(line, "He").trim();
                    rejectFound = true;

                } else if (line.startsWith("Alfabeto(")) {

                    if (alphabetFound) {
                        throw new IllegalArgumentException(
                                "Definición de Alfabeto duplicada."
                        );
                    }

                    for (String symbol : extractContent(
                            line, "Alfabeto").split(",")) {

                        String value = symbol.trim();

                        if (value.length() != 1) {
                            throw new IllegalArgumentException(
                                    "Cada símbolo debe tener un carácter."
                            );
                        }

                        if (!alphabet.add(value.charAt(0))) {
                            throw new IllegalArgumentException(
                                    "Símbolo duplicado en el alfabeto."
                            );
                        }
                    }

                    alphabetFound = true;

                } else if (line.startsWith("MT")) {

                    Matcher matcher = TRANSITION_PATTERN.matcher(line);

                    if (!matcher.matches()) {
                        throw new IllegalArgumentException(
                                "Formato de transición incorrecto."
                        );
                    }

                    String currentState = matcher.group(1).trim();
                    char readSymbol = matcher.group(2).charAt(0);
                    String nextState = matcher.group(3).trim();
                    char writeSymbol = matcher.group(4).charAt(0);

                    Movement movement = Movement.valueOf(
                            matcher.group(5)
                    );

                    transitions.add(new Transition(
                            currentState,
                            readSymbol,
                            nextState,
                            writeSymbol,
                            movement
                    ));

                } else {

                    throw new IllegalArgumentException(
                            "Instrucción desconocida."
                    );
                }

            } catch (RuntimeException exception) {

                throw new IllegalArgumentException(
                        "Error en línea " + lineNumber + ": "
                        + exception.getMessage(),
                        exception
                );
            }
        }

        // Comprueba que todas las definiciones principales existan.
        if (!statesFound || !alphabetFound || !initialFound
                || !acceptFound || !rejectFound) {

            throw new IllegalArgumentException(
                    "La definición de la máquina está incompleta."
            );
        }

        return new TuringMachine(
                states,
                initialState,
                acceptState,
                rejectState,
                alphabet,
                transitions
        );
    }

    /**
     * Extrae el contenido ubicado entre paréntesis.
     */
    private String extractContent(String line, String keyword) {

        Pattern pattern = Pattern.compile(
                "^" + Pattern.quote(keyword)
                + "\\s*\\((.*)\\)\\s*$"
        );

        Matcher matcher = pattern.matcher(line);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(
                    "Formato incorrecto en " + keyword + "."
            );
        }

        return matcher.group(1);
    }

    /**
     * Convierte una lista de nombres separados por comas.
     */
    private Set<String> parseNames(String content) {

        Set<String> names = new HashSet<>();

        for (String name : content.split(",")) {

            String value = name.trim();

            if (value.isEmpty()) {
                throw new IllegalArgumentException(
                        "Se encontró un estado vacío."
                );
            }

            if (!names.add(value)) {
                throw new IllegalArgumentException(
                        "Estado duplicado: " + value
                );
            }
        }

        return names;
    }
}
