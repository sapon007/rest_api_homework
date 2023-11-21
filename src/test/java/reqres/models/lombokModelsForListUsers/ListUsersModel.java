package reqres.models.lombokModelsForListUsers;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ListUsersModel {
    private int page, per_page, total, total_pages;
    private ArrayList<DataModel> data;
    private SupportModel support;
}
