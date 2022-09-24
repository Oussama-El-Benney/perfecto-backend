package org.sid.pricecomparisonbackend.repositories;

import org.sid.pricecomparisonbackend.entities.Product;
import org.sid.pricecomparisonbackend.enums.CategoryNature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {

//  List<Product> findByNameContains(String keyword);



  @Query("select p from Product p where p.id = :id")
  Product findProductById(@Param("id") Long id);

  List<Product> findByNameContains(String keyword);


  @Query("select p from Product p where p.name like :kw")
  List<Product> searchProducts(@Param("kw") String keyword);


  List<Product> findTop5ByOrderByIdDesc();

  List<Product> findTop5ByOrderByRatingDesc();

  List<Product> findByCategoryNature(CategoryNature categoryNature);

}
