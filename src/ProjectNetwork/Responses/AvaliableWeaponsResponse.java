package ProjectNetwork.Responses;

import Model.Weapon;
import Modelo.Arma;
import Network.Response.BaseResponse;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

public class AvaliableWeaponsResponse extends BaseResponse {

    public Arma[] armasDisponibles;

    public AvaliableWeaponsResponse(Arma[] weapons){
        this.type = GameResponsesType.SENDWEAPONS;
        this.armasDisponibles = weapons;
    }
}
