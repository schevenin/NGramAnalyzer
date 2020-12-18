package FrequencyCount;

/*
  Program 3: N-Grams and Frequency Counts
  Author: Rogelio Schevenin Jr.
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FrequencyCount {
    // INSTANCE VARIABLE(S)
    private final Map<String, Integer> TOKENS = new HashMap<>();
    private final int TOTAL;

    // CONSTRUCTOR(S)
    FrequencyCount(List<String> text) {
		TOTAL = text.size();
		for (String s : text) {
			add(s);
		}
    }

    FrequencyCount(List<String> text, int degree) {
		TOTAL = text.size();
    	// Add or update key/value pairs for every string in the list
    	if ((degree > 0) && (degree <= text.size())) {
    		// Visit each element in list
	    	for (int i = 0; i+degree <= text.size(); i++) {
	    		StringBuilder phrase = new StringBuilder();
	    		// Construct N-gram using i plus the next four
	    		for (int r = i; r < i+degree; r++) {
	    			if (r == i+degree - 1) {
		    				phrase.append(text.get(r));
		    			} else {
		    				phrase.append(text.get(r)).append(" ");
		    			}
	    		}
				// Check presence of phrase in map and add
	    		add(phrase.toString());
	    	}
    	} else {
    		System.out.println("Please specify a valid degree.");
    	}
    }

    // ACCESS METHOD(S)
    public List<String> head() {
    	// Create list of entry sets from HashMap
		List<Map.Entry<String, Integer>> list = new LinkedList<>(TOKENS.entrySet());

		// Sort the list using comparator (IDE suggested this shortcut)
		list.sort(Map.Entry.comparingByValue());

        // Reverse list of strings
		Collections.reverse(list);

		// Create list of Strings
		List<String> strings = new LinkedList<>();

    	// Ensure list isn't greater than 20
    	if (list.size() > 20) {
    		for (int i = 0; i < 20; i++) {
    			strings.add(list.get(i).getKey());
			}
       	} else {
			for (Map.Entry<String, Integer> stringIntegerEntry : list) {
				strings.add(stringIntegerEntry.getKey());
			}
       	}

    	return strings;
    }

    public List<String> tail () {
		// Create list of entry sets from HashMap
		List<Map.Entry<String, Integer>> list = new LinkedList<>(TOKENS.entrySet());

		// Sort the list using comparator (IDE suggested this shortcut)
		list.sort(Map.Entry.comparingByValue());

		// Create list of Strings
		List<String> strings = new LinkedList<>();

		// Ensure list isn't greater than 20
		if (list.size() > 20) {
			for (int i = 0; i < 20; i++) {
				strings.add(list.get(i).getKey());
			}
		} else {
			for (Map.Entry<String, Integer> stringIntegerEntry : list) {
				strings.add(stringIntegerEntry.getKey());
			}
		}

		return strings;
    }

    public String randomToken() {
        return new LinkedList<>(TOKENS.keySet()).get(new Random().nextInt(TOKENS.size()));
    }

    public int count(String token) {
		return TOKENS.getOrDefault(token, 0);
    }

    public int percent(String token) {
    	// Check presence of phrase in map
		if (TOKENS.containsKey(token)) {
			return (TOKENS.get(token)*100) / TOTAL;
		} else {
			System.out.println("Token not found. Could not calculate percentage.");
			return 0;
		}
    }

    // UPDATE METHOD(S)
    public int add(String token) {
		if (TOKENS.containsKey(token)) {
			TOKENS.put(token, count(token) + 1);
			return count(token);
		} else {
			TOKENS.put(token, 1);
			return 1;
		}
    }
}
