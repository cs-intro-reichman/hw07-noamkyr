
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		//String word = "lisense";
		//String word1 = "hello1";
		//String word2 = "hpll03";
		//System.out.println(levenshtein(word1, word2));


		int threshold = Integer.parseInt(args[1]);
		//int threshold = 2;
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		// Your code goes here
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		if (word1.isEmpty()){
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
		int count = 0;
		if (word1.charAt(0) != word2.charAt(0)){
			count ++;
		}


		return count + levenshtein(tail(word1), tail(word2));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < dictionary.length; i++) {
			String current  = in.readString();
			dictionary[i] = current;
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {

		int min = -1;
		String closest = "";
		for (int i = 0; i < dictionary.length; i++) {
			int distance = levenshtein(word,dictionary[i]);
			if (distance == 1){
				return dictionary[i];
			}

			if (distance <= threshold){
				if (min == -1){
					closest = dictionary[i];
					min = distance;
				} else {
					if (distance < min){
						min = distance;
						closest = dictionary[i];
					}
				}
			}

		}

		if (min == -1){
			return word;
		}

		return closest;
	}

}
