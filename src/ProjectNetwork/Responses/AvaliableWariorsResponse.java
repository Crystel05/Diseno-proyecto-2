package ProjectNetwork.Responses;

import Modelo.Personaje;
import Network.Response.BaseResponse;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

public class AvaliableWariorsResponse extends BaseResponse {

    public Personaje[] guerreros;

    public AvaliableWariorsResponse(Personaje[] guerreros) {
        this.type = GameResponsesType.SENDWARIORS;
        this.guerreros = guerreros;
    }
}
