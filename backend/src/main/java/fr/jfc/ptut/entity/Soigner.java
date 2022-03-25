package fr.jfc.ptut.entity;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import fr.jfc.ptut.enume.*;
import java.lang.Enum;

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
@Getter @Setter@ToString
@Entity // Une entité JPA
public class Soigner {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;

    @ManyToOne(optional = false) 
    private Medicament medicament;
    @ManyToOne(optional = false) 
    private Utilisateur utilisateur;
    @NonNull
    @ManyToOne(optional = false) 
    private Maladie maladie;

    private int valduree;
    @Enumerated(EnumType.ORDINAL)
    private UniteDuree uniteduree;
    private int valfreq;
    @Enumerated(EnumType.ORDINAL)
    private UniteFreq unitefreq;
    private int doseparprise;

}
