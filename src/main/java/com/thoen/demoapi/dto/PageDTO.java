package com.thoen.demoapi.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageDTO {
    private List<?> data;
    private PaginationDTO pagination;

    public PageDTO(Page<?> page) {
        this.data = page.getContent();
        this.pagination = PaginationDTO.builder()
                .pageSize(page.getSize())
                .pageNumber(page.getNumber() + 1)
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .numberOfElements(page.getNumberOfElements())
                .first(page.isFirst())
                .last(page.isLast())
                .empty(page.isEmpty())
                .build();
    }
}
