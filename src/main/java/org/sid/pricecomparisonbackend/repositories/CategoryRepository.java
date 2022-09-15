package org.sid.pricecomparisonbackend.repositories;

import org.sid.pricecomparisonbackend.entities.Category;
import org.sid.pricecomparisonbackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

  public Category findByName(String name);

//  @Query("select p from Product p GROUP BY ")
//  List<Product> countTotalProductsByCategory();
}
