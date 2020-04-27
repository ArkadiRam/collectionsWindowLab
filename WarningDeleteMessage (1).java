import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.Iterator;



/*
 * Autor: Arkadzi Zaleuski 250929
 * Data: 09-12-2019
 *
 * 
*/
public class WarningDeleteMessage extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2663728651841042465L;
	JPanel panel = new JPanel();
	Font font = new Font("MonoSpaced", Font.BOLD, 12);
	
	JButton okButton =  new JButton(" OK ");
	JButton cancelButton = new JButton("CANCEL");
	JLabel label = new JLabel();
	boolean result = true;
	
	public WarningDeleteMessage(Window parent, String message)
	{
		super(parent, Dialog.ModalityType.DOCUMENT_MODAL);
		
		this.setTitle("Warning");
		setLocationRelativeTo(parent);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(450, 280);
		setResizable(false);
		setLocationRelativeTo(null);
		
		okButton.setPreferredSize(new Dimension(200,40));
		cancelButton.setPreferredSize(new Dimension(200,40));
		label.setFont(font);
		panel.add(okButton);
		panel.add(cancelButton);
		label.setText(message);
		panel.add(label, BorderLayout.CENTER);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		setContentPane(panel);
	
		setVisible(true);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent event) {
		 Object eventSource = event.getSource();
		if(eventSource == okButton) {
		
			Object[] arrayOfLaptops =  MyWindowApp.group.collection.toArray();
			java.util.Iterator<Laptop> iter = MyWindowApp.group.iterator();
			while(iter.hasNext()) {
			    Laptop l = iter.next();
			    if (l== (Laptop)arrayOfLaptops[MyWindowApp.table.getSelectedIndex()]) {
			        iter.remove();
			    }
			}
			 dispose();
		}else if(eventSource == cancelButton) {
			result = false;
			dispose();
		}
		
		
	}

}
