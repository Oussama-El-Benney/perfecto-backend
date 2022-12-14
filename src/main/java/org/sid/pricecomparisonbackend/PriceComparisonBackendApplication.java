package org.sid.pricecomparisonbackend;

import lombok.extern.slf4j.Slf4j;
import org.sid.pricecomparisonbackend.entities.*;
import org.sid.pricecomparisonbackend.enums.CategoryNature;
import org.sid.pricecomparisonbackend.enums.PersonNature;
import org.sid.pricecomparisonbackend.mappers.ProductMapperImpl;
import org.sid.pricecomparisonbackend.repositories.*;
import org.sid.pricecomparisonbackend.secrservice.AuthEntryPointJwt;
import org.sid.pricecomparisonbackend.secrservice.repo.AppUserRepository;
import org.sid.pricecomparisonbackend.services.PriceComparisonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.sid.pricecomparisonbackend.secrservice.entities.AppRole;
import org.sid.pricecomparisonbackend.secrservice.entities.AppUser;
import org.sid.pricecomparisonbackend.secrservice.service.AccountService;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.Stream;

@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@SpringBootApplication
public class PriceComparisonBackendApplication {
  private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

  public static void main(String[] args) {
    SpringApplication.run(PriceComparisonBackendApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  @Bean
  CommandLineRunner start(PriceComparisonService priceComparisonService,
                          MagasinRepository magasinRepository,
                          ProductRepository productRepository,
                          FavoritesRepository favoritesRepository,
                          PersonRepository personRepository,
                          ProductMapperImpl productMapper,
                          AccountService accountService,
                          CategoryRepository categoryRepository,
                          AppUserRepository appUserRepository) {
    return args -> {
//authetication

//      accountService.addNewUser(new AppUser(6, "user1", "123", new ArrayList<>()));
//      accountService.addNewUser(new AppUser(7,"user1","123",new ArrayList<>()));
//      accountService.addNewUser(new AppUser(8,"user2","123",new ArrayList<>()));
//      accountService.addNewUser(new AppUser(9,"user3","123",new ArrayList<>()));
//      accountService.addNewUser(new AppUser(10,"user4","123",new ArrayList<>()));
//      accountService.addRoleToUser("user3","USER");
//      accountService.addRoleToUser("user4","ADMIN");
//      accountService.addRoleToUser("user36","CLIENT");
//      accountService.addNewUser(new AppUser(0,"user40","123",new ArrayList<>()));
//      accountService.addRoleToUser("user40","CLIENT");

//      accountService.addNewRole(new AppRole(1, "ADMIN"));
//      accountService.addNewRole(new AppRole(2, "CLIENT"));
//      accountService.addNewUser(new AppUser(0, "oussa", "123",new ArrayList<>()));
//      accountService.addRoleToUser("oussa","CLIENT");
//      accountService.addNewUser(new AppUser(0, "sirat", "123",new ArrayList<>()));
//      accountService.addRoleToUser("sirat","CLIENT");

//category
//      Stream.of("Smartphone", "Tablette", "Pc Portable","Headphone").forEach(name -> {
//        Category category = new Category();
//        category.setName(name);
//        categoryRepository.save(category);
//
//      });

      //db completion
//      Stream.of("Sirat", "Oussama", "Mohammed").forEach(name -> {
//        Client customer = new Client();
//        customer.setNature(PersonNature.CLIENT);
//        customer.setName(name);
//        customer.setUsername(name + "_kun");
//        customer.setEmail(name + "@gmail.com");
//        personRepository.save(customer);
//
//      });


//      Client client = new Client();
//      client.setNature(PersonNature.CLIENT);
//      client.setName("ohsama");
//      client.setUsername("ohsama" + "_kun");
//      client.setEmail("ohsama" + "@gmail.com");
//      client.setAdresse("maamoura");
//      client.setTelNumber(29004323);


      Favorites favorites = new Favorites();
      favorites.setId(2L);
//      favorites.setAppUser(appUserRepository.findByUsername("sirat"));
      favorites.setName("sirat favorites");
//      client.setFavorites(favorites);
//      favorites.setClient(client);

      Magasin magasin = new Magasin();
      magasin.setName("myTek");
      magasin.setId(2L);
      magasinRepository.save(magasin);


      MagasinProduct prod = new MagasinProduct();
      prod.setName("Iphone 11 Pro max plus");
      prod.setQuantity(5);
//      prod.setMagasin(magasin);
      prod.setRating(4);
      prod.setPrice(1500.51);
      prod.setCategoryNature(CategoryNature.Smartphone);
      prod.setImageSrc("https://thumbs.dreamstime.com/b/new-iphone-features-all-screen-design-99917162.jpg");
      productRepository.save(prod);


      MagasinProduct produ = new MagasinProduct();
      produ.setName("nokia 3310");
      produ.setQuantity(3);
      produ.setRating(4);
//      produ.setMagasin(magasin);
      produ.setPrice(150.2);
      produ.setImageSrc("https://upload.wikimedia.org/wikipedia/commons/7/78/Nokia_3310_Blue_R7309170_%28retouch%29.png");
      productRepository.save(produ);

//fav
      Product note11pro = new Product();
      note11pro.setName("Note 11 Pro 5g");
      note11pro.setQuantity(11);
      note11pro.setRating(5);
      note11pro.setImageSrc("https://fdn2.mobgsm.com/vv/pics/xiaomi/xiaomi-redmi-note-11-pro-5g-global-1.jpg");
      productRepository.save(note11pro);


//      note11pro.setFavoriteList(favorites);

      MagasinProduct produc = new MagasinProduct();
      produc.setName("Note 11 Pro 5g");
      produc.setQuantity(11);
      produc.setRating(5);
//      produc.setMagasin(magasin);
      produc.setPrice(1100);
      produc.setImageSrc("https://fdn2.mobgsm.com/vv/pics/xiaomi/xiaomi-redmi-note-11-pro-5g-global-1.jpg");
      productRepository.save(produc);

      Collection<Product> productList = new ArrayList<>();


      productList.add(note11pro);
      productList.add(produ);
      productList.add(prod);
      productList.add(produc);
      if(appUserRepository.findByUsername("sirat")!=null){
        appUserRepository.findByUsername("sirat").setFavProd(productList);
      }
      logger.info(productList.toString());
//      favorites.setFavoriteProducts(productList);
//      logger.info(favorites.getFavoriteProducts().toString());

//      personRepository.save(client);
      favoritesRepository.save(favorites);
//      priceComparisonService.magasinProductList().forEach(magasinProduct -> {
//
//      });
//      logger.info(favorites.getFavoriteProducts().toString());
    };
  }

  //  @Bean
  CommandLineRunner start(PersonRepository personRepository,
                          MagasinProductRepository magasinProductRepository,
                          ProductRepository productRepository,
                          MagasinRepository magasinRepository) {
    return args -> {
//      Stream.of("Sirat", "Oussama", "Mohamed").forEach(name -> {
//        Client customer = new Client();
//        customer.setNature(PersonNature.CLIENT);
//        customer.setName(name);
//        customer.setUsername(name + "_kun");
//        customer.setEmail(name + "@gmail.com");
//        personRepository.save(customer);
//      });
//
//      Stream.of("Sirat", "Oussama", "Mohamed").forEach(name -> {
//        Client client = new Client();
//        client.setNature(PersonNature.CLIENT);
//        client.setName(name);
//        client.setUsername(name + "-kun");
//        client.setEmail(name + "@gmail.com");
//        client.setTelNumber(29004323);
//        personRepository.save(client);
//      });
//
//      Admin admin = new Admin();
//      admin.setNature(PersonNature.ADMIN);
//      admin.setName("oussama");
//      admin.setUsername("Oussama-kun");
//      admin.setEmail("oussama@gmail.com");
//      admin.setConnectionDate(new Date(2001 - 1900, 0, 13));
//      personRepository.save(admin);
//
//      Magasin magasin = new Magasin();
//      magasin.setName("Jumia");
//      magasin.setId(1L);
//      magasinRepository.save(magasin);
//
//      personRepository.findByNature(PersonNature.CLIENT).forEach(client -> {
//        log.info(client.getName());
//        if (Objects.equals(client.getName(), "Sirat")) {
//          client.setAdresse("bousalem");
//        } else {
//          client.setAdresse("maamoura");
//        }
//
//        log.info(client.getAdresse());
//        personRepository.save(client);
//      });

    };
  }
}

