package model;

import java.util.ArrayList;
import java.util.List;

public class Carrello<T> {
	private List<T> items;
	private List<Integer> quantita;
	
	
	public Carrello() {
		items = new ArrayList<T>();
		quantita = new ArrayList<Integer>();
	}
	
	public List<T> getItems() {
		return items;
	}
	
	public List<Integer> getQuantita() {
		return quantita;
	}
	
	public void addItem(T item, int q) {
		if(items.contains(item)) {
			int i = items.indexOf(item);
			int tot = quantita.remove(i) + q;
			quantita.add(i, tot);
		} else {
			items.add(item);
			quantita.add(q);
		}
	}
	
	public int deleteItem(T item) {
		int i = items.lastIndexOf(item);
		items.remove(i);
		quantita.remove(i);
		return i;
	}
	
	public void deleteItems() {
		items.clear();
		quantita.clear();
	}
}
