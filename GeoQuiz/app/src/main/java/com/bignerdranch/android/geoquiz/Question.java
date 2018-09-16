package com.bignerdranch.android.geoquiz;

/**
 * Created by Yangyulin on 2018/8/27.
 */
public class Question {

    private int mTextResId;//问题文本
    private boolean mAnswerTrue;//问题答案
    private int isAnswerd;
    private boolean isCheat;

    public Question(int textResId, boolean answerTrue,boolean isCheat) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        this.isCheat = isCheat;
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

    public int getIsAnswerd() {
        return isAnswerd;
    }

    public void setIsAnswerd(int isAnswerd) {
        this.isAnswerd = isAnswerd;
    }

    public boolean isCheat() {
        return isCheat;
    }

    public void setCheat(boolean cheat) {
        isCheat = cheat;
    }
}
