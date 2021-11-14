package Modelo.Data;

import java.io.Serializable;
import java.util.ArrayList;

public class EquipoDatos implements Serializable {

    ArrayList<GuerreroDatos> datosGuerreros = new ArrayList<>();
    String nombreUsuario;

    public ArrayList<GuerreroDatos> getDatosGuerreros() {

        return datosGuerreros;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public EquipoDatos(){

    }

    public void addDatosGuerrero(GuerreroDatos guerreroDatos){
        datosGuerreros.add(guerreroDatos);
    }


}
