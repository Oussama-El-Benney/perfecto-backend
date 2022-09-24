package org.sid.pricecomparisonbackend.repositories;

import org.sid.pricecomparisonbackend.entities.Client;
import org.sid.pricecomparisonbackend.entities.Favorites;
import org.sid.pricecomparisonbackend.entities.Magasin;
import org.sid.pricecomparisonbackend.secrservice.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FavoritesRepository extends JpaRepository<Favorites,Long> {


//  public Favorites findByUser(AppUser appUser);
}
