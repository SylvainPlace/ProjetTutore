package fr.jfc.ptut.dto;

import lombok.Data;

/**
 * Un "DTO" (Data Transfer Object) pour représenter 
 * une ville.
 */
@Data // Lombok pour auto-générer getter / setter / toString...
public class CityForm {
    private String name;
    private int population;
    private Integer country;
}
