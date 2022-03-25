package fr.jfc.ptut.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

public class Toucher {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    @NonNull
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({ "maladies" })
    private Maladie maladie;

    @NonNull
    @ManyToOne(optional = false)
    @JsonIgnoreProperties({ "corps" })
    private Corps corps;
    
}
