package _skn.sequencegenerator.model.DTO.response;

import _skn.sequencegenerator.enumeration.SequenceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SequenceResponse {
    private Long id;
    private SequenceType sequenceType;
    private Long currentValue;
    private Integer incrementValue;
    private String prefix;
    private Integer totalDigit;
    private Instant createdAt;
    private Instant updatedAt;


}
