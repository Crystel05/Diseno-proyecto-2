package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import Modelo.Partida;

import java.util.ArrayList;

public class ErrorCommand extends BaseCommand{


    public ErrorCommand() {
        this.type = CommandsE.ERROR;
    }

    @Override
    public void execute(String[] params) {
        Partida.getInstance().errorCommand((String)params[0]);
    }
}
