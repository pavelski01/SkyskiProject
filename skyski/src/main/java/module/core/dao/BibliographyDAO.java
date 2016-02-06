package module.core.dao;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import module.core.dto.BibliographyDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Stateful public class BibliographyDAO implements Serializable
{
	public BibliographyDAO() {}
	
	@PostConstruct @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) private void initialize()
	{
		List<Object[]> resultList = this.em.createQuery(
			"SELECT a.forename, a.surname, b.title, b.volume, b.edition, b.placeOfPublication, b.yearOfPublication "
				+ "FROM Authors a JOIN a.books b ORDER BY b.title, b.volume, a.surname", Object[].class
		).getResultList();
		this.bibliographyList = new ArrayList<BibliographyDTO>();
		ArrayList<String> forename = new ArrayList<String>();
		ArrayList<String> surname = new ArrayList<String>();
		String title = "";
		String volume = null;
		String edition = null;
		String placeOfPublication = null;
		String yearOfPublication = null;
		for (Object[] result : resultList)
		{
			if (title.equals(""))
			{
				forename.add(result[0].toString());
				surname.add(result[1].toString());
				title = result[2].toString();
				volume = (result[3] == null) ? "" : result[3].toString();
				edition = (result[4] == null) ? "" : this.arabicNumberToRomanNumber((Integer)result[4]);
				placeOfPublication = result[5].toString();
				yearOfPublication = result[6].toString();
			}
			else if (title.equals(result[2]) && volume.equals((result[3] == null) ? "" : result[3].toString()))
			{
				forename.add(result[0].toString());
				surname.add(result[1].toString());
			}
			else
			{
				this.bibliographyList.add(new BibliographyDTO(
					forename.toArray(new String[forename.size()]), surname.toArray(new String[surname.size()]),
					title, volume, edition, placeOfPublication, yearOfPublication
				));
				forename = new ArrayList<String>();
				surname = new ArrayList<String>();
				forename.add(result[0].toString());
				surname.add(result[1].toString());
				title = result[2].toString();
				volume = (result[3] == null) ? "" : result[3].toString();
				edition = (result[4] == null) ? "" : arabicNumberToRomanNumber((Integer)result[4]);
				placeOfPublication = result[5].toString();
				yearOfPublication = result[6].toString();
			}
		}
		this.bibliographyList.add(new BibliographyDTO(
			forename.toArray(new String[forename.size()]), surname.toArray(new String[surname.size()]),
			title, volume, edition, placeOfPublication, yearOfPublication
		));
		Collections.sort(this.bibliographyList, new Comparator<BibliographyDTO>() {
			@Override public int compare(BibliographyDTO __bd1, BibliographyDTO __bd2) {
				return __bd1.getTitle().compareTo(__bd2.getTitle());
			}
		});
	}	
	
	public BibliographyDTO[] getArray()
	{
		return this.bibliographyList.toArray(
			new BibliographyDTO[this.bibliographyList.size()]
		);
	}
	
	private String arabicNumberToRomanNumber(int _arabicNumber)
	{
	    if (_arabicNumber < 1 || _arabicNumber > 50) return Integer.toString(_arabicNumber);
	    int[] arabicNumbersArray = {50, 40, 10, 9, 5, 4, 1};
	    String[] romanNumbersArray = {"L", "XL", "X", "IX", "V", "IV", "I"};
	    String romanNumber = "";
	    for (int i = 0; i < arabicNumbersArray.length; i++)
		    while (_arabicNumber >= arabicNumbersArray[i])
			{
		    	romanNumber += romanNumbersArray[i];
			    _arabicNumber -= arabicNumbersArray[i];
		    }
	    return romanNumber;
    }
	
	@PersistenceContext(name="persistence/skyski", unitName="skyskiPu") private EntityManager em;
	private List<BibliographyDTO> bibliographyList;
	private static final long serialVersionUID = 1L;
}