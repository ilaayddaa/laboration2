package lab2.ilayda;

public interface SearchableDataStructure<T> {
	public void add(T item);
	
	public boolean searchFor(T itemToSearchFor);
	
	public int size();
	
	public void clear();
	
	public boolean remove(T itemToRemove);
}
