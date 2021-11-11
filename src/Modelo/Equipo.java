package Modelo;

import Model.Weapon;

import java.util.ArrayList;

public class Equipo {

    private ArrayList<Personaje> guerreros;
    private  Usuario usuario;
    private boolean inTurn;

    public boolean isInTurn() {
        return inTurn;
    }

    public void setInTurn(boolean inTurn) {
        this.inTurn = inTurn;
    }

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
        usuario.setRendiciones(usuario.getRendiciones()+1);
    }

    public Personaje getGuerrero(String guerreroString) {
        for (Personaje guerrero:getGuerreros()) {
            if(guerrero.getName().equals(guerreroString));
            return guerrero;
        }
        return null;
    }

    public boolean isArmaValida(Personaje personaje,String arma){
        return personaje.isArmaValida(arma);
    }

    public boolean isPersonajeValido(String armaString){
        for (Personaje personaje:getGuerreros()
        ) {
            if(personaje.getName().equals(armaString))
                return true;
        }
        return false;
    }
}
