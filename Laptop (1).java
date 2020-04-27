import java.io.*;
import java.util.Scanner;

/*
 * Autor: Arkadzi Zaleuski 250929
 * Data: 18-10-2019
 
*/
enum Brand {
	Undefinied("Undefinied"), Dell("Dell"), HP("HP"), MSI("MSI"),
	ASUS("ASUS"), ACER("ACER"), Lenovo("Lenovo");

	private String brandName;

	@Override
	public String toString() {
		return brandName;
	}
	
	private Brand(String brandName) {
		this.brandName = brandName;
	}
}

class LaptopException extends Exception {

	private static final long serialVersionUID = 1L;

	LaptopException(String message) {
		super(message);
	}

}


public class Laptop implements Serializable, Comparable<Laptop> {
	private static final String genreMenu = "Dell\n" + "HP\n" + "MSI\n" + "ASUS\n" + "ACER\n" + "Lenovo\n";
	public static Scanner scanner = new Scanner(System.in);
	private String model;
	private int year;
	private Brand brand;

	public Laptop() throws LaptopException {
		System.out.println("Enter device's model\n");
		String name = scanner.nextLine();
		if (name == null || name.equals("") || name.equals(" "))
			throw new LaptopException("This field cant be empty\n");
		this.model = name;
	}

	public Laptop(String model) throws LaptopException {
		if (model == null || model.equals("") || model.equals(" "))
			throw new LaptopException("This field cant be empty\n");
		this.model = model;
	}
	public Laptop(int k ) {
		
	}
	public void setYear() throws LaptopException {

		int year = -1;
		System.out.println("Enter the year of publishing\n");
		try {
			year = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("Only numbers are allowed");
		}
		if (year <= 1998 || year > 2020)
			throw new LaptopException("Incorrect year");
		this.year = year;
	}
	public void setYear(int year)  throws LaptopException  {
		if (year <= 1998|| year > 2020)
			{
			
		throw new LaptopException("Incorrect year");
			}
	this.year = year;
	}
	public int getYear() {
		return this.year;
	}
	public String getModel() {
		return model;
	}
	public void setModel() {
		System.out.println("Enter new title");
		this.model = scanner.nextLine();
	}
	public void setModel(String name ) throws LaptopException {
		if (name == null || name.equals("") || name.equals(" "))
			throw new LaptopException("This field cant be empty\n");
		this.model = name;
	}

	public void setBrand(Brand a) {
		this.brand = a;
	}

	public void setBrand() throws LaptopException {
		System.out.println(genreMenu);
		System.out.println("Enter laptop's brand from the list above or leave field empty and press enter to proceed\n");
		String a = scanner.nextLine();
		if (a == null || a.equals("")) {
			this.brand = Brand.Undefinied;
			return;
		}
		for (Brand brand : Brand.values()) {
			if (brand.toString().equals(a)) {
				this.brand = brand;
				return;
			}
		}
		throw new LaptopException("No such brand");
	}
	
	public void setBrand(String s) throws LaptopException {
		brand = Brand.Undefinied;
		for (Brand genre : Brand.values()) {
			if (genre.toString().equals(s)) 
				this.brand = genre;
		}
			
	}
	public void setBrand(Object a) {
		
		this.brand = (Brand)a;
	}
	public String getBrand() {
		return brand.toString();
	}


	@Override
	public String toString() {

		return getModel() + " " + getYear() + " " + getBrand()+ "\n";
	}

	public static void printToFile(PrintWriter writer,String info) {
		writer.println(info);
	}

	
	  public static void printToFile(String file_name,String info) throws LaptopException { try
	  (
			  PrintWriter writer = new PrintWriter(file_name)) {
		  printToFile(writer,info);
	  } catch (FileNotFoundException e) { throw new
	  LaptopException("Nie odnaleziono pliku " + file_name); } }
	 
	/*
	 * Klasa readFromFile została zmodyfikowana w sposób umożliwający odczytanie upto 50 wartości,
	 * oraz zapisywaniu tych wartosci bez usunięcia istniejących wartości w storage[]
	 */
	public static Laptop readFromFile(String file_name,int i) throws LaptopException {
		String[] lines = new String[50];
		int a=0;
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(file_name)))) {
			while(( lines[a] = reader.readLine()) != null)
				a++;
		} catch (FileNotFoundException e) {
			throw new LaptopException("Cant find  " + file_name + "\n\n\n\n");
		} catch (IOException e) {
			throw new LaptopException("Error while reading from file\n");
		} 
		a=a-1;
		 return readFromFile(lines,i,a);
		
	}
// zmienna 'a' przekazuje ilosć linii zawierających tekst array'ju lines, zmienna 'i' zostaje przekazana od ConsoleApp do
//odczytania kolejnych linii pliku .
	public static Laptop readFromFile(String[] lines,int i,int a) throws LaptopException {
		
		//System.out.println(a+" "+i);
			if(i>=a)
				return null;
			String[] txt = lines[i].split("#");
			Laptop laptop = new Laptop(txt[0]);
			laptop.setYear(Integer.parseInt(txt[1]));
			laptop.setBrand(txt[2]);
			return laptop;

	
	
	}
	
	//Class'a zapisuje dane do podawanego pliku binarnego
	public static void printBin(String filename) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename) );
			for(Laptop l:MyWindowApp.group.collection)
				oos.writeObject(l.toString());
			
			oos.close();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
		}
		
		return;
	}
	

	//Class'a zczytuje dane z podawanego pliku binarnego i zapisuje te dane bez usuwania istniejacych.
	//Poprez fromValue podawamy od ktorego miejsca mozemy zapisywac dane.
	public static void readBin(String filename,int fromValue)
	{
		try {
			Laptop[] str = new Laptop[50];
			ObjectInputStream ios = new ObjectInputStream( new FileInputStream(filename));
			
			str =(Laptop[]) ios.readObject();
			
			for(int i=fromValue; str[i-fromValue] != null ; i++ )
				MyWindowApp.group.collection.add( str[i-fromValue]);
			ios.close();
		} catch (Exception e) {
			
		}
	}
	
@Override
	  	public int hashCode() { 
		  int result = (int) (year ^ (year >>> 32));
		  result = 31 * result + model.hashCode(); 
		  result = 31 * result +getBrand().hashCode();
		  	return result; 
		  	}
	  
	  public boolean equals(Object o) { 
		  if (this == o) return true;
		  if (o == null) return false; 
		  if (this.getClass() != o.getClass())
			  return false;
		  Laptop temp =  (Laptop) o;
		  return (getBrand().equals(temp.getBrand()) 
				  && year == temp.year
				  && model.equals(temp.model)); 
		  }
	  
	 @Override
	 public int compareTo(Laptop l) {
		 if (this == l) 
			 return 0; 
		 	int res =0;
		 	
		 	res+= getBrand().compareTo(l.getBrand()); 
		 	
		 	return res; 
		 	}
	 
	
}