package fr.jfc.ptut.dto;

import java.util.Date;

import lombok.Data;

/**
 * Un "DTO" (Data Transfer Object) pour représenter 
 * un soigner.
 */
@Data // Lombok pour auto-générer getter / setter / toString...
public class SoignerForm {
    // datecreation: date,
	// doseparprise: qte,
	// maladie: maladie,
	// medicament: medic,
	// uniteduree: unitduree,
	// unitefreq: unitfreq,
	// utilisateur: utilisateurs,
	// valduree: duree,
	// valfreq: freq,
    private Date datecreation;
    private String maladie;
    private String medicament;
    private String utilisateur;
    private String valduree;
    private String valfreq;
    private int doseparprise;
    private int unitduree;
    private int unitfreq;
}
