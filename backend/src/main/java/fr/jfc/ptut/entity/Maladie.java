package fr.jfc.ptut.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
<<<<<<< HEAD:backend/src/main/java/fr/jfc/ptut/entity/Corps.java
import javax.persistence.OneToOne;
=======
>>>>>>> dev:backend/src/main/java/fr/jfc/ptut/entity/Maladie.java

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
<<<<<<< HEAD:backend/src/main/java/fr/jfc/ptut/entity/Corps.java
@Getter @Setter @NoArgsConstructor @ToString
@Entity // Une entité JPA
public class Corps {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    @NonNull
    private String nomPartie;
    /*
    @OneToOne(optional = false)
    @NonNull
    private Composer nom_partie_compose;
    @NonNull
    private Composer nom_partie_est_composee;
    */
=======
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
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
    private int cim_10;
    
>>>>>>> dev:backend/src/main/java/fr/jfc/ptut/entity/Maladie.java

}
