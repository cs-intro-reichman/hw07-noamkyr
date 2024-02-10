
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];


		int threshold = Integer.parseInt(args[1]);
		//int threshold = 2;
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}
	// return the string without the first char
	public static String tail(String str) {
		return str.substring(1).toLowerCase();
	}


	// return the distance between the words according the formula
	public static int levenshtein(String word1, String word2) {

		// set the words to lowercase
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		// return the difference of the length
		if (word1.isEmpty()){
			// #feedback - note that the below if is redundant, if word2 is empty, then word2.length() will return 0
			if (word2.isEmpty()){
				return 0;
			} else {
				return word2.length();
			}
		}

		if (word2.isEmpty()){
			if (word1.isEmpty()){
				return 0;
			} else {
				return word1.length();
			}

		}

		// check the first letters
		if (word1.charAt(0) == word2.charAt(0)){
			// return the tails if equals
			return levenshtein(tail(word1), tail(word2));
		} else {
			// return the min between the recursive calls and add 1
			int min =  Math.min(levenshtein(tail(word1), word2), levenshtein(tail(word1), tail(word2)));
			min = Math.min(min, levenshtein(word1, tail(word2)));
			return 1 + min;
		}
	}

	public static String[] readDictionary(String fileName) {
		// set new array
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		// add each word to each index at the array
		for (int i = 0; i < dictionary.length; i++) {
			String current  = in.readString();
			dictionary[i] = current;
		}
		// return the dictionary
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {

		// holds the minimal distance
		int min = -1

		// holds the closest word
		String closest = "";

		// check each word in the dictionary
		for (int i = 0; i < dictionary.length; i++) {

			// get the distance of the current word
			int distance = levenshtein(word,dictionary[i]);
			// if the words are equals
			if (distance == 0){
				return dictionary[i];
			}

			// if the current word below the standard
			if (distance <= threshold){

				// if the first word below threshold update the minimal distance and the minimal distance
				// #feedback - note that both if and else do the same commands, so you can have one if with or -
				// if (min == -1 || distance < min)
				if (min == -1){
					closest = dictionary[i];
					min = distance;
				} else {
					// update the minimal distance and the minimal distance
					if (distance < min){
						min = distance;
						closest = dictionary[i];
					}
				}
			}

		}
		// if no world with lower threshold return the original word
		if (min == -1){
			return word;
		}

		// return the closest
		return closest;
	}

}
