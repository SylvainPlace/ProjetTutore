package fr.jfc.ptut.dto;

import java.util.Date;

import lombok.Data;

/**
 * Un "DTO" (Data Transfer Object) pour représenter 
 * un soigner.
 */
@Data // Lombok pour auto-générer getter / setter / toString...
public class SoignerForm {
    private Date datecreation;
    private String maladie;
    private String medicament;
    private String utilisateur;
    private String unitduree;
    private String unitfreq;
    private double doseparprise;
    private int valduree;
    private int valfreq;
}
