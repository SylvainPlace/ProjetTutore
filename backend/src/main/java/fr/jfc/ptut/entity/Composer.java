package fr.jfc.ptut.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Getter
@Setter
@ToString
@Entity // Une entité JPA
public class Composer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(mappedBy = "corps")
    @NonNull
    // On ne veut pas inclure la liste des villes dans le toString
    @ToString.Exclude
    // Sinon récursivité infinie
    private Corps nomPartie;
    /*
     * @OneToOne(mappedBy = "corps")
     * private Corps nom_partie_est_composee;
     */
}
