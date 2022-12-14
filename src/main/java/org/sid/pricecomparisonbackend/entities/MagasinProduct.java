package org.sid.pricecomparisonbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class MagasinProduct extends Product{
  private double price;
@ManyToOne (fetch = FetchType.EAGER)
  private Magasin magasin;

}
