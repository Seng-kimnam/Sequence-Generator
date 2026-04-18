package _skn.sequencegenerator.service.impl;

import _skn.sequencegenerator.enumeration.SequenceType;
import _skn.sequencegenerator.exception.SequenceAlreadyExistsException;
import _skn.sequencegenerator.exception.SequenceNotFoundException;
import _skn.sequencegenerator.model.DTO.request.SequenceRequest;
import _skn.sequencegenerator.model.DTO.response.SequenceResponse;
import _skn.sequencegenerator.model.Entity.Sequence;
import _skn.sequencegenerator.repository.SequenceRepository;
import _skn.sequencegenerator.service.SequenceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SequenceServiceImp implements SequenceService {

    private final SequenceRepository sequenceRepo;




    // formatting
    public final String formattedSequence(String prefix , Integer totalDigit , Long value){
        String formatted = String.format( "%0" + totalDigit + "d" , value);
        return prefix + "-" + formatted;
    }

    // main operation
    @Transactional(readOnly = true)
    @Override
    public List<SequenceResponse> getAllSequence() {

        List<Sequence> sequences = sequenceRepo.findAll();
        if (sequences == null || sequences.isEmpty() ) {
            return Collections.emptyList();
        }
        return sequences.stream()
                .map(Sequence::toResponse)
                .collect(Collectors.toList());
    }
    //


    @Transactional(readOnly = true)
    @Override
    public SequenceResponse getSequenceInfoByType(SequenceType sequenceType) {
        Sequence sequence = sequenceRepo.findSequenceBySequenceType(sequenceType)
                .orElseThrow(() -> new SequenceNotFoundException(
                        "Sequence not found for type: " + sequenceType
                ) );
        return sequence.toResponse();
    }

    @Transactional
    @Override
    public SequenceResponse createNewSequence(SequenceRequest request) {
        sequenceRepo.findSequenceBySequenceType(request.getSequenceType())
                .ifPresent(existing -> {
                        throw new SequenceAlreadyExistsException(
                                "Sequence already exists for type: " + request.getSequenceType()
                         );
                        }
                );
        Sequence newConfigSeq = request.toEntity();
        newConfigSeq.setSequenceType(request.getSequenceType());
        newConfigSeq.setCurrentValue(request.getCurrentValue());
        newConfigSeq.setIncrementValue(request.getIncrementValue());
        newConfigSeq.setPrefix(request.getPrefix());
        newConfigSeq.setTotalDigit(request.getTotalDigit());
        newConfigSeq.setCreatedAt(Instant.now());
        return sequenceRepo.save(newConfigSeq).toResponse();
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public String generateNextSequence(SequenceType sequenceType) {

        // get sequence by type
        log.debug("Debug new sequence for type : {} " , sequenceType.toString());
        Sequence sequence = sequenceRepo.findSequenceBySequenceTypeForUpdate(sequenceType)
                .orElseThrow(() -> new SequenceNotFoundException(
                        "Sequence not found for type : " + sequenceType
                ));

        Long nextValue = sequence.nextValue();
        sequence.setUpdatedAt(Instant.now());
        sequenceRepo.save(sequence);
        // format sequence
        String prefix = sequence.getPrefix();
        Integer totalDigit = sequence.getTotalDigit();
        String formatted = formattedSequence(prefix ,  totalDigit , nextValue);
        log.info("Generated sequence {} for type {}", formatted, sequenceType);
        return formatted;
    }



    // use to reset value
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public SequenceResponse updateCurrentSequenceValueByType(SequenceType type, Long resetToValue) {
        Sequence seqConfig = sequenceRepo.findSequenceBySequenceTypeForUpdate(type)
                .orElseThrow(() -> new SequenceNotFoundException(
                        "Sequence not found for type: " + type
                ));
        log.warn("Resetting sequence {} from {} to {}", type ,seqConfig.getCurrentValue(), resetToValue);
        seqConfig.setCurrentValue(resetToValue);
        seqConfig.setUpdatedAt(Instant.now());

        return seqConfig.toResponse();
    }


}
