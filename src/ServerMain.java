import Modelo.Partida;
import Network.Server.Server;
import ProjectNetwork.CommandRequestHandler;

import java.io.IOException;

public class ServerMain {

    public static void main(String[] args) {
        try {
            Partida server = Partida.createInstance(6000, new CommandRequestHandler());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("WHAT");
            e.printStackTrace();
        }
    }
}
