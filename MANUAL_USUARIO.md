
# MANUAL DE USUARIO
## Simulador de Máquina de Turing

**Universidad:** Universidad Mariano Gálvez de Guatemala  
**Proyecto:** Simulador de Máquina de Turing  
**Lenguaje:** Java 17  

---

## 1. Introducción

Este manual describe el procedimiento para utilizar el Simulador de Máquina de Turing. La aplicación permite cargar una máquina definida en un archivo de texto, ingresar cadenas de entrada y observar el proceso de simulación hasta obtener un resultado de aceptación o rechazo.

## 2. Requisitos previos

Antes de utilizar el programa, se recomienda contar con:

- Java JDK 17 o superior.
- Proyecto compilado correctamente.
- Los archivos de definición de máquinas de Turing disponibles en la carpeta `ejemplo`.

## 3. Inicio de la aplicación

1. Abrir el proyecto `SimuladorMaquinaTuring` en NetBeans.
2. Localizar la clase principal `SimuladorMaquinaTuring.java`.
3. Ejecutar la aplicación.
4. Esperar a que aparezca la ventana principal del simulador.

## 4. Cargar una máquina de Turing

1. Seleccionar la opción de carga de archivo disponible en la interfaz.
2. Buscar la carpeta `ejemplo` dentro del proyecto.
3. Seleccionar el archivo de definición deseado.
4. Confirmar la selección.
5. El sistema procesará el archivo y validará su estructura.

Si el archivo contiene una definición incorrecta, el simulador mostrará los errores correspondientes.

## 5. Ingresar una cadena de entrada

Después de cargar correctamente una máquina:

1. Localizar el campo de entrada de la cadena.
2. Escribir la cadena que se desea evaluar.
3. Verificar que los símbolos pertenezcan al alfabeto permitido por la máquina.
4. Iniciar la simulación mediante el control correspondiente de la interfaz.

El sistema no permite ingresar símbolos que no pertenezcan al alfabeto de entrada definido.

También es posible evaluar una cadena vacía cuando la máquina lo permite.

## 6. Interpretación de la simulación

Durante la ejecución se presenta el historial de los pasos realizados por la máquina.

La información permite observar:

- **Paso:** número de operación realizada.
- **Estado:** estado actual de la máquina.
- **Símbolo leído:** carácter ubicado bajo la cabeza lectora.
- **Posición de cabeza:** ubicación de la cabeza sobre la cinta.
- **Cinta:** contenido de la cinta durante la ejecución.

Cada transición puede modificar el símbolo escrito, cambiar el estado y desplazar la cabeza lectora.

## 7. Interpretación de los movimientos

La máquina utiliza tres movimientos:

| Movimiento | Acción |
|---|---|
| D | Desplaza la cabeza una posición a la derecha |
| I | Desplaza la cabeza una posición a la izquierda |
| N | Mantiene la cabeza en la misma posición |

El símbolo `_` representa una celda en blanco de la cinta.

## 8. Resultados de la simulación

Al finalizar la ejecución, el programa presenta el resultado correspondiente.

### Cadena aceptada

La máquina alcanza el estado de aceptación definido en el archivo.

### Cadena rechazada

La máquina alcanza el estado de rechazo definido en el archivo.

### Transición inexistente

Si no existe una transición para el estado y símbolo actuales, la simulación finaliza indicando que la cadena no fue aceptada.

### Límite de pasos

Para evitar ejecuciones indefinidas, el simulador establece un máximo de 10,000 pasos. Si se alcanza este límite, la ejecución se detiene y se informa al usuario.

## 9. Utilización de máquinas de ejemplo

El proyecto incluye diferentes archivos de ejemplo:

| Archivo | Propósito |
|---|---|
| `maquina_paridad.txt` | Reconocer cadenas binarias con cantidad par de unos |
| `maquina_termina_en_uno.txt` | Reconocer cadenas que terminan en uno |
| `maquina_movimientos.txt` | Demostrar los movimientos de la cabeza lectora |
| `maquina_posicion_negativa.txt` | Demostrar posiciones negativas de la cinta |
| `maquina_ciclo_infinito.txt` | Mostrar la detención por límite de pasos |
| `maquina_sin_trasicion.txt` | Mostrar el comportamiento cuando falta una transición |

## 10. Realizar una nueva simulación

Para evaluar otra cadena o utilizar una máquina diferente:

1. Utilizar las opciones disponibles en la interfaz para iniciar una nueva ejecución o cargar otra definición.
2. Ingresar la nueva cadena.
3. Ejecutar la simulación.
4. Consultar el historial y el resultado obtenido.

## 11. Recomendaciones de uso

- Verificar que el archivo de definición tenga todos los bloques requeridos.
- Utilizar símbolos que pertenezcan al alfabeto declarado.
- Comprobar que los estados utilizados en las transiciones estén definidos.
- Conservar los archivos de ejemplo para realizar demostraciones.
- Revisar el historial para comprender cómo evoluciona la máquina en cada paso.

## 12. Conclusión

El Simulador de Máquina de Turing permite representar de manera visual y práctica el funcionamiento de una máquina de Turing, facilitando la comprensión de sus estados, transiciones, movimientos de cinta y resultados de ejecución.