package com.litit.textanalyzer.controller;


import com.litit.textanalyzer.controller.dto.CharacterOccurrenceDto;
import com.litit.textanalyzer.controller.dto.TextAnalysisRequestDto;
import com.litit.textanalyzer.controller.dto.TextAnalysisResponseDto;
import com.litit.textanalyzer.controller.mapper.CharacterOccurrenceMapper;
import com.litit.textanalyzer.service.AdvancedTextAnalyzer;
import com.litit.textanalyzer.service.model.CharacterOccurrences;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TextAnalyzerRestController {

    private static final Logger log = LoggerFactory.getLogger(TextAnalyzerRestController.class);
    private final AdvancedTextAnalyzer advancedTextAnalyzer;
    private final CharacterOccurrenceMapper characterOccurrenceMapper;

    public TextAnalyzerRestController(AdvancedTextAnalyzer advancedTextAnalyzer, CharacterOccurrenceMapper characterOccurrenceMapper) {
        this.advancedTextAnalyzer = advancedTextAnalyzer;
        this.characterOccurrenceMapper = characterOccurrenceMapper;
    }


    @PutMapping("/text-analysis")
    public TextAnalysisResponseDto analyze(@RequestBody TextAnalysisRequestDto textAnalysisRequestDto) {

        log.info("PUT TextAnalyzerRestController.analyze: request: " + textAnalysisRequestDto);

        CharacterOccurrences characterOccurrences = textAnalysisRequestDto.vowels() ?
                advancedTextAnalyzer.detectVowels(textAnalysisRequestDto.sentence()) :
                advancedTextAnalyzer.detectConsonants(textAnalysisRequestDto.sentence());

        List<CharacterOccurrenceDto> occurrenceDtos = characterOccurrenceMapper.mapToDtos(characterOccurrences.characterOccurrences());
        TextAnalysisResponseDto responseDto = new TextAnalysisResponseDto(textAnalysisRequestDto, occurrenceDtos);

        return responseDto;
    }

    @GetMapping("/services")
    public List<Integer> getServices(@RequestParam int systemVersion) {

        log.info("GET .getServices: systemVersion: " + systemVersion);
        return Arrays.asList(1, 2, 3);
    }


}
