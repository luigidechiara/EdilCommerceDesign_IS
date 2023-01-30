package model;

import java.util.ArrayList;
import java.util.List;

public class Carrello<T> {
	private List<T> items;
	private List<Integer> quantità;
	
	
	public Carrello() {
		items = new ArrayList<T>();
		quantità = new ArrayList<Integer>();
	}
	
	public List<T> getItems() {
		return items;
	}
	
	public List<Integer> getQuantità() {
		return quantità;
	}
	
	public void addItem(T item, int q) {
		if(items.contains(item)) {
			int i = items.indexOf(item);
			int tot = quantità.remove(i) + q;
			quantità.add(i, tot);
		} else {
			items.add(item);
			quantità.add(q);
		}
	}
	
	public int deleteItem(T item) {
		int i = items.lastIndexOf(item);
		items.remove(i);
		quantità.remove(i);
		return i;
	}
	
	public void deleteItems() {
		items.clear();
		quantità.clear();
	}
}
