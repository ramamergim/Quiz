package com.mergimrama.quiz;

import android.content.Intent;
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
    public static final String KEY_INDEX = "index";
    public static final String EXTRA_RESULTS_OBJ = "com.mergimrama.quiz.model.Questions";

    private TextView mQuestionTextView;
    private TextView mAnswerTextView;
    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private Button mShowAnswersButton;

    private int mCurrentIndex = 0;

    private Question[] mQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(Bundle) called");
        setContentView(R.layout.activity_quiz);

        mQuestions = new Question[]{
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
                mCurrentIndex++;
                updateQuestion();
                if (mCurrentIndex == mQuestions.length -1) {
                    mTrueButton.setEnabled(false);
                    mFalseButton.setEnabled(false);
                    mShowAnswersButton.setVisibility(View.VISIBLE);
                }
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnswerTextView.setText(checkAnswer(false));
                mCurrentIndex++;
                updateQuestion();
                if (mCurrentIndex == mQuestions.length -1) {
                    mFalseButton.setEnabled(false);
                    mTrueButton.setEnabled(false);
                    mShowAnswersButton.setVisibility(View.VISIBLE);
                }
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
                if (mAnswerTextView.getText().equals("")) {
                    Toast.makeText(QuizActivity.this, "Answer first!", Toast.LENGTH_SHORT).show();
                } else {
                    mCurrentIndex++;
                    if (mCurrentIndex > mQuestions.length - 1) {
                        mCurrentIndex = mQuestions.length - 1;
                        mShowAnswersButton.setVisibility(View.VISIBLE);
                    } else {
                        updateQuestion();
                    }
                }
            }
        });

        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex < 1) {

                } else {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestions.length;
                    updateQuestionOnPrevious();
                    mShowAnswersButton.setVisibility(View.INVISIBLE);
                    mTrueButton.setEnabled(true);
                    mFalseButton.setEnabled(true);
                }
            }
        });

        mShowAnswersButton = (Button) findViewById(R.id.show_answers_button);
        mShowAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResults();
                Intent intent = new Intent(QuizActivity.this, ResultsActivity.class);
                intent.putExtra(EXTRA_RESULTS_OBJ, mQuestions);
                startActivity(intent);
            }
        });

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSavedInstanceState(savedInstanceState)");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    private void updateQuestion() {
        int question = mQuestions[mCurrentIndex].getQuestionResultId();
        mQuestionTextView.setText(question);
        mAnswerTextView.setText("");
    }

    private void updateQuestionOnPrevious() {
        int question = mQuestions[mCurrentIndex].getQuestionResultId();
        boolean answer = mQuestions[mCurrentIndex].isUserAnswerTrue();
        mQuestionTextView.setText(question);
        mAnswerTextView.setText(answer + "");
    }

    private int checkAnswer(boolean userAnswerTrue) {
        boolean answerTrue = mQuestions[mCurrentIndex].isAnswerTrue();

        int messageResultId = 0;

        if (userAnswerTrue == answerTrue) {
            messageResultId = R.string.correct;
            mQuestions[mCurrentIndex].setUserAnswerTrue(answerTrue);
            mAnswerTextView.setTextColor(Color.GREEN);
        } else {
            messageResultId = R.string.incorrect;
            mQuestions[mCurrentIndex].setUserAnswerTrue(answerTrue);
            mAnswerTextView.setTextColor(Color.RED);
        }

        Toast.makeText(this, messageResultId, Toast.LENGTH_SHORT).show();
        return messageResultId;
    }

    private void showResults() {
        for (int i = 0; i < mQuestions.length; i++) {
            System.out.println(i + ": " + mQuestions[i].isUserAnswerTrue());
        }
    }
}
