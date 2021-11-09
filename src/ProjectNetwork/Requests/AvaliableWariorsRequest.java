package ProjectNetwork.Requests;

import Network.Request.BaseRequest;
import ProjectNetwork.Requests.Enumerable.GameRequestTypes;

public class AvaliableWariorsRequest extends BaseRequest {

    public AvaliableWariorsRequest(){
        this.type = GameRequestTypes.AVALIABLEWARIORS;
    }

}
