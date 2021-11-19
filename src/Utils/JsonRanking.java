package Utils;


import Model.Character;
import Model.Weapon;
import Modelo.Usuario;
import com.google.gson.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;

/**
 *
 * @author Natalia
 */
public class JsonRanking {
    private static JsonRanking instance;
    private String URL;
    private final Gson json;
    private ObjFormat data;

    private JsonRanking () throws IOException{
        this.URL = "src\\Utils\\ranking.json";
        this.json = new Gson();
        data = new ObjFormat();
        this.readJSON();
        this.bubbleSortArrayList();
    }

    public static JsonRanking getInstance() throws IOException{
        if(JsonRanking.instance != null){
            return JsonRanking.instance;
        }
        JsonRanking.instance = new JsonRanking();
        return instance;
    }

    private class ObjFormat{
        public ArrayList<Usuario> usuarios = new ArrayList<>();
    }

    public void readJSON() throws FileNotFoundException, IOException {
        try {
            if(new File(this.URL).exists()){
                InputStream is = new FileInputStream(this.URL);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                ObjFormat data = json.fromJson(bufferedReader, ObjFormat.class);
                if (data !=  null){
                    this.data = data;
                }
                bufferedReader.close();
            }
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            System.out.println(e);
        }
    }


    /**
     * Con los datos cargados con anterioridad, los toma para escribir en el Json los objetos existentes
     * para actualizar cambios
     * @throws IOException
     */
    public void updateJSON() throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(this.URL)));
            writer.write("");
            writer.write("{\"usuarios\":");
            writer.write(json.toJson(getUsuarios()));
            writer.write("}");
            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }


    }
    public void writeJSON(Usuario pObject) throws IOException {
        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(this.URL)));
            this.data.usuarios.add(pObject);
            writer.write(json.toJson(this.data));
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Usuario> getUsuarios(){
        return this.data.usuarios;
    }

    public void bubbleSortArrayList() {

        int size = this.data.usuarios.size();
        int prom1 = 0;
        int prom2 = 0;
        // loop to access each array element
        for (int i = 0; i < size - 1; i++)
            // loop to compare array elements
            for (int j = 0; j < size - i - 1; j++) {
                // compare two adjacent elements
                // change > to < to sort in descending order
                try {
                    prom1 = this.data.usuarios.get(j).getPartidasGanadas() / (this.data.usuarios.get(j).getPartidasGanadas()
                            + this.data.usuarios.get(j).getPartidasPerdidas());
                }
                catch (ArithmeticException e) {
                    System.out.println("Zero cannot divide any number");
                }

                try {
                    prom2 = this.data.usuarios.get(j + 1).getPartidasGanadas()/(this.data.usuarios.get(j + 1).getPartidasGanadas()
                            +this.data.usuarios.get(j + 1).getPartidasPerdidas());
                }
                catch (ArithmeticException e) {
                    System.out.println("Zero cannot divide any number");
                }


                if (this.data.usuarios.get(j).getPartidasGanadas() < this.data.usuarios.get(j+1).getPartidasGanadas()) {
                    // swapping occurs if elements
                    // are not in the intended order
                    Usuario temp = this.data.usuarios.get(j);
                    this.data.usuarios.set(j, this.data.usuarios.get(j + 1));
                    this.data.usuarios.set(j + 1, temp);

                }

            }
    }

    public boolean checkClient(String name){
        for (Usuario u: getUsuarios()){
            if (u.getNombre().equals(name)){
                return true;
            }
        }
        return false;
    }

    public Usuario getUserClient(String name){
        for (Usuario u: getUsuarios()){
            if (u.getNombre().equals(name)){  //Equals para strings :)
                return u;
            }
        }
        return null;
    }


}