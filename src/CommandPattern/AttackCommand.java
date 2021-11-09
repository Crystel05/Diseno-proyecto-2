package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import Modelo.Arma;
import Modelo.Guerrero;
import Modelo.Usuario;

import java.io.IOException;
import java.util.ArrayList;

public class AttackCommand extends BaseCommand {

    AttackCommand(){
        this.type = CommandsE.ATACK;
    }


    @Override
    public void execute(String[] params) { // PENDIENTE
//        if(args.length<=0) return;
//        Game.getInstance().attack(args[1], args[2]);
    }
}
