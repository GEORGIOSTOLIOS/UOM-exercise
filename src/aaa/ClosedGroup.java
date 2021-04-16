package aaa;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bbb.User;

public class ClosedGroup extends Group{

	public ClosedGroup(String gn, String gd) {
		super(gn, gd);
		// TODO Auto-generated constructor stub
	}
	
	
  public void addUserInGroup(User aUser) {
	
	
	if(List_of_Users.isEmpty()) {
		super.addUserInGroup(aUser);
	}
	
	else {
		
		boolean isFriend = false;
		
		for(User userOfThisGroup: List_of_Users) {
			
			if(aUser.isFriendwith(userOfThisGroup)) {
				super.addUserInGroup(aUser);
				isFriend = true;
				break;
			}
			
			
		}
		if(!isFriend) {
			JOptionPane.showMessageDialog(new JPanel(), "User "+aUser.getUserName()+" can not be enrolled");
		}
	}

}

}
