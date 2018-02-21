package com.mergimrama.quiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.mergimrama.quiz.model.Question;

public class QuizActivity extends AppCompatActivity {

    public static final String TAG = "QuizActivity";

    private TextView mQuestionTextView;
    private TextView mAnswerTextView;
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;

    private int mCurrentIndex = 0;

    private Question[] mQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        mQuestions = new Question[] {
                new Question(R.string.question_1, true),
                new Question(R.string.question_2, false),
                new Question(R.string.question_3, true),
                new Question(R.string.question_4, false),
                new Question(R.string.question_5, false),
                new Question(R.string.question_6, true),
                new Question(R.string.question_7, false),
                new Question(R.string.question_8, true),
                new Question(R.string.question_9, true),
                new Question(R.string.question_10, false),
                new Question(R.string.question_11, false),
                new Question(R.string.question_12, true),
                new Question(R.string.question_13, true),
                new Question(R.string.question_14, true),
                new Question(R.string.question_15, true)
        };

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        mQuestionTextView = (TextView) findViewById(R.id.question_textview);
        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswerTextView.setText(checkAnswer(true));
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswerTextView.setText(checkAnswer(false));
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
                updateQuestion();
            }
        });

        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex < 1) {

                } else {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestions.length;
                    updateQuestion();
                }
            }
        });

    }

    private void updateQuestion() {
        int question = mQuestions[mCurrentIndex].getQuestionResultId();
        mQuestionTextView.setText(question);
        mAnswerTextView.setText("");
    }

    private int checkAnswer(boolean userAnswerTrue) {
        boolean answerTrue = mQuestions[mCurrentIndex].isAnswerTrue();

        int messageResultId = 0;

        if (userAnswerTrue == answerTrue) {
            messageResultId = R.string.correct;
            mAnswerTextView.setTextColor(Color.GREEN);
        } else {
            messageResultId = R.string.incorrect;
            mAnswerTextView.setTextColor(Color.RED);
        }

        Toast.makeText(this, messageResultId, Toast.LENGTH_SHORT).show();
        return messageResultId;
    }
}
