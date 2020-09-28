package dev.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Collegue;
import dev.repository.CollegueRepository;


@Service
public class CollegueService {

	private CollegueRepository colRepo;
	
	public CollegueService(CollegueRepository colRepo) {
		this.colRepo=colRepo;
	}
	
	
	/**
	 * 
	 * @return une list d'objet  de tout les collegues
	 */
	public List<Collegue> getList() {
		return colRepo.findAll();
	}
	/**
	 * 
	 * @param id identifiant du collegue recherché
	 * @return un objet Collegue correspondant au collegue
	 */
	public Optional<Collegue> getById(Integer id) {
		return colRepo.findById(id);
	}
	
	
	
	/**	
	 * 
	 * @param matricule le matricule recherché
	 * @return un objet Collegue correspondant au matricule
	 */
	public Optional<Collegue> getByMatricule(String matricule) {
		return colRepo.findByMatricule(matricule);
	}
	
	/**	
	 * 
	 * @param nom le nom recherché
	 * @return une liste d'objet Collegue correspondant au nom
	 */
	public List<Collegue> getByNom(String nom){
	return colRepo.findAllBynom(nom);
	}
	
	/**
	 * 
	 * @param nom le nom recherché
	 * @return une liste de string de tous les matricules correspondant au nom
	 */
	public List<String> getAllMatricules() {
		return colRepo.findMatricule();
	}

	/**
	 * 
	 * @param id l'identifiant rechercher
	 * @return un optional contenant le matricule correspondant a l'id
	 */
	public Optional<String> getMatriculesById(Integer id) {
		return colRepo.findMatriculeById(id);
	}
	
	/**
	 * 
	 * @param nom
	 * @return un optional contenant le matricule correspondant a un nom
	 */
	public Optional<String> getMatriculesByNom(String nom) {
		return colRepo.findMatriculeByNom(nom);
	}
		
	/**
	 * 
	 * @param matricule le matricule du collegue à ajouter
	 * @param nom le nom du collegue à ajouter
	 * @param prenom le prenom du collegue à ajouter
	 * @param email l'email du collegue à ajouter
	 * @param dateNaissance la date de naissance du collegue à ajouter (format sql)
	 * @param photoUrl l'url de l'image du collegue à ajouter
	 * @return un objet collegue.
	 */

	@Transactional
	public Collegue creerCollegue(String nom, String prenom, LocalDate dateNaissance, String email, String photoUrl) {
		Collegue collegue = new Collegue(nom, prenom, dateNaissance, email, photoUrl);

		return colRepo.save(collegue);
	}

	
	
	@Transactional
	public Collegue updateCollegue(String matricule, String email, String urlPhoto) {
		colRepo.update(matricule, email, urlPhoto);
		return colRepo.findByMatricule(matricule)
				.orElseThrow(() -> new RuntimeException("erreur : actualisation Collegue"));
	}

	/**
	 * 
	 * @param id id du collegue a supprimer
	 * @return un boolean
	 */
	public String remUser(Integer id) {
		
		Optional<Collegue> collegueToRemove=this.getById(id);
		if(collegueToRemove.isPresent()) {
			colRepo.delete(collegueToRemove.get());
			return "Suppression reussie";
		}
		return "id non trouvé";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
