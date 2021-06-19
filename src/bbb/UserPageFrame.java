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

	private final JTextField aNewFriendField = new JTextField(6);
private final JButton backButton;
	private final JTextArea PostField;
private final JTextArea allPostsField = new JTextArea();
	private final JList<String> gList = new JList<>();
private final LoginFrame lFrame;
private final User u;
private ArrayList<User> existingUsers = new ArrayList<>();
private ArrayList<Group> existingGroups = new ArrayList<>();





public UserPageFrame(User aUser,LoginFrame lf,ArrayList<User> ul, ArrayList<Group> gL) {
existingUsers = ul;	
existingGroups = gL;
	JTextField nameField = new JTextField(aUser.getUserName(), 10);
	JTextField emailField = new JTextField(aUser.getUserEmail(), 20);
backButton = new JButton("Back to Login Screen");
PostField = new JTextArea();
lFrame = lf;
u = aUser;

DefaultListModel<String> model = new DefaultListModel<>();
ArrayList<User> sfList = aUser.getSuggestedFriends();
for(User u: sfList) {
	model.addElement(u.getUserName());
}
	JList<String> uList = new JList<>();
	uList.setModel(model);

DefaultListModel<String> model1 = new DefaultListModel<>();
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


	JPanel northPanel = new JPanel();
	northPanel.add(nameField);
northPanel.add(emailField);
northPanel.add(backButton);
	JPanel panel = new JPanel(new BorderLayout(50, 100));
	panel.add(northPanel,BorderLayout.NORTH);
	JPanel centralPanel = new JPanel();
	centralPanel.add(PostField);
	JButton postButton = new JButton("Post");
	centralPanel.add(postButton);
	JLabel recentPosts = new JLabel("Recent Posts by Friends");
	centralPanel.add(recentPosts);
centralPanel.add(allPostsField);
	JLabel suggestedFriends = new JLabel("Suggested Friends");
	centralPanel.add(suggestedFriends);
centralPanel.add(uList);
panel.add(centralPanel,BorderLayout.CENTER);
	JPanel southPanel = new JPanel();
	southPanel.add(aNewFriendField);
	JButton addButton = new JButton("Add to Friends");
	southPanel.add(addButton);
southPanel.add(gList);
	JButton enrollButtton = new JButton("Enroll");
	southPanel.add(enrollButtton);
panel.add(southPanel,BorderLayout.SOUTH);

ButtonListener2 listener = new ButtonListener2();
postButton.addActionListener(listener);
backButton.addActionListener(listener);
addButton.addActionListener(arg0 -> {

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
	else if(!existedUser.equals(u)) {
		u.addToFriends(existedUser);

	}

});

enrollButtton.addActionListener(arg0 -> {

	String selectedGroupName = gList.getSelectedValue();

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

});

this.setContentPane(panel);

this.setVisible(true);
this.setSize(800, 900);
this.setTitle("������ ������");
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
