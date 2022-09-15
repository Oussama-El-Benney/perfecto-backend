package org.sid.pricecomparisonbackend.mappers;

import org.sid.pricecomparisonbackend.dtos.CategoryDTO;
import org.sid.pricecomparisonbackend.entities.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapperImpl {
  public CategoryDTO fromCategory(Category category) {
    CategoryDTO CategoryDTO = new CategoryDTO();
    BeanUtils.copyProperties(category, CategoryDTO);
    return CategoryDTO;
  }


  public Category fromCategoryDTO(CategoryDTO CategoryDTO) {
    Category Category = new Category();
    BeanUtils.copyProperties(CategoryDTO, Category);
    return Category;
  }


}
