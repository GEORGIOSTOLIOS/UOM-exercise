package bbb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import aaa.Group;



public class User implements Serializable {

private String user_name;
private String user_email;
private ArrayList<User> List_Of_Friends;
private ArrayList<Group>List_Of_Groups;
private ArrayList<Post> pList;

 public User(String un, String ue) {
	
    this.user_name = un;
    this.user_email = ue;
    this.List_Of_Friends = new ArrayList<User>();
    this.List_Of_Groups = new ArrayList<Group>();
    this.pList = new ArrayList<Post>();
    
}

 public String getUserName() {
	
	return user_name;
}
 public String getUserEmail() {
	 
	 return user_email;
 }

 public boolean isFriendwith(User aUser) {
	
	for(User f: List_Of_Friends) {
		if(f.user_email.equals(aUser.user_email)) return true;
	}
	return false;
}
 
 public ArrayList<User> getSuggestedFriends(){
	
	 ArrayList<User> sfList = new ArrayList<User>();
	 
	 for(User friend :List_Of_Friends) {
		
		 ArrayList<User> tempList = FindCommonFriendsWith(friend);
		
		 if(!tempList.isEmpty()) {
			 
			   for(User friendofriend : friend.List_Of_Friends) {
				   if(!isFriendwith(friendofriend) && !containsUser(sfList, friendofriend) && friendofriend.user_email != this.user_email) {
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
	 ArrayList<Post> totalPosts = new ArrayList<Post>();
	 
	 for(Post p : pList) {
		 if(!containsPost(totalPosts,  p)) {
		 totalPosts.add(p);}
		 
	 }
	 
	 for(User aUser:List_Of_Friends) {
		 
		 for(Post p : aUser.pList) {
			
			 if(!containsPost(totalPosts,  p)) {
				 totalPosts.add(p);
				
			 }
		 }
	 }
	 
	 
	 Collections.sort(totalPosts);
	 return totalPosts;
	 
 }



 public void addToFriends(User aUser){
 
	if(aUser.user_email != this.user_email && !isFriendwith(aUser)) {
		List_Of_Friends.add(aUser);
		aUser.List_Of_Friends.add(this);
		System.out.println(this.user_name+" and "+aUser.user_name+" are now friends!");}
		
   
	
	
}
@SuppressWarnings("unchecked")
public ArrayList<User> FindCommonFriendsWith(User aUser) {
  
 ArrayList<User> cfsList = new ArrayList<User>();
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
	
	ArrayList<String> pcList = new ArrayList<String>();
	
	for(User userfriend: List_Of_Friends) {
		 if(!pcList.contains(userfriend.user_name) && userfriend.user_name != this.user_name)	pcList.add(userfriend.user_name);
	
		
		 for(User friendOfUserFriend:userfriend.List_Of_Friends) {
			   
			 if(!pcList.contains(friendOfUserFriend.user_name) && friendOfUserFriend.user_name != this.user_name) {
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
 
 public boolean containsPost( ArrayList<Post> pList,  Post p){

                for(Post po : pList) {
                	if(p.getPost().equals(po.getPost()))return true;
                }
                return false;
	}
 
 public boolean containsUser(ArrayList<User> uList,User aUser) {
	 for(User u : uList) {
		 
		 if(u.user_email.equals(aUser.user_email)) {
			 return true;
		 }
	 }
	 return false;
 }

 

}







