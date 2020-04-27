import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.html.HTMLDocument.Iterator;





/*
 * Autor: Arkadzi Zaleuski 250929
 * Data: 09-11-2019

*/




public class MyWindowApp extends JFrame implements ActionListener {
	JPanel panel = new JPanel();
	private static final long serialVersionUID = 1L;
	public static String path = "bookClass.txt"; 
	String warningMessage = "<html><body width='%1s'><h1>WARNING</h1>"
	        + "<p>Are you sure you want to delete this laptop? "
	        + "<p>The result cannot be undone. If you ran "
	        + "<p>out of room make sure you saved current"
	        + "<p> state of shelf.  ";
	String infoMessage = "<html><body width='%1s'><h1>INFO</h1>"
			+ "<p>Program Laptop -  wersja okienkowa"
	        + "<p>Autor programu: Arkadzi Zaleuski "
	        + "<p>Program zostal utworzony 09.11.2019 ";
	
	static GroupOfLaptops group ; 
	
	private static String[] listOfBrands = {"Undefinied", "Dell","HP","MSI",
			"ASUS", "ACER","Lenovo"};
	Font font = new Font("MonoSpaced", Font.BOLD, 12);

	JMenuBar bar = new JMenuBar();
	JMenu menu = new JMenu("laptop");
	JMenu sortingMenu = new JMenu("Sort via... ");
	JMenu typeMenu = new JMenu("Edit Group");
	JPopupMenu popup  = new JPopupMenu();
	static final String[] head = {"Brand","Model","Year of production"};
	static Table table = new Table(head);
	
	JMenuItem saveItem = new JMenuItem("Save to file");
	JMenuItem loadItem = new JMenuItem("Export from file");
	JMenuItem deleteItem = new JMenuItem("Delete");
	JMenuItem addItem = new JMenuItem("Add");
	JMenuItem exitItem = new JMenuItem("Exit");
	JMenuItem infoItem = new JMenuItem("Info");
	JMenuItem editTypeItem = new JMenuItem("Edit Type");
	JMenuItem editNameItem = new JMenuItem("Edit Name");
	
	JMenuItem sortYear = new JMenuItem("Year");
	JMenuItem sortModel = new JMenuItem("Model");
	JMenuItem sortBrand = new JMenuItem("Brand");
	

	JButton newButton = new JButton("Add laptop");
	JButton editButton = new JButton("Edit laptop ");
	JButton saveButton = new JButton("Save to file");
	JButton loadButton = new JButton("Read from file");
	JButton deleteButton = new JButton("Delete laptop");
	JButton infoButton = new JButton("About");
	JButton exitButton = new JButton("EXIT");
	


