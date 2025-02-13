package com.erebelo.springaidemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenAPIServiceTest {

    @Autowired
    private OpenAIService service;

    @Test
    void getAnswerSuccessful() {
        String answer = service.getAnswer("Tell me a dad joke.");
        System.out.println("Got the answer");
        System.out.println(answer);
    }
}
