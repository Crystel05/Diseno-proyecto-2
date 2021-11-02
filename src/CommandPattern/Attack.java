package CommandPattern;

import Modelo.Arma;
import Modelo.Guerrero;
import Modelo.Usuario;

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
    public void execute() {

    }
}
