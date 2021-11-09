package ProjectNetwork;

import CommandPattern.CommandManager;
import CommandPattern.Enumerable.CommandsE;
import CommandPattern.ICommand;
import Network.Request.IHandleRequest;
import Network.Request.IRequest;
import Network.Server.ServerRequestHandler;
import ProjectNetwork.Requests.CommandRequest;

import java.io.IOException;
import java.util.ArrayList;

public class CommandRequestHandler implements IHandleRequest {
    @Override
    public void parseRequest(IRequest request, ServerRequestHandler server) throws IOException, ClassNotFoundException {
        CommandsE commandName = (CommandsE) request.getType();//En nuestro caso tiene un enumarable que se llama tipo
        String[] commandArgs = ((CommandRequest) request).getParams();//Devuelve el otro resto
        ICommand command = CommandManager.getCommand(commandName);//Con este le pide al manager
        command.execute(commandArgs);//Aca se hace el execute y pasa al Game
    }

}
