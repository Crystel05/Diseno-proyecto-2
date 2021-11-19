package ProjectNetwork.Responses;

import Modelo.Arma;
import Modelo.Personaje;
import Network.Response.BaseResponse;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

public class DanoHechoResponse extends BaseResponse {

    public int danoHecho;
    public Personaje personaje;
    public Arma arma;

    public DanoHechoResponse(int dano,Personaje personaje,Arma arma) {
        super();
        this.type = GameResponsesType.DEALEADDAMAGE;
        this.danoHecho = dano;
        this.arma = arma;
        this.personaje = personaje;
    }
}
