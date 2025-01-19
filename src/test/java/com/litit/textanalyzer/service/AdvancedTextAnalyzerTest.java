package com.litit.textanalyzer.service;

import com.litit.textanalyzer.service.model.CharacterOccurrence;
import com.litit.textanalyzer.service.model.CharacterOccurrences;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class AdvancedTextAnalyzerTest {

    private static final String REAL_SENTENCE = "The program for calculating how many times a letter in given sentence appears.";
    private static final String CHARACTERS_WITH_CONSONANTS_ONLY = "bcdfghjklmnpqrstuvwxyzBCDFGHJKLMNPQRSTVWXYZ";
    private static final String CHARACTERS_WITH_VOWELS_ONLY = "aeiouAEIOU";
    private static final List<Character> VOWEL_LIST = Arrays.asList('A', 'E', 'I', 'O', 'U');
    private static final List<Character> CONSONANT_LIST = Arrays.asList('B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z');

    private AdvancedTextAnalyzer sut;

    @BeforeEach
    void setUp() {
        sut = new AdvancedTextAnalyzer();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void shouldDetectVowels() {

        //  given / when
        CharacterOccurrences characterOccurrences = sut.detectVowels(CHARACTERS_WITH_VOWELS_ONLY);

        //  then
        Assertions.assertNotNull(characterOccurrences);
        VOWEL_LIST.stream().forEach(vowel ->
                Assertions.assertTrue(characterOccursNumberOfTimes(characterOccurrences, vowel, 2), "Checking vowel: " + vowel));
    }

    @Test
    void shouldDetectVowelInRealSentence() {

        //  given / when
        CharacterOccurrences characterOccurrences = sut.detectVowels(REAL_SENTENCE);

        //  then
        Assertions.assertNotNull(characterOccurrences);
                Assertions.assertTrue(characterOccursNumberOfTimes(characterOccurrences, 'E', 9), "Checking vowel: e");
    }

    @Test
    void shouldDetectConsonants() {
        //  given / when
        CharacterOccurrences characterOccurrences = sut.detectConsonants(CHARACTERS_WITH_CONSONANTS_ONLY);

        //  then
        Assertions.assertNotNull(characterOccurrences);
        CONSONANT_LIST.stream().forEach(consonant ->
                Assertions.assertTrue(characterOccursNumberOfTimes(characterOccurrences, consonant, 2), "Checking vowel: " + consonant));
    }

    @Test
    void shouldDetectConsonantInRealSentence() {

        //  given / when
        CharacterOccurrences characterOccurrences = sut.detectConsonants(REAL_SENTENCE);

        //  then
        Assertions.assertNotNull(characterOccurrences);
        Assertions.assertTrue(characterOccursNumberOfTimes(characterOccurrences, 'T', 6), "Checking consonant: t");
    }


    private static boolean characterOccursNumberOfTimes(
            CharacterOccurrences characterOccurrences,
            Character character,
            int numberOfTimes) {

        return characterOccurrences.characterOccurrences().stream()
                .filter(characterOccurrence -> matchesCharacterAndOccurrence(characterOccurrence, character, numberOfTimes))
                .count() == 1l;
    }

    private static boolean matchesCharacterAndOccurrence(CharacterOccurrence characterOccurrence, Character character, int numberOfTimes) {
        return characterOccurrence.character() == character && characterOccurrence.numberOfOccurrences() == numberOfTimes;
    }
}