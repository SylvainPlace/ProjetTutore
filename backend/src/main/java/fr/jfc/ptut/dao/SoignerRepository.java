package fr.jfc.ptut.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import fr.jfc.ptut.entity.Soigner;
import fr.jfc.ptut.dto.SoignerDetailsResult;

public interface SoignerRepository extends JpaRepository<Soigner, Integer> {
    // JPQL : formulée sur le modèle conceptuel de données
    @Query("SELECT s.maladie.nom_maladie AS nomMaladie, s.medicament.nom_medic AS nomMedicament FROM Soigner s WHERE s.utilisateur.id = :idDePatient")
    List<SoignerDetailsResult> soignerDetails(int idDePatient);

}
