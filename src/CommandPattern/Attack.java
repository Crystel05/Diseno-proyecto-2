package CommandPattern;

import Modelo.Arma;
import Modelo.Personaje;

import java.util.ArrayList;

public class Attack extends BaseCommand {

    private Personaje atacante;
    private Arma arma;

    public Attack(Personaje atacante, Arma arma) {
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
            Personaje guerrero = (Personaje) params.get(0);
            Arma arma1 = (Arma) params.get(1);
            Arma arma2 = (Arma) params.get(2);
        }else {
            Personaje guerrero1 = (Personaje) params.get(0);
            Arma arma1 = (Arma) params.get(1);
            Personaje guerrero2 = (Personaje) params.get(2);
            Arma arma2 = (Arma) params.get(3);
        }
    }
}
