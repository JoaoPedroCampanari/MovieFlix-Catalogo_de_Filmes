package br.com.movieflix.service;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.exception.categoryException.CategoryAlreadyExistException;
import br.com.movieflix.exception.categoryException.CategoryNotFoundException;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public CategoryResponse findbyId(UUID id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper::toCategoryResponde)
                .orElseThrow(CategoryNotFoundException::new);
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
        if (categoryRepository.existsCategoriesByNameIgnoreCase(categoryRequest.name())){
            throw new CategoryAlreadyExistException();
        }
        Category category = categoryRepository.save(CategoryMapper.toCategory(categoryRequest));
        return CategoryMapper.toCategoryResponde(category);
    }

    @Transactional(readOnly = true)
    public Category findByIdByListFromMovie(UUID id){
        return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
    }

    @Transactional
    public CategoryResponse updateCategory(UUID id, CategoryRequest categoryRequest){
        Category category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
        if (categoryRepository.existsCategoriesByNameIgnoreCase(categoryRequest.name())){
            throw new CategoryAlreadyExistException();
        }
        category.setName(categoryRequest.name());
        return CategoryMapper.toCategoryResponde(categoryRepository.save(category));

    }

    @Transactional
    public void deletebyId(UUID id) {
        if (!categoryRepository.existsById(id)){
            throw new CategoryNotFoundException();
        }
        categoryRepository.deleteById(id);
    }
}
