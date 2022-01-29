package com.devdavi.trivia.data;

import com.devdavi.trivia.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {
    void processFinished(ArrayList<Question>questionArrayList);
}
