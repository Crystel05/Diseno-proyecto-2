package ProjectNetwork.Requests;

import CommandPattern.Enumerable.CommandsE;
import Network.Request.BaseRequest;

import java.util.ArrayList;

public class CommandRequest extends BaseRequest {

    String[] params;
    CommandsE type;

    public CommandRequest(CommandsE type, String[] params){
        this.params = params;
        this.type = type;
    }

    public String[] getParams() {
        return params;
    }
}
