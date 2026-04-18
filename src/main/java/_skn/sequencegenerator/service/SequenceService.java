package _skn.sequencegenerator.service;

import _skn.sequencegenerator.enumeration.SequenceType;
import _skn.sequencegenerator.model.DTO.request.SequenceRequest;
import _skn.sequencegenerator.model.DTO.response.SequenceResponse;
import _skn.sequencegenerator.model.Entity.Sequence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SequenceService {

    String generateNextSequence(SequenceType sequenceType);
    List<SequenceResponse> getAllSequence();

    SequenceResponse getSequenceInfoByType(SequenceType sequenceType);
    SequenceResponse createNewSequence(SequenceRequest request);

    SequenceResponse updateCurrentSequenceValueByType(SequenceType type , Long resetValue );



}
