package module.skyski.dcr;

import java.util.Arrays;
import java.util.Comparator;
import javax.faces.model.DataModel;

public class SortDataModel<E> extends DataModel<E>
{
	public SortDataModel() { this.setWrappedData(null); }
	public SortDataModel(E[] _items) { this.setWrappedData(_items); }
	public SortDataModel(DataModel<E> _model)
	{
		this.model = _model;
		this.initializeRows();
	}
	
	public int getIndex() { return this.getRowIndex(); }
	public void sortBy(final Comparator<E> _dataComparator)
	{
		Arrays.sort(
			this.rows, new Comparator<Integer>()
			{
				public int compare(Integer __row1, Integer __row2)
				{ return _dataComparator.compare(getData(__row1), getData(__row2)); }
			}
		);
	}
	
	@Override public boolean isRowAvailable() { return this.model.isRowAvailable(); }
	@Override public int getRowCount() { return this.model.getRowCount(); }
	@Override public E getRowData() { return this.model.getRowData(); }
	@Override public int getRowIndex() { return this.model.getRowIndex(); }
	@Override public void setRowIndex(int _index)
	{
		if (0 <= _index && _index < this.rows.length) this.model.setRowIndex(this.rows[_index]);
		else this.model.setRowIndex(_index);
	}
	@Override public Object getWrappedData() { return this.model.getWrappedData(); }
	@Override public void setWrappedData(Object _data)
	{
		this.model.setWrappedData(_data);
		this.initializeRows();
	}

	private E getData(int _row)
	{
		int index;
		E data;
		index = this.model.getRowIndex();
		this.model.setRowIndex(_row);
		data = this.model.getRowData();
		this.model.setRowIndex(index);
		return data;
	}
	
	private void initializeRows()
	{
		if (this.model.getRowCount() != -1)
		{
			this.rows = new Integer[this.model.getRowCount()];
			for (int i = 0; i < this.model.getRowCount(); i++) this.rows[i] = i;
		}
	}

	private DataModel<E> model;
	private Integer[] rows;
}