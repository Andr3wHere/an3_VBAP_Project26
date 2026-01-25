package cz.osu.backend.service;

import cz.osu.backend.exception.ResourceNotFoundException;
import cz.osu.backend.model.db.Category;
import cz.osu.backend.model.json.CategoryRequestDTO;
import cz.osu.backend.model.json.CategoryResponseDTO;
import cz.osu.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {
        Category category = new Category();
        category.setName(request.getName());

        Category savedCategory = categoryRepository.save(category);

        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(savedCategory.getId());
        response.setName(savedCategory.getName());

        return response;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Kategorie s ID " + id + " nebyla nalezena"));
    }

    public CategoryResponseDTO updateCategory(UUID id, CategoryRequestDTO request) {
        Category category = getCategoryById(id);
        category.setName(request.getName());
        Category savedCategory = categoryRepository.save(category);

        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(savedCategory.getId());
        response.setName(savedCategory.getName());

        return response;
    }

    public void deleteCategory(UUID id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Kategorie s ID " + id + " nebyla nalezena");
        }
        categoryRepository.deleteById(id);
    }
}
