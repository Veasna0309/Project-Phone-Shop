package com.sna.project.phonephop.PhoneShop.model.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
@Data
public class PageDTO {
    private List<?> list;
    private PaginationDTO pagination;
    public PageDTO(Page<?> page) {
        this.list = page.getContent();
        this.pagination = PaginationDTO.builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(page.getPageable().getPageSize())
                .pageNumber(page.getPageable().getPageNumber()+1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .build();
    }
//public PageDTO(Page<?> page) {
//    this.list = page.getContent();
//    this.paginationDTO=PaginationDTO.builder()
//            .empty(page.isEmpty())
//            .first(page.isFirst())
//            .last(page.isLast())
//            .pageSize(page.getSize())
//            .pageNumber(page.getNumber())
//            .totalPages(page.getTotalPages())
//            .totaleElements(page.getTotalPages())
//            .numberOfElements(page.getNumberOfElements())
//            .build();
//}
}
