package bbb;

public class DataEntry {

	public static User   isValidEmail(String name,String email) {
	  
		if(email.length()>=17  && email.indexOf('@')>=6) {
	
		 String am_direction_name = email.substring(0 ,3);
		 String email_type = email.substring(email.indexOf('@'));
		 String am_numbers = email.substring(3, email.indexOf('@'));
		
		
		

		
		 if(email_type.equals("@uom.edu.gr") && areDigits(am_numbers) && am_numbers.length()>=3 && am_numbers.length()<=5 ) {
			
			
			if(am_direction_name.equals("ics") || am_direction_name.equals("iis") || am_direction_name.equals("dai") ) {

				return new User(name, email);
				
			
			
		}}
			
		
		
		}
		System.out.println("User " +name+" has not been created. Email format is not acceptable. ");
		return null;
		
		}
		
		
		
	
	 private static boolean areDigits(String am_numbers) {
		
		for(int i=0; i<am_numbers.length(); i++) {
			if(!Character.isDigit(am_numbers.charAt(i))) {
				return false;
				
			}
		}
		return true;}
}
