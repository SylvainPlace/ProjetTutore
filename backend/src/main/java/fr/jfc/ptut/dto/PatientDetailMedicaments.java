package fr.jfc.ptut.dto;

import java.util.Date;

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
    Date getdateCreation();
    String getNomMaladie();
    
}
