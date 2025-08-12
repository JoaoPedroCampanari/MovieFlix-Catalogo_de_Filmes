package br.com.movieflix.service;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public CategoryResponse findbyId(UUID id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper::toCategoryResponde)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> getallCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toCategoryResponde)
                .toList();
    }

    @Transactional
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryRepository.save(CategoryMapper.toCategory(categoryRequest));
        return CategoryMapper.toCategoryResponde(category);
    }

    @Transactional(readOnly = true)
    public Optional<Category> findByIdByListFromMovie(UUID id){
        return categoryRepository.findById(id);
    }

    @Transactional
    public CategoryResponse updateCategory(UUID id, CategoryRequest categoryRequest){
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()){
            if (categoryRequest.name() != null){
                category.get().setName(categoryRequest.name());
            }
            return CategoryMapper.toCategoryResponde(categoryRepository.save(category.get()));
        }
        return null;
    }

    @Transactional
    public void deletebyId(UUID id) {
        categoryRepository.deleteById(id);
    }
}
