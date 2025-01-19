package com.litit.textanalyzer.service;

import com.litit.textanalyzer.service.model.CharacterOccurrence;
import com.litit.textanalyzer.service.model.CharacterOccurrences;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class AdvancedTextAnalyzer {

    private static final List<Character> VOWEL_LIST = Arrays.asList('A', 'E', 'I', 'O', 'U');
    private static final List<Character> CONSONANT_LIST = Arrays.asList('B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z');

    public CharacterOccurrences detectVowels(String sentence) {
        return detectCharacters(sentence, VOWEL_LIST);
    }

    public CharacterOccurrences detectConsonants(String sentence) {
        return detectCharacters(sentence, CONSONANT_LIST);
    }

    private static CharacterOccurrences detectCharacters(String sentence, List<Character> characterList) {
        List<CharacterOccurrence> occurrences = characterList.stream().map(character -> mapToOccurrence(character, sentence)).toList();
        return new CharacterOccurrences(occurrences);
    }

    private static CharacterOccurrence mapToOccurrence(Character character, String sentence) {

        Pattern pattern = Pattern.compile(character.toString(), Pattern.CASE_INSENSITIVE);
        long occurrences = pattern.matcher(sentence).results().count();

        return new CharacterOccurrence(character, (int) occurrences);
    }

}
