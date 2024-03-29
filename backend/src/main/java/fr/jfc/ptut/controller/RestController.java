package fr.jfc.ptut.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;

import lombok.extern.slf4j.Slf4j;

import fr.jfc.ptut.dao.MaladieRepository;
import fr.jfc.ptut.dao.MedicamentRepository;
import fr.jfc.ptut.entity.Maladie;
import fr.jfc.ptut.entity.Medicament;
import fr.jfc.ptut.dao.SoignerRepository;
import fr.jfc.ptut.dao.UtilisateurRepository;
import fr.jfc.ptut.dto.PopulationResult;
import fr.jfc.ptut.dto.SoignerForm;
import fr.jfc.ptut.dto.InfirmiereSoignerDetailsResult;
import fr.jfc.ptut.dto.PatientDetailMedicaments;
import fr.jfc.ptut.dto.IdNomDTO;
import fr.jfc.ptut.entity.Soigner;
import fr.jfc.ptut.entity.Utilisateur;
import fr.jfc.ptut.enume.Categorie;
import fr.jfc.ptut.enume.Cim;
import fr.jfc.ptut.enume.UniteDuree;
import fr.jfc.ptut.enume.UniteFreq;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/api") // This means URL's start with /rest (after Application path)
@Slf4j
public class RestController {

	@Autowired
	private MaladieRepository maladieDao;

	@Autowired
	private MedicamentRepository medicamentDao;

	@Autowired
	private SoignerRepository soignerDao;

	@Autowired
	private UtilisateurRepository utilisateurDao;

	@GetMapping(path = "InfirmierSoignerMedicament/{id}")
	public @ResponseBody List<InfirmiereSoignerDetailsResult> InfirmierSoignerDetails(@PathVariable int id) {
		log.info("Soigner avec maladie et medicament");
		return soignerDao.InfirmierSoignerDetails(id);
	}

	@GetMapping(path = "PatientSoignerMedicament/{id}")
	public @ResponseBody List<PatientDetailMedicaments> PatientSoignerDetails(@PathVariable int id) {
		log.info("Soigner avec dose et medicament");
		return soignerDao.PatientSoignerDetails(id);
	}

	@GetMapping(path = "ListePatient/")
	public @ResponseBody List<Utilisateur> ListePatient() {
		log.info("Liste des patients");
		return utilisateurDao.ListePatient();
	}

	@GetMapping(path = "idEnNom")
	public @ResponseBody List<String> idEnNom(@PathVariable int idMedic) {
		List<String> noms = new ArrayList<>();
		List<Medicament> allMedicament = this.allMedicaments();
		for (Medicament m : allMedicament) {
			log.info(m.getNom_medic());
			if (m.getId() == idMedic) {
				noms.add(m.getNom_medic());
			}
		}

		return noms;
	}

	// datecreation: date,
	// doseparprise: qte,
	// maladie: maladie,
	// medicament: medic,
	// uniteduree: unitduree,
	// unitefreq: unitfreq,
	// utilisateur: utilisateurs,
	// valduree: duree,
	// valfreq: freq,

	@PostMapping(path = "saveSoigner")
	public @ResponseBody Soigner enregistreUneVille(@RequestBody SoignerForm formData) {
		log.info("Reçu: {}", formData);
		List<Medicament> allMedicament = medicamentDao.findAll();
		List<Maladie> allMaladie = maladieDao.findAll();
		List<Utilisateur> allUtilisateur = utilisateurDao.findAll();
		Medicament medicament = null;
		Maladie maladie = null;
		Utilisateur utilisateur = null;
		String nom = formData.getUtilisateur().substring(0, formData.getUtilisateur().indexOf(" "));
		log.info(nom);
		String prenom = formData.getUtilisateur().substring(formData.getUtilisateur().indexOf(" ") + 1,
				formData.getUtilisateur().length());
		log.info(prenom);
		UniteDuree uniteDuree = null;
		UniteFreq uniteFreq = null;
		for (Medicament m : allMedicament) {
			if (m.getNom_medic().equals(formData.getMedicament())) {
				medicament = m;
				break;
			}
		}
		for (Maladie m : allMaladie) {
			if (m.getNom_maladie().equals(formData.getMaladie())) {
				maladie = m;
				break;
			}
		}
		for (Utilisateur u : allUtilisateur) {
			if (u.getNom().equals(nom)) {
				if (u.getPrenom().equals(prenom)) {
					utilisateur = u;
				}
			}
		}
		for (UniteDuree d : UniteDuree.values()) {
			if (d.toString().equals(formData.getUnitduree())) {
				uniteDuree = d;
				break;
			}
		}
		log.info("unit: {}", formData.getUnitfreq());
		for (UniteFreq f : UniteFreq.values()) {
			if (f.toString().equals(formData.getUnitfreq())) {
				uniteFreq = f;
				break;
			}
		}
		log.info("Enregistré: {}", formData.getDoseparprise());
		Soigner soigner = new Soigner();
		soigner.setDatecreation(formData.getDatecreation());
		soigner.setDoseparprise(formData.getDoseparprise());
		soigner.setMaladie(maladie);
		soigner.setMedicament(medicament);
		soigner.setUniteduree(uniteDuree);
		soigner.setUnitefreq(uniteFreq);
		soigner.setUtilisateur(utilisateur);
		soigner.setValduree(formData.getValduree());
		soigner.setValfreq(formData.getValfreq());
		soignerDao.save(soigner);
		log.info("Enregistré: {}", soigner);
		return soigner;
	}

