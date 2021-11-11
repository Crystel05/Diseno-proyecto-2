package ProjectNetwork.Responses;

import Modelo.AttackInfo;
import Network.Response.BaseResponse;
import Network.Response.IResponse;
import ProjectNetwork.Requests.Enumerable.GameRequestTypes;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

public class AttackInfoResponse extends BaseResponse {

    public AttackInfo attackInfo;
    public AttackInfoResponse(AttackInfo attackInfo) {
        this.attackInfo = attackInfo;
        this.type = GameResponsesType.ATTACKINFO;
    }
}
