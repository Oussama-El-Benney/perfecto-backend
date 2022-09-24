package org.sid.pricecomparisonbackend.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.pricecomparisonbackend.dtos.CategoryDTO;
import org.sid.pricecomparisonbackend.dtos.FavProductDTO;
import org.sid.pricecomparisonbackend.dtos.MagasinProductDTO;
import org.sid.pricecomparisonbackend.dtos.ProductDTO;
import org.sid.pricecomparisonbackend.entities.*;
import org.sid.pricecomparisonbackend.enums.CategoryNature;
import org.sid.pricecomparisonbackend.enums.PersonNature;
import org.sid.pricecomparisonbackend.exceptions.MagasinProductNotFoundException;
import org.sid.pricecomparisonbackend.mappers.CategoryMapperImpl;
import org.sid.pricecomparisonbackend.mappers.MagasinProductMapperImpl;
import org.sid.pricecomparisonbackend.mappers.ProductMapperImpl;
import org.sid.pricecomparisonbackend.repositories.*;
import org.sid.pricecomparisonbackend.secrservice.entities.AppUser;
import org.sid.pricecomparisonbackend.secrservice.repo.AppUserRepository;
import org.sid.pricecomparisonbackend.secrservice.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class PriceComparisonServiceImpl implements PriceComparisonService {
  private PersonRepository personRepository;
  private MagasinProductRepository magasinProductRepository;
  private ProductRepository productRepository;
  private CategoryRepository categoryRepository;
  private CategoryMapperImpl categoryMapper;
  private MagasinProductMapperImpl magasinProductMapper;
  private FavoritesRepository favoritesRepository;
  private ProductMapperImpl productMapper;
  private AccountService accountService;
  private AppUserRepository appUserRepository;

  @Override
  public Person savePerson(Person person, PersonNature nature) {

    log.info("Saving new Person");
//    Person savedPerson = personRepository.save(person);

    if (nature == PersonNature.CLIENT) {
      person = new Client();
    } else {
      person = new Admin();
    }
    Person savedPerson = personRepository.save(person);
    return savedPerson;
  }

  @Override
  public MagasinProductDTO saveMagasinProduct(MagasinProductDTO magasinProductDTO) {
    log.info("Saving new Product");
    MagasinProduct magasinProduct = magasinProductMapper.fromMagasinProductDTO(magasinProductDTO);
    MagasinProduct savedMagasinProduct = magasinProductRepository.save(magasinProduct);
    return magasinProductMapper.fromMagasinProduct(savedMagasinProduct);
  }

  @Override
  public MagasinProduct getMagasinProduct(Long productId) throws MagasinProductNotFoundException {
    MagasinProduct magasinProduct = magasinProductRepository.findById(productId)
            .orElseThrow(() -> new MagasinProductNotFoundException("Product not found"));
    return magasinProduct;
  }

  @Override
  public MagasinProductDTO getMagasinProductDTO(Long productId) throws MagasinProductNotFoundException {
    MagasinProduct magasinProduct = magasinProductRepository.findById(productId)
            .orElseThrow(() -> new MagasinProductNotFoundException("Product not found"));
    MagasinProductDTO magasinProductDTO = magasinProductMapper.fromMagasinProduct(magasinProduct);
    return magasinProductDTO;
  }

  @Override
  public List<MagasinProductDTO> magasinProductList() {
    List<MagasinProduct> magasinProducts = magasinProductRepository.findAll();

    List<MagasinProductDTO> magasinProductDTOS = magasinProducts.stream().map(prod ->
            magasinProductMapper.fromMagasinProduct(prod)).collect(Collectors.toList());

    return magasinProductDTOS;
  }

  @Override
  public List<CategoryDTO> CategoriesList() {
    List<Category> Categories = categoryRepository.findAll();
    List<CategoryDTO> categoryDTOS = Categories.stream().map(cat ->
            categoryMapper.fromCategory(cat)).collect(Collectors.toList());
    return categoryDTOS;
  }

  @Override
  public int CategoryLength(Long id) {
    Optional<Category> Category = categoryRepository.findById(id);
//    category.get
    return 0;
  }

  @Override
  public List<ProductDTO> searchProducts(String keyword) {
    List<Product> products = productRepository.searchProducts(keyword);
//    List<Product> product=productRepository.findByNameContains(keyword);
//
////    List<MagasinProductDTO> MagasinProductDTOS = products.stream().map(prod -> magasinProductMapper.fromMagasinProduct(prod)).collect(Collectors.toList());
    List<ProductDTO> ProductDTOS = products.stream().map(prod -> productMapper.fromProduct(prod)).collect(Collectors.toList());
//
    return ProductDTOS;
//    return null;
  }

  @Override
  public List<ProductDTO> getRecentProducts() {
    List<Product> products = productRepository.findTop5ByOrderByIdDesc();
    List<ProductDTO> ProductDTOS = products.stream().map(prod -> productMapper.fromProduct(prod)).collect(Collectors.toList());
    return ProductDTOS;
  }

  @Override
  public List<ProductDTO> getTopRatedProducts() {
    List<Product> products = productRepository.findTop5ByOrderByRatingDesc();
    List<ProductDTO> ProductDTOS = products.stream().map(prod -> productMapper.fromProduct(prod)).collect(Collectors.toList());
    return ProductDTOS;
  }

  public ProductDTO searchProductsById(Long id) {
//        List<Product> products=productRepository.findByNameContains(keyword);

    Product products = productRepository.findProductById(id);
//    List<ProductDTO> ProductDTOS = products.stream().map(prod -> productMapper.fromProduct(prod)).collect(Collectors.toList());
    ProductDTO ProductDTOS = productMapper.fromProduct(products);

    return ProductDTOS;
//    return null;
  }

  @Override
  public List<ProductDTO> getProductsByCategory(String s) {

    List<Product> CategoryProducts = productRepository.findByCategoryNature(CategoryNature.valueOf(s));
//    List<MagasinProductDTO> MagasinProductDTOS = products.stream().map(prod -> magasinProductMapper.fromMagasinProduct(prod)).collect(Collectors.toList());
    List<ProductDTO> CategoryProductsDTOS = CategoryProducts.stream().map(prod -> productMapper.fromProduct(prod)).collect(Collectors.toList());

    return CategoryProductsDTOS;
  }

  @Override
  public Collection<Product> getFavorites(Principal principal) {
//    Favorites FavProductsss = favoritesRepository.findByUser(appUserRepository.findByUsername("sirat"));
//    AppUser appUser= (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    String name = principal.getName();
    AppUser appUser=  accountService.loadUserByUsername(principal.getName());
    Collection<Product> favProd = appUser.getFavProd();
//    Favorites FavProducts = favoritesRepository.findByUser(appUser);

    return favProd;
  }

  @Override
  public ProductDTO addProductToFavorites(FavProductDTO favProductDTO, Principal principal ) {
    log.info("Adding new Product to Favorites");
//    Product product = productMapper.fromProductDTO(productDTO);
    Product product = productRepository.findProductById(favProductDTO.getId());
    AppUser appUser=  accountService.loadUserByUsername(principal.getName());
    appUser.getFavProd().add(product);
    return productMapper.fromProduct(product);
  }

  @Override
  public void deleteProductFromFavorites(Long productId, Principal principal) {
    log.info("Deleting Product from Favorites");
    Product product = productRepository.findProductById(productId);
    AppUser appUser=  accountService.loadUserByUsername(principal.getName());
    appUser.getFavProd().remove(product);
  }


}
