package ProjectNetwork.Responses;

import Network.Response.BaseResponse;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

public class ConnectResponse extends BaseResponse {

    String content = "Conexion exitosa";
    int clientId;

    public ConnectResponse(int clientId){
        this.clientId = clientId;
        this.type = GameResponsesType.CONNECT;
    }

}
