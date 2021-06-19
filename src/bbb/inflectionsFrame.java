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
	private final JButton backButton;
private final LoginFrame login;

	@SuppressWarnings("SpellCheckingInspection")
	public inflectionsFrame(User aUser, LoginFrame lf) {
		JPanel panel = new JPanel();
	login = lf;

		JList<String> uList = new JList<>();
	DefaultListModel<String> model = new DefaultListModel<>();
		ArrayList<String> cList = aUser.FindPossibleCases();
	model.addElement("************************************************************************\t");
	model.addElement(aUser.getUserName()+  " has been infected. The following users have to be tested");
	model.addElement("************************************************************************\t");
	for(String name: cList) {
		model.addElement(name);
	}
	uList.setModel(model);
	
	panel.add(uList);
	
	backButton = new JButton("Back to Login Screen");
	panel.add(backButton);
	
	this.setContentPane(panel);
	
	ButtonListener listener = new ButtonListener();
	backButton.addActionListener(listener);
	
	
	
	this.setVisible(true);
	this.setSize(400, 400);
	this.setTitle("������ �������� ���");
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
