package CommandPattern;

import Modelo.Weapon;
import Modelo.Character;

import java.util.ArrayList;

public class Attack extends BaseCommand {

    private Character atacante;
    private Weapon arma;

    public Attack(Character atacante, Weapon arma) {
        this.atacante = atacante;
        this.arma = arma;
    }

    @Override
    public String getCommandName() {
        return null;
    }

    @Override
    public void execute(ArrayList<Object> params) { // PENDIENTE
        if(params.size() == 3){ // doble arma
            Character guerrero = (Character) params.get(0);
            Weapon arma1 = (Weapon) params.get(1);
            Weapon arma2 = (Weapon) params.get(2);
        }else {
            Character guerrero1 = (Character) params.get(0);
            Weapon arma1 = (Weapon) params.get(1);
            Character guerrero2 = (Character) params.get(2);
            Weapon arma2 = (Weapon) params.get(3);
        }
    }
}
