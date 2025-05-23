package com.sna.project.phonephop.PhoneShop.model.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;

@Builder
@Data
public class PaginationDTO {
    private int pageSize;
    private int  pageNumber;
    private int totalPages;
    private long totalElements;
    private long numberOfElements;
    private boolean first;
    private boolean last;
    private boolean empty;

    /*
            "content": [
            {
                "id": 4,
                "name": "Techno"
            }
        ],
        "pageable": {
            "pageNumber": 0,
            "pageSize": 1,
            "sort": {
                "empty": true,
                "unsorted": true,
                "sorted": false
            },
            "offset": 0,
            "unpaged": false,
            "paged": true
        },
        "last": false,
        "totalElements": 4,
        "totalPages": 4,
        "size": 1,
        "number": 0,
        "sort": {
            "empty": true,
            "unsorted": true,
            "sorted": false
        },
        "first": true,
        "numberOfElements": 1,
        "empty": false
     */
}
