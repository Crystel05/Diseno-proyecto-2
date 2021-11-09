package CommandPattern;

import CommandPattern.Enumerable.CommandsE;

import java.util.ArrayList;

public interface ICommand {

    CommandsE getType();
    void execute(String[] params); //poner parámetros
}
