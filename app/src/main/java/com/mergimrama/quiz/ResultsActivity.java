package com.mergimrama.quiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.mergimrama.quiz.model.Question;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

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
        mQuestions = (ArrayList<Question>) getIntent().getSerializableExtra(QuizActivity.EXTRA_RESULTS_OBJ);

        mListAdapter.setQuestions(mQuestions);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
