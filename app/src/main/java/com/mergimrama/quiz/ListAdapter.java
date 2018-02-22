package com.mergimrama.quiz;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.mergimrama.quiz.model.Question;

import java.util.ArrayList;

/**
 * Created by Mergim on 22-Feb-18.
 */

public class ListAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private ViewHolder mViewHolder;
    private ArrayList<Question> mQuestions = new ArrayList<>();

    public ListAdapter(LayoutInflater inflater) {
        mLayoutInflater = inflater;
    }

    @Override
    public int getCount() {
        return mQuestions.size();
    }

    @Override
    public Object getItem(int position) {
        return mQuestions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.list_view, viewGroup, false);
            mViewHolder = new ViewHolder(view);
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }

        int question = mQuestions.get(position).getQuestionResultId();
        int userAnswer = mQuestions.get(position).getUserAnswer();
        boolean solution = mQuestions.get(position).isAnswerTrue();

        mViewHolder.questionTextView.setText(question);
        mViewHolder.userAnswerTextView.setText(translateNum(userAnswer));
        mViewHolder.solutionTextView.setText(solution + "");

        return view;
    }

    class ViewHolder {
        TextView questionTextView;
        TextView userAnswerTextView;
        TextView solutionTextView;

        public ViewHolder(View v) {
            questionTextView = (TextView) v.findViewById(R.id.questions_text_view_res);
            userAnswerTextView = (TextView) v.findViewById(R.id.user_answer_text_view);
            solutionTextView = (TextView) v.findViewById(R.id.solution_text_view);
        }
    }

    public void setQuestions(ArrayList<Question> questions) {
        mQuestions = questions;
        notifyDataSetChanged();
    }

    private String translateNum(int number) {
        switch (number) {
            case 0 : {
                return "false";
            }
            case 1 : {
                return "true";
            }
            default: return null;
        }
    }
}
