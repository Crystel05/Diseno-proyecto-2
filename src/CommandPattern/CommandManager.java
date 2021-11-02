package CommandPattern;

import java.util.HashMap;

public class CommandManager {

    private HashMap<CommandsE, BaseCommand> comandos;
    private static CommandManager commandManager;

    public CommandManager getInstance(){
        if (commandManager == null)
            return new CommandManager();
        return commandManager;
    }

    public ICommand getCommand(CommandsE comando){
        return null; //retornar comando?
    }

    public void registrarCommand(CommandsE comando, ICommand comandClass){
        
    }

}
