package Modelo;

import java.util.*; //Proporciona estructuras de datos y utilidades auxiliares.

public class Modelo {
    private ArrayList<String> cartas; //Arraylist para almacenar los valores de las cartas
    private ArrayList<Boolean> estadoCartas; //Arraylist para almacenar si una carta está descubierta
    private ArrayList<Boolean> cartasEmparejadas; //Arraylist para almacenar si una carta ya fue emparejada
    private Stack<Integer> pilaMovimientos; //Pila para almacenar los movimientos y permitir deshacer


    public Modelo() {
        pilaMovimientos = new Stack<>(); //Inicializa la pila de movimientos
        inicializarCartas(); //se inicializan las cartas
    }

    //METODOS

    private void inicializarCartas() {
        cartas = new ArrayList<>(); //Inicializa la lista de cartas
        estadoCartas = new ArrayList<>(); //Inicializa la lista de estados de cartas
        cartasEmparejadas = new ArrayList<>(); //Inicializa la lista de cartas emparejadas
        String[] valores = {"A", "B", "C", "D", "E", "F", "G", "H"}; //Valores de las cartas
        for (String valor : valores) {
            cartas.add(valor);
            cartas.add(valor); //Se agregan parejas de cartas con el mismo valor
        }
        Collections.shuffle(cartas); //Para mezclar las cartas
        for (int i = 0; i < cartas.size(); i++) {
            estadoCartas.add(false); //Todas las cartas comienzan ocultas
            cartasEmparejadas.add(false); //Ninguna carta está emparejada al inicio
        }
    }

    public String getCarta(int indice) {
        return cartas.get(indice); //Devuelve el valor de la carta en la posición indicada
    }

    public void descubrirCarta(int indice) {
        estadoCartas.set(indice, true); //Marca la carta como descubierta
        pilaMovimientos.push(indice); //Guarda el movimiento en la pila
    }

    public void ocultarCarta(int indice) {
        estadoCartas.set(indice, false); //Marca la carta como oculta
    }

    public void emparejarCarta(int indice) {
        cartasEmparejadas.set(indice, true); //Marca la carta como emparejada
    }


    public boolean juegoTerminado() {
        return !estadoCartas.contains(false); //Verifica si todas las cartas están descubiertas
    }

    public void reiniciar() {
        cartas.clear(); //Limpia
        estadoCartas.clear(); //Las oculta a todas
        cartasEmparejadas.clear(); //Las que estaban emparejadas, se limpian.
        inicializarCartas(); //Vuelven a inicializarse
    }
}
