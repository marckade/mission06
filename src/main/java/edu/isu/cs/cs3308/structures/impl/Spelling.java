package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.SpellChecker;

import java.util.HashSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Spelling implements SpellChecker {

    private List<String> stringList = new ArrayList<>();
    private HashSet<String> lexicon = new HashSet<>();

    //Puts alphabet into char array
    private char[] changes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'".toCharArray();

    //saveLexican checks lower case for funciton
    public Spelling() {
        saveLexcion(false);
    }

    /**
     * Checks the spelling of the given string.
     *
     * @param s The string to check the spelling of
     * @return A list of alternatives, if the list is length 1
     * containing the same value as s, then the provided word
     * was correctly spelled. Else it was not.
     */
    @Override
    public List<String> check(String s) {
        stringList.clear();
        stringList.add(s);

        if (lexicon.contains(s)) return stringList;

        processWord(s);
        if (stringList.size() == 1)
        {
            stringList = new ArrayList<>();
            saveLexcion(true);
            processWord(s);
        }
        removeDuplicates();

        if (stringList.size() == 1) stringList.add("No suggestions");
        return stringList;
    }

    /**
     * Saves the dictionary of word in its current form or lowercase
     * @param asLower True to make all words lowercase, False for original casing
     */
    private void saveLexcion(boolean asLower) {
        try {
            FileReader fileRead = new FileReader("data/en-US.dic");
            BufferedReader buffer = new BufferedReader(fileRead);
            for (String line; (line = buffer.readLine()) != null; )
            {
                if (asLower)
                {
                    line = line.toLowerCase();
                }
                lexicon.add(line);
            }
            buffer.close();
        } catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    /**
     * Remove duplicate suggestions from the processing list
     */
    private void removeDuplicates() {
        for (int i = 0; i < stringList.size(); i++) {
            for (int c = i+1; c < stringList.size(); c++) {
                if (stringList.get(i).equals(stringList.get(c))) {
                    stringList.remove(c);
                }
            }
        }
    }

    /**
     * Process the word in four ways by checking:
     * Swapping adjacent characters
     * Deleting a character
     * Inserting a character
     * Replacing a character
     * Results will be added to the suggestion list
     *
     * @param word The word to check against the lexicon
     */
    private void processWord(String word) {
        for (int check = 1; check < 5; check++) {
            for (int i = 0; i < word.length() - 1; i++) {
                StringBuilder verify = new StringBuilder(word);

                if (check <= 2) {
                    if (check == 1) {
                        verify.insert(i, word.charAt(i + 1));
                        verify.deleteCharAt(i + 2);
                    } else {
                        verify.deleteCharAt(i);
                    }
                } else {
                    for (char c : changes) {
                        verify = new StringBuilder(word);

                        if (check == 3) {
                            verify.insert(i, c);
                        } else {
                            verify.setCharAt(i, c);
                        }
                    }
                }

                if (lexicon.contains(verify.toString())) {
                    stringList.add(verify.toString());
                }
            }
        }


    }
}
