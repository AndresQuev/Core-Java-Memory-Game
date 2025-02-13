package Controlador;

import Modelo.Modelo;
import java.awt.event.ActionEvent;  //Importa las clases necesarias para manejar los eventos de acción.
import java.awt.event.ActionListener;  //Importa la interfaz ActionListener, que escuchará las acciones de los botones.
import javax.swing.JButton;  //Importa la clase JButton para trabajar con botones en la interfaz gráfica.
import Vista.Vista;  //Importa la clase Vista, que manejará la interfaz gráfica.

public class Controlador implements ActionListener {//implementa la interfaz ActionListener para manejar los clics en los botones.
    private Modelo modelo;  //Variable que almacena una referencia al modelo del juego (la lógica y las cartas).
    private Vista vista;  //Variable que almacena una referencia a la vista (la interfaz gráfica).
    private boolean esperandoSegundaCarta = false;  //indica si estamos esperando una segunda carta para comparar.
    private int indicePrimeraCarta = -1;  //Variable para almacenar el índice de la primera carta seleccionada.

    public Controlador(Modelo modelo, Vista vista) {  //Constructor del controlador que recibe el modelo y la vista.
        this.modelo = modelo;  //Inicializa la referencia al modelo.
        this.vista = vista;  //Inicializa la referencia a la vista.
    }


    //ESTE METODO SE EJECUTA CUANDO SE HACE CLICK EN UN BOTON
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton botonClickeado = (JButton) e.getSource(); //Obtiene el botón que fue clickeado.
        int indice = -1;  //Variable que almacenará el índice del botón clickeado.

        for (int i = 0; i < vista.obtenerBotones().length; i++) {  //Recorre todos los botones de la vista.
            if (vista.obtenerBotones()[i] == botonClickeado) {  //Si el botón clickeado es el mismo que el botón actual en el bucle:
                indice = i;  //Asigna el índice del botón clickeado.
                break;  //Sale del bucle porque ya se encontró el botón correspondiente.
            }
        }

        vista.voltearCarta(indice, modelo.getCarta(indice));  //Voltea la carta en la vista, mostrando el valor de la carta.
        modelo.descubrirCarta(indice);  //Marca la carta como descubierta en el modelo.

        if (!esperandoSegundaCarta) {  //Si no estamos esperando una segunda carta:
            indicePrimeraCarta = indice;  //Guarda el índice de la primera carta.
            esperandoSegundaCarta = true;  //Establece que estamos esperando la segunda carta.
        } else {  //Si ya hemos seleccionado la primera carta y ahora seleccionamos la segunda:
            vista.voltearCarta(indice, modelo.getCarta(indice));  //Voltea la segunda carta en la vista.

            final int finalIndicePrimeraCarta = indicePrimeraCarta;  //Se hace final para ser utilizado en la expresión lambda.
            final int finalIndice = indice;  //Se hace final para ser utilizado en la expresión lambda.

            javax.swing.Timer timer = new javax.swing.Timer(500, evt -> {  //Crea un temporizador de 500 ms
                if (modelo.getCarta(finalIndicePrimeraCarta).equals(modelo.getCarta(finalIndice))) {  //Si las cartas son iguales:
                    vista.deshabilitarCartas(finalIndicePrimeraCarta, finalIndice);  //Deshabilita los botones de las cartas emparejadas.
                    modelo.emparejarCarta(finalIndicePrimeraCarta);  //Marca las cartas como emparejadas en el modelo.
                    modelo.emparejarCarta(finalIndice);  //Marca las cartas como emparejadas en el modelo.
                } else {  // Si las cartas no son iguales:
                    modelo.ocultarCarta(finalIndicePrimeraCarta);  //Oculta la primera carta en el modelo.
                    modelo.ocultarCarta(finalIndice);  //Oculta la segunda carta en el modelo.
                    vista.voltearCarta(finalIndicePrimeraCarta, "?");  //Vuelve a voltear la primera carta en la vista (muestra "?").
                    vista.voltearCarta(finalIndice, "?");  //Vuelve a voltear la segunda carta en la vista (muestra "?").
                }

                esperandoSegundaCarta = false;  //Ya no espera la segunda carta.

                if (modelo.juegoTerminado()) {  //Si el juego ha terminado:
                    vista.mostrarMensajeFinJuego();  // Muestra un mensaje de fin de juego en la vista.
                }
            });

            timer.setRepeats(false);  //El temporizador se ejecuta solo una vez.
            timer.start();  // Inicia el temporizador.
        }
    }

    // Método que reinicia el juego, restableciendo el estado del modelo y de la vista.
    public void reiniciarJuego() {
        modelo.reiniciar();  //Reinicia la lógica del juego en el modelo.
        vista.limpiarCartas();  //Restablece las cartas a su estado inicial (ocultas y habilitadas).
        esperandoSegundaCarta = false;  //Restablece la bandera de espera de la segunda carta.
        indicePrimeraCarta = -1;  //Restablece el índice de la primera carta.

        //Rehabilita todos los botones de la vista, asegurándose que las cartas vuelvan a su estado inicial.
        for (JButton boton : vista.obtenerBotones()) {
            boton.setEnabled(true);  //Habilita cada botón (en caso de que estuviera deshabilitado).
            boton.setText("?");  //Restaura el texto de cada botón a "?".
        }
    }

    //Este método se utiliza para asociar el controlador a los botones.
    public void setControlador(Controlador controlador) {
        for (JButton boton : this.vista.botones) {  //Recorre todos los botones y les agrega un ActionListener que llama al controlador cuando se hace clic.
            boton.addActionListener(controlador);
        }
        this.vista.botonReiniciar.addActionListener(e -> controlador.reiniciarJuego());  //Agrega un ActionListener al botón de reiniciar que llama al método reiniciarJuego del controlador.
    }
}