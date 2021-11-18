package ProjectNetwork.Responses;

import Modelo.Equipo;
import Network.Response.BaseResponse;
import Network.Response.IResponse;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

public class UpdateResponse extends BaseResponse {

    public Equipo equipo;
    public  Equipo equipoEnemigo;

    public UpdateResponse(Equipo equipo,Equipo equipoEnemigo){
        this.type = GameResponsesType.UPDATE;
        this.equipo = equipo;
        this.equipoEnemigo = equipoEnemigo;
    }
}
