package com.litit.textanalyzer.controller;

import com.litit.textanalyzer.controller.dto.CharacterOccurrenceDto;
import com.litit.textanalyzer.controller.dto.TextAnalysisRequestDto;
import com.litit.textanalyzer.controller.mapper.CharacterOccurrenceMapper;
import com.litit.textanalyzer.service.AdvancedTextAnalyzer;
import com.litit.textanalyzer.service.model.CharacterOccurrence;
import com.litit.textanalyzer.service.model.CharacterOccurrences;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TextAnalyzerRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AdvancedTextAnalyzer advancedTextAnalyzer;

    @MockitoBean
    private CharacterOccurrenceMapper characterOccurrenceMapper;

    @InjectMocks
    private TextAnalyzerRestController textAnalyzerRestController;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        textAnalyzerRestController = new TextAnalyzerRestController(advancedTextAnalyzer, characterOccurrenceMapper);
        this.mockMvc = MockMvcBuilders.standaloneSetup(textAnalyzerRestController).build();
    }

    @Test
    public void testAnalyze() throws Exception {

        List<CharacterOccurrence> occurrenceList = Arrays.asList(new CharacterOccurrence('A', 3));
        CharacterOccurrences characterOccurrences = new CharacterOccurrences(occurrenceList);
        List<CharacterOccurrenceDto> occurrenceDtos = Arrays.asList(new CharacterOccurrenceDto('A', 3));

        when(advancedTextAnalyzer.detectVowels(anyString())).thenReturn(characterOccurrences);
        when(characterOccurrenceMapper.mapToDtos(characterOccurrences.characterOccurrences())).thenReturn(occurrenceDtos);

        mockMvc.perform(put("/text-analysis")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sentence\":\"This is a sentence\"," +
                                "\"vowels\":true}"))
                .andExpect(status().isOk());
    }
}