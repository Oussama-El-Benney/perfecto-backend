package org.sid.pricecomparisonbackend.secrservice.repo;


import org.sid.pricecomparisonbackend.secrservice.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
  AppUser findByUsername(String username);

  @Query("select p from AppUser p where p.username = ?1")
   boolean findExistByname(String name);

  boolean existsAppUserByUsername(String username);
}
