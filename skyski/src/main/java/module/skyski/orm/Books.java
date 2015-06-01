package module.skyski.orm;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name="Books.findAll", query="SELECT b FROM Books b")
@Table(name="books")
public class Books implements Serializable
{
	public Books() {}

	public Integer getId() { return this.id; }
	public void setId(Integer _id) {	this.id = _id; }
	public Integer getEdition() { return this.edition; }
	public void setEdition(Integer _edition) { this.edition = _edition; }
	public String getTitle() { return this.title; }
	public void setTitle(String _title) { this.title = _title; }
	public Integer getVolume() { return this.volume; }
	public void setVolume(Integer _volume) { this.volume = _volume; }
	public String getPlaceOfPublication() {	return this.placeOfPublication;	}
	public void setPlaceOfPublication(String _placeOfPublication) { this.placeOfPublication = _placeOfPublication; }
	public Integer getYearOfPublication() {	return this.yearOfPublication; }
	public void setYearOfPublication(Integer _yearOfPublication) { this.yearOfPublication = _yearOfPublication; }
	public List<Authors> getAuthors() { return this.authors; }
	public void setAuthors(List<Authors> _authors) { this.authors = _authors;	}
	
	@Column(name="id") @Id private Integer id;
	@Column(name="edition") private Integer edition;
	@Column(name="place_of_publication") private String placeOfPublication;
	@Column(name="title") private String title;
	@Column(name="volume") private Integer volume;
	@Column(name="year_of_publication") private Integer yearOfPublication;
	@ManyToMany(mappedBy="books") private List<Authors> authors;
	private static final long serialVersionUID = 1L;
}