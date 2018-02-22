package com.mergimrama.quiz.model;

import java.io.Serializable;

/**
 * Created by Mergim on 21-Feb-18.
 */

public class Question implements Serializable {
    private int mQuestionResultId;
    private boolean mAnswerTrue;
    private boolean mUserAnswerTrue;
    private int mUserAnswer = -1;

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

    public boolean isUserAnswerTrue() {
        return mUserAnswerTrue;
    }

    public void setUserAnswerTrue(boolean userAnswerTrue) {
        mUserAnswerTrue = userAnswerTrue;
    }

    public int getUserAnswer() {
        return mUserAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        mUserAnswer = userAnswer;
    }
}
