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
            commandManager = new CommandManager();
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
        CommandManager.registrarCommand(CommandsE.ATACK,new AttackCommand());
        CommandManager.registrarCommand(CommandsE.ERROR,new ErrorCommand());
        CommandManager.registrarCommand(CommandsE.GIVEUP,new GiveUp());
        CommandManager.registrarCommand(CommandsE.MUTUALEXIT,new MutualExit());
        CommandManager.registrarCommand(CommandsE.PASSTURN,new PassTurn());
        CommandManager.registrarCommand(CommandsE.RECHARGEWEAPON,new RechargeWeapon());
        CommandManager.registrarCommand(CommandsE.USEWILDCARD,new UseWildCard());
        CommandManager.registrarCommand(CommandsE.SELECTPLAYER,new SelectPlayer());
    }
}
