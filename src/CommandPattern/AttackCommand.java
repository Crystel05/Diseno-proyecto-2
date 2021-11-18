package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import Modelo.Partida;

import java.io.IOException;

public class AttackCommand extends BaseCommand {

    public AttackCommand(){
        this.type = CommandsE.ATACK;
    }

    @Override
    public void execute(String[] params,int clientId) throws IOException {
        Partida.getInstance().attackCommand(params[0],params[1],clientId);
    }
}
