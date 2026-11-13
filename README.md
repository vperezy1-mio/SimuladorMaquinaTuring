# Simulador de Máquina de Turing

## Universidad Mariano Gálvez de Guatemala

**Proyecto académico:** Simulador de Máquina de Turing  
**Lenguaje de programación:** Java  
**Versión de Java:** Java 17  
**Herramienta de construcción:** Apache Maven  
**Interfaz gráfica:** Java Swing  

---

## 1. Descripción del proyecto

El Simulador de Máquina de Turing es una aplicación desarrollada en Java que permite cargar, validar y ejecutar definiciones formales de máquinas de Turing mediante archivos de texto.

El programa permite seleccionar un archivo que contiene la definición de una máquina, ingresar una cadena de entrada y observar paso a paso el comportamiento de la cinta, el estado actual, el símbolo leído, la posición de la cabeza lectora y las transiciones ejecutadas.

Al finalizar la simulación, el sistema informa si la cadena fue aceptada o rechazada por la máquina.

## 2. Objetivos

### Objetivo general

Desarrollar un simulador de Máquina de Turing que permita comprender y representar su funcionamiento mediante una interfaz gráfica, aplicando los conceptos fundamentales de la teoría de la computación.

### Objetivos específicos

- Interpretar definiciones formales de máquinas de Turing desde archivos de texto.
- Validar los estados, alfabetos y transiciones definidos.
- Verificar que las cadenas de entrada utilicen símbolos permitidos.
- Simular los movimientos de la cabeza lectora sobre la cinta.
- Registrar el historial de ejecución.
- Determinar si una cadena es aceptada o rechazada.

## 3. Funcionalidades principales

- Carga de archivos de definición de máquinas de Turing.
- Validación de la estructura y contenido de las definiciones.
- Ingreso interactivo de cadenas de entrada.
- Validación del alfabeto de entrada.
- Representación de una cinta con posiciones positivas y negativas.
- Ejecución de movimientos hacia la derecha (D), izquierda (I) y sin movimiento (N).
- Registro del historial de simulación.
- Identificación de estados de aceptación y rechazo.
- Detección de transiciones inexistentes.
- Límite de seguridad de 10,000 pasos para detener simulaciones que no terminan.
- Posibilidad de cargar otra máquina y ejecutar nuevas cadenas.

## 4. Tecnologías utilizadas

| Tecnología | Utilización |
|---|---|
| Java 17 | Desarrollo de la aplicación |
| Java Swing | Interfaz gráfica |
| Apache Maven | Gestión y construcción del proyecto |
| JUnit 5 | Framework de pruebas |

## 5. Estructura del proyecto

```text
SimuladorMaquinaTuring/
│
├── ejemplo/
│   ├── maquina_paridad.txt
│   ├── maquina_termina_en_uno.txt
│   ├── maquina_movimientos.txt
│   ├── maquina_posicion_negativa.txt
│   ├── maquina_ciclo_infinito.txt
│   └── maquina_sin_trasicion.txt
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/umg/simuladormaquinaturing/
│   │           ├── controller/
│   │           ├── engine/
│   │           ├── gui/
│   │           ├── loader/
│   │           ├── model/
│   │           ├── validation/
│   │           └── SimuladorMaquinaTuring.java
│   │
│   └── test/
│       └── java/
│
├── pom.xml
└── README.md
```

## 6. Requisitos del sistema

Para ejecutar el proyecto se necesita:

- Java Development Kit (JDK) 17 o superior.
- Apache Maven.
- NetBeans u otro entorno de desarrollo compatible con proyectos Maven.

## 7. Instalación y ejecución

### Clonar el repositorio

```bash
git clone URL_DEL_REPOSITORIO
```

### Abrir el proyecto

Abrir la carpeta `SimuladorMaquinaTuring` desde NetBeans como proyecto Maven.

### Compilar el proyecto

Desde la terminal, ejecutar:

```bash
mvn clean install
```

### Ejecutar la aplicación

Desde NetBeans, ejecutar la clase principal:

```text
SimuladorMaquinaTuring.java
```

También puede iniciarse desde la terminal, utilizando el comando:

```bash
mvn exec:java -Dexec.mainClass="com.umg.simuladormaquinaturing.SimuladorMaquinaTuring"
```

Nota: para utilizar este último comando se debe configurar el complemento Maven Exec en el proyecto. Como alternativa, se puede ejecutar la clase principal directamente desde NetBeans.

## 8. Formato del archivo de definición

La máquina de Turing se define mediante un archivo de texto con la siguiente estructura:

```text
Estados(q0, q1, qa, qr)
Inicial(q0)
Ha(qa)
He(qr)
Alfabeto(0, 1, _)

MT(q0, 0) = (q0, 0, D)
MT(q0, 1) = (q1, 1, D)
MT(q0, _) = (qa, _, N)
```

### Descripción de los elementos

| Elemento | Descripción |
|---|---|
| Estados | Conjunto de estados de la máquina |
| Inicial | Estado inicial |
| Ha | Estado de aceptación |
| He | Estado de rechazo |
| Alfabeto | Símbolos permitidos en la cinta |
| MT | Función de transición |

### Movimientos de la cabeza lectora

- **D:** desplazamiento hacia la derecha.
- **I:** desplazamiento hacia la izquierda.
- **N:** permanece en la posición actual.

El símbolo `_` representa el espacio en blanco de la cinta.

## 9. Máquinas de ejemplo

En la carpeta `ejemplo` se incluyen diferentes definiciones para demostrar el funcionamiento del simulador:

| Archivo | Descripción |
|---|---|
| maquina_paridad.txt | Reconoce cadenas binarias con cantidad par de unos |
| maquina_termina_en_uno.txt | Reconoce cadenas binarias que terminan en uno |
| maquina_movimientos.txt | Demuestra los movimientos D, I y N |
| maquina_posicion_negativa.txt | Demuestra el desplazamiento hacia posiciones negativas |
| maquina_ciclo_infinito.txt | Demuestra el límite máximo de pasos |
| maquina_sin_trasicion.txt | Demuestra el comportamiento ante una transición inexistente |

## 10. Arquitectura general

El proyecto está organizado por responsabilidades:

- **GUI:** administra la interfaz gráfica.
- **Controller:** coordina las operaciones entre la interfaz y el simulador.
- **Loader:** interpreta los archivos de definición.
- **Validation:** verifica la estructura de la máquina y las cadenas de entrada.
- **Model:** representa los componentes de la máquina de Turing.
- **Engine:** ejecuta la simulación y genera el historial de resultados.

## 11. Consideraciones

La cinta se representa mediante una estructura de datos que permite acceder a posiciones positivas y negativas, simulando una cinta conceptualmente infinita.

Para evitar ejecuciones indefinidas, el simulador establece un límite de 10,000 pasos.

Cuando no existe una transición aplicable, la ejecución finaliza indicando que la cadena no fue aceptada.

## 12. Autoría

Proyecto académico desarrollado para la Universidad Mariano Gálvez de Guatemala.

**Autor:** Vielman Perez