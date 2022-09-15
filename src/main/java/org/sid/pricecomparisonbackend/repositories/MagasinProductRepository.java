package org.sid.pricecomparisonbackend.repositories;

import org.sid.pricecomparisonbackend.entities.Magasin;
import org.sid.pricecomparisonbackend.entities.MagasinProduct;
import org.sid.pricecomparisonbackend.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MagasinProductRepository extends JpaRepository<MagasinProduct, Long> {
  List<MagasinProduct> findByNameContains(String keyword);
}
