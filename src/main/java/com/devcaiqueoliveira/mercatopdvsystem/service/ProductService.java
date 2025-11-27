package com.devcaiqueoliveira.mercatopdvsystem.service;

import com.devcaiqueoliveira.mercatopdvsystem.entity.Category;
import com.devcaiqueoliveira.mercatopdvsystem.entity.Product;
import com.devcaiqueoliveira.mercatopdvsystem.exception.BusinessRuleException;
import com.devcaiqueoliveira.mercatopdvsystem.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository repository;
    private final CategoryService categoryService;

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto com o ID: " + id + " não encontrado."));
    }

    public List<Product> listAll() {
        return repository.findAll();
    }

    @Transactional
    public Product save(Product product) {

        product.setActive(true);

        if (repository.existsByBarCode(product.getBarCode())) {
            throw new BusinessRuleException("Já existe um produto com este código de barras cadastrado.");
        }

        Category category = categoryService.findById(product.getCategory().getId());
        product.setCategory(category);

        return repository.save(product);
    }

    @Transactional
    public Product update(Product product) {
        Optional<Product> existingProductOpt = repository.findByBarCode(product.getBarCode());

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();
            if (!existingProduct.getId().equals(product.getId())) {
                throw new BusinessRuleException("Este código de barras ja pertence a outro produto.");
            }
        }

        return  repository.save(product);
    }

    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado no sistema.");
        }
        repository.deleteById(id);
    }
}