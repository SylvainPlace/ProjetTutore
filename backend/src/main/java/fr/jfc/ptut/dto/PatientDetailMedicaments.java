package fr.jfc.ptut.dto;

/**
 * Un "DTO" (Data Transfer Object) pour représenter 
 * le résultat d'une requête SQL.
 * Cette interface sera "automatiquement" implémentée par Spring.
 * @see monprojet.dao.SoignerRepository
 */
public interface PatientDetailMedicaments {
    int getId();
    String getNomMedicament();
    String getInfoPrises();
    String getContreIndications();
    int getValDuree();
    String getUniteDuree();
    int getValFreq();
    String getUniteFreq();
    int getDoseParPrise();
    String getNomMaladie();
    
}
