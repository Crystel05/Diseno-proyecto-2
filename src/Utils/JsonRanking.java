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

    private void readJSON() throws FileNotFoundException, IOException {
        try {
            if(new File(this.URL).exists()){
                InputStream is = new FileInputStream(this.URL);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                ObjFormat data = json.fromJson(bufferedReader, ObjFormat.class);
                //System.out.println(data);
                //System.out.println(data !=  null);
                if (data !=  null){
                    this.data = data;
                }
                bufferedReader.close();
            }
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            System.out.println(e);
        }
    }



    public void updateJSON(Usuario pObject) throws IOException {
        try {
            InputStream is = new FileInputStream(this.URL);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            JsonElement element = json.fromJson(bufferedReader, JsonElement.class);
            System.out.println("Element");
            System.out.println(element);
            JsonElement u = element.getAsJsonObject().get("usuarios");
            System.out.println("usuarios");
            System.out.println(u);
            JsonArray n = u.getAsJsonArray(); //.get("nombre");
            System.out.println("nombre");
            System.out.println(n);
            for (int i=0; i<n.size();i++){
                JsonElement usuario = n.get(i);
                System.out.println("User especifico");
                System.out.println(usuario.getAsJsonObject());
                if (usuario.getAsJsonObject().get("nombre").getAsString().equals("name")){
                    System.out.println(usuario.getAsJsonObject().get("nombre").getAsString());
                    usuario.getAsJsonObject().addProperty("nombre", "cambio");
                }

            }

            //writeJSON(pObject);
            //reader.close();
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    public void writeJSON(Usuario pObject) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(this.URL)));
            this.data.usuarios.add(pObject);
            //System.out.println(json.toJson(pObject.getAspect()));
            writer.write(json.toJson(this.data));
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public ArrayList<Usuario> getUsuarios(){
        return this.data.usuarios;
    }


}