package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import Modelo.Partida;

import java.io.IOException;
import java.util.ArrayList;

public class RechargeWeapon extends BaseCommand{


    public RechargeWeapon() {
        this.type = CommandsE.RECHARGEWEAPON;
    }


    @Override
    public void execute(String[] params,int clientId) throws IOException {
        Partida.getInstance().rechargeCommand(params[0]);
    }
}
