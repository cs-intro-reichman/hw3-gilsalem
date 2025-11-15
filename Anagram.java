import java.util.Random;

/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  
	
public static int counter(String str, char ch)
 {
    int count = 0;
    
    for (int i = 0; i < str.length(); i++) {
        if (str.charAt(i) == ch) {
            count++; 
    }
}
    
    return count;
}



	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		// Replace the following statement with your code
		boolean flag=true;
		str1=preProcess(str1);
		str2=preProcess(str2);
		int count1=0;
		int count2=0;
	if(str1.length()!=str2.length())
	{
		flag=false;
		return flag;
	}
	else
	{
		for(int i=0;i<str1.length();i++)
		{
			char ch=str1.charAt(i);
			
				if(str2.contains(Character.toString(ch)))
				{
					count1=counter(str1,ch);
				}
		
				if(str2.contains(Character.toString(ch)))
				{
					count2=counter(str2,ch);
				}
			if(count1!=count2)
			{
				flag=false;
				break;
			}
			count1=0;
			count2=0;
		}
	}
		return flag;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {

		String strnew="";
		// Replace the following statement with your code
		for(int i=0;i<str.length();i++)
		{
 			char ch=str.charAt(i);

			if ((Character.isLetter(ch))) 
			{
				ch=Character.toLowerCase(ch);
				strnew=strnew+ch;

				
			}
		
		}
		
		return strnew;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		Random random = new Random();
		StringBuilder source = new StringBuilder(str);
		StringBuilder result = new StringBuilder();
		while (source.length() > 0) {
			int index = random.nextInt(source.length());
			char ch = source.charAt(index);
			result.append(ch);
			source.deleteCharAt(index);
		}
		
		return result.toString();
	}
}
