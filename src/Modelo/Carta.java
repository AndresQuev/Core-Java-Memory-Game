package Modelo;

public class Carta {

    // Atributos
    private String valor;  //Valor de la carta
    private boolean descubierta;  //Indicar si la carta se ha dado vuelta o no
    private boolean emparejada;  //Indicr si la carta está emparejada con otra carta.

    // Constructor
    public Carta(String valor) {
        this.valor = valor;
        this.descubierta = false;  //Inicialmente está oculta
        this.emparejada = false;  //Inicialmente no está emparejada con otra
    }

    // Métodos
    public String getValor() {  //Método para obtener el valor de la carta.
        return valor;
    }

    public boolean estaDescubierta() {  //Método que verifica si la carta está descubierta.
        return descubierta;
    }

    public void descubrir() {  //Método para descubrir la carta.
        this.descubierta = true;  //Cambia el estado de la carta a "descubierta".
    }

    public void ocultar() {  //Método para ocultar (volver a tapar) la carta.
        this.descubierta = false;  //Cambia el estado de la carta a "oculta".
    }

    public boolean estaEmparejada() {  //Método que verifica si la carta está emparejada con otra carta.
        return emparejada;  // Devuelve "true" si la carta está emparejada, de lo contrario "false".
    }

    public void emparejar() {  //Método para emparejar la carta.
        this.emparejada = true;  //Cambia el estado de la carta a "emparejada".
    }

    @Override
    public String toString() {  //Sobrescribe el método toString() para representar la carta como un string.
        return valor;
    }
}
