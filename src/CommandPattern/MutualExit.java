package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import Modelo.Partida;

import java.io.IOException;
import java.util.ArrayList;

public class MutualExit extends BaseCommand{


    public MutualExit() {
        this.type = CommandsE.MUTUALEXIT;
    }


    @Override
    public void execute(String[] params,int clientId) throws IOException {
        Partida.getInstance().mutualExitCommand();
    }
}
