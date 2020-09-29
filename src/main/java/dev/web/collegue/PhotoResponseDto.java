package dev.web.collegue;

import com.sun.istack.NotNull;

public class PhotoResponseDto {
	
	@NotNull
	private String matricule;
	@NotNull
	private String photoUrl;
	/**
	 * @param matricule
	 * @param photoUrl
	 */
	public PhotoResponseDto(String matricule, String photoUrl) {
		super();
		this.matricule = matricule;
		this.photoUrl = photoUrl;
	}
	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}
	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	/**
	 * @return the photoUrl
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}
	/**
	 * @param photoUrl the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	

}
