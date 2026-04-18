package _skn.sequencegenerator.model.Entity;

import _skn.sequencegenerator.enumeration.SequenceType;
import _skn.sequencegenerator.model.DTO.response.SequenceResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "sequences")

public class Sequence  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "sequence_type" , nullable = false, unique = true, length = 20)
    private SequenceType sequenceType;

    @Column(name = "current_value" , nullable = false )
    private Long currentValue;


    @Column(name = "increment_value" , nullable = false)
    private Integer incrementValue;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "total_digit")
    private Integer totalDigit;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public SequenceResponse toResponse(){
        return SequenceResponse.builder()
                .id(this.id)
                .sequenceType(this.sequenceType)
                .currentValue(this.currentValue)
                .incrementValue(this.incrementValue)
                .prefix(this.prefix)
                .totalDigit(this.totalDigit)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
    public Long nextValue(){
        this.currentValue+=this.incrementValue;
        return currentValue;
    }


}
