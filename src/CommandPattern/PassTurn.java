package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import Modelo.Partida;

import java.io.IOException;
import java.util.ArrayList;

public class PassTurn extends BaseCommand{

    public PassTurn() {
        this.type = CommandsE.PASSTURN;
    }


    @Override
    public void execute(String[] params,int clientId) throws IOException {
        Partida.getInstance().passTurnCommand();
    }
}
