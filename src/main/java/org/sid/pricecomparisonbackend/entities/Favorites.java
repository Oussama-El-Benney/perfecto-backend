package org.sid.pricecomparisonbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.pricecomparisonbackend.secrservice.entities.AppUser;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorites {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;

//  @OneToOne
//  private Client client;
//
//  @OneToOne(mappedBy = "favorites")
//  private AppUser appUser;
//
//  @OneToMany(mappedBy = "favoriteList",fetch = FetchType.EAGER)
//  private List<Product> favoriteProducts=new ArrayList<>();
}
