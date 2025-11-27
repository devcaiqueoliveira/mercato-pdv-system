package com.devcaiqueoliveira.mercatopdvsystem.controller;

import com.devcaiqueoliveira.mercatopdvsystem.controller.dto.CategoryResponse;
import com.devcaiqueoliveira.mercatopdvsystem.controller.mapper.CategoryMapper;
import com.devcaiqueoliveira.mercatopdvsystem.entity.Category;
import com.devcaiqueoliveira.mercatopdvsystem.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<Category> categories = categoryService.listAll();

        List<CategoryResponse> responses = categories.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}