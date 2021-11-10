package CommandPattern;

import CommandPattern.Enumerable.CommandsE;

import java.io.IOException;
import java.util.ArrayList;

public interface ICommand {

    CommandsE getType();
    void execute(String[] params) throws IOException; //poner parámetros
}
