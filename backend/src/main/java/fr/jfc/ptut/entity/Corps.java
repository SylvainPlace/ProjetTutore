package fr.jfc.ptut.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
@Getter @Setter @NoArgsConstructor @ToString
@Entity // Une entité JPA
public class Corps {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    @NonNull
    private String nom_partie;
    /*
    @OneToOne(optional = false)
    @NonNull
    private Composer nom_partie_compose;
    @NonNull
    private Composer nom_partie_est_composee;
    */

}
