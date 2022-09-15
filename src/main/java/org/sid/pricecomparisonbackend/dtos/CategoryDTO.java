package org.sid.pricecomparisonbackend.dtos;

import lombok.Data;
import org.sid.pricecomparisonbackend.entities.Category;


@Data
public class CategoryDTO extends Category {
  private Long id;
  private String name;
//  private int productsNumber;
}
