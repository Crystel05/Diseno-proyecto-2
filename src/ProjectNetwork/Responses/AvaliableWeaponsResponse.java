package ProjectNetwork.Responses;

import Model.Weapon;
import Modelo.Arma;
import Network.Response.BaseResponse;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

import java.util.ArrayList;

public class AvaliableWeaponsResponse extends BaseResponse {

    public ArrayList<Arma> armasDisponibles;

    public AvaliableWeaponsResponse(ArrayList<Arma> weapons){
        this.type = GameResponsesType.SENDWEAPONS;
        this.armasDisponibles = weapons;
    }
}
