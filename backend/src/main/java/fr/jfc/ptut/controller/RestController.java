package fr.jfc.ptut.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import fr.jfc.ptut.dao.CityRepository;
import fr.jfc.ptut.dao.CountryRepository;
import fr.jfc.ptut.dto.CityForm;
import fr.jfc.ptut.dto.PopulationResult;
import fr.jfc.ptut.entity.City;
import fr.jfc.ptut.entity.Country;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/api") // This means URL's start with /rest (after Application path)
@Slf4j
public class RestController {

	@Autowired
	private CityRepository cityDao;

	@Autowired
	private CountryRepository countryDao;


	/**
	 * Enregistre une ville dans la base
	 * Requête HTTP POST à l'URL http://localhost:8989/rest/saveCity	
	 * @param laVille la ville à enregistrer, initialisée par Spring à partir des
	 *                valeurs transmises dans la requête POST
	 *                Spring fera une requête SQL INSERT ou UPDATE pour enregistrer
	 *                la ville dans la base
	 * @return la ville enregistrée (avec sa clé) en format JSON
	 */
	@PostMapping(path = "saveCity") 
	public @ResponseBody City enregistreUneVille(@RequestBody CityForm formData) {
		log.info("Reçu: {}", formData);
		Country c = countryDao.findById(formData.getCountry())
		   .orElseThrow(() -> new IllegalArgumentException("Pays inconnu"));
		City laVille = new City(formData.getName(), c);
		laVille.setPopulation(formData.getPopulation());
		cityDao.save(laVille);
		log.info("Enregistré: {}", laVille);
		return laVille;
	}

	/**
	 * Enregistre un pays dans la base
	 * Requête HTTP POST à l'URL http://localhost:8989/rest/saveCountry	
	 * @param lePays  le pays à enregistrer, initialisée par Spring à partir des
	 *                valeurs  transmises dans la requête POST
	 *                Spring fera une requête SQL INSERT ou UPDATE pour enregistrer
	 *                le pays dans la base
	 * @return le pays enregistré (avec sa clé) en format JSON
	 */
	@PostMapping(path = "saveCountry") 
	public @ResponseBody Country enregistreUnPays(@RequestBody Country lePays) {
		log.info("Reçu: {}", lePays);
		countryDao.save(lePays);
		log.info("Enregistré: {}", lePays);
		return lePays;
	}

	@GetMapping(path = "population") 
	public @ResponseBody List<PopulationResult> populationParPays() {
		log.info("Population pour chaque les pays");
		return countryDao.populationParPaysJPQL();
	}

	@GetMapping(path = "allCities") 
	public @ResponseBody List<City> allCities() {
		log.info("Renvoie la liste des villes");
		return cityDao.findAll();
	}

	@GetMapping(path = "allCountries") 
	public @ResponseBody List<Country> allCountries() {
		log.info("Renvoie la liste des pays");
		return countryDao.findAll();
	}


}
