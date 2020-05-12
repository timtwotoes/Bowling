package dk.timtwotoes.bowling.network;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class SuccessData {
    private Boolean success;
    private Object input;

    public Boolean getSuccess() {
        return success;
    }
    public Object getInput() {
        return input;
    }

    public static SuccessData fromJSON(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonString, SuccessData.class);
    }

}
