package ProjectNetwork.Requests;

import Modelo.Data.EquipoDatos;
import Modelo.Equipo;
import Network.Request.BaseRequest;
import ProjectNetwork.Requests.Enumerable.GameRequestTypes;

public class SelectedWarriors extends BaseRequest {

    public EquipoDatos equipoElegido;
    public int clientId;

    public SelectedWarriors(EquipoDatos equipo,int clientId){
        this.equipoElegido = equipo;
        this.type = GameRequestTypes.SELECTEDTEAM;
        this.clientId = clientId;
    }

}
