import CommandPattern.CommandManager;
import Modelo.EnumTipoPersonaje;
import Modelo.Partida;
import Modelo.Personaje;
import Network.Server.Server;
import ProjectNetwork.CommandRequestHandler;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) {
        try {
            Partida.createInstance(6000, new CommandRequestHandler());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("WHAT");
            e.printStackTrace();
        }
    }
}
