package module.skyski.dto;

import java.io.Serializable;

public class BibliographyDTO implements Serializable
{
	public BibliographyDTO(
		String[] _forename, String[] _surname, String _title,
		String _volume, String _edition, String _placeOfPublication, String _yearOfPublication
	)
	{
			this.forename = _forename;
			this.surname = _surname;
			this.title = _title;
			this.volume = _volume;
			this.edition = _edition;
			this.placeOfPublication = _placeOfPublication;
			this.yearOfPublication = _yearOfPublication;
	}
	
	public String[] getForename() { return this.forename; }
	public void setForename(String[] _forename) { this.forename = _forename; }
	public String[] getSurname() { return this.surname; }
	public void setSurname(String[] _surname) { this.surname = _surname; }
	public String getTitle() { return this.title; }
	public void setTitle(String _title) { this.title = _title; }
	public String getVolume() {	return this.volume; }
	public void setVolume(String _volume) { this.volume = _volume; }
	public String getEdition() { return this.edition; }
	public void setEdition(String _edition) { this.edition = _edition; }
	public String getPlaceOfPublication() {	return this.placeOfPublication; }
	public void setPlaceOfPublication(String _placeOfPublication) { this.placeOfPublication = _placeOfPublication; }
	public String getYearOfPublication() { return this.yearOfPublication; }
	public void setYearOfPublication(String _yearOfPublication) { this.yearOfPublication = _yearOfPublication; }

	private String[] forename;
	private String[] surname;
	private String title;
	private String volume;
	private String edition;
	private String yearOfPublication;
	private String placeOfPublication;
	private static final long serialVersionUID = 1L;
}