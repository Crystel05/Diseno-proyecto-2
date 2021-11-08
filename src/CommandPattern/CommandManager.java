package CommandPattern;

import java.util.ArrayList;
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
        return comandos.get(comando);
    }

    public void registrarCommand(CommandsE comando, ICommand comandClass){
        //
    }

    public void executeCommand(ArrayList<Object> params, ICommand command) {
        command.execute(params);
    }

}
