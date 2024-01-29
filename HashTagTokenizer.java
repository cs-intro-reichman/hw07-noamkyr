

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		//String hashTag = "iloverecursion";
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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

	public static boolean existInDictionary(String word, String []dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			if (word.equals(dictionary[i])){
				return true;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
		boolean has_found = false;
		int next_index = 0;
        for (int i = 1; i <= N && has_found == false; i++) {
			String current = hashtag.substring(0,i).toLowerCase();
			if(existInDictionary(current, dictionary)){
				has_found = true;
				next_index = i;
				System.out.println(current);
			}
        }
		if (has_found){
			breakHashTag(hashtag.substring(next_index), dictionary);
		} else {
			return;
		}

    }

}
