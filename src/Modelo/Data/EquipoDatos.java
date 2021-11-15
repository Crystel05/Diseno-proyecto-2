package Modelo.Data;

import java.io.Serializable;
import java.util.ArrayList;

public class EquipoDatos implements Serializable {

    ArrayList<GuerreroDatos> datosGuerreros = new ArrayList<>();
    String nombreUsuario;

    public EquipoDatos(){

    }

    public ArrayList<GuerreroDatos> getDatosGuerreros() { return datosGuerreros; }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre(){return this.nombreUsuario;}

    public void addDatosGuerrero(GuerreroDatos guerreroDatos){
        datosGuerreros.add(guerreroDatos);
    }


}
