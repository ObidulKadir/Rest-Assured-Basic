package Review;

import java.util.Arrays;
import java.util.HashMap;

import org.testng.annotations.Test;


public class HashMap2 {
	@Test
	void hashMaps() {
	    // String Key ebong Object Value (je kono dhoroner data rakhar jonno) diye HashMap create kora hoyeche
	    HashMap <String, Object> abc = new HashMap<>(); 
	    
	    // "Student Name" key-te String value "Habib" rakha hoyeche
	    abc.put("Student Name", "Habib"); 
	    
	    // "age" key-te Integer value 21 rakha hoyeche
	    abc.put("age", 21); 
	    
	    // "Gender" key-te String value "F" rakha hoyeche
	    abc.put("Gender", "F"); 
	    
	    // Ekti String Array create kora hoyeche
	    String subjectArr[] = {"a", "b"}; 
	    
	    // "Courses" key-te puro array-tike value hisebe rakha hoyeche
	    abc.put("Courses", subjectArr); 
	    
	    // Puro HashMap-ti console-e print kora hoyeche
	    System.out.println(abc); 
	    
	    // Shudhumatro "Gender" key-er value-ti (F) print kora hoyeche
	    System.out.println(abc.get("Gender")); 
	    
	    // 'abc' variable-ti kon class-er object (eikhane HashMap) seta print kora hoyeche
	    System.out.println(abc.getClass().getSimpleName()); 
	    
	    // Lambda function-er maddhome map-er protiti Key ebong Value loop chalano hoyeche
	    abc.forEach((Key, value) -> {
	        
	        // Check kora hocche value-ti ki asholei ekta String Array (String[]) kina
	        if (value instanceof String[]) {
	            // Value-ke Object theke String Array-te convert (casting) kora hocche
	            String[] bc = (String[]) value;
	            // Arrays.toString() bebohar kore array-er element gulo print kora hocche
	            System.out.println(Key + " : " + Arrays.toString((bc)));
	        } else {
	        
	        // HashMap-er protiti Key ebong Value sorasori print kora hocche
	        System.out.println("Key : " + Key + ", Value: " + value);
	        }
	    });
	}
	
		
	
	

}
