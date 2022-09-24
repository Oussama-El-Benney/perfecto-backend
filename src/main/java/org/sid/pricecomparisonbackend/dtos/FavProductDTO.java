package org.sid.pricecomparisonbackend.dtos;

import lombok.Data;
import org.sid.pricecomparisonbackend.entities.Product;


@Data
public class FavProductDTO extends Product {
  private Long id;
  private String name;
}
