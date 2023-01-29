package model;

import lombok.*;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Facts {

    @JsonbProperty("current_page") Integer currentPage;
    @JsonbProperty("data") List<Fact> factList;
    @JsonbProperty("first_page_url") String firstPageUrl;
    @JsonbProperty("from") Integer from;
    Integer to;
    @JsonbProperty("last_page") Integer lastPage;
    @JsonbProperty("last_page_url") String lastPageUrl;
    @JsonbProperty("next_page_url") String nextPageUrl;
    @JsonbProperty("path") String path;
    @JsonbProperty("total") Integer total;

    @JsonbProperty("per_page") Integer perPage;
    @JsonbProperty("prev_page_url") Integer prevPageUrl;

}
