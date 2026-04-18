package _skn.sequencegenerator.controller;

import _skn.sequencegenerator.enumeration.SequenceType;
import _skn.sequencegenerator.model.DTO.request.SequenceRequest;
import _skn.sequencegenerator.model.DTO.response.SequenceResponse;
import _skn.sequencegenerator.model.Entity.Sequence;
import _skn.sequencegenerator.service.SequenceService;
import _skn.sequencegenerator.shared.ApiResponse;
import _skn.sequencegenerator.shared.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sequences")
public class SequenceController extends BaseResponse {

    // injection
    private final SequenceService sequenceService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SequenceResponse>>> listAllSequences(){

        List<SequenceResponse> sequenceResponses = sequenceService.getAllSequence();

        return responseEntity(
                true,
                sequenceResponses.isEmpty() ? "None any Sequence exist" :  "Get all sequences successfully",
                sequenceResponses.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK,
                sequenceResponses
        );
    }
    @PostMapping
    public ResponseEntity<ApiResponse<SequenceResponse>> insertNewSequence(@RequestBody @Valid SequenceRequest sequenceRequest){
        return responseEntity(
                true,
                "Create new sequence successfully.",
                HttpStatus.CREATED,
                sequenceService.createNewSequence(sequenceRequest)
        );
    }
    @GetMapping("/{type}")
    public ResponseEntity<ApiResponse<SequenceResponse>> getSequenceInfo(@PathVariable SequenceType type){
        return responseEntity(
                true,
                "Create new sequence successfully.",
                HttpStatus.OK,
                sequenceService.getSequenceInfoByType(type)
        );
    }
    @PostMapping("/{type}/generate-next")
    public ResponseEntity<ApiResponse<String>> generateNextValue(@PathVariable SequenceType type){
        return responseEntity(
                true,
                "Generate next sequence successfully.",
                HttpStatus.CREATED,
                sequenceService.generateNextSequence(type)
        );
    }
    @PostMapping("/{type}/reset")
    public ResponseEntity<ApiResponse<SequenceResponse>> resetSequenceValue( @PathVariable SequenceType type ,
                                                                   @RequestParam(defaultValue = "0") Long value){
        return responseEntity(
                true,
                "Sequence type " + type + " reset to " + value,
                HttpStatus.CREATED,
                sequenceService.updateCurrentSequenceValueByType(type, value)
        );
    }
}
