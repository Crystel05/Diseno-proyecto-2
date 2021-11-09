package ProjectNetwork.Responses;

import Modelo.Guerrero;
import Network.Response.BaseResponse;
import Network.Response.IResponse;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

public class AvaliableWariorsResponse extends BaseResponse {
    public AvaliableWariorsResponse(Guerrero[] guerreros) {
        this.type = GameResponsesType.SENDWARIORS;
    }
}
