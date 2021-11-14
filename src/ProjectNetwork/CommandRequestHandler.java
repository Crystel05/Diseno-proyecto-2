package ProjectNetwork;

import CommandPattern.CommandManager;
import CommandPattern.Enumerable.CommandsE;
import CommandPattern.ICommand;
import Modelo.Arma;
import Modelo.Partida;
import Modelo.Personaje;
import Network.Request.IHandleRequest;
import Network.Request.IRequest;
import Network.Request.ISendRequest;
import Network.Server.ServerRequestHandler;
import ProjectNetwork.Requests.CommandRequest;
import ProjectNetwork.Requests.ConnectRequest;
import ProjectNetwork.Requests.Enumerable.GameRequestTypes;
import ProjectNetwork.Requests.SelectedWarriors;
import ProjectNetwork.Responses.AvaliableWariorsResponse;
import ProjectNetwork.Responses.AvaliableWeaponsResponse;
import ProjectNetwork.Responses.ConnectResponse;
import ProjectNetwork.Responses.MessageResponse;

import java.io.IOException;
import java.util.ArrayList;

public class CommandRequestHandler implements IHandleRequest {
    @Override
    public void parseRequest(IRequest request, ServerRequestHandler handler) throws IOException, ClassNotFoundException {
        GameRequestTypes requestType = (GameRequestTypes) request.getType();
        switch (requestType){
            case COMMAND:
                CommandsE commandName = ((CommandRequest) request).getCommandType();//En nuestro caso tiene un enumarable que se llama tipo
                String[] commandArgs = ((CommandRequest) request).getParams();//Devuelve el otro resto
                ICommand command = CommandManager.getCommand(commandName);//Con este le pide al manager
                command.execute(commandArgs);//Aca se hace el execute y pasa al Game
                break;
            case CONNECT:
                int id = handler.getClientes().size();
                System.out.println("Conectandose"+id);
                handler.addToClients(new CommandServerSideClient(id));
                handler.getResponseSender().sendResponse(new ConnectResponse(id));
                ArrayList<Personaje> personajes = Partida.getInstance().cargarPersonajes();
                handler.getResponseSender().sendResponse(new AvaliableWariorsResponse(personajes));
                handler.getResponseSender().sendResponse(new AvaliableWeaponsResponse(Partida.getInstance().cargarArmas()));
                break;
            case SELECTEDTEAM:
                SelectedWarriors selectedWarriorsRequest = (SelectedWarriors) request;
                CommandServerSideClient client = (CommandServerSideClient)handler.getClientes().get(selectedWarriorsRequest.clientId);
                client.setEquipo(selectedWarriorsRequest.equipoElegido);
                break;
            default:
                System.out.println("Not known request");
                break;
        }

    }

}
