package ProjectNetwork.Responses;

import Modelo.Personaje;
import Network.Response.BaseResponse;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

import java.util.ArrayList;

public class AvaliableWariorsResponse extends BaseResponse {

    public ArrayList<Personaje> guerreros;

    public AvaliableWariorsResponse(ArrayList<Personaje> guerreros) {
        this.type = GameResponsesType.SENDWARIORS;
        this.guerreros = guerreros;
    }
}
