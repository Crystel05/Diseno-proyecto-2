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

    private Comunicador comunicador = Comunicador.getInstance();
    private EquipoDatos equipoDatos = new EquipoDatos();

    //PRUEBAS
    ControladorPantalla controladorPantalla = ControladorPantalla.getInstance();

    private GuerreroDatos personajeEscogiendo;
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
    void agregarArma(ActionEvent event) throws FileNotFoundException {
        if (cantArmasPersonaje < 5)
            personajeEscogiendo.addArma(armasDisponibles.getSelectionModel().getSelectedItem());
        cantArmasPersonaje++;
        //Aca se podria asignar las imagenes
    }

    @FXML
    void escogerPersonaje(ActionEvent event) {//Crearlo ya con sus armas asignadas
        if (cantArmasPersonaje==5){
            equipoDatos.addDatosGuerrero(personajeEscogiendo);
            cantPersonajes++;
            cantArmasPersonaje = 0;
        }
        //Aca se podria asignar las imagenes
    }

    @FXML
    void cambioPersonaje(MouseEvent event) throws FileNotFoundException { //Escogerlo del combobox actual
        //ControladorPantalla.getInstance().setInitialData(); //Donde poner
        personajeEscogiendo = new GuerreroDatos(personajesDisponibles.getSelectionModel().getSelectedItem());
//        String pathFoto = personajeEscogiendo.getAspect().get(1).get(1); //CREO preguntar después
//        InputStream stream = new FileInputStream(pathFoto);
//        Image image = new Image(stream);
//        previewPersonaje.setImage(image);
    }

    @FXML
    void armaEscogida(MouseEvent event) throws FileNotFoundException {//Cuando se toca el comboBox
        Weapon arma = new Weapon(); //escoger una del combobox
        //recocorrer las armas que existen y
        //  Weapon arma = armasDisponibles.getSelectionModel().getSelectedItem();
//        String pathFoto = arma.getAspect().get(1).get(1); //CREO preguntar después
//        InputStream stream = new FileInputStream(pathFoto);
//        Image image = new Image(stream);
//        previewArma.setImage(image);
    }

    @FXML
    void jugar(ActionEvent event) throws IOException, ClassNotFoundException {
//        if (cantArmasPersonaje == 4) {
//            Node source = (Node) event.getSource();
//            Stage stageActual = (Stage) source.getScene().getWindow();
//            stageActual.close();
//
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLS/pantallaJugador.fxml"));
//            Parent root = fxmlLoader.load();
//            Scene scene = new Scene(root);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("CSS/textarea.css")).toExternalForm());
//            stage.setTitle("JUEGO PELEAS 1:1");
//            stage.getIcons().add(new Image("Vista/Imágenes/ícono.png"));
//            stage.setResizable(false);
//            stage.show();
//        }
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
