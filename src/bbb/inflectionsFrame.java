package bbb;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;




@SuppressWarnings("serial")
public class inflectionsFrame extends JFrame {
private JPanel panel;
private JList<String> ulist;
private JButton backButton;
private LoginFrame login;

private ArrayList<String> cList = new ArrayList<String>();

public inflectionsFrame(User aUser,LoginFrame lf) {
	panel = new JPanel();
	login = lf;
	
	ulist = new JList<String>();
	DefaultListModel<String> model = new DefaultListModel<String>();
	cList = aUser.FindPossibleCases();
	model.addElement("************************************************************************\t");
	model.addElement(aUser.getUserName()+  " has been infected. The following users have to be tested");
	model.addElement("************************************************************************\t");
	for(String name: cList) {
		model.addElement(name);
	}
	ulist.setModel(model);
	
	panel.add(ulist);
	
	backButton = new JButton("Back to Login Screen");
	panel.add(backButton);
	
	this.setContentPane(panel);
	
	ButtonListener listener = new ButtonListener();
	backButton.addActionListener(listener);
	
	
	
	this.setVisible(true);
	this.setSize(400, 400);
	this.setTitle("Πιθανή Μετάδοση Ιού");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	
	
}

class ButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == backButton) {
		    
			setVisible(false);
			login.setVisible(true);
			
			
		}
		
	}
	
}

}
