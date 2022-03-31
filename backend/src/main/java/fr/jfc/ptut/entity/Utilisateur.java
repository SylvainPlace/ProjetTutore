package fr.jfc.ptut.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import fr.jfc.ptut.entity.Soigner;

//4 import pour l'enum
import fr.jfc.ptut.enume.*;
import java.lang.Enum;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

import lombok.Getter; //génère du code, librairie lombok
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
@NoArgsConstructor
@RequiredArgsConstructor
@ToString // code facilité

@Entity // déclare une entité JPA
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String adresse_mail;

    private String prenom;

    private String nom;

    @NonNull
    private String mdp;

    private String date_de_naiss;

    @Enumerated(EnumType.ORDINAL)
    private Categorie categorie;

    @OneToMany(mappedBy = "utilisateur")
    // On ne veut pas inclure la liste des villes dans le toString
    @ToString.Exclude
    // On ne veut pas inclure la liste des villes dans le JSON
    // @JsonIgnore
    // On ne veut pas inclure le pays dans la liste des villes
    @JsonIgnoreProperties({ "utilisateur" })
    // Sinon récursivité infinie
    private List<Soigner> soigners = new ArrayList<>();
}
