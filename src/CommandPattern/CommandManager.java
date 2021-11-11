package CommandPattern;

import CommandPattern.Enumerable.CommandsE;
import FileManager.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class CommandManager {

    private static HashMap<CommandsE, ICommand> comandos;
    private static CommandManager commandManager;

    private CommandManager(){
        comandos = new HashMap<>();
    }

    public static CommandManager getInstance(){
        if (commandManager == null)
            return new CommandManager();
        return commandManager;
    }

    public static ICommand getCommand(CommandsE comando){
        return comandos.get(comando);
    }

    public static void registrarCommand(CommandsE comando, ICommand comandClass){
        comandos.put(comando, comandClass);
    }

    public static void createCommands(){
        CommandManager.registrarCommand(CommandsE.CHAT,new Chat());
    }
    }
