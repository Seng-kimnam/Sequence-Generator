package _skn.sequencegenerator.model.DTO.request;

import _skn.sequencegenerator.enumeration.SequenceType;
import _skn.sequencegenerator.model.Entity.Sequence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SequenceRequest {
    private SequenceType sequenceType;
    private Long currentValue;
    private Integer incrementValue;
    private String prefix;
    private Integer totalDigit;

    public Sequence toEntity(){
        return Sequence.builder()
                .id(null)
                .sequenceType(this.sequenceType)
                .currentValue(this.currentValue)
                .incrementValue(this.incrementValue)
                .prefix(this.prefix)
                .totalDigit(this.totalDigit)
                .build();

    }

}
