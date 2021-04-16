package bbb;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import aaa.Group;

@SuppressWarnings("serial")
public class UserPageFrame extends JFrame{
	
private JPanel panel = new JPanel(new BorderLayout(50,100));
private JTextField nameField;
private JTextField emailField;
private JTextField aNewFriendField = new JTextField(6);
private JButton backButton;
private JButton addButton = new JButton("Add to Friends");
private JButton enrollButtton = new JButton("Enroll");
private JTextArea PostField;
private JTextArea allPostsField = new JTextArea();
private JPanel northPanel = new JPanel();
private JPanel centralPanel = new JPanel();
private JPanel southPanel = new JPanel();
private JButton postButton = new JButton("Post");
private JLabel recentPosts = new JLabel("Recent Posts by Friends");
private JLabel suggestedFriends = new JLabel("Suggested Friends");
private JList<String> uList = new JList<String>();
private JList<String> gList = new JList<String>();
private LoginFrame lFrame;
private User u;
private ArrayList<User> existingUsers = new ArrayList<User>();
private ArrayList<Group> existingGroups = new ArrayList<Group>();





public UserPageFrame(User aUser,LoginFrame lf,ArrayList<User> ul, ArrayList<Group> gL) {
existingUsers = ul;	
existingGroups = gL;
nameField = new JTextField(aUser.getUserName(),10);
emailField = new JTextField(aUser.getUserEmail(),20);
backButton = new JButton("Back to Login Screen");
PostField = new JTextArea();
lFrame = lf;
u = aUser;

DefaultListModel<String> model = new DefaultListModel<String>();
ArrayList<User> sfList = aUser.getSuggestedFriends();
for(User u: sfList) {
	model.addElement(u.getUserName());
}
uList.setModel(model);

DefaultListModel<String> model1 = new DefaultListModel<String>();
for(Group g: existingGroups) {
	String aGroup = g.getGroupName();
	model1.addElement(aGroup);
}
gList.setModel(model1);


PostField.setPreferredSize( new Dimension(600 , 250 ) );
allPostsField.setPreferredSize( new Dimension(600 , 250 ) );

ArrayList<Post> totalPosts = u.getAllPosts();
for(Post p:totalPosts ) {
	allPostsField.setText(allPostsField.getText()+"\r\n"+p.getWhoPosted().getUserName()+", "+p.getTime()+", "+p.getPost());
}





northPanel.add(nameField);
northPanel.add(emailField);
northPanel.add(backButton);
panel.add(northPanel,BorderLayout.NORTH);
centralPanel.add(PostField);
centralPanel.add(postButton);
centralPanel.add(recentPosts);
centralPanel.add(allPostsField);
centralPanel.add(suggestedFriends);
centralPanel.add(uList);
panel.add(centralPanel,BorderLayout.CENTER);
southPanel.add(aNewFriendField);
southPanel.add(addButton);
southPanel.add(gList);
southPanel.add(enrollButtton);
panel.add(southPanel,BorderLayout.SOUTH);

ButtonListener2 listener = new ButtonListener2();
postButton.addActionListener(listener);
backButton.addActionListener(listener);
addButton.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String searchedUser = aNewFriendField.getText();
		
		User existedUser = null;
		
		for(User u : existingUsers) {
			System.out.println(u.getUserName()+","+searchedUser);
			if(u.getUserName().equals(searchedUser)) {
				existedUser = u;
				break;
			}
			
		}
		
		if(existedUser==null) {
			JOptionPane.showMessageDialog(new JPanel(), "This user does not exist");
			
		}
		else if(existedUser.isFriendwith(u)) {
			JOptionPane.showMessageDialog(new JPanel(), "You are already friends with this user");
		}
		else if(!existedUser.equals(u.getUserName())) {
			u.addToFriends(existedUser);
			
		}
		
	}
});

enrollButtton.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		String selectedGroupName = (String)gList.getSelectedValue();
		
		Group selectedGroup = null;
		
		for(Group g:existingGroups) {
			
			if(g.getGroupName().equals(selectedGroupName)) {
				selectedGroup = g;
				break;
			}
			
		}
		
		if(selectedGroup == null) {
			
			JOptionPane.showMessageDialog(new JPanel(), "Please select a group");
		}
		else if(selectedGroup.hasTheMember(u)) {
			JOptionPane.showMessageDialog(new JPanel(), "Already Enrolled");
			
		}
		else {
			selectedGroup.addUserInGroup(u);
		}
		
	}
});

this.setContentPane(panel);

this.setVisible(true);
this.setSize(800, 900);
this.setTitle("Σελίδα Χρήστη");
this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

}



class ButtonListener2 implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource().equals(backButton) ) {
			setVisible(false);
			lFrame.setVisible(true);
			
		}
		else {
			Post p = new Post(u, PostField.getText());
			u.addAPost(p);
			ArrayList<Post> totalPosts = u.getAllPosts();
			allPostsField.setText("");
			PostField.setText("");
			for(Post aPost : totalPosts) {
				
				allPostsField.append(aPost.getWhoPosted().getUserName()+", "+aPost.getTime()+","+aPost.getPost() + "\n");
			}
		    
			
			
		}
		
	}
	
}

}
