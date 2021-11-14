package Modelo.Data;

import java.util.ArrayList;

public class GuerreroDatos {
    ArrayList<String> nombresArmas = new ArrayList<>();
    String name;

    public GuerreroDatos(String name){
        this.name = name;
    }

    public void addArma(String string){
        nombresArmas.add(string);
    }

    public ArrayList<String> getNombresArmas() {
        return nombresArmas;
    }

    public String getName() {
        return name;
    }
}
