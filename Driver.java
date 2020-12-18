package FrequencyCount;

/*
  Program 3: N-Grams and Frequency Counts
  Author: Rogelio Schevenin Jr.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        List<String> tokens = new LinkedList<>();

        // If no args provided
        if (args.length == 0) {
        	System.out.println("Program Usage: java Driver {Path to file}");
        	return;
        }

        // Find file from args
        File text = new File(args[0]);

        // Check file
        Scanner scanner;
        try {
            scanner = new Scanner(text);

            // Extract tokens (as lowercase)
            while (scanner.hasNext()) {
                tokens.add(scanner.next().toLowerCase());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found! Please specify the file's directory (path).");
            return;
        }

        // Build frequency count object
        FrequencyCount object = new FrequencyCount(tokens);

        // Display info about file by calling head() & tail()
        System.out.println("==========================");
        System.out.println("CALLING HEAD()");
        System.out.println("==========================");
        System.out.println("20 most frequent N-grams: " + object.head());
        System.out.println("==========================");
        System.out.println("CALLING TAIL()");
        System.out.println("==========================");
        System.out.println("20 least frequent N-grams: " + object.tail());

        // Display info about percentage for head() and tail() tokens
        List<String> most = object.head();
        System.out.println("==========================");
        System.out.println("DISPLAYING PERCENTAGE INFO FOR HEAD()");
        System.out.println("==========================");
        for (String s : most) {
            System.out.println("\"" + s + "\" appears " + object.count(s) + " time(s) (" + object.percent(s) + "%)");
        }
        List<String> least = object.tail();
        System.out.println("==========================");
        System.out.println("DISPLAYING PERCENTAGE INFO FOR TAIL()");
        System.out.println("==========================");
        for (String s : least) {
            System.out.println("\"" + s + "\" appears " + object.count(s) + " time(s) (" + object.percent(s) + "%)");
        }

        // Construct frequency count object using degree 4 (four words)
        FrequencyCount object2 = new FrequencyCount(tokens, 4);

        // Use randomToken() to construct a random phrase/poem
        // It shall print one 4-gram on a line, followed by a line with two 4-grams, and concluded with a single 4-gram
        System.out.println("==========================");
        System.out.println("USING RANDOM TOKEN() TO CONSTRUCT A RANDOM PHRASE/POEM");
        System.out.println("==========================");
        System.out.println(object2.randomToken());
        System.out.println(object2.randomToken() + " " + object2.randomToken());
        System.out.println(object2.randomToken());
    }
}