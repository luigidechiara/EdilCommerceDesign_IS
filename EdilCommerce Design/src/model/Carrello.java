package model;

import java.util.ArrayList;
import java.util.List;

public class Carrello<T> {
	List<T> items;
	List<Integer> quantit�;
	
	public Carrello() {
		items = new ArrayList<T>();
		quantit� = new ArrayList<Integer>();
	}
	
	public List<T> getItems() {
		return items;
	}
	
	public List<Integer> getQuantit�() {
		return quantit�;
	}
	
	public void addItem(T item, int q) {
		if(items.contains(item)) {
			int i = items.indexOf(item);
			int tot = quantit�.remove(i) + q;
			quantit�.add(i, tot);
		} else {
			items.add(item);
			quantit�.add(q);
		}
	}
	
	public int deleteItem(T item) {
		int i = items.lastIndexOf(item);
		items.remove(i);
		quantit�.remove(i);
		return i;
	}
	
	public void deleteItems() {
		items.clear();
		quantit�.clear();
	}
}
