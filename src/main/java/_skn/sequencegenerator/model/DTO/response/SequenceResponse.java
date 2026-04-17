package _skn.sequencegenerator.model.DTO.response;

import _skn.sequencegenerator.enumeration.SequenceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SequenceResponse {
    private Long id;
    private SequenceType sequenceType;
    private Long currentValue;
    private String prefix;
    private Integer incrementValue;
    private Integer totalDigit;
}
