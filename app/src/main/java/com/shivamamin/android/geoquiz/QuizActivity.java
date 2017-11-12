package com.shivamamin.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private static final String KEY_POINTS = "points";
    private static final String KEY_NUM_QUESTIONS_ANSWERED = "num_questions_answered";
    private static final String KEY_QUESTIONS = "question";
    private static final String KEY_CHEATER = "cheater";
    private static int mPoints = 0;
    private static int mNum_Questions_Answered = 0;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mCheatButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true, false, false),
            new Question(R.string.question_oceans, true, false, false),
            new Question(R.string.question_mideast, false, false, false),
            new Question(R.string.question_africa, false, false, false),
            new Question(R.string.question_americas, true, false, false),
            new Question(R.string.question_asia, true, false, false),
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle): called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mPoints = savedInstanceState.getInt(KEY_POINTS, 0);
            mNum_Questions_Answered = savedInstanceState.getInt(KEY_NUM_QUESTIONS_ANSWERED, 0);
            for(int x = 0; x < mQuestionBank.length; x++){
                mQuestionBank[x].setAnswered(savedInstanceState.getBoolean(KEY_QUESTIONS + " " + String.valueOf(x), false));
                mQuestionBank[x].setCheater(savedInstanceState.getBoolean(KEY_CHEATER + " " + String.valueOf(x), false));
            }
        }
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevQuestion();
            }
        });
        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });
        updateQuestion();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK){
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if(data == null) {
                return;
            }
            mQuestionBank[mCurrentIndex].setCheater(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: called");
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState: called");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
        savedInstanceState.putInt(KEY_POINTS, mPoints);
        savedInstanceState.putInt(KEY_NUM_QUESTIONS_ANSWERED, mNum_Questions_Answered);
        for(int x = 0; x < mQuestionBank.length; x++) {
            savedInstanceState.putBoolean(KEY_QUESTIONS + " " + String.valueOf(x), mQuestionBank[x].isAnswered());
            savedInstanceState.putBoolean(KEY_CHEATER + " " + String.valueOf(x), mQuestionBank[x].isCheater());
        }
    }

    private void prevQuestion(){
        if(mCurrentIndex == 0){
            mCurrentIndex = mQuestionBank.length - 1;
        } else {
            mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
        }
        updateQuestion();
    }

    private void nextQuestion(){
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        updateQuestion();
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);

        if (mQuestionBank[mCurrentIndex].isAnswered()) {
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
            mCheatButton.setEnabled(false);
        } else {
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
            mCheatButton.setEnabled(true);
        }
    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;

        if (mQuestionBank[mCurrentIndex].isCheater()) {
            messageResId = R.string.judgment_toast;
        } else {
            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
                mPoints++;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

        mNum_Questions_Answered++;

        mQuestionBank[mCurrentIndex].setAnswered(true);

        updateQuestion();

        Toast messageToast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        messageToast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
        messageToast.show();

        if(mQuestionBank.length == mNum_Questions_Answered) {
            double avg = Math.round(((double)mPoints / (double)mQuestionBank.length) * 100);
            Log.d(TAG, String.valueOf(mPoints));
            Log.d(TAG, String.valueOf(mQuestionBank.length));
            Log.d(TAG, String.valueOf(avg));
            Toast averageToast = Toast.makeText(this, "Average: " + String.valueOf(avg), Toast.LENGTH_LONG);
            averageToast.show();
        }
    }
}
