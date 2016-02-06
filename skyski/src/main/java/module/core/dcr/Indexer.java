package module.core.dcr;

import java.io.Serializable;

public class Indexer implements Serializable
{
	public Indexer() { this.index = 0; }
	
	public int getIndex() { return ++this.index; }
	
	private transient int index;
	private static final long serialVersionUID = 1L;
}