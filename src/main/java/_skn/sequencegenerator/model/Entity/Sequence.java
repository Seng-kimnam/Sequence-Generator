package _skn.sequencegenerator.model.Entity;

import _skn.sequencegenerator.enumeration.SequenceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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



}
