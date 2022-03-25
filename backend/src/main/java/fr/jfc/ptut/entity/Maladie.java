package fr.jfc.ptut.entity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.jfc.ptut.enume.Cim;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/

@Entity // Une entité JPA
public class Maladie {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
     
    private Integer id;
   @NonNull
    private String nom_maladie;
    @NonNull
    private String symptomes;
    @NonNull
    private String description;
    @NonNull
    private String facteur_aggravant;

    @NonNull
    @Enumerated(EnumType.ORDINAL)
      private Cim cim_10;

}
