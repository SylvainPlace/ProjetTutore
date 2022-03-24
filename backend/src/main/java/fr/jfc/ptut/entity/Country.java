package fr.jfc.ptut.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

// Un exemple d'entité
// On utilise Lombok pour auto-générer getter / setter / toString...
// cf. https://examples.javacodegeeks.com/spring-boot-with-lombok/
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString
@Entity // Une entité JPA
public class Country {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    @Column(unique=true)
    @NonNull
    private String code;   
    @Column(unique=true)
    @NonNull
    private String name;
    @OneToMany(mappedBy="country")
    // On ne veut pas inclure la liste des villes dans le toString
    @ToString.Exclude 
    // On ne veut pas inclure la liste des villes dans le JSON
    // @JsonIgnore 
    // On ne veut pas inclure le pays dans la liste des villes
    @JsonIgnoreProperties({ "country" }) 
    // Sinon récursivité infinie    
    private List<City> cities = new ArrayList<>();
}
