package Day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;


public class FakerLibrary {
	@Test
	void fakerLibraryTest() {
		Faker faker = new Faker();
		
		String fullName = faker.name().fullName();
		String email = faker.internet().safeEmailAddress();
		
		String ancient = faker.ancient().hero();
		System.out.println(ancient);
		
		//https://github.com/dius/java-faker 
				
	}

}
