package com.litit.textanalyzer.controller.mapper;

import com.litit.textanalyzer.controller.dto.CharacterOccurrenceDto;
import com.litit.textanalyzer.service.model.CharacterOccurrence;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterOccurrenceMapper {

    public List<CharacterOccurrenceDto> mapToDtos(List<CharacterOccurrence> characterOccurrences) {
        return characterOccurrences.stream().map(characterOccurrence -> mapToDto(characterOccurrence)).toList();
    }

    public CharacterOccurrenceDto mapToDto(CharacterOccurrence characterOccurrence) {
        return new CharacterOccurrenceDto(characterOccurrence.character(), characterOccurrence.numberOfOccurrences());
    }
}
