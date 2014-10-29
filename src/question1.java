/*
 * Given a string, 'stringToLong' routine converts the string to a long, without using the built in functions. 
 * Limitations: Input string can be of maximum 19 characters as 'long' cannot be more than 19 numbers.
 * Input string should only have numbers between 0 and 9. It can be a negative number. 
 */
public class question1 {
	public static long stringToLong(String str){		
		// In case of a ',' or '.' takes only the initial part of the string. 
		if(str.contains(",")){
			str = str.split(",")[0];
		}
		else if(str.contains(".")){
			str = str.replace('.', ' ');
			str = str.split(" ")[0];
		}
		int start = 0;
		int size = str.length();
		boolean neg = str.startsWith("-");
		boolean pos = str.startsWith("+");
		boolean endL = str.endsWith("L") || str.endsWith("l");
		boolean error = false;
		
		//Handling strings with negative, positive sign and 'L'.
		start = (neg||pos) ? 1: 0;
		int end = endL ? size - 1: size;
		
		long result = 0;
		
		/*Gives an error if the string has any special character or alphabets*/
		for(int i= start; i<end; i++){
			char ch = str.charAt(i);
			if(ch < 48 || ch > 57){
				error = true;
				break;
			}
			else{
				result = result * 10 + Character.digit(ch, 10);
			}
		}
		if(error){
			System.out.println("Error");
		}
		result = neg? -result:result;
		System.out.println(result);
		return result;
	} 
	public static void main(String[] args){
		 long i = stringToLong("8985763372286482443L");
		 if (i == 8985763372286482443L){
		  System.out.println("success");
		 }
		 else{
		  System.out.println("failure");
		 }
	}
}
