package com.shivamamin.android.geoquiz;

/**
 * Created by xShiv on 2017-09-28.
 */

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;
    private boolean mIsAnswered;
    private boolean mIsCheater;

    public Question(int textResId, boolean answerTrue, boolean isAnswered, boolean isCheater){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mIsAnswered = isAnswered;
        mIsCheater = isCheater;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public boolean isAnswered() {
        return mIsAnswered;
    }

    public void setAnswered(boolean answered) {
        mIsAnswered = answered;
    }

    public boolean isCheater() { return mIsCheater; }

    public void setCheater(boolean cheater) { mIsCheater = cheater; }
}