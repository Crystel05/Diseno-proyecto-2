package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import Modelo.Partida;

import java.io.IOException;
import java.util.ArrayList;

public class GiveUp extends BaseCommand{



    public GiveUp() {
        this.type = CommandsE.GIVEUP;
    }


    @Override
    public void execute(String[] params,int clientId) throws IOException {
        Partida.getInstance().giveUpCommand(params);
    }
}
