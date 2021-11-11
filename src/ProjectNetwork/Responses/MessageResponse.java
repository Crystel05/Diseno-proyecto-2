package ProjectNetwork.Responses;

import Network.Response.BaseResponse;
import ProjectNetwork.Responses.Enumerable.GameResponsesType;

public class MessageResponse extends BaseResponse {

    public String content;

    public MessageResponse(String content){
        this.type = GameResponsesType.MESSAGE;
        this.content = content;
    }

}
