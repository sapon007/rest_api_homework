package reqres.models.lombokmodelsforlistusers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class ListUsersModel {
    private int page, total;
    @JsonProperty("per_page")
    private int perPage;
    @JsonProperty("total_pages")
    private int totalPages;
    private ArrayList<DataModel> data;
    private SupportModel support;
}
