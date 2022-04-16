package fr.jfc.ptut.dto;

import lombok.Data;

/**
 * Un "DTO" (Data Transfer Object) pour représenter 
 * une ville.
 */
@Data // Lombok pour auto-générer getter / setter / toString...
public class IdNomDTO {
    private String nomMadicament;
    private String nomMaladie;
}
