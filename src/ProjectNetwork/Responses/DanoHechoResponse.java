package ProjectNetwork.Responses;

import Network.Response.BaseResponse;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

public class DanoHechoResponse extends BaseResponse {

    public int danoHecho;
    public DanoHechoResponse(int dano) {
        super();
        this.type = GameResponsesType.DEALEADDAMAGE;
        this.danoHecho = dano;
    }
}
