package _skn.sequencegenerator.controller;

import _skn.sequencegenerator.shared.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sequences")
public class SequenceController extends BaseResponse {
    @GetMapping
    public String sendMessage(){
        return "HELLO SEQUENCE";
    }
}
