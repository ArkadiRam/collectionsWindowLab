import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JOptionPane.*;

/*
 * Autor: Arkadzi Zaleuski 250929
 * Data: 09-12-2019
 
*/

public class GroupManagerWindowApp{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final String JOptionPane = null;
	private JFrame frame;
	private GroupOfLaptops gol;
	public String input;
	JMenuItem mntmAuthor = new JMenuItem("Author");
	public static java.util.List<GroupOfLaptops> listOfGroups = new ArrayList<GroupOfLaptops>();
	static final String [] header = {"Name","Type","Number of Laptops"};
	static Table content = new Table(header);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupManagerWindowApp window = new GroupManagerWindowApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GroupManagerWindowApp() {
		initialize();
	}

	JButton editButton = new JButton("Edit group");
	JButton addButton = new JButton("Add");
	JButton deleteButton = new JButton("Remove");
	
	
		
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	
		
		frame = new JFrame();
		frame.setBackground(UIManager.getColor("Button.background"));
		frame.setTitle("Group Manager");
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));
		frame.setBounds(100, 100, 566, 410);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(new BorderLayout());
		
		frame.getContentPane().add(content, BorderLayout.NORTH);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Group...");
		menuBar.add(mnNewMenu);
		
		editButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MyWindowApp(frame,	listOfGroups.get(content.getSelectedIndex()) );
				
			}
		});
		deleteButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					new MyWindowApp(frame,	listOfGroups.get(content.getSelectedIndex()) );
					try {
						listOfGroups.remove(content.getSelectedIndex());
						content.refresh();
						}catch(Exception ex) {
							
						}
						
				}
			
		});
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String[] options  = {"Lista   (klasa Vector)",
						"Lista   (klasa ArrayList)",
						"Lista   (klasa LinkedList)",
						"Zbior   (klasa HashSet)",
						"Zbior   (klasa TreeSet)"} ;
				
				String type = (String)javax.swing.JOptionPane.showInputDialog(frame,"Enter the type of group", "Enter the group's name", javax.swing.JOptionPane.PLAIN_MESSAGE , null, options, "klasa Vector");
				String name = (String)javax.swing.JOptionPane.showInputDialog(frame,"Enter the name of group", "Enter the group's name",javax.swing.JOptionPane.PLAIN_MESSAGE, null,null, "");
				for(CollectionType a: CollectionType.values())
				{
					if(a.toString().equals(type))
						gol = new GroupOfLaptops(name,type);
					if(name == "" || name== " ")
						name = javax.swing.JOptionPane.showInputDialog(new JFrame(),"Incorrect name ", "Dialog", javax.swing.JOptionPane.ERROR_MESSAGE);
						
				}
				listOfGroups.add(gol);
				content.refresh();
				new MyWindowApp(frame,gol);
				
				
				
			}
		});
		
	
		JMenuItem nmi = new JMenuItem("Add Group");
		nmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] options  = {"Lista   (klasa Vector)",
						"Lista   (klasa ArrayList)",
						"Lista   (klasa LinkedList)",
						"Zbior   (klasa HashSet)",
						"Zbior   (klasa TreeSet)"} ;
				
				String type = (String)javax.swing.JOptionPane.showInputDialog(frame,"Enter the type of group", "Enter the group's name", javax.swing.JOptionPane.PLAIN_MESSAGE , null, options, "klasa Vector");
				String name = (String)javax.swing.JOptionPane.showInputDialog(frame,"Enter the name of group", "Enter the group's name",javax.swing.JOptionPane.PLAIN_MESSAGE, null,null, "");
				for(CollectionType a: CollectionType.values())
				{
					if(a.toString().equals(type))
						gol = new GroupOfLaptops(name,type);
					if(name == "" || name== " ")
						name = javax.swing.JOptionPane.showInputDialog(new JFrame(),"Incorrect name ", "Dialog", javax.swing.JOptionPane.ERROR_MESSAGE);
						
				}
				listOfGroups.add(gol);
				content.refresh();
				new MyWindowApp(frame,gol);
				
				
			}
		});
		
		mnNewMenu.add(nmi);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Edit Group");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MyWindowApp(frame,	listOfGroups.get(content.getSelectedIndex()) );
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem_1);
		
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Remove Group");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				listOfGroups.remove(content.getSelectedIndex());
				content.refresh();
				}catch(Exception ex) {
					
				}
				
			}
		});
		
		mnNewMenu.add(mntmNewMenuItem);
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Import from...");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FileChooser fc = new FileChooser("Choose file to read from");
				String path = fc.getPath();
				String headLine;
				BufferedReader reader ;
				
					try {
						reader = new BufferedReader(new FileReader(new File(path)));
						try {
					while((headLine = reader.readLine())!=null) {
					try {
						String[] elements = headLine.split("#");
						GroupOfLaptops gol = new GroupOfLaptops(elements[0],elements[1]);
						for(int i=0;i<Integer.parseInt(elements[2]);i++) {
							String lines = reader.readLine();
							String[] subLines = lines.split("#");
							Laptop laptop = new Laptop(subLines[0]);
							laptop.setYear(Integer.parseInt(subLines[1]));
							laptop.setBrand(subLines[2]);
							gol.add(laptop);
						}
						listOfGroups.add(gol);
					}catch(Exception exep) {
						javax.swing.JOptionPane.showMessageDialog(frame,"Fail reading the file. Changes werent saved", "", javax.swing.JOptionPane.ERROR_MESSAGE);
						return;
						}
						}
				} catch (IOException e1) {
					javax.swing.JOptionPane.showMessageDialog(frame,"Faled to read from file. Changes werent saved", "", javax.swing.JOptionPane.ERROR_MESSAGE);
					return;
				}
					} catch (FileNotFoundException e2) {
						javax.swing.JOptionPane.showMessageDialog(frame,"File wasn't found. Changes werent saved", "", javax.swing.JOptionPane.ERROR_MESSAGE);
						return;
					}
		
					content.refresh();
					try {
						reader.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			
		});
		
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Save to...");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringBuilder sb = new StringBuilder();
				for(GroupOfLaptops group:listOfGroups) {
				java.util.Iterator<Laptop> iter =group.iterator();
				   sb.append(group.getName()+"#");
				   sb.append(group.getType()+ "#" + group.size() +  "#\n");
				   
					do{
						Laptop l = iter.next();
				  sb.append(l.getModel()+"#");
				  sb.append(l.getYear()+"#");
				  sb.append(l.getBrand()+"#\n"); 
				  }while(iter.hasNext() );
			}
				FileChooser fc = new FileChooser("Choose file or directory to save your data");
				String path = fc.getPath();
				try {
					Laptop.printToFile(path, sb.toString());
				} catch (LaptopException e1) {
					javax.swing.JOptionPane.showMessageDialog(frame,"Something went wrong. Changes werent saved", "", javax.swing.JOptionPane.ERROR_MESSAGE);
				}
				content.refresh();
			}
			
		});
	
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("Manage...");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Sum");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroupOfLaptops g1= new GroupOfLaptops(" ",CollectionType.ARRAY_LIST); GroupOfLaptops g2= new GroupOfLaptops(" ",CollectionType.ARRAY_LIST);
				String[] s = new String[20];
				int i=0;
				for(GroupOfLaptops gol: GroupManagerWindowApp.listOfGroups) {
					s[i]=gol.getName();
					i++;
				}
				String gol1 = (String)javax.swing.JOptionPane.showInputDialog(frame,"Choose first group", "Sum of groups", javax.swing.JOptionPane.PLAIN_MESSAGE , null, s, "expand...");
				for(GroupOfLaptops gol: GroupManagerWindowApp.listOfGroups) {
					if(gol.getName().equals(gol1)) {
						g1=gol;
					}
	
				}
				String gol2 = (String)javax.swing.JOptionPane.showInputDialog(frame,"Choose second group", "Sum of groups", javax.swing.JOptionPane.PLAIN_MESSAGE , null, s, "expand...");
				for(GroupOfLaptops gol: GroupManagerWindowApp.listOfGroups) {
					if(gol.getName().equals(gol2)) {
						g2=gol;
					}
				}
					listOfGroups.add(GroupOfLaptops.sumOf(g1, g2));
					content.refresh();
				}
		});
		
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Difference");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroupOfLaptops g1= new GroupOfLaptops(" ",CollectionType.ARRAY_LIST); GroupOfLaptops g2= new GroupOfLaptops(" ",CollectionType.ARRAY_LIST);
				String[] s = new String[20];
				int i=0;
				for(GroupOfLaptops gol: GroupManagerWindowApp.listOfGroups) {
					s[i]=gol.getName();
					i++;
				}
				String gol1 = (String)javax.swing.JOptionPane.showInputDialog(frame,"Choose first group", "Difference of groups", javax.swing.JOptionPane.PLAIN_MESSAGE , null, s, "expand...");
				for(GroupOfLaptops gol: GroupManagerWindowApp.listOfGroups) {
					if(gol.getName().equals(gol1)) {
						g1=gol;
					}
	
				}
				String gol2 = (String)javax.swing.JOptionPane.showInputDialog(frame,"Choose second group", "Difference of groups", javax.swing.JOptionPane.PLAIN_MESSAGE , null, s, "expand...");
				for(GroupOfLaptops gol: GroupManagerWindowApp.listOfGroups) {
					if(gol.getName().equals(gol2)) {
						g2=gol;
					}
				}
					listOfGroups.add(GroupOfLaptops.differenceOf(g1, g2));
					content.refresh();
				
			}
		});
		
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("XOR");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GroupOfLaptops g1= new GroupOfLaptops(" ",CollectionType.ARRAY_LIST);  GroupOfLaptops g2= new GroupOfLaptops(" ",CollectionType.ARRAY_LIST);
				String[] s = new String[20];
				int i=0;
				for(GroupOfLaptops gol: GroupManagerWindowApp.listOfGroups) {
					s[i]=gol.getName();
					i++;
				}
				String gol1 = (String)javax.swing.JOptionPane.showInputDialog(frame,"Choose first group", "Symmetrical difference of groups", javax.swing.JOptionPane.PLAIN_MESSAGE , null, s, "expand...");
				for(GroupOfLaptops gol: GroupManagerWindowApp.listOfGroups) {
					if(gol.getName().equals(gol1)) {
						g1=gol;
					}
	
				}
				String gol2 = (String)javax.swing.JOptionPane.showInputDialog(frame,"Choose second group",  "Symmetrical difference of groups", javax.swing.JOptionPane.PLAIN_MESSAGE , null, s, "expand...");
				for(GroupOfLaptops gol: GroupManagerWindowApp.listOfGroups) {
					if(gol.getName().equals(gol2)) {
						g2=gol;
					}
				}
					listOfGroups.add(GroupOfLaptops.symdifferenceOf(g1, g2));
					content.refresh();
				
			}
		});
	
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("AND");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GroupOfLaptops g1 = new GroupOfLaptops(" ",CollectionType.ARRAY_LIST);  GroupOfLaptops g2=new GroupOfLaptops(" ",CollectionType.ARRAY_LIST);
				String[] s = new String[20];
				int i=0;
				for(GroupOfLaptops gol: GroupManagerWindowApp.listOfGroups) {
					s[i]=gol.getName();
					i++;
				}
				String gol1 = (String)javax.swing.JOptionPane.showInputDialog(frame,"Choose first group", "Intersection of groups", javax.swing.JOptionPane.PLAIN_MESSAGE , null, s, "expand...");
				for(GroupOfLaptops gol: GroupManagerWindowApp.listOfGroups) {
					if(gol.getName().equals(gol1)) {
						g1=gol;
					}
	
				}
				String gol2 = (String)javax.swing.JOptionPane.showInputDialog(frame,"Choose second group", "Intersection of groups", javax.swing.JOptionPane.PLAIN_MESSAGE , null, s, "expand...");
				for(GroupOfLaptops gol: GroupManagerWindowApp.listOfGroups) {
					if(gol.getName().equals(gol2)) {
						g2=gol;
					}
				}
					listOfGroups.add(GroupOfLaptops.intersectionOf(g1, g2));
					content.refresh();
			}
			
		});
		
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		mntmAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String infoMessage = "<html><body width='%1s'><h1>INFO</h1>"
						+ "<p>Program Laptop -  wersja okienkowa"
				        + "<p>Autor programu: Arkadzi Zaleuski "
				        + "<p>Program zostal utworzony 12.07.2019 ";
				javax.swing.JOptionPane.showMessageDialog(frame,infoMessage,"",javax.swing.JOptionPane.INFORMATION_MESSAGE );
			}
		});
		
		mnAbout.add(mntmAuthor);
		frame.setVisible(true);
	}
}

 

