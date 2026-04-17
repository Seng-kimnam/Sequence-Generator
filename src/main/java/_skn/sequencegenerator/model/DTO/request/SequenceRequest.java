package _skn.sequencegenerator.model.DTO.request;

import _skn.sequencegenerator.enumeration.SequenceType;

public class SequenceRequest {
    private SequenceType sequenceType;
    private Long currentValue;
    private String prefix;
    private Integer incrementValue;
    private Integer totalDigit;
}
