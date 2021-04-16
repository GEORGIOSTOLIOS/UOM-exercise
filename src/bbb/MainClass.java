package bbb;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


import aaa.ClosedGroup;
import aaa.Group;



public class MainClass {

	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		File f = new File("save.ser");
		File f1 = new File("save1.ser");
		ArrayList<User> existingUsers = new ArrayList<User>();
		ArrayList<Group> gList = new ArrayList<Group>();
		if(!f.exists()) {
			System.out.println(" den yparxei");
	
		User u1 = DataEntry.isValidEmail("Makis", "iis1998@uom.edu.gr");
		User u2 = DataEntry.isValidEmail("Petros", "ics1924@uom.edu.gr");
		User u3 = DataEntry.isValidEmail("Maria", "iis2012@uom.edu.gr");
		User u4 = DataEntry.isValidEmail("Gianna", "iis19133@uom.edu.gr"); 
		User u5 = DataEntry.isValidEmail("Nikos", "dai1758@uom.edu.gr");
		User u6 = DataEntry.isValidEmail("Babis", "ics19104@uom.edu.gr");
		User u7 = DataEntry.isValidEmail("Stella", "dai1827@uom.edu.gr");
		User u8 = DataEntry.isValidEmail("Eleni", "ics2086@gmail.com");
		
		
		
		u1.addToFriends(u2);
		u1.addToFriends(u5);
		u5.addToFriends(u6);
		u3.addToFriends(u4);
		u3.addToFriends(u2);
		u4.addToFriends(u6);
		u5.addToFriends(u3);
		u1.addToFriends(u6);
		u5.addToFriends(u2);
		u7.addToFriends(u1);
		
		ArrayList<User> commonFriends = new ArrayList<User>();
		commonFriends = u5.FindCommonFriendsWith(u4);
		u5.printCommonFriendsWith( u4,commonFriends);
		commonFriends = u1.FindCommonFriendsWith(u5);
		u1.printCommonFriendsWith(u5,commonFriends);
		
		u1.printUserFriends();
		u3.printUserFriends();
		
		
		Group g1 = new Group("WebGurus","A group for web passionates");
		ClosedGroup g2 = new ClosedGroup("ExamSolutions","Solutions to common exam questions"); 
		
		
		g1.addUserInGroup(u4);
		g1.addUserInGroup(u3);
		g1.addUserInGroup(u2);
		
		g2.addUserInGroup(u4);
		
		g2.addUserInGroup(u6);
		g2.addUserInGroup(u5);
		
		u4.printUserGroups();
		
		g1.printMembersOfGroup();
		g2.printMembersOfGroup();
		
		ArrayList<String> pcList = new ArrayList<String>();
		pcList = u4.FindPossibleCases();
		u4.printPossibleCases(pcList);
		
		
		existingUsers.add(u1);
		existingUsers.add(u2);
		existingUsers.add(u3);
		existingUsers.add(u4);
		existingUsers.add(u5);
		existingUsers.add(u6);
		existingUsers.add(u7);
		
		
		gList.add(g1);
		gList.add(g2);}
		
		
		
		
		else {
			System.out.println(" yparxei");
			try {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream oist = new ObjectInputStream(fis);
				existingUsers =(ArrayList<User>) oist.readObject();
				oist.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				FileInputStream fis = new FileInputStream(f1);
				ObjectInputStream oist = new ObjectInputStream(fis);
				gList =(ArrayList<Group>) oist.readObject();
				oist.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println(existingUsers.get(0).getUserName());
		 LoginFrame lf = new LoginFrame(existingUsers,gList);}
		
		}


