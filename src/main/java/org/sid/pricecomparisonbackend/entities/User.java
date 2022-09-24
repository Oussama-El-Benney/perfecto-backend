//package org.sid.pricecomparisonbackend.entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.sid.pricecomparisonbackend.secrservice.entities.AppRole;
//
//import javax.persistence.*;
//import java.util.List;
//import java.util.Set;
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//
//public class User {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private long id;
//  private String username;
//  private String fullName;
//  private String email;
//  private String bio;
//  private String avatar;
//  @ElementCollection
//  private List<String> roles;
//
////  @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
////  private Favorites favorites;
//}
