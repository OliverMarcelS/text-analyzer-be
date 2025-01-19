package com.litit.textanalyzer.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

class TextAnalyzerTest {

    private TextAnalyzer sut;
    private static final String VOWELS_STRING = "vowels";
    private static final String CONSONANTS_STRING = "consonants";
    private static final String SENTENCE = "The program for calculating how many times letter in given sentence appears. " +
            "It gives numbers either for vowels or for consonants based on program input.";
    private static final String SENTENCE_WITHOUT_VOWELS = "Th prgrm fr clcltng hw mny tms lttr n gvn sntnc pprs.";
    private static final String SENTENCE_WITHOUT_CONSONANTS = "i.o.";
    private static final String SENTENCE_WITH_CONSONANTS_ONLY = "bcdfghjklmnpqrstuvwxyz";
    private static final List<Character> VOWEL_LIST = Arrays.asList('A', 'E', 'I', 'O', 'U');
    private static final List<Character> CONSONANT_LIST = Arrays.asList('B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z');

    private final PrintStream systemOut = System.out;
    private final ByteArrayOutputStream systemOutCaptor = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(systemOutCaptor));
        sut = new TextAnalyzer();
    }

    @AfterEach
    void tearDown() {
        System.setOut(systemOut);
    }

    @Test
    void mainShouldDetectVowels() {

        //  given / when
        TextAnalyzer.main(new String[]{VOWELS_STRING, SENTENCE});
        String output = systemOutCaptor.toString();

        //  then
        Assertions.assertNotNull(output);

        Assertions.assertTrue(letterAppearsNumberOfTimes('A', 9, output));
        Assertions.assertTrue(letterAppearsNumberOfTimes('E', 15, output));
        Assertions.assertTrue(letterAppearsNumberOfTimes('I', 8, output));
        Assertions.assertTrue(letterAppearsNumberOfTimes('O', 11, output));
        Assertions.assertTrue(letterAppearsNumberOfTimes('U', 3, output));
    }

    @Test
    void mainNotShouldDetectVowels() {

        //  given / when
        TextAnalyzer.main(new String[]{VOWELS_STRING, SENTENCE_WITHOUT_VOWELS});
        String output = systemOutCaptor.toString();

        //  then
        Assertions.assertNotNull(output);
        Assertions.assertTrue(letterAppearsNumberOfTimes('A', 0, output));
        Assertions.assertTrue(letterAppearsNumberOfTimes('E', 0, output));
        Assertions.assertTrue(letterAppearsNumberOfTimes('I', 0, output));
        Assertions.assertTrue(letterAppearsNumberOfTimes('O', 0, output));
        Assertions.assertTrue(letterAppearsNumberOfTimes('U', 0, output));
    }

    @Test
    void mainShouldDetectConsonants() {

        //  given / when
        TextAnalyzer.main(new String[]{CONSONANTS_STRING, SENTENCE_WITH_CONSONANTS_ONLY});
        String output = systemOutCaptor.toString();

        //  then
        Assertions.assertNotNull(output);
        CONSONANT_LIST.stream().forEach(consonant ->
                Assertions.assertTrue(letterAppearsNumberOfTimes(consonant, 1, output), "Checking consonant: " + consonant));
        ;
    }

    @Test
    void mainShouldNotDetectConsonants() {

        //  given / when
        TextAnalyzer.main(new String[]{CONSONANTS_STRING, SENTENCE_WITHOUT_CONSONANTS});
        String output = systemOutCaptor.toString();

        //  then
        Assertions.assertNotNull(output);
        CONSONANT_LIST.stream().forEach(consonant ->
                Assertions.assertFalse(letterAppearsNumberOfTimes(consonant, 1, output), "Checking consonant: " + consonant));
        ;
    }

    private boolean letterAppearsNumberOfTimes(char letter, int numberOfTimes, String output) {
        String searchText = String.format("Letter '%c' appears %d", letter, numberOfTimes);
        return output.contains(searchText);
    }
}