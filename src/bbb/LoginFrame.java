package bbb;





import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import aaa.Group;




@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private final JTextField namefield;
private final JTextField emailField = new JTextField("user email",6);
private final JButton userpagebtn;
	private final ArrayList<User> uList;
private ArrayList<Group> gList;
private final LoginFrame lFrame;


public LoginFrame(ArrayList<User> uL, ArrayList<Group> gL) {
	JPanel panel = new JPanel();
	uList = uL;
	gList = gL;
	lFrame = this;
	
	
	userpagebtn = new JButton("Enter User Page");
	JButton inflectionsbtn = new JButton("Show Potential Infections");
	namefield = new JTextField("user name ",6);
	JButton createUser = new JButton("New User");
	panel.add(createUser);
	panel.add(namefield);
	panel.add(emailField);
	panel.add(userpagebtn);
	panel.add(inflectionsbtn);
	JButton saveButton = new JButton("Save PamakBook");
	panel.add(saveButton);
	
	
	
	ButtonListener listener = new ButtonListener();
	userpagebtn.addActionListener(listener);
	inflectionsbtn.addActionListener(listener);
	createUser.addActionListener(arg0 -> {
		String user_name = namefield.getText().trim();

		boolean exists = false;

		for(User user : uList) {

			if(user.getUserName().equals(user_name)) {
				exists = true;
				break;
			}
		}
		if(exists) {
			JOptionPane.showMessageDialog(new JPanel(), "User "+user_name+" already exists");
		}
		else {
			{
				User aUser = DataEntry.isValidEmail(user_name, emailField.getText());
				if(aUser == null) {
					JOptionPane.showMessageDialog(new JPanel(), "Invalid User Email");
				}
				else {
					uList.add(aUser);
					System.out.println(aUser.getUserName());
				}
			}
		}

	});
	saveButton.addActionListener(arg0 -> {

		try {

			File f = new File("save.ser");

			FileOutputStream fouts = new FileOutputStream(f);
			ObjectOutputStream douts = new ObjectOutputStream(fouts);
			douts.writeObject(uList);
			douts.close();
			fouts.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			File f1 = new File("save1.ser");
			FileOutputStream fouts = new FileOutputStream(f1);
			ObjectOutputStream douts = new ObjectOutputStream(fouts);
			douts.writeObject(gList);
			douts.close();
			fouts.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	});
	
	this.setContentPane(panel);
	
	this.setVisible(true);
	this.setResizable(true);
	this.setSize(350,150);
	this.setTitle("�������� ������");
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
}

class ButtonListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		String selectedUser = namefield.getText().trim();
		User sUser = null;
		
		
		for(User aUser : uList) {
			
			if(aUser.getUserName().equals(selectedUser)) {
				sUser = aUser;
				break;
			}
			
		}
		
		if(sUser == null) {
			JOptionPane.showMessageDialog(new JPanel(), "User "+selectedUser+" Not Found");
		}
		else if(e.getSource().equals(userpagebtn)) {
			setVisible(false);
			new UserPageFrame(sUser,lFrame,uList,gList);
			
		}
		
		else {
			setVisible(false);
			new inflectionsFrame(sUser, lFrame);
		}
		
		
		
	}
	
}

	

	
	

}
