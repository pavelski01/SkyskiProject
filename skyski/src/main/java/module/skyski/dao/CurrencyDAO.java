package module.skyski.dao;

import module.skyski.dto.CurrencyDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class CurrencyDAO {
	
	@PostConstruct private void initialize()
	{
		this.resultList = this.em.createQuery(
			"SELECT c.id, c.code, c.locale, c.rate, c.symbol, c.noun, c.adjective "	+ 
				"FROM Currency c ORDER BY c.id", 
			Object[].class
		).getResultList();
		this.currencyList = new ArrayList<CurrencyDTO>(this.resultList.size());
		for (Object[] result : resultList) 
			this.currencyList.add(new CurrencyDTO(
				(Integer)result[0], (Character[])result[1], result[2].toString(), 
				(Double)result[3], result[4].toString(), result[5].toString(), result[6].toString()
			));
		Collections.sort(this.currencyList, new Comparator<CurrencyDTO>() {
			@Override public int compare(CurrencyDTO __c1, CurrencyDTO __c2) {
				return __c1.getId() - __c2.getId();
			}
		});
	}
	
	public CurrencyDTO[] getArray()
	{ return this.currencyList.toArray(new CurrencyDTO[this.currencyList.size()]); }
	
	@PersistenceContext(name="persistence/skyski", unitName="skyskiPu") private EntityManager em;
	private List<Object[]> resultList;
	private List<CurrencyDTO> currencyList;
}