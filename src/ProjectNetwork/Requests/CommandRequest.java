package ProjectNetwork.Requests;

import CommandPattern.Enumerable.CommandsE;
import Network.Request.BaseRequest;
import ProjectNetwork.Requests.Enumerable.GameRequestTypes;

import java.util.ArrayList;

public class CommandRequest extends BaseRequest {

    public String[] params;
    public CommandsE commandType;
    public int clientId;

    public CommandRequest(CommandsE commandType, String[] params,int clientId){
        this.params = params;
        this.commandType = commandType;
        this.type = GameRequestTypes.COMMAND;
        this.clientId = clientId;
    }

    public String[] getParams() {
        return params;
    }
    public CommandsE getCommandType(){return commandType;};
}
