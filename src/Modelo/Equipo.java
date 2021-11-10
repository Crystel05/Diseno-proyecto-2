package Modelo;

import java.util.ArrayList;

public class Equipo {

    private ArrayList<Personaje> guerreros;
    private  Usuario usuario;

    public Equipo(ArrayList<Personaje> guerreros, Usuario usuario) {
        this.guerreros = guerreros;
        this.usuario = usuario;
    }

    public Equipo() {
    }

    public ArrayList<Personaje> getGuerreros() {
        return guerreros;
    }

    public void setGuerreros(ArrayList<Personaje> guerreros) {
        this.guerreros = guerreros;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void giveUp(){
        //Modificar el historial
    }
}
