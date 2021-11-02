package Modelo;

import java.util.ArrayList;

public class Equipo {

    private ArrayList<Guerrero> guerreros;
    private  Usuario usuario;

    public Equipo(ArrayList<Guerrero> guerreros, Usuario usuario) {
        this.guerreros = guerreros;
        this.usuario = usuario;
    }

    public Equipo() {
    }

    public ArrayList<Guerrero> getGuerreros() {
        return guerreros;
    }

    public void setGuerreros(ArrayList<Guerrero> guerreros) {
        this.guerreros = guerreros;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
