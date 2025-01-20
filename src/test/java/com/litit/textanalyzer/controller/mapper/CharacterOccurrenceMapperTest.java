

package com.litit.textanalyzer.controller.mapper;

import com.litit.textanalyzer.controller.dto.CharacterOccurrenceDto;
import com.litit.textanalyzer.service.model.CharacterOccurrence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CharacterOccurrenceMapperTest {

    private CharacterOccurrenceMapper characterOccurrenceMapper;

    @BeforeEach
    public void setUp() {
        characterOccurrenceMapper = new CharacterOccurrenceMapper();
    }

    @Test
    public void testMapToDtos() {

        //  given
        CharacterOccurrence occurrence1 = new CharacterOccurrence('A', 3);
        CharacterOccurrence occurrence2 = new CharacterOccurrence('B', 5);
        List<CharacterOccurrence> occurrences = List.of(occurrence1, occurrence2);

        //  when
        List<CharacterOccurrenceDto> dtos = characterOccurrenceMapper.mapToDtos(occurrences);

        //  then
        assertEquals(2, dtos.size());
        assertEquals('A', dtos.get(0).character());
        assertEquals(3, dtos.get(0).numberOfOccurrences());
        assertEquals('B', dtos.get(1).character());
        assertEquals(5, dtos.get(1).numberOfOccurrences());
    }

    @Test
    public void testMapToDto() {

        //  given
        CharacterOccurrence occurrence = new CharacterOccurrence('C', 7);

        //  when
        CharacterOccurrenceDto dto = characterOccurrenceMapper.mapToDto(occurrence);

        //  then
        assertEquals('C', dto.character());
        assertEquals(7, dto.numberOfOccurrences());
    }
}