package services.test;

import org.json.JSONObject;

public class UserCreationTest {

	public static void main (String args[]){
		JSONObject user1 = services.UserCreation.createUser("bertrandclement", "jesuisunepatate", "bdclement@orange.fr", "57");
		System.out.println(user1);
		JSONObject user2 = services.UserCreation.createUser("bertrandclement", "jesuisunepatate", "bdclement@orange.fr", "57");
		System.out.println(user2);
		JSONObject user3 = services.UserCreation.createUser("clementbertrand", "jesuisunepatate", "bdclement@orange.fr", "57");
		System.out.println(user3);
	}
	
}
