package module.core.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import module.core.dto.MerchandiseDTO;

@Stateful public class MerchandiseDAO implements Serializable
{
	public MerchandiseDAO() {}
		
	public void getInitialize(String _merchandiseLang)
	{
		this.resultList = (List<Object[]>)this.em.createNamedQuery(_merchandiseLang, Object[].class).getResultList();
		this.merchandiseList = new ArrayList<MerchandiseDTO>(this.resultList.size());
		for (Object[] result : this.resultList) 
			this.merchandiseList.add(
				new MerchandiseDTO(
					(Integer)result[0], result[1].toString(), (Boolean)result[2],
					(Double)result[3], (Double)result[4] 
				)
			);
		Collections.sort(this.merchandiseList, new Comparator<MerchandiseDTO>() {
			@Override public int compare(MerchandiseDTO _m1, MerchandiseDTO _m2)
			{ return _m1.getName().compareTo(_m2.getName()); }
		});
	}
	
	public MerchandiseDTO[] getArray() { return this.merchandiseList.toArray(new MerchandiseDTO[this.merchandiseList.size()]); }
	public List<MerchandiseDTO> getList() { return this.merchandiseList; }
	
	@PersistenceContext(name="persistence/skyski", unitName="skyskiPu") private EntityManager em;
	private List<MerchandiseDTO> merchandiseList;
	private List<Object[]> resultList;
	private static final long serialVersionUID = 1L;
}