package org.sid.pricecomparisonbackend.web;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.pricecomparisonbackend.dtos.CategoryDTO;
import org.sid.pricecomparisonbackend.dtos.FavProductDTO;
import org.sid.pricecomparisonbackend.dtos.MagasinProductDTO;
import org.sid.pricecomparisonbackend.dtos.ProductDTO;
import org.sid.pricecomparisonbackend.entities.Product;
import org.sid.pricecomparisonbackend.exceptions.MagasinProductNotFoundException;
import org.sid.pricecomparisonbackend.services.PriceComparisonService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class ProductRestController {
private PriceComparisonService priceComparisonService;

  @GetMapping("/products")
  public List<MagasinProductDTO> products(){
    return priceComparisonService.magasinProductList();
  }


  @GetMapping("/products/{id}")
  public MagasinProductDTO getMagasinProduct(@PathVariable(name = "id") Long productId) throws MagasinProductNotFoundException {
    return priceComparisonService.getMagasinProductDTO(productId);
  }
  @GetMapping("/products/search")
  public List<ProductDTO> searchProducts(@RequestParam(name = "keyword",defaultValue = "") String keyword){
    return priceComparisonService.searchProducts("%"+keyword+"%");
  }

  @GetMapping("/products/searchs")
  public ProductDTO searchProductsById(@RequestParam(name = "id",defaultValue = "") Long id){
    return priceComparisonService.searchProductsById(id);
  }

  @PostMapping("/products")
  public MagasinProductDTO saveProduct(@RequestBody MagasinProductDTO magasinProductDTO){
    return priceComparisonService.saveMagasinProduct(magasinProductDTO);
  }

  @GetMapping("/categories")
  public List<CategoryDTO> categories(){
    return priceComparisonService.CategoriesList();
  }

  @GetMapping("/products/recent")
  public List<ProductDTO> recentProducts(){
    return priceComparisonService.getRecentProducts();
  }

  @GetMapping("/products/toprated")
  public List<ProductDTO> topRatedProducts(){
    return priceComparisonService.getTopRatedProducts();
  }


  @GetMapping("/products/search?category=")
  public List<ProductDTO> getProductsByCategory(@RequestParam(name = "category") String category){
    return priceComparisonService.getProductsByCategory(category);
  }

  @GetMapping("/favorites")
  public Collection<Product> getFavorites(Principal principal){
    return priceComparisonService.getFavorites(principal);
  }

//  @PostMapping("/addProductToFavorites/{productId}")
//  public ProductDTO addProductToFavorites(@PathVariable Long productId, Principal principal){
//
//    return priceComparisonService.addProductToFavorites(productId,principal);
//  }
  @PostMapping("/addProductToFavorites")
  public ProductDTO addProductToFavorites(@RequestBody FavProductDTO favProductDTO, Principal principal){
    return priceComparisonService.addProductToFavorites(favProductDTO,principal);
  }
  @DeleteMapping("/deleteProductFromFavorites/{productId}")
  public void deleteProductFromFavorites(@PathVariable Long productId, Principal principal){
    priceComparisonService.deleteProductFromFavorites(productId,principal);
  }
}



