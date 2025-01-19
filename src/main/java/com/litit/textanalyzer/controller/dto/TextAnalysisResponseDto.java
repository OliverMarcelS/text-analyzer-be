package com.litit.textanalyzer.controller.dto;

import java.util.List;

public record TextAnalysisResponseDto(TextAnalysisRequestDto textAnalysisRequest, List<CharacterOccurrenceDto> characterOccurrences) {
}
