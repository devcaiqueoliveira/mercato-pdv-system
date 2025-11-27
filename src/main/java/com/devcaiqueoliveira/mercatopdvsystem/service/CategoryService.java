package com.devcaiqueoliveira.mercatopdvsystem.service;

import com.devcaiqueoliveira.mercatopdvsystem.entity.Category;
import com.devcaiqueoliveira.mercatopdvsystem.exception.BusinessRuleException;
import com.devcaiqueoliveira.mercatopdvsystem.exception.EntityNotFoundException;
import com.devcaiqueoliveira.mercatopdvsystem.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository repository;

    public Category findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria com ID: " + id + " não encontrada."));
    }

    public List<Category> listAll() {
        return repository.findAll();
    }

    @Transactional
    public Category create(Category category) {

        validateUniqueName(category.getName());
        category.setActive(true);

        return repository.save(category);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Categoria não encontrada para ser removida");
        }
        repository.deleteById(id);
    }

    @Transactional
    public Category update(Long id, Category newData) {
        Category existingCategory = findById(id);
        validateUniqueNameForUpdate(newData.getName(), id);
        existingCategory.updateFrom(newData);
        return repository.save(existingCategory);
    }

    private void validateUniqueName(String name) {
        if (repository.existsByName(name)) {
            throw new BusinessRuleException("Já existe uma categoria com este nome.");
        }
    }

    private void validateUniqueNameForUpdate(String name, Long id) {
        if (repository.existsByNameAndIdNot(name, id)) {
            throw new BusinessRuleException("Uma categoria com este nome já pertence a outro registro.");
        }
    }

}