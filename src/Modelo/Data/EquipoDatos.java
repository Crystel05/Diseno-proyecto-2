package Modelo.Data;

import java.util.ArrayList;

public class EquipoDatos {
    public ArrayList<GuerreroDatos> getDatosGuerreros() {
        return datosGuerreros;
    }

    ArrayList<GuerreroDatos> datosGuerreros = new ArrayList<>();

    public EquipoDatos(){

    }

    public void addDatosGuerrero(GuerreroDatos guerreroDatos){
        datosGuerreros.add(guerreroDatos);
    }


}
