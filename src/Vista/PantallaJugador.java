package Vista;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class PantallaJugador implements Initializable {

    private Boolean borrando = false;
    private Boolean mostrandoError = false;
    private String lineaComando = "";
    private ArrayList<Text> errores = new ArrayList<>();
    private ArrayList<Text> inicios = new ArrayList<>();


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
    private Text armaAt1;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        Text tI = new Text(">>");
        tI.setFont(new Font("Eras Demi ITC", 17));
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
                    comando.setFont(new Font("Eras Demi ITC", 17));
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
                            if (event.getCode() == KeyCode.ENTER){
                                lineaComando = newValue.replace("\n", "");
                                if (lineaComando.equals("error")) {
                                    comandosMostrar.getChildren().add(new Text("\n"));
                                    Text error = new Text("Esto es un mensaje de error");
                                    error.setFill(Color.RED);
                                    error.setFont(new Font("Eras Demi ITC", 17));
                                    errores.add(error);
                                    comandosMostrar.getChildren().add(error);
                                    lineaComando = "";
                                    comandosMostrar.getChildren().add(new Text("\n"));
                                }

                                Text inicio = new Text(">>");
                                inicio.setFont(new Font("Eras Demi ITC", 17));
                                inicio.setFill(Color.GREEN);
                                inicios.add(inicio);
                                comandosMostrar.getChildren().add(new Text("\n"));
                                comandosMostrar.getChildren().add(inicio);
                                //hacer algo
                                lineaComando = "";
                            }
                            borrando = false;
                        }
                    }
                });
            }
        });

    }
}
