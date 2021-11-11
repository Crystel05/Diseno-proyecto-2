package Modelo;

public class Usuario {

    private String nombre;
    private int ranking;
    private int partidasGanadas;
    private int partidasPerdidas;
    private int ataquesExitosos;
    private int ataquesFallados;
    private int rendiciones;

    public Usuario(String nombre, int ranking, int partidasGanadas, int partidasPerdidas) {
        this.nombre = nombre;
        this.ranking = ranking;
        this.partidasGanadas = partidasGanadas;
        this.partidasPerdidas = partidasPerdidas;
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public Usuario() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }

    public int getAtaquesExitosos() {
        return ataquesExitosos;
    }

    public void setAtaquesExitosos(int ataquesExitosos) {
        this.ataquesExitosos = ataquesExitosos;
    }

    public int getAtaquesFallados() {
        return ataquesFallados;
    }

    public void setAtaquesFallados(int ataquesFallados) {
        this.ataquesFallados = ataquesFallados;
    }

    public int getRendiciones() {
        return rendiciones;
    }

    public void setRendiciones(int rendiciones) {
        this.rendiciones = rendiciones;
    }

}

