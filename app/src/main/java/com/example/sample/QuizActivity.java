package com.example.sample;


import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class QuizActivity extends Activity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionTextView;
    //Create a question Bank of Question Objects that you can iterate through
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_oceans,true)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton = findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(false);
            }
        });

        mQuestionTextView = findViewById(R.id.question_text_view);


        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            updateQuestion(true);
            }
        });

        mPrevButton = findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion(false);
            }
        });

        updateQuestion(true);
    }

    private void updateQuestion(boolean val) {

        if(!val){
            mCurrentIndex -= 2;
        }
        int question = mQuestionBank[mCurrentIndex].getTextResId();
            mQuestionTextView.setText(question);
                mCurrentIndex++;

    }

    private void checkAnswer(boolean userPressedTrue){
        boolean currAnswer = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId;
        if(userPressedTrue == currAnswer){
            messageResId = R.string.correct_toast;
        }

        else{
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(QuizActivity.this,messageResId,Toast.LENGTH_SHORT).show();
    }
}
