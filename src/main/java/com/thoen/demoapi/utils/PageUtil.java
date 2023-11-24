package com.thoen.demoapi.utils;

import org.springframework.data.domain.Pageable;

public interface PageUtil {
    int DEFAULT_PAGE_NUMBER = 1;
    int DEFAULT_PAGE_LIMIT = 5;

    String PAGE_LIMIT = "limit";
    String PAGE_SIZE = "page";

    static Pageable getPageable(int pageNumber, int pageSize) {
        if (pageNumber < 1) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize < 1) {
            pageSize = DEFAULT_PAGE_LIMIT;
        }
        return org.springframework.data.domain.PageRequest.of(pageNumber - 1, pageSize);
    }
}
