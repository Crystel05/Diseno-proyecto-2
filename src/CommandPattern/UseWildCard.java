package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import Modelo.Arma;
import Modelo.Partida;

import java.io.IOException;
import java.util.ArrayList;

public class UseWildCard extends BaseCommand{

    public UseWildCard() {
        this.type = CommandsE.USEWILDCARD;
    }

    @Override
    public void execute(String[] params,int clientId) throws IOException {
        if(params.length == 3){ // doble arma
            Partida.getInstance().doubleWeapon(params[0],params[1],params[2],clientId);
        }else {
            Partida.getInstance().doubleAttack(params[0],params[1],params[2],params[3],clientId);
        }
    }
}
