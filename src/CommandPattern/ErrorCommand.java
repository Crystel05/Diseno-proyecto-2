package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import Modelo.Partida;

import java.io.IOException;
import java.util.ArrayList;

public class ErrorCommand extends BaseCommand{


    public ErrorCommand() {
        this.type = CommandsE.ERROR;
    }

    @Override
    public void execute(String[] params) throws IOException {
        Partida.getInstance().errorCommand((String)params[0]);
    }
}
