package com.mergimrama.quiz.model;

/**
 * Created by Mergim on 21-Feb-18.
 */

public class Question {
    private int mQuestionResultId;
    private boolean mAnswerTrue;

    public Question(int questionResultId, boolean answerTrue) {
        mQuestionResultId = questionResultId;
        mAnswerTrue = answerTrue;
    }

    public int getQuestionResultId() {
        return mQuestionResultId;
    }

    public void setQuestionResultId(int questionResultId) {
        mQuestionResultId = questionResultId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
