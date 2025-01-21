package com.erebelo.springaidemo.controller;

import static com.erebelo.springaidemo.constant.BusinessConstant.OPENAI_ASK_PATH;
import static com.erebelo.springaidemo.constant.BusinessConstant.OPENAI_CAPITAL_INFO_PATH;
import static com.erebelo.springaidemo.constant.BusinessConstant.OPENAI_CAPITAL_PATH;
import static com.erebelo.springaidemo.constant.BusinessConstant.OPENAI_PATH;

import com.erebelo.springaidemo.model.Answer;
import com.erebelo.springaidemo.model.CapitalRequest;
import com.erebelo.springaidemo.model.Question;
import com.erebelo.springaidemo.service.OpenAIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(OPENAI_PATH)
public class OpenAIController {

    private final OpenAIService openAIService;

    @PostMapping(path = OPENAI_ASK_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Answer> askQuestions(@RequestBody Question question) {
        log.info("GET {}", OPENAI_PATH + OPENAI_ASK_PATH);
        return ResponseEntity.ok(openAIService.getAnswer(question));
    }

    @PostMapping(path = OPENAI_CAPITAL_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Answer> getCapital(@RequestBody CapitalRequest capitalRequest) {
        log.info("GET {}", OPENAI_PATH + OPENAI_CAPITAL_PATH);
        return ResponseEntity.ok(openAIService.getCapital(capitalRequest));
    }

    @PostMapping(path = OPENAI_CAPITAL_INFO_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Answer> getCapitalInfo(@RequestBody CapitalRequest capitalRequest) {
        log.info("GET {}", OPENAI_PATH + OPENAI_CAPITAL_INFO_PATH);
        return ResponseEntity.ok(openAIService.getCapitalInfo(capitalRequest));
    }
}
