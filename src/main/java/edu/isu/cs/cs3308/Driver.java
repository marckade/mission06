package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.Spelling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Driver {


    public static void main(String[] args)
    {
        while(true)
        {
            checkSpelling(getText());
        }
    }

    private static String[] getText()
    {
        String sentence = "";
        System.out.println("\r\nWhat Sentence do you need checked?");

        try
        {
            BufferedReader inputText = new BufferedReader(new InputStreamReader(System.in));
            sentence = inputText.readLine();
        } catch (IOException exception)
        {
            System.err.println(exception);
        }

        return sentence.replaceAll("[^\\w\\'\\ ]","").split("\\s+");
    }


    private static void checkSpelling(String[] words) {
        Spelling spellChecker = new Spelling();
        boolean noMistakes = true;
        int numMistakes = 1;

        for (String word : words)
        {

            //This creates the list of problems with the results.
            List<String> results = spellChecker.check(word.toLowerCase());

            //If the size of the results (of mistakes) is greater than one then there is a mistake
            if (results.size() > 1)
            {
                StringBuilder str = new StringBuilder();
                for (int i = 1; i < results.size(); i++)
                {
                    if (i < 6) str.append(results.get(i) + ", ");
                }
                String options = str.substring(0, str.length() - 2);

                if (numMistakes == 1)
                {
                    System.out.println("\r\nMisspelled Words:");
                }
                System.out.printf("%3d. %s: [%s]\r\n", numMistakes, results.get(0), options);

                noMistakes = false;
                numMistakes++;
            }
        }

        if (noMistakes) System.out.println("\r\nNo misspellings!");
    }
}
