/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.umg.simuladormaquinaturing;

/**
 *
 * @author Vielman Perez
 */

import com.umg.simuladormaquinaturing.gui.MainWindow;
import javax.swing.SwingUtilities;

/**
 * Clase principal del simulador de máquina de Turing.
 */
public class SimuladorMaquinaTuring {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            MainWindow ventana = new MainWindow();
            ventana.setVisible(true);
        });
    }
}
