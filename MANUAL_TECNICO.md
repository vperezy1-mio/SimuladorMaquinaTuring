
# MANUAL TÉCNICO
## Simulador de Máquina de Turing

**Universidad:** Universidad Mariano Gálvez de Guatemala  
**Proyecto:** Simulador de Máquina de Turing  
**Lenguaje:** Java 17  
**Herramienta de construcción:** Apache Maven  
**Interfaz gráfica:** Java Swing  

---

## 1. Introducción

Este documento describe la estructura técnica, arquitectura, componentes y funcionamiento interno del Simulador de Máquina de Turing.

El sistema fue desarrollado utilizando Java y una arquitectura organizada por paquetes, con el propósito de separar las responsabilidades de la interfaz gráfica, el procesamiento de archivos, la validación de datos, el modelo de la máquina y la ejecución de las simulaciones.

## 2. Arquitectura del sistema

El proyecto utiliza una organización modular por responsabilidades.

Los principales paquetes son:

| Paquete | Responsabilidad |
|---|---|
| `gui` | Presentación de la interfaz gráfica |
| `controller` | Coordinación de las operaciones |
| `loader` | Lectura e interpretación de archivos |
| `validation` | Validación de máquinas y cadenas |
| `model` | Representación de los componentes de la máquina |
| `engine` | Ejecución y control de la simulación |

## 3. Estructura del código fuente

```text
com.umg.simuladormaquinaturing/
│
├── SimuladorMaquinaTuring.java
│
├── controller/
│   └── SimulationController.java
│
├── engine/
│   ├── SimulationResult.java
│   ├── SimulationStatus.java
│   ├── SimulationStep.java
│   └── TuringMachineSimulator.java
│
├── gui/
│   └── MainWindow.java
│
├── loader/
│   └── MachineFileLoader.java
│
├── model/
│   ├── Movement.java
│   ├── Tape.java
│   ├── Transition.java
│   └── TuringMachine.java
│
└── validation/
    └── MachineValidator.java
```

## 4. Descripción de los componentes

### 4.1 Clase principal: SimuladorMaquinaTuring

Es el punto de entrada de la aplicación. Se encarga de iniciar el programa y mostrar la ventana principal.

### 4.2 Paquete GUI

**MainWindow**

Contiene la interfaz gráfica del sistema. Permite seleccionar archivos, ingresar cadenas, iniciar simulaciones y consultar los resultados e historial de ejecución.

### 4.3 Paquete Controller

**SimulationController**

Actúa como intermediario entre la interfaz gráfica y los componentes encargados de procesar y ejecutar la máquina de Turing.

Coordina la carga, validación y simulación de los datos.

### 4.4 Paquete Loader

**MachineFileLoader**

Lee el archivo de definición de la máquina de Turing, interpreta sus bloques y construye la estructura correspondiente para su utilización en el simulador.

### 4.5 Paquete Validation

**MachineValidator**

Verifica que la definición de la máquina cumpla las condiciones necesarias para ejecutarse.

Entre sus responsabilidades se encuentran:

- Comprobar los estados definidos.
- Validar el estado inicial.
- Validar los estados de aceptación y rechazo.
- Comprobar el alfabeto.
- Revisar las transiciones.
- Validar los símbolos de las cadenas de entrada.

### 4.6 Paquete Model

**TuringMachine**

Representa formalmente la máquina de Turing, incluyendo estados, alfabeto, estado inicial, estados finales y transiciones.

**Transition**

Representa una transición con estado actual, símbolo leído, estado siguiente, símbolo escrito y movimiento.

**Tape**

Representa la cinta de la máquina mediante una estructura de datos que permite almacenar y consultar posiciones positivas y negativas.

**Movement**

Enumera los movimientos disponibles para la cabeza lectora:

- D: derecha.
- I: izquierda.
- N: sin movimiento.

### 4.7 Paquete Engine

**TuringMachineSimulator**

