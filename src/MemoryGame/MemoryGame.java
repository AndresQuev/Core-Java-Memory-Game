package MemoryGame;

import Vista.Vista;  //Importa la clase Vista, que contiene la interfaz gráfica.
import Controlador.Controlador;  //Importa la clase Controlador, que maneja la interacción entre la vista y el modelo.
import Modelo.Modelo;  //Importa la clase Modelo, que maneja la lógica del juego.
import javax.swing.SwingUtilities;  //Importa SwingUtilities, para manejar la interfaz gráfica de Swing.

public class MemoryGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {  //invokeLater para que el código dentro de este Main se ejecute en el hilo de eventos de Swing.
            Modelo modelo = new Modelo();  //Crear instancia de la clase Modelo, que contiene la lógica del juego.
            Vista vista = new Vista();  //Crear instancia de la clase Vista, que contiene la interfaz gráfica.
            Controlador controlador = new Controlador(modelo, vista);  //Crear instancia de la clase Controlador
            controlador.setControlador(controlador);  //Asociar controlador a la vista
            vista.setVisible(true);  //Mostrar la interfaz grafica
        });
    }
}
