import Model.*;
import Model.Character;
import Modelo.Arma;
import Modelo.Personaje;
import Modelo.Usuario;
import Utils.JsonLoader;
import Utils.JsonRanking;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pruebas {
    private static JsonLoader json;
    private static JsonRanking jsonRanking;
    private static ICreator factory;
    private static ICreator factory2;

    //GeneralViewerController.getInstance().getCharacterList()

    public static void main(String[] args) throws IOException {

        /*
        json = JsonLoader.getInstance();
        factory = new CharacterPrototypeFactory();
        factory2 = new WeaponPrototypeFactory();

        json.getWeapons().forEach(it -> {
            factory2.addPrototype(it.getName(), it);
        });

        json.getCharacters().forEach(it -> {
            factory.addPrototype(it.getName(), it);
        });

        System.out.println("-----------------Imprimiendo todos los prototipos---------------------"+"\n");
        System.out.println(factory2.getPrototypes()); //Imprimir los prototipos
        System.out.println(factory.getPrototypes()); //Imprimir los prototipos
        System.out.println(factory.getPrototypes().get("Qiqi"));  //Tomando a Qiqi del hash map

        System.out.println("-----------------Imprimiendo todo lo que está en el Json para el personaje Qiqi---------------------"+"\n");
        Personaje personaje = (Personaje) factory.getPrototypes().get("Qiqi");
        System.out.println(personaje.getLevel());
      System.out.println("Arma prueba perfecto");
        System.out.println(personaje.getArmas().get(0).getArmaAtaca());
        System.out.println(personaje.getCurrentWeapon());
        System.out.println(personaje.getDirection());
        System.out.println(personaje.getFieldsInArmy());
        System.out.println(personaje.getHitsPerTime());
        System.out.println(personaje.getLife());
        System.out.println(personaje.getLevelRequired());
        System.out.println(personaje.getName());
        System.out.println(personaje.getAspect());
        System.out.println(personaje.getCost());

        System.out.println("-----------------Imprimiendo prototipo arma tomado del hash con un deepClone ---------------------"+"\n");
        Arma pruebaW = (Arma) factory2.getPrototypeDeepClone("Gouba");
        System.out.println(pruebaW.getName());
        System.out.println(pruebaW.getScope());
        System.out.println(pruebaW.getArmaAtaca());
        */


        jsonRanking = JsonRanking.getInstance();  //Se lee y se guardan user en array
        /*System.out.println("print despues de leer");
        System.out.println(jsonRanking.getUsuarios());

        Usuario u = jsonRanking.getUsuarios().get(0);  //Agarra el Personaje1
        u.setNombre("PersonajeCambiado");  //Cambio objeto
        jsonRanking.updateJSON();  //Quiero actualizar
        */

        

    }

}
