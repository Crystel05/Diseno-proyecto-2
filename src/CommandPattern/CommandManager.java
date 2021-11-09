package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import FileManager.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandManager {

    private static HashMap<CommandsE, ICommand> comandos;
    private static CommandManager commandManager;
    private static Logger logger;

    public CommandManager getInstance(){
        if (commandManager == null)
            return new CommandManager();
        return commandManager;
    }

    public static ICommand getCommand(CommandsE comando){
        return comandos.get(comando);
    }

    public void registrarCommand(CommandsE comando, ICommand comandClass){
        comandos.put(comando, comandClass);
    }

    }
