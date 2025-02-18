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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(OPENAI_PATH)
public class OpenAIController {

    private final OpenAIService service;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = OPENAI_ASK_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Answer askQuestions(@RequestBody Question question) {
        log.info("GET {}", OPENAI_PATH + OPENAI_ASK_PATH);
        return service.getAnswer(question);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = OPENAI_CAPITAL_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Answer getCapital(@RequestBody CapitalRequest capitalRequest) {
        log.info("GET {}", OPENAI_PATH + OPENAI_CAPITAL_PATH);
        return service.getCapital(capitalRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(path = OPENAI_CAPITAL_INFO_PATH, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Answer getCapitalInfo(@RequestBody CapitalRequest capitalRequest) {
        log.info("GET {}", OPENAI_PATH + OPENAI_CAPITAL_INFO_PATH);
        return service.getCapitalInfo(capitalRequest);
    }
}
