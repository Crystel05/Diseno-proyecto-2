package CommandPattern;

import Modelo.Arma;
import Modelo.Guerrero;
import Modelo.Usuario;

import java.util.ArrayList;

public class Attack extends BaseCommand {

    private Guerrero atacante;
    private Arma arma;

    public Attack(Guerrero atacante, Arma arma) {
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
            Guerrero guerrero = (Guerrero) params.get(0);
            Arma arma1 = (Arma) params.get(1);
            Arma arma2 = (Arma) params.get(2);
        }else {
            Guerrero guerrero1 = (Guerrero) params.get(0);
            Arma arma1 = (Arma) params.get(1);
            Guerrero guerrero2 = (Guerrero) params.get(2);
            Arma arma2 = (Arma) params.get(3);
        }
    }
}
