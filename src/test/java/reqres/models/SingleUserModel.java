package reqres.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SingleUserModel {

    private DataModel data;
    private SupportModel support;

    @Data
    public static class DataModel {
        private int id;
        private String email, avatar;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
    }

    @Data
    public static class SupportModel {
        private String url, text;
    }
}
