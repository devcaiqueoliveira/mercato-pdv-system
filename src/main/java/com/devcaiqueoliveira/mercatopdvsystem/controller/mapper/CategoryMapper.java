package com.devcaiqueoliveira.mercatopdvsystem.controller.mapper;

import com.devcaiqueoliveira.mercatopdvsystem.controller.dto.CategoryRequest;
import com.devcaiqueoliveira.mercatopdvsystem.controller.dto.CategoryResponse;
import com.devcaiqueoliveira.mercatopdvsystem.entity.Category;

public class CategoryMapper {

    public static Category toCategory(CategoryRequest request) {
        return Category.builder()
                .name(request.name())
                .description(request.description())
                .active(true)
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category entity) {
        return new CategoryResponse(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getActive()
        );
    }
}
