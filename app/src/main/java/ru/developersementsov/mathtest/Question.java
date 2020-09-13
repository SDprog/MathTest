package ru.developersementsov.mathtest;

public class Question {
    private int questionTextId;
    private boolean answerTrue;

    public Question(int questionTextId, boolean answerTrue) {
        this.questionTextId = questionTextId;
        this.answerTrue = answerTrue;
    }

    public int getQuestionTextId() {
        return questionTextId;
    }

    public boolean isAnswerTrue() {
        return answerTrue;
    }
}
