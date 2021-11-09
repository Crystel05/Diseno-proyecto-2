package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import Modelo.Arma;
import Modelo.Guerrero;
import Modelo.Partida;
import Modelo.Usuario;

import java.io.IOException;
import java.util.ArrayList;

public class AttackCommand extends BaseCommand {

    public AttackCommand(){
        this.type = CommandsE.ATACK;
    }

    @Override
    public void execute(String[] params) throws IOException {
        Partida.getInstance().attackCommand(params[0],params[1]);
    }
}
