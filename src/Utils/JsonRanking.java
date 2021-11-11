package Utils;


import Model.Character;
import Model.Weapon;
import Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        this.URL = "src\\resources\\ranking.json";
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

    public void writeJSON(Usuario pObject) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter(new File(this.URL)));
            this.data.usuarios.add(pObject);
            //System.out.println(json.toJson(pObject.getAspect()));
            writer.write(json.toJson(this.data));
            writer.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }


    public ArrayList<Usuario> getCharacters(){
        return this.data.usuarios;
    }


}