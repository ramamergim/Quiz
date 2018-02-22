package com.mergimrama.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.mergimrama.quiz.model.Question;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    private Question[] mQuestion;

    private ListView mListView;
    private ListAdapter mListAdapter;

    private ArrayList<Question> mQuestions = new ArrayList<Question>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        mListView = (ListView) findViewById(R.id.list_view);
        mListAdapter = new ListAdapter(getLayoutInflater());
        mListView.setAdapter(mListAdapter);
        mQuestion = (Question[]) getIntent().getSerializableExtra(QuizActivity.EXTRA_RESULTS_OBJ);

        mListAdapter.setQuestions(fillArrayList());
    }

    private ArrayList<Question> fillArrayList() {
        ArrayList<Question> questions = new ArrayList<>();

        for (int i = 0; i < mQuestion.length; i++) {
            questions.add(mQuestion[i]);
        }

        return questions;
    }
}
