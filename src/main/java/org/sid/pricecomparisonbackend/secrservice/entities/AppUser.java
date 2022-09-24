package org.sid.pricecomparisonbackend.secrservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.sid.pricecomparisonbackend.entities.Favorites;
import org.sid.pricecomparisonbackend.entities.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String username;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;
  @ManyToMany(fetch = FetchType.EAGER)
  private Collection<AppRole> appRoles=new ArrayList<>();


  private String email;
  private String bio;
  private String avatar;
  @OneToMany()
  private Collection<Product> favProd = new ArrayList<>();

//  @OneToOne(fetch = FetchType.LAZY)
//  private Favorites favorites;

  public AppUser(long user_id, String username, String password,ArrayList<AppRole> roles) {
    this.id = user_id;
    this.username = username;
    this.password = password;
    this.appRoles = roles;
  }

}