	public MyWindowApp(Window parent, GroupOfLaptops gol) {
		this.setTitle(gol.getName());
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(480, 480);
		setResizable(false);
		setLocationRelativeTo(null);
		group = gol;		
		this.setJMenuBar(bar);  
		bar.add(menu);
		bar.add(sortingMenu);
		bar.add(typeMenu);
		menu.add(addItem);
		menu.add(deleteItem);
		menu.add(saveItem);
		menu.add(loadItem);
		menu.add(infoItem);
		menu.add(exitItem);
		sortingMenu.add(sortYear);
		sortingMenu.add(sortBrand);
		sortingMenu.add(sortModel);
		typeMenu.add(editTypeItem);
		typeMenu.add(editNameItem);
	
		
		newButton.addActionListener(this);
		deleteButton.addActionListener(this);
		editButton.addActionListener(this);
		infoButton.addActionListener(this);
		exitButton.addActionListener(this);
		loadButton.addActionListener(this);
		saveButton.addActionListener(this);

		
		menu.addActionListener(this);
		loadItem.addActionListener(this);
		addItem.addActionListener(this);
		saveItem.addActionListener(this);
		deleteItem.addActionListener(this);
		exitItem.addActionListener(this);
		infoItem.addActionListener(this);
		editTypeItem.addActionListener(this);
		editNameItem.addActionListener(this);
		sortingMenu.addActionListener(this);
		sortYear.addActionListener(this);
		sortBrand.addActionListener(this);
		sortModel.addActionListener(this);
		
		
		newButton.setPreferredSize(new Dimension(150,30));
		deleteButton.setPreferredSize(new Dimension(150,30));
		editButton.setPreferredSize(new Dimension(150,30));
		infoButton.setPreferredSize(new Dimension(150,30));
		exitButton.setPreferredSize(new Dimension(150,30));
		loadButton.setPreferredSize(new Dimension(150,30));
		saveButton.setPreferredSize(new Dimension(150,30));
		
		
		panel.add(newButton);
		panel.add(deleteButton);
		panel.add(saveButton);
		panel.add(loadButton);
		panel.add(editButton);
		panel.add(infoButton);
		panel.add(exitButton);
		panel.add(table);
		setContentPane(panel);

		setVisible(true);
		table.refreshFrom(group);

	}
	@Override
	public void actionPerformed(ActionEvent event) {
		Object eventSource = event.getSource();

		
		 if(eventSource == editButton) {

			 if(table.getSelectedIndex()<0)
			 {
					JOptionPane.showMessageDialog(this,"Select a laptop", "", JOptionPane.ERROR_MESSAGE);
					return;

			 }
			  String brandString =  (String)javax.swing.JOptionPane.showInputDialog(this,"Enter the brand", "Editing",javax.swing.JOptionPane.PLAIN_MESSAGE,null,listOfBrands, "");
			 String model =  (String)javax.swing.JOptionPane.showInputDialog(this,"Enter the model", "Editing",javax.swing.JOptionPane.PLAIN_MESSAGE, null,null, "");
			 int year = Integer.parseInt( (String)javax.swing.JOptionPane.showInputDialog(this,"Enter year of production", "Editing",javax.swing.JOptionPane.PLAIN_MESSAGE, null,null, ""));
			
			 Brand brand = Brand.Undefinied;
			 for(Brand b: Brand.values()) {
				if(b.name().equals(brandString))
				{
					brand = b;
					break;
				}
			 }
			WarningDeleteMessage  mess =  new WarningDeleteMessage(this, "Are you sure you wanna save changes?" );
			 	if(mess.result) {
			 	Laptop l = new Laptop(1);
			 	try {
			 	l.setBrand(brand);
			 	l.setYear(year);
			 	l.setModel(model);
			 	}catch(Exception e) {
			 		JOptionPane.showMessageDialog(this,"Incorrect input. Changes werent saved", "", JOptionPane.ERROR_MESSAGE);
			 		return;
			 	}
			 	group.add(l);
			 	}
				table.refreshFrom(group);
				
				
		 }else if(eventSource == deleteButton || eventSource == deleteItem) {
			 if(table.getSelectedIndex()<0)
			 {
				  JOptionPane.showMessageDialog(this,"There's no data to delete on this position. You might wanna change one.", "", JOptionPane.INFORMATION_MESSAGE);
			 }
			 else {
			new WarningDeleteMessage(this, warningMessage);
				
				table.refreshFrom(group);
			 }
		 
		
			
		 }else if(eventSource == newButton || eventSource == addItem) { 
			 
			 String brandString =  (String)javax.swing.JOptionPane.showInputDialog(this,"Choose the Brand", "Editing",javax.swing.JOptionPane.PLAIN_MESSAGE, null,listOfBrands, "expand...");
			 Brand brand = Brand.Undefinied;
			 for(Brand b: Brand.values()) {
				if(b.name().equals(brandString))
					brand = b;
			 }
			 
			 Laptop l = new Laptop(1);
			 
			 	l.setBrand(brand);
			 	try {
					l.setYear(Integer.parseInt( (String)javax.swing.JOptionPane.showInputDialog(this,"Enter year of production", "Editing",javax.swing.JOptionPane.PLAIN_MESSAGE, null,null, "")));
				} catch (HeadlessException | LaptopException e) {
					JOptionPane.showMessageDialog(this,"Incorrect input. Changes werent saved", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
			 	try {
					l.setModel((String)javax.swing.JOptionPane.showInputDialog(this,"Enter the model", "Editing",javax.swing.JOptionPane.PLAIN_MESSAGE, null,null, ""));
				} catch (HeadlessException | LaptopException e) {
					JOptionPane.showMessageDialog(this,"Incorrect input. Changes werent saved", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
			 	group.add(l);
				table.refreshFrom(group);
				
		 }
		
		 else if(eventSource == exitButton || eventSource == exitItem)
		 {
			 GroupManagerWindowApp.content.refresh();
			 dispose();
		 }
		 else if(eventSource == infoButton || eventSource == infoItem) {
			 String infoMessage = "<html><body width='%1s'><h1>INFO</h1>"
						+ "<p>Program Laptop -  wersja okienkowa"
				        + "<p>Autor programu: Arkadzi Zaleuski "
				        + "<p>Program zostal utworzony 12.09.2019 ";
				javax.swing.JOptionPane.showMessageDialog(this,infoMessage,"",javax.swing.JOptionPane.INFORMATION_MESSAGE );
		 }
			 
		 else if(eventSource ==  saveButton || eventSource == saveItem) {
			 if(group.size() == 0)
				 {JOptionPane.showMessageDialog(this,"There's no data to save", "Error ",JOptionPane.ERROR_MESSAGE);
			 return;
			 }
			 
			 //Zapisanie w sposób tworzenia String ze wszystimi danymi i podawania go do zapisania do pliku.
			 java.util.Iterator<Laptop> iter = MyWindowApp.group.iterator();
			  StringBuilder sb = new StringBuilder(); 
				do{
					Laptop l = iter.next();
			  sb.append(l.getModel()+"#");
			  sb.append(l.getYear()+"#");
			  sb.append(l.getBrand()+"#\n"); 
			  }while(iter.hasNext() );
			 
				
				//droga do zapisania danych zostaje pobierana za pomocą dodatkowego okeinka 
				// new InputData(this, "Type file name ");
			  	
				
				//tworzenie standardowego okna dialogowego do wyboru nazwy pliku JFileChooser.
				FileChooser fc = new FileChooser("Choose file or directory to save your data");
				path = fc.getPath();
				
				
				try {
					Laptop.printToFile(path, sb.toString());
				} catch (LaptopException e) {
					JOptionPane.showMessageDialog(this,"Somtheing went wrong. Changes werent saved", "", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				}else if(eventSource ==loadButton || eventSource == loadItem) {
					FileChooser fc = new FileChooser("Choose file to read from");
					path = fc.getPath();
					int i=0;
					Laptop l = new Laptop(1);
					try {
						while((l= Laptop.readFromFile(path, i))!=null){
							
						group.add(l);
						i++;
						}
					} catch (LaptopException e) {
						JOptionPane.showMessageDialog(this,"Error while reading file. ", "", JOptionPane.ERROR_MESSAGE);
						return;
					}
					table.refreshFrom(group);
			 
		 		}
		 else if(eventSource == sortYear) {
			 
			 try {
				group.sortYear();
				table.refreshFrom(group);
			} catch (LaptopException e) {
				JOptionPane.showMessageDialog(this,"Unsortable collection.", "", JOptionPane.ERROR_MESSAGE);
				return;
				
			}
		 }
else if(eventSource == sortModel) {
			 
			 try {
				group.sortModel();
				table.refreshFrom(group);
			} catch (LaptopException e) {
				JOptionPane.showMessageDialog(this,"Unsortable collection.", "", JOptionPane.ERROR_MESSAGE);
				return;
				
			}
		 }
else if(eventSource == sortBrand) {
	 
	 try {
		group.sortBrand();
		table.refreshFrom(group);
	} catch (LaptopException e) {
		JOptionPane.showMessageDialog(this,"Unsortable collection.", "", JOptionPane.ERROR_MESSAGE);
		return;

	}
}
else if(eventSource == editNameItem) {
	String nick =  (String)javax.swing.JOptionPane.showInputDialog(this,"Enter new name", "Editing",javax.swing.JOptionPane.PLAIN_MESSAGE, null,null, "");
	group.setName(nick);
	GroupManagerWindowApp.content.refresh();
	
}else if(eventSource == editTypeItem) {
	String[] options  = {"Lista   (klasa Vector)",
			"Lista   (klasa ArrayList)",
			"Lista   (klasa LinkedList)",
			"Zbior   (klasa HashSet)",
			"Zbior   (klasa TreeSet)"} ;
	String nick =  (String)javax.swing.JOptionPane.showInputDialog(this,"Choose new type", "Editing",javax.swing.JOptionPane.PLAIN_MESSAGE, null,options, "");
	CollectionType temp = group.getCollectionType();
	for(CollectionType t:CollectionType.values() ) {
		if(t.toString().equals(nick)) {
			temp = t;
		}
	}
	group.changeType(temp);
		
	GroupManagerWindowApp.content.refresh();
}
	}
}






