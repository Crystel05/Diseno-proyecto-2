package ProjectNetwork.Requests;

import Modelo.Equipo;
import Network.Request.BaseRequest;
import ProjectNetwork.Requests.Enumerable.GameRequestTypes;

public class SelectedWarriors extends BaseRequest {

    public Equipo equipoElegido;
    public int clientId;

    public SelectedWarriors(Equipo equipo){
        this.equipoElegido = equipo;
        this.type = GameRequestTypes.SELECTEDTEAM;
    }

}
