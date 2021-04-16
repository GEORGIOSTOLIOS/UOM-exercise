package aaa;

import java.io.Serializable;
import java.util.ArrayList;

import bbb.User;

public class Group implements Serializable{

protected String group_name;
protected String group_description;
protected ArrayList<User> List_of_Users;

	public Group(String gn,String gd) {
		group_name = gn;
		group_description = gd;
		List_of_Users = new ArrayList<User>();
	}
	
	public String getGroupName() {
		return group_name;
	}
	
	
	
	public boolean hasTheMember(User aUser) {
		
		for(User u:List_of_Users) {
			if(aUser.getUserName().equals(u.getUserName())) return true;
			
		}
		return false;
	}
	
	
	public void addUserInGroup(User aUser) {
		
		if(!hasTheMember(aUser)) {
			List_of_Users.add(aUser);
			aUser.addAGroup(this);
			System.out.println(aUser.getUserName()+" has been successfully enrolled in group "+group_name);
		}
		else {
			System.out.println("FAILED:"+aUser.getUserName()+" cannot be enrolled in group "+group_name);
		}
		
	}
	
	public void printMembersOfGroup() {
		
		System.out.println("*******************************");
		System.out.println("Members of group "+group_name);
		System.out.println("*******************************\t");
		
		for (int i = 0; i < List_of_Users.size(); i++) {
			
			System.out.println((i+1)+": "+List_of_Users.get(i).getUserName());
			
		}
		
		
		
		
		
		
	}
	
	
     
}
