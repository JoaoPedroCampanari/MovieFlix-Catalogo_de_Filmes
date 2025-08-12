package br.com.movieflix.controller;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Path;
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
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getallCategories() {
        return ResponseEntity.ok().body(categoryService.getallCategories());
    }

    @GetMapping("{id}")
    public ResponseEntity<CategoryResponse> findbyID(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok().body(categoryService.findbyId(id));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.saveCategory(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletebyId(@PathVariable(value = "id") UUID id) {
        categoryService.deletebyId(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable(name = "id") UUID id, @RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok().body(categoryService.updateCategory(id, categoryRequest));
    }
}
