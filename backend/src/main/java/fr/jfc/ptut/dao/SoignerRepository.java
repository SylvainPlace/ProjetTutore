package fr.jfc.ptut.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import fr.jfc.ptut.entity.Soigner;
import fr.jfc.ptut.dto.InfirmiereSoignerDetailsResult;
import fr.jfc.ptut.dto.PatientDetailMedicaments;

public interface SoignerRepository extends JpaRepository<Soigner, Integer> {
    // JPQL : formulée sur le modèle conceptuel de données
    @Query("SELECT s.maladie.nom_maladie AS nomMaladie, s.medicament.nom_medic AS nomMedicament FROM Soigner s WHERE s.utilisateur.id = :idDePatient")
    List<InfirmiereSoignerDetailsResult> InfirmierSoignerDetails(int idDePatient);

    // JPQL : formulée sur le modèle conceptuel de données
    @Query("SELECT s.id AS id, s.medicament.nom_medic AS nomMedicament, s.medicament.info_prise AS infoPrises, s.medicament.contre_indications AS contreIndications, s.valduree AS valDuree, s.uniteduree AS uniteDuree, s.valfreq AS valFreq, s.unitefreq AS uniteFreq, s.doseparprise AS doseParPrise, s.datecreation AS dateCreation, s.maladie.nom_maladie AS nomMaladie FROM Soigner s WHERE s.utilisateur.id = :idDePatient")
    List<PatientDetailMedicaments> PatientSoignerDetails(int idDePatient);

}
