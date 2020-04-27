import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;

import java.util.Set;

/*
 * Autor: Arkadzi Zaleuski 250929
 * Data: 09-12-2019
 *
*/


enum CollectionType {
	VECTOR("Lista   (klasa Vector)"),
	ARRAY_LIST("Lista   (klasa ArrayList)"),
	LINKED_LIST("Lista   (klasa LinkedList)"),
	HASH_SET("Zbior   (klasa HashSet)"),
	TREE_SET("Zbior   (klasa TreeSet)");
	
	String typeName;
	
	private CollectionType(String s){
		typeName  = s;
	}
@Override
	public String toString() {
		return this.typeName;
		}

public Collection<Laptop> createCollection() throws LaptopException {
	switch (this) {
	case VECTOR:      return new Vector<Laptop>();
	case ARRAY_LIST:  return new ArrayList<Laptop>();
	case HASH_SET:    return new HashSet<Laptop>();
	case LINKED_LIST: return new LinkedList<Laptop>();
	case TREE_SET:    return new TreeSet<Laptop>();
	default:          throw new LaptopException("Given type isnt definied");
	}
	
}

}



public class GroupOfLaptops implements Iterable<Laptop>   {
	private CollectionType type;
	private String name;
	public Collection<Laptop> collection;
	
	public GroupOfLaptops(String name, CollectionType type) {
		if(name != null && name !="")
		setName(name);
		setType(type);
		try {
			collection = this.type.createCollection();
			}catch(LaptopException e){System.out.println(e.getMessage());}
		
	}
	
	public GroupOfLaptops(String name, String type) {
		if(name != null)
		setName(name);
		try {
		for(CollectionType typ: CollectionType.values())
		{
			if(typ.toString().equals(type))
			{
				setType(typ);
			}
		}
		
		collection = this.type.createCollection();
		
		}catch(LaptopException e){System.out.println(e.getMessage());}
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	public String getType() {
		return type.toString();
	}
	public CollectionType getCollectionType() {
		return type;
	}
	public void setType(String type) throws LaptopException {
		if(type== null)
			throw new LaptopException("Incorrect type");
		for(CollectionType temp: CollectionType.values())
		{
			if(temp.toString().equals(type))
				setType(temp);
		}
	}	
	public void changeType(CollectionType type) {
		if (this.type == type)
			return;
		Collection<Laptop> oldCollection = collection;
		try {
			collection = type.createCollection();
		} catch (LaptopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.type = type;
		for (Laptop p : oldCollection)
			collection.add(p);
	}
	
	public void setType(CollectionType type) {
		this.type = type;
	}
	
	public int size() {
		return collection.size();
	}
	
	public void clear() {
		//collection.removeAll(this.collection);
		collection.clear();
	}
	
	public void add(Laptop l) {
		
		collection.add(l);
		
	}
	
	public void remove() {
		
	}
	
	public static GroupOfLaptops  sumOf(GroupOfLaptops coll1, GroupOfLaptops coll0) {
		
		String name = new String (coll1.getName() + " OR " + coll0.getName());
		CollectionType a;
		if(coll1.collection instanceof Set && !(coll0.collection instanceof Set) ) {
			a = coll1.type;
		}
			else a = coll0.type;
			
		GroupOfLaptops g = new GroupOfLaptops(name,a);
		for(Laptop l: coll1.collection)
			g.add(l);
		for(Laptop l: coll0.collection)
			g.add(l);
		
		return g;
		
	}
	
public static GroupOfLaptops  differenceOf(GroupOfLaptops coll1, GroupOfLaptops coll0) {
		
		String name = new String (coll1.name + " SUB " + coll0.name);
		CollectionType a;
		if(coll1.collection instanceof Set && !(coll0.collection instanceof Set) ) {
			a = coll1.type;
		}
			else a = coll0.type;
		GroupOfLaptops g = new GroupOfLaptops(name,a);
		
		for(Laptop l: coll1.collection) {
			if(!coll0.collection.contains(l)) {
				g.add(l);
			}
		}
		
		
		return g;
		
}
		public static GroupOfLaptops  symdifferenceOf(GroupOfLaptops coll1, GroupOfLaptops coll0) {
			
			String name = new String (coll1.name + " XOR " + coll0.name);
			CollectionType a;
			if(coll1.collection instanceof Set && !(coll0.collection instanceof Set) ) {
				a = coll1.type;
			}
				else a = coll0.type;
			GroupOfLaptops g = new GroupOfLaptops(name,a);
			
			for(Laptop l: coll1.collection) {
				if(!coll0.collection.contains(l)) {
					
				}
			}
			for(Laptop l: coll0.collection) {
				if(!coll1.collection.contains(l)) {
					g.add(l);
				}
			}
			
			return g;
		}
		
		
		
		public static GroupOfLaptops  intersectionOf(GroupOfLaptops coll1, GroupOfLaptops coll0) {
				
				String name = new String (coll1.name + " AND " + coll0.name);
				CollectionType a;
				if(coll1.collection instanceof Set && !(coll0.collection instanceof Set) ) {
					a = coll1.type;
				}
					else a = coll0.type;
				GroupOfLaptops g = new GroupOfLaptops(name,a);
				
				for(Laptop l: coll1.collection) {
					if(coll0.collection.contains(l)) {
						g.add(l);
					}
				}
				
				return g;
		
	}
	//sort
		public void sortBrand() throws LaptopException {
			if(this.type == CollectionType.HASH_SET || this.type == CollectionType.TREE_SET)
				throw new LaptopException("Set of objects can't be sorted\n");
			Collections.sort((List<Laptop>) this.collection);
		}
		 
		public void sortYear() throws LaptopException {
			if(this.type == CollectionType.HASH_SET || this.type == CollectionType.TREE_SET)
				throw new LaptopException("Set of objects can't be sorted\n");
			Collections.sort((List<Laptop>) this.collection , new Comparator<Laptop>(){
				@Override
				public int compare(Laptop l1, Laptop l2) {
					int c =0;
					if(l1.getYear() > l2.getYear())
						return 1;
					if(l1.getYear() < l2.getYear())
						return -1;
			
					return c;
				}
			});
		}
		
		public void sortModel() throws LaptopException {
			if(this.type == CollectionType.HASH_SET || this.type == CollectionType.TREE_SET)
				throw new LaptopException("Set of objects can't be sorted\n");
			Collections.sort((List<Laptop>) this.collection, new Comparator<Laptop>() {
				@Override
				public int compare(Laptop l1, Laptop l2) {
					int c =0;
					if(l1.getModel().compareTo(l2.getModel())>0)
						return 1;
					if(l1.getModel().compareTo(l2.getModel())<0)
						return -1;
			
					return c;
				}
				
			});	
			
		}
		
		public static void printToFIle(PrintWriter writer, GroupOfLaptops group) {
			Laptop.printToFile(writer, group.name);
			Laptop.printToFile(writer, group.type.toString());
			for(Laptop l: group.collection) {
				Laptop.printToFile(writer,l.toString());
			}
		}

		@Override
		public Iterator<Laptop> iterator() {
			return collection.iterator();
		}

		
	//delete
	//
	
	
	
}
