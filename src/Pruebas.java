import Model.CharacterPrototypeFactory;
import Model.ICreator;
import Model.WeaponPrototypeFactory;
import Modelo.Arma;
import Modelo.Personaje;
import Utils.JsonLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pruebas {
    private static JsonLoader json;
    private static ICreator factory;
    private static ICreator factory2;

    //GeneralViewerController.getInstance().getCharacterList()

    public static void main(String[] args) throws IOException {
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
        System.out.println(personaje.getArmas());
        System.out.println(personaje.getCurrentWeapon());
        System.out.println(personaje.getDirection());
        System.out.println(personaje.getFieldsInArmy());
        System.out.println(personaje.getHitsPerTime());
        System.out.println(personaje.getLife());
        System.out.println(personaje.getLevelRequired());
        System.out.println(personaje.getName());
        System.out.println(personaje.getAspect());
        System.out.println(personaje.getCost());


        /*
        Personaje pruebaP = (Personaje) factory.getPrototypeDeepClone("Qiqi");
        System.out.println(pruebaP.getName()); */

    }

}
