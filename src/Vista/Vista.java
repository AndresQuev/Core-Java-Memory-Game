package Vista;

import Controlador.Controlador;  //Importa la clase Controlador
import javax.swing.*;  //Importa las clases necesarias de la biblioteca Swing para la interfaz gráfica.
import java.awt.*;  //Importa las clases de diseño y componentes gráficos

//2 MANEJOS DE EVENTOS, CLIQUEAR LAS CARTAS Y BOTON DE REINICIAR


public class Vista extends JFrame {//extiende JFrame para crear una ventana de la aplicación.

    public JButton[] botones;  //Arreglo de botones que cada uno representa a una carta.
    public JButton botonReiniciar;  //botón de reinicio.
    private static final int FILAS = 4;  //Define la cantidad de filas en el tablero
    private static final int COLUMNAS = 4;  //Define la cantidad de columnas en el tablero

    public Vista() { //Constructor de la clase Vista
        setTitle("Memorama");  //Título
        setSize(400, 400);  //Tamaño, 400 px x 400 px
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //El programa se cierra al cerrar la ventana
        setLocationRelativeTo(null);  //Centra la ventana en la pantalla.
        setLayout(new BorderLayout());  //Establece el layout (diseño) de la ventana con un BorderLayout.

        JPanel panelCartas = new JPanel();  //Crea un JPanel para contener las cartas.
        panelCartas.setLayout(new GridLayout(FILAS, COLUMNAS, 5, 5));  //Establece un GridLayout con 4 filas y 4 columnas, y espacio de 5 px
        botones = new JButton[FILAS * COLUMNAS];  //Arreglo de botones con el tamaño adecuado para el tablero de cartas.

        for (int i = 0; i < botones.length; i++) { //Crea los botones y los agrega al panelCartas.
            botones[i] = new JButton("?");  //Establece el texto inicial de cada botón como "?" (carta oculta).
            botones[i].setFont(new Font("Arial", Font.BOLD, 20));  //Establece la fuente del texto del botón.
            botones[i].setFocusPainted(false);  //Elimina el efecto de enfoque en los botones.
            botones[i].setBackground(Color.WHITE);  //Establece el fondo de cada botón como blanco.
            botones[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));  //Establece un borde negro y delgado para cada botón.

            panelCartas.add(botones[i]);  //Añade el botón al panelCartas.
        }

        add(panelCartas, BorderLayout.CENTER);  //Añade el panelCartas al centro de la ventana (en el BorderLayout).

        //BOTON DE REINICIO

        botonReiniciar = new JButton("Reiniciar");  //Crea el botón de reinicio con el texto "Reiniciar".
        botonReiniciar.setFont(new Font("Arial", Font.BOLD, 16));  //Establece la fuente del botón de reinicio.
        botonReiniciar.setBackground(Color.LIGHT_GRAY);  //Establece el fondo del botón de reinicio como gris claro.
        botonReiniciar.setForeground(Color.BLACK);  //Establece el color del texto del botón de reinicio como negro.
        botonReiniciar.setFocusPainted(false);  //Elimina el efecto de enfoque en el botón de reinicio.
        botonReiniciar.setPreferredSize(new Dimension(150, 40));  //Establece un tamaño más pequeño para el botón de reinicio.
        add(botonReiniciar, BorderLayout.SOUTH);  //Añade el botón de reinicio al sur (parte inferior) de la ventana.

    }



    //Método para actualizar el texto de un botón específico en el tablero de cartas.
    public void actualizarBoton(int indice, String texto) {
        botones[indice].setText(texto);  //Cambia el texto del botón en el índice especificado.
    }

    //Método que devuelve el arreglo de botones para acceder desde el controlador
    public JButton[] obtenerBotones() {
        return botones;  //Retorna el arreglo de botones.
    }

    // Método para voltear una carta (mostrar el valor de la carta).
    public void voltearCarta(int indice, String texto) {
        botones[indice].setText(texto);  // Cambia el texto del botón en el índice especificado al valor de la carta.
    }

    // Método para voltear las cartas nuevamente a "?" después de un breve retraso (simula el tiempo de espera al no emparejar las cartas).
    public void voltearCartasDeNuevo(int indice1, int indice2) {
        try {
            //Si no ponia esto, la carta se volteaba muy rapido.

            Thread.sleep(500);  //Pausa de medio segundo para simular el tiempo de espera.
            botones[indice1].setText("?");  //Cambia el texto de la primera carta a "?".
            botones[indice2].setText("?");  //Cambia el texto de la segunda carta a "?".
        } catch (InterruptedException e) {
            e.printStackTrace();  //Si ocurre una excepción durante el sleep, se imprime el error.
        }
    }

    //Método para deshabilitar los botones de las cartas emparejadas (para que no se puedan volver a seleccionar).
    public void deshabilitarCartas(int indice1, int indice2) {
        botones[indice1].setEnabled(false);  //Deshabilita el primer botón (carta emparejada).
        botones[indice2].setEnabled(false);  //Deshabilita el segundo botón (carta emparejada).
    }

    //Método para mostrar un mensaje al finalizar el juego (cuando el jugador ha ganado).
    public void mostrarMensajeFinJuego() {
        JOptionPane.showMessageDialog(this, "¡Felicidades, has ganado!");  //Muestra el mensaje de victoria
    }

    //Método para limpiar todos los botones, es decir, restablecerlos a su estado inicial (ocultos y habilitados).
    public void limpiarCartas() {
        for (JButton boton : botones) {  //Recorre todos los botones.
            boton.setEnabled(true);  //Habilita todos los botones.
            boton.setText("?");  //Cambia el texto de todos los botones a "?" (carta oculta).
        }
    }

    //Método que reinicia el juego, limpiando las cartas.
    public void reiniciarJuego() {
        limpiarCartas();  //Llama al método limpiarCartas para restablecer el estado de las cartas.
    }
}
