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
import Modelo.Arma;
import Modelo.Data.EquipoDatos;
import Modelo.Data.GuerreroDatos;
import Modelo.Personaje;
import javafx.animation.PauseTransition;
import javafx.animation.Transition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
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
    private Text nombrePersonaje;

    @FXML
    private Text nombreArma;

    @FXML
    void guardarPersonaje(ActionEvent event) throws IOException {
        String clientName = nombreUsuario.getText();
        if (!clientName.equals("")){
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
                abrirNot("Cantidad de personajes incorrecta", "Se deben agregar 5 armas\n a cada personaje\nantes de guardarlo");
            }
        }
        else{
            abrirNot("Error de inicio de sesión", "Debe ingresar un nombre para\n inicar a jugar");
        }
    }


    /**
     * Botón del más para agregar un arma al personaje creándose actualmente
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    void agregarArma(ActionEvent event) throws IOException {
        if (personajeEscogiendo != null){
            if (cantArmasPersonaje < 5){
                personajeEscogiendo.addArma(armasDisponibles.getSelectionModel().getSelectedItem());
                cantArmasPersonaje = cantArmasPersonaje+1;
            }
            else{
                abrirNot("Error al agregar arma", "No se pueden agregar más armas\nya hay 5");
            }
        }
        else{
            abrirNot("Error al agregar arma", "Primero escoja un personaje para\nescoger las armas");
        }

    }

    /**
     * Botón de escoger personaje, agarra al personaje seleccionado, lo crea y espera a que se le agreguen armas
     * @param event
     */
    @FXML
    void escogerPersonaje(ActionEvent event) throws IOException {
        if (cantPersonajes < 4) {
            personajeEscogiendo = new GuerreroDatos(personajesDisponibles.getSelectionModel().getSelectedItem());
            cantPersonajes++;
        }else{
            abrirNot("Error al agregar personaje", "Ya se agregaron todos los personajes");
        }
    }

    @FXML
    void cambioPersonaje(MouseEvent event) throws FileNotFoundException {
        ControladorPantalla.getInstance().setInitialData(); //TODO:ESTO ES TEMPORAL QUITAR
    }

    @FXML
    void jugar(ActionEvent event) throws IOException, ClassNotFoundException {
        testFor();
        if (!nombreUsuario.getText().equals("")) {
            if (cantPersonajes == 4) {
                equipoDatos.setNombreUsuario(nombreUsuario.getText());//Se puede validar luego
                controladorPantalla.enviarEquipoElegido(equipoDatos);
                Node source = (Node) event.getSource();
                Stage stageActual = (Stage) source.getScene().getWindow();
                stageActual.close();

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLS/cargando.fxml"));
                Parent root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("CSS/textarea.css")).toExternalForm());
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.getIcons().add(new Image("Vista/Imágenes/ícono.png"));
                stage.setResizable(false);
                stage.show();

                PauseTransition delay = new PauseTransition(Duration.seconds(5));
                delay.setOnFinished(e -> {
                    try {
                        abrirVentanaJuego(stage);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                });
                delay.play();
            } else {
                abrirNot("Error al agregar personaje", "Faltan " + (4 - cantPersonajes) + " para poder jugar");
            }
        }else{
            abrirNot("Error al iniciar al juego", "No se puede iniciar el juego\nhasta que escriba un nombre de usuario");
        }

    }

    private void abrirVentanaJuego(Stage stageCargar) throws IOException {
        stageCargar.close();
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
                try {
                    personajesDisponibles.setItems(FXCollections.observableArrayList(personajes)); //agregar aquí los personajes disponibles
                    armasDisponibles.setItems(FXCollections.observableArrayList(armas)); //agregar aquí las armas disponibles
                }catch (ArrayIndexOutOfBoundsException ignored){}
            }
        });
    }

    public void abrirNot(String titulo, String cont) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLS/Notificaciones.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("CSS/textarea.css")).toExternalForm());
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image("Vista/Imágenes/ícono.png"));
        stage.setResizable(false);
        stage.show();

        Text tit = (Text) scene.lookup("#titulo");
        tit.setText(titulo);
        Text contenido = (Text) scene.lookup("#contenidoNot");
        contenido.setText(cont);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            controladorPantalla.setInicioJuego(this);//Esto asocia esta pantalla con el controlador
            controladorPantalla.connectionRequest();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        personajesDisponibles.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                nombrePersonaje.setText(personajesDisponibles.getSelectionModel().getSelectedItem());
                try {
                    String pathFoto = controladorPantalla.getPersonajes().get(personajesDisponibles.getSelectionModel().getSelectedIndex()).getAspect().get(1).get(0); //CREO preguntar después
                    InputStream stream = null;
                    stream = new FileInputStream(pathFoto);
                    Image image = new Image(stream);
                    previewPersonaje.setImage(image);
                } catch (FileNotFoundException | ArrayIndexOutOfBoundsException ignored) {}
            }
        });
        armasDisponibles.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    Arma arma = controladorPantalla.getArmas().get(armasDisponibles.getSelectionModel().getSelectedIndex());
                    String pathFoto = arma.getAspect().get(1).get(0);
                    InputStream stream = null;
                    stream = new FileInputStream(pathFoto);
                    Image image = new Image(stream);
                    previewArma.setImage(image);
                    nombreArma.setText(arma.getName());
                } catch (FileNotFoundException | ArrayIndexOutOfBoundsException ignored) {}
            }
        });

    }
}
