package Modelo;

import java.util.ArrayList;

public class Equipo {

    private ArrayList<Character> guerreros;
    private  Usuario usuario;

    public Equipo(ArrayList<Character> guerreros, Usuario usuario) {
        this.guerreros = guerreros;
        this.usuario = usuario;
    }

    public Equipo() {
    }

    public ArrayList<Character> getGuerreros() {
        return guerreros;
    }

    public void setGuerreros(ArrayList<Character> guerreros) {
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
