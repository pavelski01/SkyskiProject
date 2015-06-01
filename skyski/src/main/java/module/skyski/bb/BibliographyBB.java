package module.skyski.bb;

import module.skyski.dao.BibliographyDAO;
import module.skyski.dcr.SortDataModel;
import module.skyski.dto.BibliographyDTO;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.ArrayDataModel;
import javax.faces.model.DataModel;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

@Named("bibliography")
@SessionScoped
public class BibliographyBB implements Serializable
{
	public BibliographyBB()
	{
		this.authorsASC = false;
		this.titlesASC = true;
		this.publicationASC = false;
	}
	
	@PostConstruct private void initialize()
	{
		this.filterModel = new SortDataModel<BibliographyDTO>(
			new ArrayDataModel<BibliographyDTO>(this.bibliographyList.getArray())
		);
	}
	
	public DataModel<BibliographyDTO> getFilterModel() { return this.filterModel; }
	
	public String sortByAuthors()
	{
		this.authorsASC = !this.authorsASC;
		this.publicationASC = false;
		this.titlesASC = false;
		this.filterModel.sortBy(new Comparator<BibliographyDTO>() {
			@Override public int compare(BibliographyDTO __b1, BibliographyDTO __b2)
			{
				if (authorsASC)
					return (Arrays.toString(__b1.getSurname()).compareTo(Arrays.toString(__b2.getSurname())) == 0) ?
							__b1.getTitle().compareTo(__b2.getTitle()) :
								Arrays.toString(__b1.getSurname()).compareTo(Arrays.toString(__b2.getSurname()));
				else
					return (Arrays.toString(__b2.getSurname()).compareTo(Arrays.toString(__b1.getSurname())) == 0) ?
						__b2.getTitle().compareTo(__b1.getTitle()) :
							Arrays.toString(__b2.getSurname()).compareTo(Arrays.toString(__b1.getSurname()));
			}
		});
		return null;
	}
	
	public String sortByTitles()
	{
		this.authorsASC = false;
		this.publicationASC = false;
		this.titlesASC = !this.titlesASC;
		this.filterModel.sortBy(new Comparator<BibliographyDTO>() {
			@Override public int compare(BibliographyDTO __b1, BibliographyDTO __b2)
			{
				if (titlesASC)
					return (__b1.getTitle().compareTo(__b2.getTitle()) == 0) ?
						__b1.getVolume().compareTo(__b2.getVolume()) :
							__b1.getTitle().compareTo(__b2.getTitle());
				else
					return (__b2.getTitle().compareTo(__b1.getTitle()) == 0) ?
						__b2.getVolume().compareTo(__b1.getVolume()) :
							__b2.getTitle().compareTo(__b1.getTitle());
			}
		});
		return null;
	}
	
	public String sortByPublication()
	{
		this.authorsASC = false;
		this.publicationASC = !this.publicationASC;
		this.titlesASC = false;
		this.filterModel.sortBy(new Comparator<BibliographyDTO>() {
			@Override public int compare(BibliographyDTO __b1, BibliographyDTO __b2)
			{
				if (publicationASC)
					return (__b1.getYearOfPublication().compareTo(__b2.getYearOfPublication()) == 0) ?
						__b1.getPlaceOfPublication().compareTo(__b2.getPlaceOfPublication()) :
							__b1.getYearOfPublication().compareTo(__b2.getYearOfPublication());
				else
					return (__b2.getYearOfPublication().compareTo(__b1.getYearOfPublication()) == 0) ?
						__b2.getPlaceOfPublication().compareTo(__b1.getPlaceOfPublication()) :
							__b2.getYearOfPublication().compareTo(__b1.getYearOfPublication());
			}
		});
		return null;
	}
	
	@EJB private BibliographyDAO bibliographyList;
	private SortDataModel<BibliographyDTO> filterModel;
	private boolean authorsASC;
	private boolean titlesASC;
	private boolean publicationASC;
	private static final long serialVersionUID = 1L;
}