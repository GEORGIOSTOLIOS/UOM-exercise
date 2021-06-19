package bbb;







import java.io.Serializable;
import java.text.DateFormat;

import java.util.Date;
import java.util.Locale;





public class Post implements Comparable<Post>,Serializable {

private Date timestamp;
private final String aPost;
private final User aUser;
private static final Locale GREEK_LOCALE = new Locale("el", "GR");
public static final DateFormat USER_DF_TIME = DateFormat.getDateTimeInstance(DateFormat.LONG,
        DateFormat.SHORT,GREEK_LOCALE );
final String dateString =USER_DF_TIME.format(timestamp= new Date());







public Post(User u,String p) {
	aUser= u;
	aPost = p;
	
}
public User getWhoPosted() {
	return aUser;
	
}
public void setDateTime(Date d) {
    this.timestamp = d;
  }

public String getTime() {
	
	
	return dateString;
	
	
}

public String getPost() {
	// TODO Auto-generated method stub
	return aPost;
}
@Override
public int compareTo(Post p) {
	// TODO Auto-generated method stub
	return p.timestamp.compareTo(this.timestamp);
}

	@Override
	public boolean equals(Object obj) {
		return ((Post)obj).getPost().equals(this.aPost);
	}
}
