package fr.jfc.ptut.dto;

/**
 * Un "DTO" (Data Transfer Object) pour représenter 
 * le résultat d'une requête SQL.
 * Cette interface sera "automatiquement" implémentée par Spring.
 * @see monprojet.dao.SoignerRepository
 */
public interface InfirmiereSoignerDetailsResult {
    String getNomMedicament();
    String getNomMaladie();
}
