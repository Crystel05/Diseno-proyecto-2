package ProjectNetwork.Responses;

import Modelo.Equipo;
import Network.Response.BaseResponse;
import Network.Response.IResponse;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

public class UpdateResponse extends BaseResponse {
    Equipo equipo;
    public UpdateResponse(Equipo equipo){
        this.type = GameResponsesType.UPDATE;
        this.equipo = equipo;
    }
}
