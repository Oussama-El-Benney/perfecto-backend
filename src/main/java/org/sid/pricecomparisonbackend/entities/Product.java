package org.sid.pricecomparisonbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.pricecomparisonbackend.enums.CategoryNature;
import org.sid.pricecomparisonbackend.enums.PersonNature;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;
  private String imageSrc;
  private int quantity;
  private int rating;
  @Enumerated(EnumType.STRING)
  private CategoryNature categoryNature;
  @ManyToOne
  private Favorites favorites;
//  @ManyToOne
//  private Category category;
}
