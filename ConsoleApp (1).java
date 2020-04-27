import java.util.Scanner;

/*
 * Autor: Arkadzi Zaleuski 250929
 * Data: 18-10-2019
 * Clasa kontolująca przebieg programu, przechowująca wszyskie dane, tworząca objekty 
 * zarządzająca tymi objęktami w określone sposoby
 */
public class ConsoleApp {
	{
		System.out.println("Arkadzi Zaleuski 250929 10.18.2019\n\n\n\n");
	}
	
	
	  public static void removeCLaptop() { for (int i = currentNumOfLaptop; i <
	  (storage.length - 1); i++) storage[i] = storage[i + 1]; }
	 
	
	public static Laptop[] storage = new Laptop[50];
	public static int currentNumOfLaptop = 0;
	public static Scanner scanner = new Scanner(System.in);

	private static final String Menu = "1.Add laptop\n" + "2.Edit laptop\n" + "3.Remove laptop from the storage\n"
			+ "4.Show all laptops\n" + "5.Save to file\n" + "6.Read from file\n" + "7.Exit\n";
	private static final String ChangeMenu = "1.Change model\n" + "2. Change production year\n" + "3.Change brand\n"
			+ "4.Return to menu";

	public static void addBook() throws LaptopException {
		try {
			storage[currentNumOfLaptop] = new Laptop();
			storage[currentNumOfLaptop].setYear();
			storage[currentNumOfLaptop].setBrand();
			currentNumOfLaptop++;
		} catch (LaptopException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removeLaptop() throws LaptopException {
		int num = 0;
		try {
			num = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("This field requires a number");
		}
		num--;
		if (num >= currentNumOfLaptop || num < 0) {
			throw new LaptopException("No such position");
		}
		for (int i = num; i < (currentNumOfLaptop); i++)
			storage[num] = storage[num + 1];

		currentNumOfLaptop--;
	}
	
	
	

	public void editLaptop(int num) {
		do {
			try {
				System.out.println(ChangeMenu);

				switch (Integer.parseInt(scanner.nextLine())) {
				case 1:
					storage[num - 1].setModel();
					break;
				case 2:
					storage[num - 1].setYear();
					break;
				case 3:
					storage[num - 1].setBrand();
					break;
				case 4:
					return;
				default:
					System.out.println("Wrong input\n\n");
					break;

				}
			} catch (LaptopException e) {
				System.out.println("Wrong input\n\n");
			}
		} while (true);

	}

	private void MainLoop() {
		int move = 0;
		do {
			System.out.println(Menu + "Current number of laptops: " + currentNumOfLaptop);
			try {
				move = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {

			}
			switch (move) {

			case 1:
				try {
					addBook();
				} catch (LaptopException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 2:
				System.out.println("Enter the laptop's number: ");
				try {
					int a = 0;
					a = Integer.parseInt(scanner.nextLine());
					if (a <= currentNumOfLaptop && a > 0) {
						editLaptop(a);
					} else
						throw new LaptopException("This position in storage is empty or no such position");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 3:
				try {
					System.out.println("Enter the number of the laptop you want to remove ");
					removeLaptop();
					System.out.println("Laptop was removed.\n\n");
				} catch (LaptopException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 4:
				if (currentNumOfLaptop == 0) {
					System.out.println("Storage is empty\n\n");
					break;
				}
				for (int i = 0; i < currentNumOfLaptop; i++) {
					System.out.println((i + 1) + ". " + storage[i]);
				}
				break;
			case 5:
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < currentNumOfLaptop; i++) {
					sb.append(storage[i].getModel()+"#");
					sb.append(storage[i].getYear()+"#");
					sb.append(storage[i].getBrand()+"#\n");
					}
				try {
					Laptop.printToFile("bookClass.txt", sb.toString());
				}catch(Exception e) {System.out.println("Error while saving data");}
				
				break;

			case 6:
				try {
					// 'i' reprezentuje numer zczytywanej linii w pliku
					int i=0;
					while(
					(storage[currentNumOfLaptop]=Laptop.readFromFile("bookClass.txt",i)) !=null ) {
						currentNumOfLaptop++;
						i++;
					}
					}
					
				 catch (LaptopException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 7:
				System.exit(0);
				break;

			default:
				System.out.println("Incorrect input.\n\n");
			}
		} while (true);

	}

	public static void main(String[] args) {
		ConsoleApp app = new ConsoleApp();
		app.MainLoop();

	}
}
