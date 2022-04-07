package fr.jfc.ptut.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.jfc.ptut.enume.*;
import java.lang.Enum;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "soigner")
public class Soigner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medicament_id")
    @JsonManagedReference
    private Medicament medicament;

    @ManyToOne(optional = false)
    @JsonIgnoreProperties({ "soigners" })
    private Utilisateur utilisateur;

    @ManyToOne(optional = false)
    private Maladie maladie;

    private int valduree;
    @Enumerated(EnumType.ORDINAL) // STRING, ORDINAL
    private UniteDuree uniteduree;

    private int valfreq;
    @Enumerated(EnumType.ORDINAL) // STRING, ORDINAL
    private UniteFreq unitefreq;

    private int doseparprise;

    @Temporal(TemporalType.DATE)
    private Date datecreation;

}
