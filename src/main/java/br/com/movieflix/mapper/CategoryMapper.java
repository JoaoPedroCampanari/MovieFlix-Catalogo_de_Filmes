package br.com.movieflix.mapper;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.Category;
import lombok.experimental.UtilityClass;

import java.text.Normalizer;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CategoryRequest request){
        return Category.builder()
                .name(normalizarNome(request.name()))
                .build();
    }

    public static CategoryResponse toCategoryResponde(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static String normalizarNome(String nome){
        return Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "") // remove os acentos.
                .replaceAll("\\s+", "")     // remove espaços
                .replaceAll("[^a-z0-9]", "") // remove símbolos
                .toLowerCase();
    }
}
