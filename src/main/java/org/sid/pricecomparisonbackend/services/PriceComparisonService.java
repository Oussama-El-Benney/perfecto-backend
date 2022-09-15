package org.sid.pricecomparisonbackend.services;

import org.sid.pricecomparisonbackend.dtos.CategoryDTO;
import org.sid.pricecomparisonbackend.dtos.MagasinProductDTO;
import org.sid.pricecomparisonbackend.dtos.ProductDTO;
import org.sid.pricecomparisonbackend.entities.MagasinProduct;
import org.sid.pricecomparisonbackend.entities.Person;
import org.sid.pricecomparisonbackend.entities.Product;
import org.sid.pricecomparisonbackend.enums.PersonNature;
import org.sid.pricecomparisonbackend.exceptions.MagasinProductNotFoundException;

import java.util.List;

public interface PriceComparisonService {

  Person savePerson(Person person, PersonNature nature);

  MagasinProductDTO saveMagasinProduct(MagasinProductDTO magasinProductDTO);

  MagasinProduct getMagasinProduct(Long productId) throws MagasinProductNotFoundException;

  MagasinProductDTO getMagasinProductDTO(Long productId) throws MagasinProductNotFoundException;

  List<MagasinProductDTO> magasinProductList();
  List<CategoryDTO> CategoriesList();
  int CategoryLength(Long id);
  List<ProductDTO> searchProducts(String s);
  List<ProductDTO> getRecentProducts();
  List<ProductDTO> getTopRatedProducts();
  List<ProductDTO> searchProductsById(Long id);

  List<ProductDTO> getProductsByCategory(String s);

}
