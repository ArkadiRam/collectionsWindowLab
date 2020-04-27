import java.awt.Color;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/*
 * Autor: Arkadzi Zaleuski 250929
 * Data: 09-12-2019
 
*/

public class Table extends JScrollPane{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private JTable table;
		
	 
		private DefaultTableModel tableModel;
		
		
	public Table(String[] tableheader) {
		
		tableModel = new DefaultTableModel(tableheader, 0);
		table = new JTable(tableModel);
		table.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
		table.setCellSelectionEnabled(true);
		table.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table.setForeground(Color.BLACK);
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBounds(10, 11, 530, 327);
		JTableHeader myTableHeader = table.getTableHeader();
		myTableHeader.setBackground(Color.GRAY);
		
		table = new JTable(tableModel) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false; 
			}
		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setRowSelectionAllowed(true);

		setViewportView(table);
		
	}
	
	public void refresh() {
		tableModel.setRowCount(0);
		for(GroupOfLaptops gol: GroupManagerWindowApp.listOfGroups)
		{
			String []row = {gol.getName(),gol.getType(),Integer.toString(gol.size())};
				tableModel.addRow(row);
		}

	}
	
	public void refreshFrom(GroupOfLaptops group) {
		tableModel.setRowCount(0);
		for(Laptop gol: group.collection)
		{
			String []row = {gol.getBrand(),gol.getModel(),Integer.toString(gol.getYear())};
				tableModel.addRow(row);
		}
	}
	int getSelectedIndex(){
		int k =  table.getSelectedRow();
		if(k<0)
		{
			javax.swing.JOptionPane.showMessageDialog(this,"Nothing is selcted"," " ,javax.swing.JOptionPane.ERROR_MESSAGE);
		}
		return k;
	}
		
}