package ProjectNetwork.Requests;

import Network.Request.BaseRequest;
import ProjectNetwork.Requests.Enumerable.GameRequestTypes;

public class ConnectRequest extends BaseRequest {

    public ConnectRequest(){
        this.type = GameRequestTypes.CONNECT;
    }

}
