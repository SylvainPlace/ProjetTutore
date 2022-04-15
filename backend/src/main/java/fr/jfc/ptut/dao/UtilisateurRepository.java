package fr.jfc.ptut.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.jfc.ptut.entity.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    // JPQL : formulée sur le modèle conceptuel de données
    @Query("SELECT u FROM Utilisateur u WHERE u.categorie = 0")
    List<Utilisateur> ListePatient();

}
