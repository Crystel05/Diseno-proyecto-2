package CommandPattern;

import Modelo.Partida;

import java.io.IOException;
import java.util.ArrayList;

public class SelectPlayer extends BaseCommand {

    public SelectPlayer() {
    }

    @Override
    public void execute(String[] params,int clientId) throws IOException {
        Partida.getInstance().selectPlayerCommand();
    }
}
