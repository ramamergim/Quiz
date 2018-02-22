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

import java.util.ArrayList;
import java.util.Arrays;

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
    private ArrayList<Question> mQuestionsList = new ArrayList<>();

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

        mQuestionsList.addAll(Arrays.asList(mQuestions));

        mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);

        mQuestionTextView = (TextView) findViewById(R.id.question_textview);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);

                if (mCurrentIndex == mQuestionsList.size() - 1) {
                    mShowAnswersButton.setVisibility(View.VISIBLE);
                }
            }
        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);

                if (mCurrentIndex == mQuestionsList.size() -1) {
                    mShowAnswersButton.setVisibility(View.VISIBLE);
                }
            }
        });

        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mCurrentIndex = (mCurrentIndex + 1) % mQuestionsList.length;
                if (mQuestionsList.get(mCurrentIndex).getUserAnswer() == -1) {
                    Toast.makeText(QuizActivity.this, "Answer first!", Toast.LENGTH_SHORT).show();
                } else {
                    mCurrentIndex++;
                    if (mCurrentIndex > mQuestionsList.size() - 1) {
                        mCurrentIndex = mQuestionsList.size() - 1;
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
                    Toast.makeText(QuizActivity.this, "It's the first question!", Toast.LENGTH_SHORT).show();
                } else {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionsList.size();
                    //updateQuestionOnPrevious();
                    updateQuestion();
                    mShowAnswersButton.setVisibility(View.INVISIBLE);
                }
            }
        });

        mShowAnswersButton = (Button) findViewById(R.id.show_answers_button);
        mShowAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, ResultsActivity.class);
                intent.putExtra(EXTRA_RESULTS_OBJ, mQuestionsList);
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
        int question = mQuestionsList.get(mCurrentIndex).getQuestionResultId();
        mQuestionTextView.setText(question);
//        mAnswerTextView.setText("");
        if (mQuestionsList.get(mCurrentIndex).getUserAnswer() == -1) {
            mAnswerTextView.setText("");
            mTrueButton.setEnabled(true);
            mFalseButton.setEnabled(true);
        } else {
            String answer = translateNum(mQuestionsList.get(mCurrentIndex).getUserAnswer());
            mAnswerTextView.setText(answer);
            mTrueButton.setEnabled(false);
            mFalseButton.setEnabled(false);
        }
    }

    /*private void updateQuestionOnPrevious() {
        int question = mQuestions[mCurrentIndex].getQuestionResultId();
        boolean answer = mQuestions[mCurrentIndex].isUserAnswerTrue();
        mQuestionTextView.setText(question);
        mAnswerTextView.setText(answer + "");
    }*/

    private int checkAnswer(boolean userAnswerTrue) {
        boolean answerTrue = mQuestionsList.get(mCurrentIndex).isAnswerTrue();

        int messageResultId = 0;

        if (userAnswerTrue == answerTrue) {
            messageResultId = R.string.correct;
            mQuestionsList.get(mCurrentIndex).setUserAnswerTrue(answerTrue);
            mQuestionsList.get(mCurrentIndex).setUserAnswer(1); //1 stands for true, 0 for false, -1 empty
            mAnswerTextView.setTextColor(Color.GREEN);
        } else {
            messageResultId = R.string.incorrect;
            mQuestionsList.get(mCurrentIndex).setUserAnswerTrue(answerTrue);
            mQuestionsList.get(mCurrentIndex).setUserAnswer(0);
            mAnswerTextView.setTextColor(Color.RED);
        }

        Toast.makeText(this, "Answered. Go to next question >", Toast.LENGTH_SHORT).show();
        return messageResultId;
    }

    private void showResults() {
        for (int i = 0; i < mQuestionsList.size(); i++) {
            System.out.println(i + ": " + mQuestionsList.get(i).isUserAnswerTrue());
        }
    }

    private String translateNum(int number) {
        switch (number) {
            case 0 : {
                mAnswerTextView.setTextColor(Color.RED);
                return "Incorrect";
            }
            case 1 : {
                mAnswerTextView.setTextColor(Color.GREEN);
                return "Correct";
            }
            default: return null;
        }
    }
}
