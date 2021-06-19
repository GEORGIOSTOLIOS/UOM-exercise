package bbb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import aaa.Group;



public class User implements Serializable {

private final String user_name;
private final String user_email;
private final ArrayList<User> List_Of_Friends;
private final ArrayList<Group>List_Of_Groups;
private final ArrayList<Post> pList;

 public User(String un, String ue) {
	
    this.user_name = un;
    this.user_email = ue;
    this.List_Of_Friends = new ArrayList<>();
    this.List_Of_Groups = new ArrayList<>();
    this.pList = new ArrayList<>();
    
}

 public String getUserName() {
	
	return user_name;
}
 public String getUserEmail() {
	 
	 return user_email;
 }

 public boolean isFriendwith(User aUser) {
	
	for(User f: List_Of_Friends) {
		if(f.equals(aUser)) return true;
	}
	return false;
}
 
 public ArrayList<User> getSuggestedFriends(){
	
	 ArrayList<User> sfList = new ArrayList<>();
	 
	 for(User friend :List_Of_Friends) {
		
		 ArrayList<User> tempList = FindCommonFriendsWith(friend);
		
		 if(!tempList.isEmpty()) {
			 
			   for(User friendofriend : friend.List_Of_Friends) {
				   if(!isFriendwith(friendofriend) && !sfList.contains(friendofriend)) {
					   sfList.add(friendofriend);
				   }
			   }
		 }
		 
	 }
	 return sfList;
	 
 }
 
 public void addAPost(Post p) {
	 
	 
	 pList.add(p);
 }
 
 public ArrayList<Post> getAllPosts(){
	 ArrayList<Post> totalPosts = new ArrayList<>();
	 
	 for(Post p : pList) {
		 if(!totalPosts.contains(p)) {
		 totalPosts.add(p);}
		 
	 }
	 
	 for(User aUser:List_Of_Friends) {
		 
		 for(Post p : aUser.pList) {
			
			 if(!totalPosts.contains(p)) {
				 totalPosts.add(p);
				
			 }
		 }
	 }
	 
	 
	 Collections.sort(totalPosts);
	 return totalPosts;
	 
 }



 public void addToFriends(User aUser){
 
	if(!aUser.equals(this) && !isFriendwith(aUser)) {
		List_Of_Friends.add(aUser);
		aUser.List_Of_Friends.add(this);
		System.out.println(this.user_name+" and "+aUser.user_name+" are now friends!");}
		
   
	
	
}
@SuppressWarnings("unchecked")
public ArrayList<User> FindCommonFriendsWith(User aUser) {
  
 ArrayList<User> cfsList;
 cfsList =(ArrayList<User>)List_Of_Friends.clone(); 
 cfsList.retainAll(aUser.List_Of_Friends);
 
 return cfsList;

  
	 
	 
	
}

public void printCommonFriendsWith(User aUser,ArrayList<User> cfsList) {
	System.out.println("**************************************\t");
	  System.out.println("Common friends of "+this.user_name+" and "+aUser.user_name);
	  System.out.println("**************************************");


	  for(int i =0; i<cfsList.size(); i++) {
			System.out.println(i+1+": "+cfsList.get(i).user_name);
			
			
			
		}
		   System.out.println("--------------------------------------\t");

}

 public void printUserFriends() {
	System.out.println("**************************************\t");
	System.out.println("Friends of "+this.user_name);
	System.out.println("**************************************\t");
	
	for(int i=0; i<List_Of_Friends.size(); i++) {
		System.out.println(i+1+": "+List_Of_Friends.get(i).user_name);
	}
	System.out.println("--------------------------------------\t");
	
	
}
 public void addAGroup(Group g) {
	List_Of_Groups.add(g);
}

 public void printUserGroups() {
	System.out.println("************************************** ");
	System.out.println("Groups that "+this.user_name+" has been enrolled in");
	System.out.println("**************************************\t ");
	
	
	
	for(int i =0; i<List_Of_Groups.size(); i++) {
		System.out.println(i+1+": "+List_Of_Groups.get(i).getGroupName());
	}
	System.out.println("--------------------------------------");
}

 public ArrayList<String> FindPossibleCases() {
	System.out.println(this.user_name+"  has been infected. The following users have to be tested");
	System.out.println("*******************************");
	
	ArrayList<String> pcList = new ArrayList<>();
	
	for(User userfriend: List_Of_Friends) {
		 if(!pcList.contains(userfriend.user_name) && !userfriend.equals(this))	pcList.add(userfriend.user_name);
	
		
		 for(User friendOfUserFriend:userfriend.List_Of_Friends) {
			   
			 if(!pcList.contains(friendOfUserFriend.user_name) && !friendOfUserFriend.equals(this)  ) {
				 pcList.add(friendOfUserFriend.user_name);
			 }
		
		   
			
		}
		  
		  
	
	
	}
	 Collections.sort(pcList);
	 return pcList;
		
}
 
 public void printPossibleCases(ArrayList<String> pcList) {
	 
	 for(String pCaseName: pcList) {
		 System.out.println(pCaseName);
	 }
	 
	 System.out.println("-----------------------------");
 }




	@Override
	public boolean equals(Object obj) {
     	if (!(obj instanceof User)) return false;
 	 	return ((User) obj).getUserEmail().equals(this.user_email);
   }
}







