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
 * Estados posibles del resultado de una simulación.
 */
public enum SimulationStatus {

    ACCEPTED,               // La cadena fue aceptada.
    REJECTED,               // La máquina llegó al estado de rechazo.
    HALTED_NO_TRANSITION,   // No existe una transición aplicable.
    STEP_LIMIT_EXCEEDED     // Se alcanzó el límite de pasos.
}
