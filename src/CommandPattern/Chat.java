package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import Modelo.Partida;

import java.io.IOException;
import java.util.ArrayList;

public class Chat extends BaseCommand{

    public Chat() {
        this.type = CommandsE.CHAT;
    }

    @Override
    public void execute(String[] params) throws IOException {
        Partida.getInstance().chatCommand(params[0]);
    }
}
