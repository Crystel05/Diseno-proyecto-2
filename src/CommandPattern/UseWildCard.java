package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import Modelo.Arma;

import java.util.ArrayList;

public class UseWildCard extends BaseCommand{

    public UseWildCard() {
        this.type = CommandsE.USEWILDCARD;
    }

    @Override
    public void execute(String[] params) {
//        if(params.size() == 3){ // doble arma
//            Guerrero guerrero = (Guerrero) params.get(0);
//            Arma arma1 = (Arma) params.get(1);
//            Arma arma2 = (Arma) params.get(2);
//            //EjecutorComandos.doubleArmaAttack();
//        }else {
//            Guerrero guerrero1 = (Guerrero) params.get(0);
//            Arma arma1 = (Arma) params.get(1);
//            Guerrero guerrero2 = (Guerrero) params.get(2);
//            Arma arma2 = (Arma) params.get(3);
//        }
    }
}
