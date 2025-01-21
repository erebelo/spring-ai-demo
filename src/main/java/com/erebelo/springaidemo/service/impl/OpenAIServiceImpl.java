package com.erebelo.springaidemo.service.impl;

import com.erebelo.springaidemo.model.Answer;
import com.erebelo.springaidemo.model.CapitalRequest;
import com.erebelo.springaidemo.model.Question;
import com.erebelo.springaidemo.service.OpenAIService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;

    @Value("classpath:templates/capital-prompt.st")
    private Resource capitalPrompt;

    @Value("classpath:templates/capital-info-prompt.st")
    private Resource capitalInfoPrompt;

    @Override
    public String getAnswer(String question) {
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();

        ChatResponse response = chatModel.call(prompt);

        return response.getResult().getOutput().getContent();
    }

    @Override
    public Answer getAnswer(Question question) {
        log.info("Fetching answer");
        return new Answer(this.getAnswer(question.question()));
    }

    @Override
    public Answer getCapital(CapitalRequest capitalRequest) {
        log.info("Fetching capital");
        BeanOutputConverter<Answer> converter = new BeanOutputConverter<>(Answer.class);
        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(capitalPrompt);
        Prompt prompt = promptTemplate
                .create(Map.of("stateOrCountry", capitalRequest.stateOrCountry(), "format", format));
        ChatResponse response = chatModel.call(prompt);

        return converter.convert(response.getResult().getOutput().getContent());
    }

    @Override
    public Answer getCapitalInfo(CapitalRequest capitalRequest) {
        log.info("Fetching capital and info");
        BeanOutputConverter<Answer> converter = new BeanOutputConverter<>(Answer.class);
        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(capitalInfoPrompt);
        Prompt prompt = promptTemplate
                .create(Map.of("stateOrCountry", capitalRequest.stateOrCountry(), "format", format));
        ChatResponse response = chatModel.call(prompt);

        return converter.convert(response.getResult().getOutput().getContent());
    }
}
