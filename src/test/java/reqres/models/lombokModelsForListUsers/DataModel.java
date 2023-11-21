package reqres.models.lombokModelsForListUsers;

import lombok.Data;

@Data
public class DataModel {
    private int id;
    private String email, first_name, last_name, avatar;
}
