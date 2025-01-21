package com.erebelo.springaidemo.service;

import com.erebelo.springaidemo.model.Answer;
import com.erebelo.springaidemo.model.CapitalRequest;
import com.erebelo.springaidemo.model.Question;

public interface OpenAIService {

    String getAnswer(String question);

    Answer getAnswer(Question question);

    Answer getCapital(CapitalRequest capitalRequest);

    Answer getCapitalInfo(CapitalRequest capitalRequest);

}
