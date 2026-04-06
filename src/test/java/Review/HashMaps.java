package Review;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class HashMaps{

	@Test
	void hashMaps() {
		
		HashMap<String, Object> data = new HashMap<>(); //it stores data in key-value pairs.

		data.put("name", "Jason Mamua");
		data.put("age", 21);
		data.put("grade", "F");
		
		String courseArry[] = {
				"Python",".NET"
		};
		data.put("courses", courseArry);
		
		System.out.println("Data Size : "+data.size()); // printing the size of data in hasmap
		
//		Way 1:
		for (Map.Entry<String, Object> entry : data.entrySet()) // printing the Whole data using the for loop
		{
			String key = entry.getKey(); //storing the key entries in key value.
			Object value = entry.getValue(); // storing the value entiries in value.
			
			
			if(value instanceof String[]) { // finding is there any array available in value.
				System.out.println(key + " : " + Arrays.toString((String[]) value)); // if array is found then convert it string and print it.
				
			}else { // if array not found then print other key value data.
				System.out.println(key + " : " + value);
			}
		}
		
//		way 2:
		
		data.forEach((key,value) -> {
//			System.out.println(key + "=>" +value);
			if(value instanceof String[]) {
				System.out.println(key + " : " + Arrays.toString((String[]) value));
			}else {
				System.out.println(key + " : "+value);
			}
		});
		
		System.out.println(Arrays.toString((String[]) data.get("courses")));
		
	}
}