Es el componente encargado de ejecutar la simulación. Lee los símbolos de la cinta, consulta las transiciones, actualiza los estados, escribe símbolos y desplaza la cabeza lectora.

También controla las condiciones de finalización y el límite máximo de pasos.

**SimulationResult**

Almacena el resultado final de una ejecución, incluyendo estado final, cantidad de pasos, contenido de la cinta e historial.

**SimulationStep**

Representa un paso individual de la simulación y almacena la información necesaria para mostrar su evolución.

**SimulationStatus**

Define los posibles resultados de ejecución:

- ACCEPTED.
- REJECTED.
- HALTED_NO_TRANSITION.
- STEP_LIMIT_EXCEEDED.

## 5. Funcionamiento interno

El proceso general de ejecución sigue esta secuencia:

1. El usuario selecciona un archivo de definición.
2. El cargador interpreta su contenido.
3. El validador comprueba la estructura de la máquina.
4. El usuario ingresa una cadena de entrada.
5. Se valida que los símbolos pertenezcan al alfabeto permitido.
6. Se inicializa la cinta y la cabeza lectora.
7. El simulador consulta el símbolo actual.
8. Busca la transición correspondiente.
9. Escribe el símbolo indicado y ejecuta el movimiento.
10. Actualiza el estado y registra el paso en el historial.
11. Repite el procedimiento hasta alcanzar una condición de finalización.
12. Presenta el resultado al usuario.

## 6. Representación de la cinta

La cinta se implementa utilizando una estructura `HashMap` con posiciones enteras como claves y caracteres como valores.

Esta representación permite almacenar posiciones negativas y positivas sin necesidad de crear un arreglo de tamaño fijo.

El símbolo `_` representa una celda en blanco.

La cabeza lectora mantiene una posición que se actualiza según el movimiento indicado por cada transición.

## 7. Función de transición

Una transición tiene la siguiente estructura:

```text
MT(estado_actual, simbolo_leido) =
(estado_siguiente, simbolo_escrito, movimiento)
```

El simulador utiliza esta información para determinar el siguiente estado, modificar el contenido de la cinta y actualizar la posición de la cabeza lectora.

## 8. Control de ejecución

La simulación contempla las siguientes condiciones:

- Estado de aceptación: finaliza indicando que la cadena fue aceptada.
- Estado de rechazo: finaliza indicando que la cadena fue rechazada.
- Transición inexistente: detiene la ejecución cuando no encuentra una transición aplicable.
- Límite máximo: detiene la ejecución al alcanzar 10,000 pasos.

## 9. Tecnologías y herramientas

| Herramienta | Versión o uso |
|---|---|
| Java | 17 |
| Apache Maven | Gestión del proyecto y dependencias |
| JUnit Jupiter | 5.11.4 |
| Maven Compiler Plugin | 3.13.0 |
| Maven Surefire Plugin | 3.5.2 |
| NetBeans | Entorno de desarrollo |
| Swing | Interfaz gráfica |

## 10. Configuración de Maven

El archivo `pom.xml` contiene la configuración de compilación y dependencias del proyecto.

Se utiliza Java 17 como versión de compilación y JUnit 5.11.4 como dependencia de pruebas.

Para construir el proyecto se utiliza:

```bash
mvn clean install
```

Este comando limpia los archivos generados anteriormente, compila el código fuente, compila las pruebas y construye el paquete del proyecto.

## 11. Consideraciones técnicas

- La aplicación utiliza una representación de cinta que admite posiciones negativas.
- Las definiciones se cargan desde archivos de texto.
- La validación se realiza antes de ejecutar la simulación.
- El historial permite consultar la evolución de la máquina.
- El límite de pasos evita que una ejecución indefinida mantenga el programa ocupado.

## 12. Conclusión

La arquitectura modular del Simulador de Máquina de Turing permite separar las funciones principales del sistema y facilita su mantenimiento y comprensión.

La aplicación integra lectura de archivos, validación, representación de una máquina formal y ejecución de transiciones, proporcionando una herramienta práctica para estudiar los fundamentos de la teoría de la computación.