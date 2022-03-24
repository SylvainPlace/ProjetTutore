package fr.jfc.ptut.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
<<<<<<< HEAD:backend/src/main/java/fr/jfc/ptut/entity/Maladie.java
import javax.persistence.OneToOne;
=======
>>>>>>> Oceane:backend/src/main/java/fr/jfc/ptut/entity/Medicament.java

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
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
<<<<<<< HEAD:backend/src/main/java/fr/jfc/ptut/entity/Maladie.java
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
    

}
=======
public class Medicament {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    @NonNull
    private String nom_medic;
    @NonNull
    private String info_prise;
    private String contre_indications;
}



    
>>>>>>> Oceane:backend/src/main/java/fr/jfc/ptut/entity/Medicament.java
