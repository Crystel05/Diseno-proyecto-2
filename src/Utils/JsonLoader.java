package Utils;
import Modelo.Arma;
import Modelo.Personaje;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Natalia
 */
public class JsonLoader {
    private static JsonLoader instance;
    private String URL;
    private final Gson json;
    private ObjFormat data;

    private JsonLoader() throws IOException{
        this.URL = "src\\Utils\\test.json";
        //Feo porque la ruta está alambrada
        this.json = new Gson();
        data = new ObjFormat();
        this.readJSON();
    }

    public static JsonLoader getInstance() throws IOException{
        if(JsonLoader.instance != null){
            return JsonLoader.instance;
        }
        JsonLoader.instance = new JsonLoader();
        return instance;
    }

    private class ObjFormat{
        public ArrayList<Personaje> characters = new ArrayList<>();
        public ArrayList<Arma> weapons = new ArrayList<>();
    }

    private void readJSON() throws FileNotFoundException, IOException {
        try {
            if(new File(this.URL).exists()){
                InputStream is = new FileInputStream(this.URL);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
                ObjFormat data = json.fromJson(bufferedReader, ObjFormat.class);
                System.out.println(data);
                System.out.println(data !=  null);
                if (data !=  null){
                    this.data = data;
                }
                bufferedReader.close();
            }
        } catch (JsonIOException | JsonSyntaxException | IOException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Personaje> getCharacters(){
        return this.data.characters;
    }

    public ArrayList<Arma> getWeapons(){
        return this.data.weapons;
    }



}