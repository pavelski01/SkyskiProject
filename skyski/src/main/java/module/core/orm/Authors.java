package module.core.orm;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name="Authors.findAll", query="SELECT a FROM Authors a")
@Table(name="authors")
public class Authors implements Serializable
{
	public Authors() {}

	public Integer getId() { return this.id; }
	public void setId(Integer _id) { this.id = _id; }
	public String getForename() { return this.forename;	}
	public void setForename(String _forename) { this.forename = _forename; }
	public String getSurname() { return this.surname; }
	public void setSurname(String _surname) { this.surname = _surname; }
	public List<Books> getBooks() {	return this.books; }
	public void setBooks(List<Books> _books) { this.books = _books; }
	
	@Column(name="id") @Id private Integer id;
	@Column(name="forename") private String forename;
	@Column(name="surname") private String surname;
	@JoinTable(
		name="authors_books",
		joinColumns={ @JoinColumn(name="author_id") },
		inverseJoinColumns={ @JoinColumn(name="book_id") }
	)
	@ManyToMany	private List<Books> books;
	private static final long serialVersionUID = 1L;
}