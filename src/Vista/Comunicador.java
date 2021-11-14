package Vista;

import Modelo.Personaje;

import java.util.ArrayList;

public class Comunicador {
    private ArrayList<Personaje> guerrerosEscogidos = new ArrayList<>();
    private static Comunicador comunicador;

    public Comunicador(){}

    public static Comunicador getInstance(){
        if (comunicador == null){
            comunicador = new Comunicador();
        }
        return comunicador;
    }

    public ArrayList<Personaje> getGuerrerosEscogidos() {
        return guerrerosEscogidos;
    }

    public void setGuerrerosEscogidos(ArrayList<Personaje> guerrerosEscogidos) {
        this.guerrerosEscogidos = guerrerosEscogidos;
    }
}
