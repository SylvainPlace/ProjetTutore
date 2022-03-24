package fr.jfc.ptut.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.jfc.ptut.dto.PopulationResult;
import fr.jfc.ptut.entity.City;
import fr.jfc.ptut.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 
//

public interface CountryRepository extends JpaRepository<Country, Integer> {

    /**
     * Calculer la population totale d'un pays.
     * @param idDuPays l'identifiant du pays
     * @return sa population totale (en millions d'habitants)
     */
    // On peut mettre des "méthodes par défaut" dans les interfaces.
    default int populationDuPaysJava(int idDuPays) {
        int resultat = 0;
        Country country = findById(idDuPays).orElseThrow();
        for (City c : country.getCities()) {
            resultat += c.getPopulation();
        }
        // Ou alors, en une seule ligne :
        // resultat = country.getCities().stream().mapToInt(City::getPopulation).sum();
        return resultat;
    }

    // JPQL : formulée sur le modèle conceptuel de données
    @Query("SELECT SUM(c.population) FROM City c WHERE c.country.id = :idDuPays")
    int populationDuPaysJPQL(int idDuPays);

    // SQL : formulée sur le modèle logique de données, il faut connaître la clé étrangère
    @Query(value = "SELECT SUM(c.population) FROM City c WHERE c.country_id = :idDuPays", 
    nativeQuery = true)
    int populationDuPaysSQL(int idDuPays);

    // JPQL : formulée sur le modèle conceptuel de données, la jointure est implicite
    @Query("SELECT c.country.name AS countryName, SUM(c.population) AS populationTotale FROM City c GROUP BY countryName")
    List<PopulationResult> populationParPaysJPQL();

    // SQL : formulée sur le modèle logique de données, il faut expliciter la jointure
    @Query(value = "SELECT Country.name AS countryName, SUM(population) AS populationTotale FROM Country INNER JOIN City ON country_id = Country.id GROUP BY countryName", 
    nativeQuery = true)
    List<PopulationResult> populationParPaysSQL();

}
