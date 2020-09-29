package dev.web.collegue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import dev.dto.CollegueDto;
import dev.entite.Collegue;
import dev.services.CollegueService;

import static java.util.stream.Collectors.*;



@CrossOrigin
@RestController
@RequestMapping("collegues") // ressource "collegues"
public class CollegueCtrl {

	private CollegueService colServ;

	public CollegueCtrl(CollegueService colServ) {
		this.colServ = colServ;
	}



	@GetMapping
	public List<String> listCollegue(@RequestParam String nom) {
		return colServ.getByNom(nom)
				.stream()
				.map(Collegue::getMatricule)
				.collect(toList());

	}

	/**
	 * url : [SERVER]/collegue/getMatricule?nom={nom}
	 * @return un json de tous les matricules en bdd 
	 */
	@GetMapping("{matricule}")
	public ResponseEntity<?> findByMatricule(@PathVariable String matricule) {
		Optional<Collegue> byMatricule = colServ.getByMatricule(matricule);

		if(byMatricule.isPresent()) {
			return ResponseEntity.ok(byMatricule.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	/**
	 * url : [SERVER]/gallerie
	 * @return un tableau de string
	 */
	@GetMapping("photos")
	public List<PhotoResponseDto> listPhoto() {
		List<PhotoResponseDto> res = new ArrayList<>();
		for (Collegue col: colServ.getList()) {
			PhotoResponseDto photoresponseDto = new PhotoResponseDto(col.getMatricule(), col.getPhotoUrl());
			res.add(photoresponseDto);
		}
		return res;
	}

	
	
	/**
	 * url : [SERVER]/collegue
	 * @param collegueDto un objet collegueDto au format json
	 * @return un objet collegueDto au format json
	 */

	@PostMapping
	public ResponseEntity<?> newCollegue(@RequestBody @Valid ColleguerequestDto colreq, BindingResult resValid) {

		if (!resValid.hasErrors()) {
			Collegue collegue = colServ.creerCollegue(colreq.getNom(), colreq.getPrenoms(),
					colreq.getDateNaissance(), colreq.getEmail(), colreq.getPhotoUrl());

			return ResponseEntity.ok(collegue);
		} else {
			return ResponseEntity.badRequest().body("tous les champs sont obligatoires !");
		}

	}
	

	
	@PatchMapping("/{matricule}")
	public ResponseEntity<?> editUser(@PathVariable String matricule,
			@RequestBody @Valid CollegueRequestDtoPatch collegueDto, BindingResult resValid) {

		Collegue editCollegue = colServ.updateCollegue(matricule, collegueDto.getEmail(),
				collegueDto.getPhotoUrl());
		return ResponseEntity.ok(editCollegue);
	}
	/**
	 * 
	 * @param id id du collegue a remove
	 * @return une String 
	 */
	@DeleteMapping
	public ResponseEntity<?> remUser(@RequestParam Integer id) {
		return ResponseEntity.ok(colServ.remUser(id));
		
	}
	
	
	
}
