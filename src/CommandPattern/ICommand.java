package CommandPattern;

import java.util.ArrayList;

public interface ICommand {

    String getCommandName();
    void execute(ArrayList<Object> params); //poner parámetros
}
