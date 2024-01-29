

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		// set new array
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		// add each word to new index at the array
		for (int i = 0; i < dictionary.length; i++) {
			String current  = in.readString();
			dictionary[i] = current;
		}
		// return the array
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {

		// check each word in the dictionary and check if equals to the word
		for (int i = 0; i < dictionary.length; i++) {
			if (word.equals(dictionary[i])){
				// word found
				return true;
			}
		}
		// word not found
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
		boolean has_found = false; // holds if the current word has found
		int next_index = 0; // holds the index of the next word in the string

		// check from the first letter if real word and extend if not
        for (int i = 1; i <= N && has_found == false; i++) {
			// set the word to lowercase
			String current = hashtag.substring(0,i).toLowerCase();

			// if the word in the dictionary
			if(existInDictionary(current, dictionary)){
				has_found = true;
				next_index = i;
				System.out.println(current);
			}
        }

		// if the word was found do the same check from the start of the next word
		if (has_found){
			breakHashTag(hashtag.substring(next_index), dictionary);
		} else {
			return;
		}

    }

}
