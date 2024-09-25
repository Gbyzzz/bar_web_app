package com.gbyzzz.bar_search.entity.requests;

import com.gbyzzz.bar_search.entity.pagination.Pagination;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestWrapper {
    private String query;
    private Pagination pagination;
}
