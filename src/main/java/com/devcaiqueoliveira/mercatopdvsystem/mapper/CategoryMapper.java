package com.devcaiqueoliveira.mercatopdvsystem.mapper;

import com.devcaiqueoliveira.mercatopdvsystem.controller.dto.CategoryRequest;
import com.devcaiqueoliveira.mercatopdvsystem.controller.dto.CategoryResponse;
import com.devcaiqueoliveira.mercatopdvsystem.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class CategoryMapper {

    public static Category toCategory(CategoryRequest request) {
        return Category.builder()
                .name(request.name())
                .description(request.description())
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
