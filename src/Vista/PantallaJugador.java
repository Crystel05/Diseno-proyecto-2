package Vista;

import CommandPattern.Enumerable.CommandsE;
import Controller.ControladorPantalla;
import Model.Weapon;
import Modelo.Personaje;
import Modelo.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class PantallaJugador implements Initializable {

    private ControladorPantalla controladorPantalla;
    private Comunicador comunicador = Comunicador.getInstance();
    private Boolean borrando = false;
    private Boolean mostrandoError = false;
    private String lineaComando = "";
    private ArrayList<Text> errores = new ArrayList<>();
    private ArrayList<Text> inicios = new ArrayList<>();
    private ArrayList<Text> rankingNames= new ArrayList();
    private ArrayList<Text> comandosHechos= new ArrayList();
    private Text ultimo;
    private int cantComandos;
    private int nuevo;
    private ArrayList<ImageView> guerrerosFotos = new ArrayList<>();
    private ArrayList<Text> armasPersonaje = new ArrayList<>();
    private ArrayList<Text> arma1 = new ArrayList<>();
    private ArrayList<Text> arma2 = new ArrayList<>();
    private ArrayList<Text> arma3 = new ArrayList<>();
    private ArrayList<Text> arma4 = new ArrayList<>();
    private ArrayList<Text> arma5 = new ArrayList<>();
    private ArrayList<ArrayList<Text>> porcentajes = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> porcentajesFinales = new ArrayList<>();

    @FXML
    private Text arma1At1;

    @FXML
    private Text arma1At10;

    @FXML
    private Text arma1At2;

    @FXML
    private Text arma1At3;

    @FXML
    private Text arma1At4;

    @FXML
    private Text arma1At5;

    @FXML
    private Text arma1At6;

    @FXML
    private Text arma1At7;

    @FXML
    private Text arma1At8;

    @FXML
    private Text arma1At9;

    @FXML
    private Text arma1Nombre;

    @FXML
    private Text arma2At1;

    @FXML
    private Text arma2At10;

    @FXML
    private Text arma2At2;

    @FXML
    private Text arma2At3;

    @FXML
    private Text arma2At4;

    @FXML
    private Text arma2At5;

    @FXML
    private Text arma2At6;

    @FXML
    private Text arma2At7;

    @FXML
    private Text arma2At8;

    @FXML
    private Text arma2At9;

    @FXML
    private Text arma2Nombre;

    @FXML
    private Text arma3At10;

    @FXML
    private Text arma3At2;

    @FXML
    private Text arma3At3;

    @FXML
    private Text arma3At4;

    @FXML
    private Text arma3At5;

    @FXML
    private Text arma3At6;

    @FXML
    private Text arma3At7;

    @FXML
    private Text arma3At8;

    @FXML
    private Text arma3At9;

    @FXML
    private Text arma3Nombre;

    @FXML
    private Text arma4At1;

    @FXML
    private Text arma4At10;

    @FXML
    private Text arma4At2;

    @FXML
    private Text arma4At3;

    @FXML
    private Text arma4At4;

    @FXML
    private Text arma4At5;

    @FXML
    private Text arma4At6;

    @FXML
    private Text arma4At7;

    @FXML
    private Text arma4At8;

    @FXML
    private Text arma4At9;

    @FXML
    private Text arma4Nombre;

    @FXML
    private Text arma5At1;

    @FXML
    private Text arma5At10;

    @FXML
    private Text arma5At2;

    @FXML
    private Text arma5At3;

    @FXML
    private Text arma5At4;

    @FXML
    private Text arma5At5;

    @FXML
    private Text arma5At6;

    @FXML
    private Text arma5At7;

    @FXML
    private Text arma5At8;

    @FXML
    private Text arma5At9;

    @FXML
    private Text arma5Nombre;

    @FXML
    private Text arma3At1;

    @FXML
    private Text armaAtaca;

    @FXML
    private Text armaAtacante;

    @FXML
    private Text ataquesCont;

    @FXML
    private Text ataquesMio;

    @FXML
    private TextArea comandos;

    @FXML
    private TextFlow comandosMostrar;

    @FXML
    private Text danoG1;

    @FXML
    private Text danoG2;

    @FXML
    private Text danoG3;

    @FXML
    private Text danoG4;

    @FXML
    private Text falladasContr;

    @FXML
    private Text falladasMio;

    @FXML
    private Text ganadasContricante;

    @FXML
    private Text ganadasMio;

    @FXML
    private ImageView guerrero1;

    @FXML
    private ImageView guerrero2;

    @FXML
    private ImageView guerrero3;

    @FXML
    private ImageView guerrero4;

    @FXML
    private ImageView guerreroAtacó;

    @FXML
    private Text guerreroQueAtaca;

    @FXML
    private ImageView guerroQueAtaca;

    @FXML
    private Text nomG1;

    @FXML
    private Text nomG2;

    @FXML
    private Text nomG3;

    @FXML
    private Text nomG4;

    @FXML
    private Text nomR1;

    @FXML
    private Text nomR10;

    @FXML
    private Text nomR2;

    @FXML
    private Text nomR3;

    @FXML
    private Text nomR4;

    @FXML
    private Text nomR5;

    @FXML
    private Text nomR6;

    @FXML
    private Text nomR7;

    @FXML
    private Text nomR8;

    @FXML
    private Text nomR9;

    @FXML
    private Text nombreGuerreroAtacante;

    @FXML
    private Text nombreGuerreroUsando;

    @FXML
    private Text perdidasContrincante;

    @FXML
    private Text perdidasMio;

    @FXML
    private Text porcentajeDanoInfringido;

    @FXML
    private Text porcentajeVidaUsando;

    @FXML
    private Text puestoContra;

    @FXML
    private Text puestoMio;

    @FXML
    private Text rendicionesContr;

    @FXML
    private Text rendicionesMio;

    @FXML
    private Text tipoGuerreroAtaca;

    @FXML
    private Text nombreContrincante;

    @FXML
    public void seleccionadoGuerrero1(MouseEvent event){
//        Personaje guerrero1 = comunicador.getGuerrerosEscogidos().get(0);
//        nombreGuerreroUsando.setText(guerrero1.getName());
//        porcentajeVidaUsando.setText(String.valueOf(guerrero1.getLife())); //no estoy segura de que sea así
//        ArrayList<Weapon> armas = guerrero1.getArmas();
//        ponerNombreArma(armas);
        escribirPorcentajes(0);
    }

    @FXML
    public void seleccionadoGuerrero2(MouseEvent event){
//        Personaje guerrero2 = comunicador.getGuerrerosEscogidos().get(1);
//        nombreGuerreroUsando.setText(guerrero2.getName());
//        porcentajeVidaUsando.setText(String.valueOf(guerrero2.getLife())); //no estoy segura de que sea así
//        ArrayList<Weapon> armas = guerrero2.getArmas();
//        ponerNombreArma(armas);
        escribirPorcentajes(1);
    }

    @FXML
    public void seleccionadoGuerrero3(MouseEvent event){
//        Personaje guerrero3 = comunicador.getGuerrerosEscogidos().get(2);
//        nombreGuerreroUsando.setText(guerrero3.getName());
//        porcentajeVidaUsando.setText(String.valueOf(guerrero3.getLife())); //no estoy segura de que sea así
//        ArrayList<Weapon> armas = guerrero3.getArmas();
//        ponerNombreArma(armas);
        escribirPorcentajes(2);
    }

    @FXML
    public void seleccionadoGuerrero4(MouseEvent event){
//        Personaje guerrero4 = comunicador.getGuerrerosEscogidos().get(3);
//        nombreGuerreroUsando.setText(guerrero4.getName());
//        porcentajeVidaUsando.setText(String.valueOf(guerrero4.getLife())); //no estoy segura de que sea así
//        ArrayList<Weapon> armas = guerrero4.getArmas();
//        ponerNombreArma(armas);
        escribirPorcentajes(3);
    }

    private void ponerNombreArma(ArrayList<Weapon> armas){
        for (int i = 0; i<4; i++){
            armasPersonaje.get(i).setText(armas.get(i).getName());
        }
    }

    private void colocarPorcentajes(){
        for (int i = 0; i<5; i++){
            ArrayList<Integer> porcentajesArma = new ArrayList<>();
            for (int j = 0; j < 10; j++){
                String porcentaje = porcentaje();
                porcentajesArma.add(Integer.valueOf(porcentaje));
            }
            porcentajesFinales.add(porcentajesArma);
        }
    }

    private void escribirPorcentajes(int armaIndex){
        ArrayList<Integer> arma = porcentajesFinales.get(armaIndex);
        for (int j = 0; j<arma.size()-1; j++){
            porcentajes.get(armaIndex).get(j).setText(String.valueOf(arma.get(j)));
        }
    }

    private String porcentaje(){
        int porcentaje =(int) (Math.random()*100+1);
        return String.valueOf(porcentaje);
    }

    private void llenarListas(){
        rankingNames.add(nomR1);rankingNames.add(nomR2);rankingNames.add(nomR3);rankingNames.add(nomR4);rankingNames.add(nomR5);
        rankingNames.add(nomR6);rankingNames.add(nomR7);rankingNames.add(nomR8);rankingNames.add(nomR9);rankingNames.add(nomR10);

        guerrerosFotos.add(guerrero1); guerrerosFotos.add(guerrero2); guerrerosFotos.add(guerrero3); guerrerosFotos.add(guerrero4);

        arma1.add(arma1At1);arma1.add(arma1At2);arma1.add(arma1At3);arma1.add(arma1At4);arma1.add(arma1At5);
        arma2.add(arma2At1);arma2.add(arma2At2);arma2.add(arma2At3);arma2.add(arma2At4);arma2.add(arma2At5);
        arma3.add(arma3At1);arma3.add(arma3At2);arma3.add(arma3At3);arma3.add(arma3At4);arma3.add(arma3At5);
        arma4.add(arma4At1);arma4.add(arma4At2);arma4.add(arma4At3);arma4.add(arma4At4);arma4.add(arma4At5);
        arma5.add(arma5At1);arma5.add(arma5At2);arma5.add(arma5At3);arma5.add(arma5At4);arma5.add(arma5At5);

        porcentajes.add(arma1); porcentajes.add(arma2); porcentajes.add(arma3); porcentajes.add(arma4); porcentajes.add(arma5);

    }

    private void cargarDatosRanking(){
        ArrayList<String> nombres = new ArrayList<>(); // agregar aquí el archivo o lista real
        nombres.add("Manchas");
        nombres.add("Manchas");
        nombres.add("Manchas");
        nombres.add("Manchas");
        nombres.add("Manchas");
        nombres.add("Manchas");
        nombres.add("Manchas");
        nombres.add("Manchas");
        for (int i = 0; i < nombres.size()-1; i++){
            rankingNames.get(i).setText(nombres.get(i));
        }
    }

    private void cargarDatosCompetidores(){
        Usuario actual = controladorPantalla.getUsuario(); //cambiar esto por el usuario real
        System.out.println(actual);
        puestoMio.setText("#"+actual.getRanking());
        ganadasMio.setText(String.valueOf(actual.getPartidasGanadas()));
        perdidasMio.setText(String.valueOf(actual.getAtaquesFallados()));
        falladasMio.setText(String.valueOf(actual.getAtaquesFallados()));
        ataquesMio.setText(String.valueOf(actual.getAtaquesExitosos()));
        rendicionesMio.setText(String.valueOf(actual.getRendiciones()));

        Usuario contrincante = controladorPantalla.getUsuarioEnemigo(); //cambiar esto por el usuario real
        System.out.println(contrincante);
        puestoContra.setText("#"+contrincante.getRanking());
        nombreContrincante.setText("("+contrincante.getNombre()+")");
        ganadasContricante.setText(String.valueOf(contrincante.getPartidasGanadas()));
        perdidasContrincante.setText(String.valueOf(contrincante.getPartidasPerdidas()));
        ataquesCont.setText(String.valueOf(contrincante.getAtaquesExitosos()));
        falladasContr.setText(String.valueOf(contrincante.getAtaquesFallados()));
        rendicionesContr.setText(String.valueOf(contrincante.getRendiciones()));
    }

    private void cargarDatosAtaque(){
        
    }

    private void cargarDatosAtacado(){

    }

    private void cargarDatosEquipo() throws FileNotFoundException {
        for (int i = 0; i < comunicador.getGuerrerosEscogidos().size()-1; i++){
            String pathFoto = comunicador.getGuerrerosEscogidos().get(i).getAspect().get(1).get(1);
            FileInputStream stream = new FileInputStream(pathFoto);
            Image image = new Image(stream);
            guerrerosFotos.get(i).setImage(image);
        }

    }

    @FXML
    public void agregarDatos(MouseEvent event){
        cargarDatosCompetidores();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controladorPantalla = ControladorPantalla.getInstance();
        controladorPantalla.setPantallaJugador(this);
        llenarListas();
        cargarDatosRanking();
        colocarPorcentajes();
        comandos.skinProperty().addListener(new ChangeListener<Skin<?>>() {
            @Override
            public void changed(ObservableValue<? extends Skin<?>> observable, Skin<?> oldValue, Skin<?> newValue) {
                if (newValue != null && newValue.getNode() instanceof Region) {
                    Region r = (Region) newValue.getNode();
                    r.setBackground(Background.EMPTY);

                    r.getChildrenUnmodifiable().stream().
                            filter(n -> n instanceof Region).
                            map(n -> (Region) n).
                            forEach(n -> n.setBackground(Background.EMPTY));

                    r.getChildrenUnmodifiable().stream().
                            filter(n -> n instanceof Control).
                            map(n -> (Control) n).
                            forEach(c -> c.skinProperty().addListener(this));
                }
            }
        });
        comandosMostrar.getChildren().add(new Text("\n"));
        Text tI = new Text(">>");
        ultimo = tI;
        tI.setFont(new Font("Eras Demi ITC", 15));
        tI.setFill(Color.GREEN);
        inicios.add(tI);
        comandosMostrar.getChildren().add(tI);
        comandos.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!borrando) {
                    Text comando = new Text(newValue.replace(oldValue, ""));
                    comando.setText(comando.getText().replace("\n", ""));
                    comando.setFill(Color.GREEN);
                    comando.setFont(new Font("Eras Demi ITC", 15));
                    comandosMostrar.getChildren().add(comando);
                }
                comandos.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (event.getCode() == KeyCode.BACK_SPACE){
                            borrando = true;
                            if (comandosMostrar.getChildren().size()>0) {
                                try {
                                    Node elemento = comandosMostrar.getChildren().get(comandosMostrar.getChildren().size()-1);
                                    for (Text i: inicios) {
                                        if (!elemento.equals(i)) {
                                            comandosMostrar.getChildren().remove(elemento);
                                        } else {
                                            comandosMostrar.getChildren().add(i);
                                        }
                                    }
                                    for (Text t: errores){
                                        if (!elemento.equals(t)){
                                            comandosMostrar.getChildren().remove(elemento);
                                        }else{
                                            comandosMostrar.getChildren().add(t);
                                        }
                                    }
                                }catch (IllegalArgumentException ignore){}
                            }
                        }else{
                            borrando = false;
                            if (event.getCode() == KeyCode.ENTER) {
                                cantComandos ++;
                                String[] comandos = newValue.split("\n");
                                lineaComando = comandos[comandos.length-1]; //este es el comando
                                Text t = new Text(lineaComando);
                                t.setFill(Color.GREEN);
                                t.setFont(new Font("Eras Demi ITC", 15));
                                comandosHechos.add(t);
                                ObservableList<Node> textosA = comandosMostrar.getChildren();
                                for (int i = textosA.size()-1; i>=0; i--){
                                    if (textosA.get(i).equals(ultimo)) {
                                        comandosMostrar.getChildren().remove(textosA.get(i));
                                        break;
                                    }
                                    comandosMostrar.getChildren().remove(textosA.get(i));
                                }
                                Text in = new Text(">>");
                                in.setFont(new Font("Eras Demi ITC", 15));
                                in.setFill(Color.GREEN);
                                inicios.add(in);
                                comandosMostrar.getChildren().add(in);
                                comandosMostrar.getChildren().add(t);


                                if (!buscarComando(lineaComando)) { //una lista de errores o mensajes
                                    comandosMostrar.getChildren().add(new Text("\n"));
                                    Text error = new Text("Esto es un mensaje de error"); //cambiar este error por los errores que aparezcan
                                    error.setFill(Color.RED);
                                    comandosHechos.add(error);
                                    error.setFont(new Font("Eras Demi ITC", 15));
                                    errores.add(error);
                                    comandosMostrar.getChildren().add(error);
                                    comandosMostrar.getChildren().add(new Text("\n"));
                                    Text ini = new Text(">>");
                                    ultimo = ini;
                                    ini.setFont(new Font("Eras Demi ITC", 15));
                                    ini.setFill(Color.GREEN);
                                    inicios.add(ini);
                                    comandosMostrar.getChildren().add(ini);
                                }else{
                                    try {
                                        requestCommand();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (ClassNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    if (cantComandos >= 9){
                                        ObservableList<Node> textos = comandosMostrar.getChildren();
                                        for (int i = textos.size()-1; i>=0; i--){
                                            comandosMostrar.getChildren().remove(textos.get(i));
                                        }
                                        for (int i = nuevo; i<comandosHechos.size(); i++){
                                            Text inicio = new Text(">>");
                                            inicio.setFill(Color.GREEN);
                                            inicio.setFont(new Font("Eras Demi ITC", 15));
                                            comandosMostrar.getChildren().add(new Text("\n"));
                                            comandosMostrar.getChildren().add(inicio);
                                            comandosMostrar.getChildren().add(comandosHechos.get(i));
                                        }
                                        nuevo++;
                                    }
                                    Text inicio = new Text(">>");
                                    ultimo = inicio;
                                    inicio.setFont(new Font("Eras Demi ITC", 15));
                                    inicio.setFill(Color.GREEN);
                                    inicios.add(inicio);
                                    comandosMostrar.getChildren().add(new Text("\n"));
                                    comandosMostrar.getChildren().add(inicio);
                                }
                            }
                        }
                    }


                });
            }
        });
    }

    private boolean buscarComando(String lineaComando) {
        String comando = lineaComando.split(" ")[0].toUpperCase();
        for (CommandsE commandsE:
        CommandsE.values()) {
            if(commandsE.toString().equals(comando))
                return true;
        }
        return false;
    }

    public void requestCommand() throws IOException, ClassNotFoundException {
        String[] params = lineaComando.split(" ");
        controladorPantalla.requestCommand(params[0], Arrays.copyOfRange(params,1,params.length));
    }

    public static void main(String[] args){
        for (int i = 0; i<100; i++) {
            int num = (int) (Math.random() * 100 + 1);
            System.out.println(num);
        }
    }
}
