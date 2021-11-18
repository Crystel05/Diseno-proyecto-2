package Vista;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

import Controller.ControladorPantalla;
import Model.Weapon;
import Modelo.Arma;
import Modelo.Data.EquipoDatos;
import Modelo.Data.GuerreroDatos;
import Modelo.Equipo;
import Modelo.Personaje;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class InicioJuego implements Initializable {

    private EquipoDatos equipoDatos = new EquipoDatos();

    //PRUEBAS
    ControladorPantalla controladorPantalla = ControladorPantalla.getInstance();

    private GuerreroDatos personajeEscogiendo ;
    private int cantPersonajes = 0;
    private int cantArmasPersonaje = 0;

    @FXML
    private ComboBox<String> armasDisponibles;

    @FXML
    private TextField nombreUsuario;

    @FXML
    private ComboBox<String> personajesDisponibles;

    @FXML
    private ImageView previewArma;

    @FXML
    private ImageView previewPersonaje;


    @FXML
    void guardarPersonaje(ActionEvent event) throws FileNotFoundException {
        String clientName = nombreUsuario.getText();
        if (clientName != ""){
            if (cantArmasPersonaje==5){
                equipoDatos.setNombreUsuario(clientName);
                equipoDatos.addDatosGuerrero(personajeEscogiendo);
                cantArmasPersonaje = 0;  //Reiniciar el contador
                //HACER EL REQUST DE MANDAR EL EQUIPO
                System.out.println("Guerreo es: " + equipoDatos.getNombre());
                System.out.println("Armas son: " + equipoDatos.getDatosGuerreros());
                personajeEscogiendo = null; //Limpiar objeto
                equipoDatos = new EquipoDatos(); //Limpiar el objeto
                cantPersonajes++;
            }
            else{
                System.out.println("Se deben agregar 5 armas a cada personaje");
            }
        }
        else{
            System.out.println("Nombre de cliente requerido para conectarse");
        }
    }


    /**
     * Botón del más para agregar un arma al personaje creándose actualmente
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    void agregarArma(ActionEvent event) throws FileNotFoundException {
        if (personajeEscogiendo != null){
            if (cantArmasPersonaje < 5){
                personajeEscogiendo.addArma(armasDisponibles.getSelectionModel().getSelectedItem());
                cantArmasPersonaje = cantArmasPersonaje+1;

                System.out.println("Arma escogida: " + armasDisponibles.getSelectionModel().getSelectedItem());
            }
            else{
                System.out.println("No se pueden agregar más armas");
            }
        }
        else{
            System.out.println("Escoja un personaje");
        }
        //Aca se podria asignar las imagenes
        //recocorrer las armas que existen y
        Arma arma = controladorPantalla.getArmas().get(armasDisponibles.getSelectionModel().getSelectedIndex());
        String pathFoto = arma.getAspect().get(1).get(0); //CREO preguntar después
        InputStream stream = new FileInputStream(pathFoto);
        Image image = new Image(stream);
        previewArma.setImage(image);

    }

    /**
     * Botón de escoger personaje, agarra al personaje seleccionado, lo crea y espera a que se le agreguen armas
     * @param event
     */
    @FXML
    void escogerPersonaje(ActionEvent event) throws FileNotFoundException {

        personajeEscogiendo = new GuerreroDatos(personajesDisponibles.getSelectionModel().getSelectedItem());
        System.out.println("Personaje escogido: "+ personajeEscogiendo.getName());
        //Aca se podria asignar las imagenes
        String pathFoto = controladorPantalla.getPersonajes().get(personajesDisponibles.getSelectionModel().getSelectedIndex()).getAspect().get(1).get(0); //CREO preguntar después
        InputStream stream = new FileInputStream(pathFoto);
        Image image = new Image(stream);
        previewPersonaje.setImage(image);
    }

    @FXML
    void cambioPersonaje(MouseEvent event) throws FileNotFoundException {
        ControladorPantalla.getInstance().setInitialData(); //TODO:ESTO ES TEMPORAL QUITAR
    }


    @FXML
    void armaEscogida(MouseEvent event) throws FileNotFoundException {
    }

    @FXML
    void jugar(ActionEvent event) throws IOException, ClassNotFoundException {
        testFor();

        if (cantPersonajes == 4) {
            equipoDatos.setNombreUsuario(nombreUsuario.getText());//Se puede validar luego
            controladorPantalla.enviarEquipoElegido(equipoDatos);
            Node source = (Node) event.getSource();
            Stage stageActual = (Stage) source.getScene().getWindow();
            stageActual.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLS/pantallaJugador.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("CSS/textarea.css")).toExternalForm());
            stage.setTitle("JUEGO PELEAS 1:1");
            stage.getIcons().add(new Image("Vista/Imágenes/ícono.png"));
            stage.setResizable(false);
            stage.show();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            controladorPantalla.setInicioJuego(this);//Esto asocia esta pantalla con el controlador
            controladorPantalla.connectionRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void testFor(){
        for(int i = 0;i<4;i++){
            personajeEscogiendo = new GuerreroDatos(controladorPantalla.getPersonajes().get(i).getName());
            for (int j = 0;j <5;j++){
                personajeEscogiendo.addArma(controladorPantalla.getArmas().get(j).toString());
            }
            equipoDatos.addDatosGuerrero(personajeEscogiendo);
            cantPersonajes++;
        }
    }


    public void cargarDatosIniciales(){
        ArrayList<String> personajes = new ArrayList<>();  //Agarrarlos de
        ArrayList<String> armas = new ArrayList<>();

        for (Personaje personaje :ControladorPantalla.getInstance().getPersonajes()
        ) {
            personajes.add(personaje.getName());
        }
        for (Arma arma :ControladorPantalla.getInstance().getArmas()
        ) {
            armas.add(arma.getName());
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                personajesDisponibles.setItems(FXCollections.observableArrayList(personajes)); //agregar aquí los personajes disponibles
                armasDisponibles.setItems(FXCollections.observableArrayList(armas)); //agregar aquí las armas disponibles
            }
        });
    }
}