	/**
	 * Enregistre une ville dans la base
	 * Requête HTTP POST à l'URL http://localhost:8989/rest/saveCity
	 * 
	 * @param laVille la ville à enregistrer, initialisée par Spring à partir des
	 *                valeurs transmises dans la requête POST
	 *                Spring fera une requête SQL INSERT ou UPDATE pour enregistrer
	 *                la ville dans la base
	 * @return la ville enregistrée (avec sa clé) en format JSON
	 */
	/*
	 * @PostMapping(path = "saveCity")
	 * public @ResponseBody City enregistreUneVille(@RequestBody CityForm formData)
	 * {
	 * log.info("Reçu: {}", formData);
	 * Country c = countryDao.findById(formData.getCountry())
	 * .orElseThrow(() -> new IllegalArgumentException("Pays inconnu"));
	 * City laVille = new City(formData.getName(), c);
	 * laVille.setPopulation(formData.getPopulation());
	 * cityDao.save(laVille);
	 * log.info("Enregistré: {}", laVille);
	 * return laVille;
	 * }
	 * 
	 * /**
	 * Enregistre un pays dans la base
	 * Requête HTTP POST à l'URL http://localhost:8989/rest/saveCountry
	 * 
	 * @param lePays le pays à enregistrer, initialisée par Spring à partir des
	 * valeurs transmises dans la requête POST
	 * Spring fera une requête SQL INSERT ou UPDATE pour enregistrer
	 * le pays dans la base
	 * 
	 * @return le pays enregistré (avec sa clé) en format JSON
	 * 
	 * @PostMapping(path = "saveCountry")
	 * public @ResponseBody Country enregistreUnPays(@RequestBody Country lePays) {
	 * log.info("Reçu: {}", lePays);
	 * countryDao.save(lePays);
	 * log.info("Enregistré: {}", lePays);
	 * return lePays;
	 * }
	 */

	@GetMapping(path = "medicamentsByName")
	public @ResponseBody List<Medicament> medicamentsByName(String mot) {
		List<Medicament> allMedicament = this.allMedicaments();
		List<Medicament> searchMedicament = new ArrayList<>();
		for (Medicament m : allMedicament) {
			log.info(m.getNom_medic());
			if (m.getNom_medic().toLowerCase().contains(mot.toLowerCase())) {
				searchMedicament.add(m);
			}
		}
		log.info(searchMedicament.toString());

		return searchMedicament;
	}

	@GetMapping(path = "allMedicaments")
	public @ResponseBody List<Medicament> allMedicaments() {
		log.info("Renvoie la liste des medicaments");
		return medicamentDao.findAll();
	}

	@GetMapping(path = "allCategorie")
	public @ResponseBody List<Categorie> allCategorie() {
		log.info("Renvoie la liste des categorie");
		List<Categorie> lesCategories = new ArrayList<>();
		for (Categorie c : Categorie.values()) {
			lesCategories.add(c);
		}
		return lesCategories;
	}

	@GetMapping(path = "allCim")
	public @ResponseBody List<Cim> allCim() {
		log.info("Renvoie la liste des cim");
		List<Cim> lesCims = new ArrayList<>();
		for (Cim c : Cim.values()) {
			lesCims.add(c);
		}
		return lesCims;
	}

	@GetMapping(path = "allUniteDuree")
	public @ResponseBody List<UniteDuree> allUniteDuree() {
		log.info("Renvoie la liste des durees");
		List<UniteDuree> lesUniteDuree = new ArrayList<>();
		for (UniteDuree c : UniteDuree.values()) {
			lesUniteDuree.add(c);
		}
		return lesUniteDuree;
	}

	@GetMapping(path = "allUniteFreq")
	public @ResponseBody List<UniteFreq> allUniteFreq() {
		log.info("Renvoie la liste des fréquences");
		List<UniteFreq> lesUniteFreq = new ArrayList<>();
		for (UniteFreq c : UniteFreq.values()) {
			lesUniteFreq.add(c);
		}
		return lesUniteFreq;
	}

	/*
	 * @GetMapping(path = "allCountries")
	 * public @ResponseBody List<Country> allCountries() {
	 * log.info("Renvoie la liste des pays");
	 * return countryDao.findAll();
	 * }
	 */

}
