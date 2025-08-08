package br.com.movieflix.controller;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getallCategories() {
        List<CategoryResponse> categoryResponses = categoryService
                .getallCategories()
                .stream()
                .map(CategoryMapper::toCategoryResponde)
                .toList();
        return ResponseEntity.ok().body(categoryResponses);
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> findbyID(@PathVariable(value = "id") UUID id) {
        return categoryService.findbyId(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryResponde(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest request) {
        Category category = categoryService.saveCategory(CategoryMapper.toCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryResponde(category));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletebyId(@PathVariable(value = "id") UUID id) {
        categoryService.deletebyId(id);
        return ResponseEntity.noContent().build();
    }
}
