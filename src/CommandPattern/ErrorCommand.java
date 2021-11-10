package CommandPattern;

import java.util.ArrayList;

public class ErrorCommand extends BaseCommand{


    public ErrorCommand() {

    }

    @Override
    public void execute(String[] params) {
        //Partida.getInstace().errorCommand((String)params.get(0));
    }
}
